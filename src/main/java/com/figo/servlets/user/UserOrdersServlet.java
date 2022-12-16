package com.figo.servlets.user;



import com.figo.criteria.PayCardCriteria;
import com.figo.daos.OrderCarPhotoDAO;
import com.figo.domain.OrderCarPhoto;
import com.figo.dtos.orders.OrderUpdateDTO;
import com.figo.dtos.paycards.PayCardDTO;
import com.figo.enums.OrderStatus;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.order.OrderService;
import com.figo.services.paycard.PayCardService;
import com.figo.utils.serviceutil.OrderUtil;
import com.figo.utils.serviceutil.PayCardUtil;
import com.figo.utils.serviceutil.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MyOrders")
public class UserOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = UserUtil.getSessionUserId(req);
        List<OrderCarPhoto>orders=OrderCarPhotoDAO.getUserOrders(id);
        System.out.println(orders);
        PayCardService service = PayCardUtil.getService();
        Response<DataDTO<List<PayCardDTO>>> all = service.getAll(new PayCardCriteria(id));
        req.setAttribute("cards", all.data().getData());
        req.setAttribute("myOrders",orders);
        req.getRequestDispatcher("/usersFrontendPages/UserOrders.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PayCardService service = PayCardUtil.getService();
        OrderService serviceO = OrderUtil.getService();
        String orderId = req.getParameter("orderId");
        double totalPrice= Double.parseDouble(req.getParameter("totalPrice"));
        String command = req.getParameter("command");
        switch (command) {
            case "payed" -> {
                int card = Integer.parseInt(req.getParameter("cardIdForPay"));
                Response<DataDTO<PayCardDTO>> response = service.get(String.valueOf(card));
                PayCardDTO data = response.data().getData();
                if (data.getBalance() >= totalPrice) {
                    data.setBalance(data.getBalance()-totalPrice);
                    Response<DataDTO<Boolean>> update = service.update(data);
                    if (!update.data().getData()) {
                       serviceO.update(new OrderUpdateDTO(orderId, OrderStatus.Payed));
                        req.setAttribute("editOrderStatus", update.data().getError().getFriendlyMessage());
                    }
                } else {
                    req.setAttribute("editOrderStatus", "You don't have enough money in your account");
                }
            }
            case "addCard" -> req.getRequestDispatcher("/usersFrontendPages/addCard.jsp").forward(req, resp);
            case "completed" -> serviceO.update(new OrderUpdateDTO(orderId , OrderStatus.COMPLETED));
        }
        Integer id = UserUtil.getSessionUserId(req);
        List<OrderCarPhoto>orders=OrderCarPhotoDAO.getUserOrders(id);
        req.setAttribute("myOrders",orders);
        req.getRequestDispatcher("/usersFrontendPages/UserOrders.jsp").forward(req,resp);

    }
}
