package com.figo.servlets.user;
import com.figo.criteria.PayCardCriteria;
import com.figo.dtos.paycards.CreateCardDTO;
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


@WebServlet("/addCard")
public class AddCardServlet extends HttpServlet {
    PayCardService service = PayCardUtil.getService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = UserUtil.getSessionUserId(req);
        Response<DataDTO<List<PayCardDTO>>> all = service.getAll(new PayCardCriteria(id));
        req.setAttribute("cards", all.data().getData());
        req.getRequestDispatcher("usersFrontendPages/addCard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cardNumber = req.getParameter("cardNumber");
        String cardBalance = req.getParameter("cardBalance");
        String cardNumberForDelete = req.getParameter("cardNumberForDelete");
        Integer id = UserUtil.getSessionUserId(req);
        Response<DataDTO<List<PayCardDTO>>> all = service.getAll(new PayCardCriteria(id));
        req.setAttribute("cards", all.data().getData());
        if (Objects.nonNull(cardNumberForDelete)) {
            Response<DataDTO<Boolean>> delete = service.delete(cardNumberForDelete);
            if (!delete.data().isSuccess()) {
                req.setAttribute("cardStatus", delete.data().getError().getFriendlyMessage());
            }
        } else {
            Response<DataDTO<String>> create = service.create(new CreateCardDTO(id, cardNumber, cardBalance));
            if (create.data().isSuccess()) {

                req.setAttribute("cardStatus", create.data().getData());
                req.getRequestDispatcher("/usersFrontendPages/userMainPage.jsp").forward(req, resp);
            } else {
                req.setAttribute("cardStatus", create.data().getError().getFriendlyMessage());
            }
        }
        req.getRequestDispatcher("/usersFrontendPages/addCard.jsp").forward(req, resp);
    }

}

