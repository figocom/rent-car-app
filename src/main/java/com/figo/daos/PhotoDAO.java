package com.figo.daos;

import com.figo.configs.DatabaseConfiguration;
import com.figo.domain.Photo;
import com.figo.domain.User;
import com.figo.dtos.photos.PhotoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhotoDAO extends GenericDAO<User> implements AbstractDAO {
   public PhotoDAO(){super();}


    public void save(Photo photo) {
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            int carId = CarDAO.getCarId(photo.getCarNumber());
            String query = "insert into photo(url, car_id)  values (?,?)";
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            for (int i = 0; i < photo.getUrls().size(); i++) {
                statement.setString(1, photo.getUrls().get(i));
                statement.setInt(2, carId );
                boolean execute = statement.execute();
                if (execute) {
                   throw new IllegalArgumentException("Error in server");
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error in server");
        }

    }

    public List<Photo> getPhotos(List<String> carNumbers) {
        List<Photo> photos=new ArrayList<>();
        for (String carNumber : carNumbers) {
            List<String> imagesByCarNumber = getImagesByCarNumber(carNumber);
            photos.add(Photo.childBuilder().urls(imagesByCarNumber).carNumber(carNumber).build());
        }
        return photos;
    }
    public static List<String> getImagesByCarNumber(String carNumber) {
        int carId =CarDAO.getCarId(carNumber);
        List<String> images = new ArrayList<>();
        Connection connection = DatabaseConfiguration.getConnection();
        try {
            String query = "select url from photo where is_deleted=false and  car_id='" + carId + "'";
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                images.add(set.getString(1));
            }
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return images;
    }
}
