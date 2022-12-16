package com.figo.daos;

import com.figo.configs.DatabaseConfiguration;
import com.figo.domain.Order;
import com.figo.dtos.orders.OrderDTO;
import com.figo.dtos.orders.OrderUpdateDTO;
import com.figo.enums.OrderStatus;
import lombok.NonNull;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDAO extends GenericDAO<Order> implements AbstractDAO {
    public OrderDAO() {
        super();
    }

    public static boolean userHaveAvailableOrder(String passport, String driverLicense) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select status from order_table where is_deleted=false and passport='" + passport +
                    "' or  driver_license='" + driverLicense + "'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            if (set.next()) {
                String status = set.getString(1);
                if (status.equals(String.valueOf(OrderStatus.COMPLETED)) || status.equals(String.valueOf(OrderStatus.REJECTED))) {
                    set.close();
                    statement.close();
                    connection.close();
                    return false;
                } else {
                    return true;
                }
            }
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }

        return false;
    }

    public static List<Order> getOrders(Integer carId) {
        List<Order> orders = new ArrayList<>();
        Connection connection = DatabaseConfiguration.getConnection();
        String query = "select * from order_table where is_deleted=false  and car_id=?";
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(query);
            preparedStatement.setInt(1, carId);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Timestamp created_at = set.getTimestamp("created_at");
                Timestamp start_time = set.getTimestamp("start_time");
                Timestamp endTime = set.getTimestamp("end_time");
                OrderStatus orderStatus = OrderStatus.valueOf(set.getString("status"));
                String passport = set.getString("passport");
                String driver_license = set.getString("driver_license");
                int region_id = set.getInt("region_id");
                String region = RegionDAO.getRegionNameById(region_id);
                Integer orderId = set.getInt("id");
                Integer userId = set.getInt("user_id");
                double totalPrice = set.getDouble("total_price");
                Order order = Order.childBuilder().
                        createdAt(created_at.toLocalDateTime()).
                        id(String.valueOf(orderId)).
                        carId(carId).
                        startTime(start_time.toLocalDateTime()).
                        endTime(endTime.toLocalDateTime()).
                        orderStatus(orderStatus).
                        passport(passport).
                        driverLicense(driver_license).
                        region(Objects.requireNonNull(region)).
                        userId(userId).
                        totalPrice(totalPrice).
                        build();
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
        return orders;
    }

    public void registerOrder(Order order) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String insert = "insert into order_table(user_id, car_id, start_time, end_time, status, passport, driver_license ,region_id ,total_price) values(?,?,?,?,?,?,?,? ,?)";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(insert);
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getCarId());
            statement.setTimestamp(3, Timestamp.valueOf(order.getStartTime()));
            statement.setTimestamp(4, Timestamp.valueOf(order.getEndTime()));
            statement.setString(5, order.getOrderStatus().toString());
            statement.setString(6, order.getPassport());
            statement.setString(7, order.getDriverLicense());
            statement.setInt(8, RegionDAO.getRegionIdByName(order.getRegion()));
            statement.setDouble(9, order.getTotalPrice());
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
    }

    public void writeHistory(@NonNull Order order) {
        List<Order> orders = getOrders(order.getCarId());
        for (Order order1 : orders) {
            if (order1.getUserId().equals(order.getUserId()) && order1.getStartTime().equals(order.getStartTime())) {
                order.setId(order1.getId());
            }
        }
        Connection connection = DatabaseConfiguration.getConnection();
        String insertHistory = "insert into order_history(order_id)values (?)";
        PreparedStatement statementHistory = null;
        try {
            statementHistory = Objects.requireNonNull(connection).prepareStatement(insertHistory);
            statementHistory.setInt(1, Integer.parseInt(order.getId()));
            statementHistory.execute();
            statementHistory.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
    }

    public List<Order> getUserOrders(Integer userId) {
        List<Order> orders = new ArrayList<>();
        Connection connection = DatabaseConfiguration.getConnection();
        String query = "select * from order_table where is_deleted=false  and user_id=?";
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Timestamp created_at = set.getTimestamp("created_at");
                Timestamp start_time = set.getTimestamp("start_time");
                Timestamp endTime = set.getTimestamp("end_time");
                OrderStatus orderStatus = OrderStatus.valueOf(set.getString("status"));
                String passport = set.getString("passport");
                String driver_license = set.getString("driver_license");
                int region_id = set.getInt("region_id");
                String region = RegionDAO.getRegionNameById(region_id);
                Integer orderId = set.getInt("id");
                Integer carId = set.getInt("car_id");
                double totalPrice = set.getDouble("total_price");
                Order order = Order.childBuilder().
                        createdAt(created_at.toLocalDateTime()).
                        id(String.valueOf(orderId)).
                        carId(carId).
                        startTime(start_time.toLocalDateTime()).
                        endTime(endTime.toLocalDateTime()).
                        orderStatus(orderStatus).
                        passport(passport).
                        driverLicense(driver_license).
                        region(Objects.requireNonNull(region)).
                        userId(userId).
                        totalPrice(totalPrice).
                        build();
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
        return orders;
    }

    public boolean update(OrderUpdateDTO dto) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query2 = "update order_table set status= ? where is_deleted=false and id=?";
            PreparedStatement statement2 = Objects.requireNonNull(connection).prepareStatement(query2);
            statement2.setString(1, String.valueOf(dto.getOrderStatus()));
            statement2.setInt(2, Integer.parseInt(dto.getId()));
            statement2.executeUpdate();
            statement2.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
        return true;
    }

}
