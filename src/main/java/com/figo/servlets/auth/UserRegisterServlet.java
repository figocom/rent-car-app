package com.figo.servlets.auth;


import com.figo.dtos.users.UserCreateDTO;

import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.user.UserService;


import com.figo.utils.serviceutil.UserUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("authorizationFrontendPages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phoneNumber");
        String username = req.getParameter("username");
        String region = req.getParameter("region");
        String address = req.getParameter("address");
        String password = req.getParameter("password");
        UserService service = UserUtil.getService();
        UserCreateDTO dto = new UserCreateDTO(firstName,lastName,phoneNumber,username,region,address,password );
        Response<DataDTO<String>> dataDTOResponse = service.create(dto);
        if (dataDTOResponse.data().isSuccess()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            session.setMaxInactiveInterval(3600);
            req.getRequestDispatcher("usersFrontendPages/userMainPage.jsp").forward(req, resp);
        }
        else {
            String errorMessage = dataDTOResponse.data().getError().getFriendlyMessage();
            req.setAttribute("errorRegister",errorMessage);
            req.getRequestDispatcher("authorizationFrontendPages/register.jsp").forward(req, resp);
        }
    }

}
