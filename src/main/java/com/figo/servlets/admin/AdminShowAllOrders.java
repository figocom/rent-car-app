package com.figo.servlets.admin;


import com.figo.daos.OrderCarPhotoDAO;
import com.figo.domain.OrderCarPhoto;
import com.figo.response.DataDTO;
import com.figo.response.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/showOrders")
public class AdminShowAllOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderCarPhoto>orders= OrderCarPhotoDAO.allOrders();
        req.setAttribute("allOrders", orders);
        req.getRequestDispatcher("/adminsFrontendPages/adminShowAllOrders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        Integer orderId = Integer.valueOf(req.getParameter("orderId"));
        String rejectCause = req.getParameter("rejectCause");
        String infoPenalty = req.getParameter("infoPenalty");
        String pricePenalty = req.getParameter("pricePenalty");
        Response<DataDTO<String>> edited = OrderCarPhotoDAO.editOrderStatus(command, orderId ,rejectCause,infoPenalty,pricePenalty);
        if (!Objects.requireNonNull(edited).data().isSuccess()) {
            req.setAttribute("editOrderStatus", edited.data().getError().getFriendlyMessage());
        }
        else {
            req.setAttribute("editOrderStatus", edited.data().getData());
        }
        List<OrderCarPhoto>orders= OrderCarPhotoDAO.allOrders();
        req.setAttribute("allOrders", orders);
        req.getRequestDispatcher("/adminsFrontendPages/adminShowAllOrders.jsp").forward(req, resp);

    }
}
