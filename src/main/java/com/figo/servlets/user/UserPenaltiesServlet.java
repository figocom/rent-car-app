package com.figo.servlets.user;


import com.figo.criteria.PayCardCriteria;
import com.figo.daos.PenaltyDAO;
import com.figo.domain.Penalty;
import com.figo.dtos.paycards.PayCardDTO;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.paycard.PayCardService;
import com.figo.utils.serviceutil.PayCardUtil;
import com.figo.utils.serviceutil.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/Penalties")
public class WorkWithPenalties extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendAttribute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String penaltyAmount = req.getParameter("penaltyAmount");
        String cardIdForPay = req.getParameter("cardIdForPay");
        String orderId = req.getParameter("orderId");

        Response<DataDTO<Boolean>> payed =PenaltyDAO.payToPenalty(orderId,cardIdForPay,penaltyAmount);
        if (!payed.data().getData()){
            req.setAttribute("userPenaltiesError", payed.data().getError().getFriendlyMessage());
        }
        sendAttribute(req, resp);
    }

    private void sendAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer userId = UserUtil.getSessionUserId(req);
        PayCardService service = PayCardUtil.getService();
        Response<DataDTO<List<PayCardDTO>>> all = service.getAll(new PayCardCriteria(userId));
        req.setAttribute("cards", all.data().getData());
        List<Penalty> penalties = PenaltyDAO.getUsersOrdersPenalties(userId);
        req.setAttribute("userPenalties", penalties);
        req.getRequestDispatcher("/usersFrontendPages/penalties.jsp").forward(req,resp);
    }
}
