package com.figo.daos;

import com.figo.configs.DatabaseConfiguration;
import com.figo.domain.Car;
import com.figo.domain.User;
import com.figo.dtos.cars.CarDTO;
import com.figo.enums.CarStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarDAO extends GenericDAO<User> implements AbstractDAO {
    public CarDAO() {
        super();
    }

    public static void checkCarNumber(String carNumber) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String checkCarNumber = "select count(*) from car where car_number=? ";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(checkCarNumber);
            statement.setString(1, carNumber);
            ResultSet resultSet = statement.executeQuery();
            int countCarByCarNumber = 0;
            if (resultSet.next()) {
                countCarByCarNumber = resultSet.getInt(1);
                resultSet.close();
            }
            if (countCarByCarNumber > 0) {
                statement.close();
                connection.close();
                throw new IllegalArgumentException("This car number number already exist");
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
    }
    public static Integer getCarId(Integer orderId) {
        Integer carId = null;
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select car_id from order_table where  id=?";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                carId = set.getInt(1);
            }
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carId;
    }


    public static int getCarId(String carNumber) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select id from car where is_deleted=false and  car_number=?";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, carNumber);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return set.getInt(1);
            }
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
        throw new IllegalArgumentException("Error searching car");
    }

    public static String getCarPrice(Integer carId) {

        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select price_per_day from car where is_deleted=false and id=?";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setInt(1, carId);
            ResultSet sets = statement.executeQuery();
            if (sets.next()) {
                String carPrice = sets.getString("price_per_day");
                sets.close();
                statement.close();
                connection.close();
                return carPrice;
            }
            sets.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
        throw new IllegalArgumentException("Error searching car");
    }

    public void saveCar(Car car) {
        try {
            Connection connection = DatabaseConfiguration.getConnection();
            String insertCar = "insert into car(model, car_number, place_count, color, region_id, year, price_per_day, add_info)values (?,?,?,?,?,?,?,?)";
            PreparedStatement statement1 = Objects.requireNonNull(connection).prepareStatement(insertCar);
            statement1.setString(1, car.getCarModel());
            statement1.setString(2, car.getCarNumber());
            statement1.setInt(3, car.getCountOfPlace());
            statement1.setString(4, car.getCarColor());
            statement1.setInt(5, RegionDAO.getRegionIdByName(car.getCarRegion()));
            statement1.setInt(6, car.getCarYear());
            statement1.setString(7, car.getPricePerDay());
            statement1.setString(8, car.getAdditionInfo());
            boolean execute = statement1.execute();
            if (execute) {
                statement1.close();
                connection.close();
                throw new IllegalArgumentException("Error in server");
            }
            statement1.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }

    }


    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select * from car where is_deleted=false";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                String model = set.getString("model");
                String car_number = set.getString("car_number");
                Integer place_count = set.getInt("place_count");
                Integer id = set.getInt("id");
                String color = set.getString("color");
                int year = set.getInt("year");
                String price_per_day = set.getString("price_per_day");
                int region_id = set.getInt("region_id");
                String region = RegionDAO.getRegionNameById(region_id);
                String add_info = set.getString("add_info");
                CarStatus car_status = CarStatus.valueOf(set.getString("car_status"));
                Car car = Car.childBuilder().
                        id(String.valueOf(id)).
                        carModel(model).
                        carNumber(car_number).
                        countOfPlace(place_count).
                        carColor(color).
                        carRegion(Objects.requireNonNull(region)).
                        carYear(year).pricePerDay(price_per_day).
                        additionInfo(add_info).
                        carStatus(car_status).
                        build();
                cars.add(car);
            }
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }


    public static boolean updateCarStatus(int carId, CarStatus valueOf) {

        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "update car set car_status = ? where is_deleted=false and id=?";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, String.valueOf(valueOf));
            statement.setInt(2, carId);
             statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw  new IllegalArgumentException("Error in server");
        }
        return true;
    }

    public CarDTO getCar(String carNumber) {

        return null;
    }

    public boolean deleteCar(String carNumber) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "update car set is_deleted=true where car_number='" + carNumber + "'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            statement.execute(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
           throw  new IllegalArgumentException("Error in server");
        }
        return  true;
    }
}
