package com.figo.daos;

import com.figo.configs.DatabaseConfiguration;
import com.figo.domain.Car;
import com.figo.domain.Order;
import com.figo.domain.OrderCarPhoto;
import com.figo.domain.Photo;
import com.figo.enums.OrderStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderCarPhotoDAO {

    public static List<OrderCarPhoto> getUserOrders(Integer id) {
        Car car = null;
        Timestamp created_time ;
        String phoneNumber=UserDAO.getUserPhoneNumber(id);
        List<OrderCarPhoto> orders = new ArrayList<>();

        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select * from order_table where   user_id='" + id+ "'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            int orderId;
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
                orderId = set.getInt("id");
                double totalPrice = set.getDouble("total_price");
                String region = RegionDAO.getRegionNameById(region_id);
                Order order = Order.childBuilder().
                        userId(id).
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

                orders.add(new OrderCarPhoto(orderId, car, order, photos, Objects.requireNonNull(created_time).toLocalDateTime(), phoneNumber));
            }

            set.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
