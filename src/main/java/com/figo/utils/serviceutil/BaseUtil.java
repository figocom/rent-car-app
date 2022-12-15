package com.figo.utils;

import com.figo.daos.UserDAO;
import com.figo.mapper.UserMapper;
import com.figo.services.user.UserService;
import com.figo.services.user.UserServiceImpl;
import com.figo.utils.validators.UserValidator;
import lombok.NonNull;
import org.mindrot.jbcrypt.BCrypt;


import java.time.LocalDateTime;
import java.util.UUID;

public class BaseUtil {



    public static String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

    public String encode(@NonNull String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public boolean match(@NonNull String rawPassword, @NonNull String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }



}
