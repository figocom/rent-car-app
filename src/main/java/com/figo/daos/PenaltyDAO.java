package com.figo.daos;

import com.figo.configs.DatabaseConfiguration;
import com.figo.domain.Penalty;
import com.figo.enums.OrderStatus;
import com.figo.response.DataDTO;
import com.figo.response.ErrorDTO;
import com.figo.response.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PenaltyDAO {
    public static List<Penalty> getUsersOrdersPenalties(Integer userId) {
        List<Penalty> penalties = new ArrayList<>();

        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select order_history.penalty ,order_history.info , c.model , c.car_number , ot.id  from order_history" +
                    " join order_table ot on ot.id = order_history.order_id" +
                    " join car c on c.id = ot.car_id where order_history.is_broken=true and penalty_is_payed=false and ot.user_id='" + userId + "'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                int order_id = set.getInt("id");

                String amount = set.getString("penalty");
                String info = set.getString("info");
                String model = set.getString("model");
                String car_number = set.getString("car_number");
                Penalty penalty = new Penalty(order_id, userId, model, car_number, info, amount);
                penalties.add(penalty);
            }
            set.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return penalties;
    }

    public static Response<DataDTO<Boolean>> payToPenalty(String orderId, String cardIdForPay, String penaltyAmount) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {

            String query = "update order_history set  penalty_is_payed= true where is_deleted=false and order_id='" + orderId + "'";
            String query2 = "update card set  balance=balance-? where is_deleted=false and card_number=?";
            String query3 = "UPDATE order_table SET status = ? WHERE id=?";
            PreparedStatement statement3 = Objects.requireNonNull(connection).prepareStatement(query3);
            Statement statement = Objects.requireNonNull(connection).createStatement();
            PreparedStatement statement2 = Objects.requireNonNull(connection).prepareStatement(query2);
            statement2.setDouble(1, Double.parseDouble(penaltyAmount.substring(0, penaltyAmount.length() - 1)));
            statement2.setString(2, cardIdForPay);
            statement3.setString(1, String.valueOf(OrderStatus.FinePayment));
            statement3.setInt(2, Integer.parseInt(orderId));
            statement3.executeUpdate();
            statement3.close();
            boolean execute = statement.execute(query);
            boolean execute1 = statement2.execute();
            if (execute || execute1) {
                return new Response<>(new DataDTO<>(new ErrorDTO("Server error!")));
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
            return new Response<>(new DataDTO<>(new ErrorDTO("Server error!")));
        }
        return new Response<>(new DataDTO<>(Boolean.TRUE));
    }
}
