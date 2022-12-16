package com.figo.daos;

import com.figo.configs.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegionDAO implements AbstractDAO{
    public static Integer getRegionIdByName(String regionName) {
        Integer regionId = null;
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select id from region where name='" + regionName + "'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            if (set.next()) {
                regionId = set.getInt(1);
            }
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return regionId;
    }

    public static List<String> getRegions() {
        List<String> regions = new ArrayList<>();
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select name from region where deleted=false";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                regions.add(set.getString(1));
            }
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }

        return regions;
    }

    public static String getRegionNameById(int region_id) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select name from region where id='" + region_id + "'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            if (set.next()) {
              return set.getString(1);
            }
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }
         return null;
    }
}
