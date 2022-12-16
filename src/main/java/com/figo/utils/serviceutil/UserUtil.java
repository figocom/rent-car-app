package com.figo.utils.serviceutil;

import com.figo.criteria.UserCriteria;
import com.figo.daos.UserDAO;
import com.figo.dtos.users.UserDTO;
import com.figo.mapper.UserMapper;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.user.UserService;
import com.figo.services.user.UserServiceImpl;
import com.figo.utils.validators.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserUtil {
    public  static UserService getService() {
        UserDAO dao = new UserDAO();
        UserMapper mapper = new UserMapper();
        UserValidator validator = new UserValidator();
        return new UserServiceImpl(dao, mapper, validator);
    }
    public static Integer getSessionUserId(HttpServletRequest req) {
        String sessionUserEmail = (String) req.getSession().getAttribute("user");
        UserService serviceUser = UserUtil.getService();
        Response<DataDTO<UserDTO>> dataDTOResponse = serviceUser.get(sessionUserEmail);
        String id = dataDTOResponse.data().getData().getId();
        return Integer.valueOf(id);
    }
    public static List<UserDTO> getAdmins() {
        UserService service =getService();
        Response<DataDTO<List<UserDTO>>> all = service.getAll(new UserCriteria(true));

        return all.data().getData();
    }
}
