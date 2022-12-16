package com.figo.servlets.auth;


import com.figo.dtos.users.LoginRequestDTO;
import com.figo.dtos.users.UserDTO;
import com.figo.response.DataDTO;
import com.figo.response.ErrorDTO;
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

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("authorizationFrontendPages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService service = UserUtil.getService();
        HttpSession session = req.getSession();
        Response<DataDTO<UserDTO>> login = service.login(new LoginRequestDTO(username, password));
        UserDTO userDTO = login.data().getData();
        if (login.data().isSuccess()) {
            Response<DataDTO<Boolean>> dataDTOResponse = UserUtil.getService().userIsAdmin(userDTO);
            if (Boolean.TRUE.equals(dataDTOResponse.data().getData())) {
                if (session.getAttribute("user") != null) {
                    session.setAttribute("user", null);
                }
                session.setAttribute("admin", userDTO.getUsername());
                session.setMaxInactiveInterval(3600);
                req.getRequestDispatcher("adminsFrontendPages/adminMainPage.jsp").forward(req, resp);

            } else if (Boolean.FALSE.equals(dataDTOResponse.data().getData())) {
                if (session.getAttribute("admin") != null) {
                    session.setAttribute("admin", null);
                }
                session.setAttribute("user", userDTO.getUsername());
                session.setMaxInactiveInterval(3600);
                req.getRequestDispatcher("usersFrontendPages/userMainPage.jsp").forward(req, resp);
            }
        } else {
            session.invalidate();
            ErrorDTO error = login.data().getError();
            String errorMessage = error.getFriendlyMessage();
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("authorizationFrontendPages/login.jsp").forward(req, resp);
        }


    }
}
