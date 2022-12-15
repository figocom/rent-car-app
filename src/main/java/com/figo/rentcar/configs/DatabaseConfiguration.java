package com.company.container;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseContainer {
    static String url="jdbc:postgresql://localhost:5432/rent_car";
    static String dbUser="postgres";
    static String dbPassword="manguberdi66";

    public static  Connection getConnection(){

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url,dbUser,dbPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
