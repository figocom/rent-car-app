package com.figo.daos;

import com.figo.configs.DatabaseConfiguration;
import com.figo.domain.Car;
import com.figo.domain.Order;
import com.figo.domain.OrderCarPhoto;
import com.figo.domain.Photo;
import com.figo.enums.CarStatus;
import com.figo.enums.OrderStatus;
import com.figo.response.DataDTO;
import com.figo.response.ErrorDTO;
import com.figo.response.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderCarPhotoDAO {

    public static List<OrderCarPhoto> getUserOrders(Integer id) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select * from order_table where   user_id='" + id + "'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            return orderCarPhotos(set, connection, statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<OrderCarPhoto> allOrders() {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select * from order_table ";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            return orderCarPhotos(set, connection, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Response<DataDTO<String>> editOrderStatus(String command, Integer orderId, String rejectCause, String infoPenalty, String pricePenalty) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "UPDATE order_table SET status = ? WHERE id=?";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);

            switch (command) {
                case "accept" -> {
                    statement.setString(1, String.valueOf(OrderStatus.ACCEPTED));
                    statement.setInt(2, orderId);
                    statement.executeUpdate();
                    statement.close();
                    connection.close();
                    return new Response<>(new DataDTO<>(orderId + "-order successfully accepted"));
                }
                case "reject" -> {
                    String queryHistory = "UPDATE order_history SET info = ? WHERE order_id=?";
                    PreparedStatement statementHistory = Objects.requireNonNull(connection).prepareStatement(queryHistory);
                    statement.setString(1, String.valueOf(OrderStatus.REJECTED));
                    statement.setInt(2, orderId);
                    statementHistory.setString(1, "Reject cause: " + rejectCause);
                    statementHistory.setInt(2, orderId);
                    statement.executeUpdate();
                    statementHistory.executeUpdate();
                    statement.close();
                    statementHistory.close();
                    connection.close();
                    return new Response<>(new DataDTO<>(orderId + "-order successfully rejected"));
                }
                case "giveFine" -> {
                    Integer carId = CarDAO.getCarId(orderId);
                    CarDAO.updateCarStatus(carId, CarStatus.BROKEN);
                    String queryHistory = "UPDATE order_history SET info = ? , is_broken=true, penalty=? WHERE order_id=?";
                    String queryOrder = "UPDATE order_table SET status=?  WHERE id=?";
                    PreparedStatement statementHistory = Objects.requireNonNull(connection).prepareStatement(queryHistory);
                    PreparedStatement statementOrder = Objects.requireNonNull(connection).prepareStatement(queryOrder);
                    statementOrder.setString(1, String.valueOf(OrderStatus.FINED));
                    statementOrder.setInt(2, orderId);
                    statementHistory.setString(1, "Penalty cause: " + infoPenalty);
                    statementHistory.setString(2, pricePenalty);
                    statementHistory.setInt(3, orderId);
                    statementHistory.executeUpdate();
                    statementOrder.executeUpdate();
                    statementHistory.close();
                    statementOrder.close();
                    connection.close();

                    return new Response<>(new DataDTO<>("Penalty gave to  " + orderId + "order"));
                }
                case "finishOrder" -> {
                    statement.setString(1, String.valueOf(OrderStatus.FINISHED));
                    statement.setInt(2, orderId);
                    statement.executeUpdate();
                    statement.close();
                    connection.close();

                    return new Response<>(new DataDTO<>(orderId + "-order successfully finished"));
                }
            }
        } catch (SQLException e) {
            ErrorDTO errorDTO =new ErrorDTO("some went wrong");
            return new Response<>(new DataDTO<>(errorDTO));
        }
        return new Response<>(new DataDTO<>(""));
    }

    private static List<OrderCarPhoto> orderCarPhotos(ResultSet set, Connection connection, Statement statement) {
        List<OrderCarPhoto> orders = new ArrayList<>();
        try {
            Car car = null;
            int orderId;
            Timestamp created_time;
            while (set.next()) {
                int car_id = set.getInt("car_id");
                String queryForCar = "select * from car where id='" + car_id + "'";
                Statement statementCar = Objects.requireNonNull(connection).createStatement();
                ResultSet setCar = statementCar.executeQuery(queryForCar);
                String carNumber = null;
                while (setCar.next()) {
                    String model = setCar.getString("model");
                    carNumber = setCar.getString("car_number");
                    String pricePerDay = setCar.getString("price_per_day");
                    String color = setCar.getString("color");
                    int region_id = setCar.getInt("region_id");
                    int place_count = setCar.getInt("place_count");
                    int year = setCar.getInt("year");
                    car = Car.childBuilder().
                            carModel(model).
                            carNumber(carNumber).
                            pricePerDay(pricePerDay).
                            carColor(color).
                            carRegion(String.valueOf(region_id)).
                            countOfPlace(place_count).
                            carYear(year).
                            build();
                }
                setCar.close();
                String queryForPhoto = "select url from photo  where car_id='" + car_id + "'";
                Statement statementPhoto = Objects.requireNonNull(connection).createStatement();
                ResultSet setPhoto = statementPhoto.executeQuery(queryForPhoto);
                List<String> urls = new ArrayList<>();
                while (setPhoto.next()) {
                    String url = setPhoto.getString("url");
                    urls.add(url);
                }
                Photo photos = Photo.childBuilder().
                        urls(urls).
                        carNumber(Objects.requireNonNull(carNumber)).
                        build();
                setPhoto.close();

                created_time = set.getTimestamp("created_at");
                Timestamp start_time = set.getTimestamp("start_time");
                Timestamp endTime = set.getTimestamp("end_time");
                OrderStatus orderStatus = OrderStatus.valueOf(set.getString("status"));
                String passport = set.getString("passport");
                String driver_license = set.getString("driver_license");
                int region_id = set.getInt("region_id");
                int user_id = set.getInt("user_id");
                String userPhoneNumber = UserDAO.getUserPhoneNumber(user_id);
                orderId = set.getInt("id");
                double totalPrice = set.getDouble("total_price");
                String region = RegionDAO.getRegionNameById(region_id);
                Order order = Order.childBuilder().
                        userId(user_id).
                        id(String.valueOf(orderId)).
                        carId(car_id).
                        startTime(start_time.toLocalDateTime()).
                        endTime(endTime.toLocalDateTime()).
                        orderStatus(orderStatus).
                        passport(passport).
                        driverLicense(driver_license).
                        region(Objects.requireNonNull(region)).
                        totalPrice(totalPrice).
                        build();

                orders.add(new OrderCarPhoto(orderId, car, order, photos, Objects.requireNonNull(created_time).toLocalDateTime(), userPhoneNumber));
            }

            set.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public static String getRejectCause(Integer orderId) {
        String info;
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select info from order_history where order_id=?";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet sets = statement.executeQuery();
            if (sets.next()) {
                info = sets.getString("info");
                sets.close();
                statement.close();
                connection.close();
                return info;
            }
            sets.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
