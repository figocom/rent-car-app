package com.figo.daos;

import com.figo.configs.DatabaseConfiguration;
import com.figo.domain.Order;
import com.figo.domain.PayCard;
import com.figo.dtos.paycards.PayCardDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PayCardDAO extends GenericDAO<PayCard> implements AbstractDAO{
    public Boolean deleteCard(String cardNumber) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "update card set is_deleted =true  where card_number='" + cardNumber + "'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            boolean execute = statement.execute(query);
            if (execute) {
                return Boolean.FALSE;
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;

    }

    public void addCard(PayCard payCard) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String checkCarNumber = "select count(*) from card where is_deleted=false and card_number=? ";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(checkCarNumber);
            statement.setString(1, payCard.getCardNumber());
            ResultSet resultSet = statement.executeQuery();
            int countCardByCardNumber = 0;
            if (resultSet.next()) {
                countCardByCardNumber = resultSet.getInt(1);
                resultSet.close();
            }
            if (countCardByCardNumber > 0) {
                statement.close();
                connection.close();
                throw  new IllegalArgumentException("This card number number already exist");
            }
            statement.close();
            String insertCar = "insert into card(user_id,card_number,balance)values (?,?,?)";
            PreparedStatement statement1 = Objects.requireNonNull(connection).prepareStatement(insertCar);
            statement1.setInt(1, payCard.getUserId());
            statement1.setString(2, payCard.getCardNumber());
            statement1.setDouble(3, payCard.getBalance());
            statement1.execute();
            statement1.close();
            connection.close();

        } catch (SQLException e) {
            throw  new IllegalArgumentException("Error in server");
        }
    }

    public List<PayCardDTO> getUserCards(Integer userId) {
        List<PayCardDTO> cards = new ArrayList<>();
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select card_number , balance from card where user_id=? and is_deleted=false";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String cardNumber = set.getString(1);
                Double cardBalance = set.getDouble(2);
                PayCardDTO cardDTO = PayCardDTO.childBuilder().

                        userId(userId).
                        cardNumber(cardNumber).
                        balance(cardBalance).
                        build();
                cards.add(cardDTO);
            }
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error in server");
        }

        return cards;
    }

    public PayCard getCard(String id) {

        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select * from card where is_deleted=false and card_number= ?";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String cardNumber=resultSet.getString("card_number");
               Double balance = resultSet.getDouble("balance");
                statement.close();
                connection.close();
                return PayCard.childBuilder().
                        cardNumber(cardNumber).
                        balance(balance).
                        build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean payToOrder(PayCard card) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "update card set balance = ? where card_number=?";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setDouble(1, card.getBalance());
            statement.setString(2, card.getCardNumber());
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
        return Boolean.TRUE;
    }
}
