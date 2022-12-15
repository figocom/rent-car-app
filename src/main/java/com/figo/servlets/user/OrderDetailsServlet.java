package com.figo.servlets;


import com.figo.dtos.cars.CarDTO;
import com.figo.dtos.orders.OrderCreateDTO;
import com.figo.dtos.users.UserDTO;
import com.figo.enums.OrderStatus;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.order.OrderService;
import com.figo.utils.CarUtil;
import com.figo.utils.OrderUtil;
import com.figo.utils.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order_detail")
public class OrderDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("usersFrontendPages/getOrderDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionUser = (String) req.getSession().getAttribute("user");
        UserDTO userDTO = UserUtil.getService().get(sessionUser).data().getData();
        String passport = req.getParameter("passport");
        String carNumber = req.getParameter("car_number");
        CarDTO carDTO = CarUtil.getService().get(carNumber).data().getData();
        String driverLicense = req.getParameter("driverLicense");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String region = req.getParameter("region");
        OrderCreateDTO dto= new OrderCreateDTO(Integer.parseInt(userDTO.getId()), Integer.parseInt(carDTO.getId()),startDate,
                endDate, OrderStatus.REQUESTED,passport,driverLicense,region,0.0);
        OrderService service = OrderUtil.getService();
        Response<DataDTO<String>> dataDTOResponse = service.create(dto);
        if (dataDTOResponse.data().isSuccess()) {
            resp.sendRedirect("/MyOrders");
        } else {
            String errorMessage = dataDTOResponse.data().getError().getFriendlyMessage();
            req.setAttribute("choiceCar",carNumber);
            req.setAttribute("detailError", errorMessage);
            req.getRequestDispatcher("usersFrontendPages/getOrderDetail.jsp").forward(req, resp);

        }
    }
}





