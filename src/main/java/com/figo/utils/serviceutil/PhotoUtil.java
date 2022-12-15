package com.figo.utils;

import com.figo.daos.PhotoDAO;
import com.figo.daos.UserDAO;
import com.figo.mapper.PhotoMapper;
import com.figo.mapper.UserMapper;
import com.figo.services.photo.PhotoService;
import com.figo.services.photo.PhotoServiceImpl;
import com.figo.services.user.UserService;
import com.figo.services.user.UserServiceImpl;
import com.figo.utils.validators.PhotoValidator;
import com.figo.utils.validators.UserValidator;

public class PhotoUtil {
    public  static PhotoService getService() {
        PhotoDAO dao = new PhotoDAO();
        PhotoMapper mapper = new PhotoMapper();
        PhotoValidator validator = new PhotoValidator();
        return new PhotoServiceImpl(dao, mapper, validator);
    }
}
