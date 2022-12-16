package com.figo.servlets.admin;


import com.figo.criteria.UserCriteria;
import com.figo.dtos.users.UserDTO;
import com.figo.dtos.users.UserUpdateDTO;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.user.UserService;
import com.figo.utils.serviceutil.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/WWADMIN")
public class WorkWithAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO> admins = UserUtil.getAdmins();
        req.setAttribute("admins" ,admins);
        req.getRequestDispatcher("adminsFrontendPages/workWithAdmin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = UserUtil.getService();
        String username = req.getParameter("phoneNumber");
        if (Objects.nonNull(username)) {
            Response<DataDTO<Boolean>> update = service.update(new UserUpdateDTO(username, false));
            if (Objects.nonNull(update.data().getError())) {
                String friendlyMessage = update.data().getError().getFriendlyMessage();
                req.setAttribute("isDeleted", friendlyMessage);
            }
            List<UserDTO> admins = UserUtil.getAdmins();
            req.setAttribute("admins", admins);
            req.getRequestDispatcher("adminsFrontendPages/workWithAdmin.jsp").forward(req, resp);

        }
        String phoneNumberForAdd = req.getParameter("phoneNumberForAdd");
        if (Objects.nonNull(phoneNumberForAdd)) {
            Response<DataDTO<Boolean>> update = service.update(new UserUpdateDTO(phoneNumberForAdd, true));
            if (!update.data().isSuccess()) {
                String friendlyMessage = update.data().getError().getFriendlyMessage();
                req.setAttribute("isDeleted", friendlyMessage);
            }
            List<UserDTO> admins = UserUtil.getAdmins();
            req.setAttribute("admins" ,admins);
            req.getRequestDispatcher("adminsFrontendPages/workWithAdmin.jsp").forward(req, resp);
        }
    }



}
