package com.figo.daos;


import com.figo.configs.DatabaseConfiguration;
import com.figo.criteria.UserCriteria;
import com.figo.domain.User;
import com.figo.dtos.users.UserDTO;
import com.figo.dtos.users.UserUpdateDTO;


import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserDAO extends GenericDAO<User> implements AbstractDAO {


    public UserDAO() {
        super();
    }

    public static String getUserPhoneNumber(Integer id) {
        String phoneNumber = null;
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select phone_number from users where is_deleted=false and id='" + id + "'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            if (set.next()) {
                phoneNumber = set.getString(1);
            }
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phoneNumber;

    }

    public Boolean userIsAdmin(UserDTO data) {
        Boolean isAdmin = null;
        Connection connection = DatabaseConfiguration.getConnection();
        try {

            String query = "select is_admin from users where is_deleted=false and username= ?";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, data.getUsername());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                isAdmin = resultSet.getBoolean("is_admin");
                return isAdmin;

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdmin;
    }

    public void registerUser(User user) throws IllegalArgumentException {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String insertUser = "insert into users(first_name, last_name, phone_number, username, address, password ,region_id)values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(insertUser);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getPassword());
            Integer regionId = RegionDAO.getRegionIdByName(user.getRegion());
            preparedStatement.setInt(7, regionId);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Error in server");
        }

    }

    public User findByUsername(String username) {

        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select * from users where is_deleted=false and username='" +username+"'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            if (set.next()) {
                String id = String.valueOf(set.getInt("id"));
                String first_name = set.getString("first_name");
                String last_name = set.getString("last_name");
                String phone_number = set.getString("phone_number");
                String region = String.valueOf(set.getInt("region_id"));
                String address = set.getString("address");
                String password = set.getString("password");
                boolean isAdmin = set.getBoolean("is_admin");
                set.close();
                statement.close();
                connection.close();
                return new User(id, first_name, last_name, phone_number, username, region, address, password, isAdmin);
            }
            set.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new User();
    }
    public static void checkUserNameAndPhone(String username, String phoneNumber) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String checkPhoneNumber = "select count(*) from users where is_deleted=false and phone_number=? ";
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(checkPhoneNumber);
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            int countUserByPhoneNumber = 0;
            checkerCountArg(preparedStatement,countUserByPhoneNumber, resultSet , "Phone number");
            String checkUserName = "select count(*) from users where is_deleted=false and username=? ";
            PreparedStatement preparedStatement1 = connection.prepareStatement(checkUserName);
            preparedStatement1.setString(1, username);
            ResultSet resultSetUserName = preparedStatement1.executeQuery();
            int countUserByUsername = 0;
            checkerCountArg(preparedStatement1, countUserByUsername,  resultSetUserName , "Username");
            connection.close();
        }catch (SQLException e) {
            throw new IllegalArgumentException("Error in server");
        }

    }

    private static void checkerCountArg(PreparedStatement preparedStatement, int count, ResultSet resultSet, String argument)  {
        try {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
                resultSet.close();
            }
            if (count > 0) {
                preparedStatement.close();
                throw new IllegalArgumentException("%s already exist".formatted(argument));
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
    }

    public List<User> getUsersList(UserCriteria criteria) {
        List<User> users = new ArrayList<>();
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select * from users where is_deleted=false and is_admin='"+criteria.isAdmin()+"'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                String id = String.valueOf(set.getInt("id"));
                String first_name = set.getString("first_name");
                String username = set.getString("username");
                String last_name = set.getString("last_name");
                String phone_number = set.getString("phone_number");
                String region = String.valueOf(set.getInt("region_id"));
                String address = set.getString("address");
                String password = set.getString("password");
                users.add(new User(id, first_name, last_name, phone_number, username, region, address, password, criteria.isAdmin()));
            }

            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public Boolean updateUser(UserUpdateDTO dto) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String checkPhoneNumber = "select count(*) from users where is_deleted=false and phone_number=? ";
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(checkPhoneNumber);
            preparedStatement.setString(1, dto.getPhoneNumber());
            ResultSet resultSet = preparedStatement.executeQuery();
            int countUserByPhoneNumber = 0;
            if (resultSet.next()) {
                countUserByPhoneNumber = resultSet.getInt(1);
                resultSet.close();
            }
            if (countUserByPhoneNumber > 0) {
                String query = "update users set is_admin='"+dto.isAddToAdmins()+"' where phone_number='" + dto.getPhoneNumber() + "'";
                Statement statement = Objects.requireNonNull(connection).createStatement();
                statement.execute(query);
                statement.close();
            } else {
                throw new IllegalArgumentException("User not found");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
          throw new IllegalArgumentException("Error in server");
        }
        return Boolean.TRUE;
    }
}
