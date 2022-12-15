package com.figo.servlets.user;
import com.figo.criteria.CarCriteria;
import com.figo.criteria.PhotoCriteria;
import com.figo.dtos.cars.CarDTO;
import com.figo.enums.CarStatus;
import com.figo.utils.CarUtil;
import com.figo.utils.PhotoUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carsShow")
public class ShowCars extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarDTO> data = CarUtil.getService().getAll(new CarCriteria(CarStatus.NOT_ON_RENT)).data().getData();
        req.setAttribute("cars" , data);
        List<String> carNumbers=new ArrayList<>();
        for (CarDTO dto : data) {
            carNumbers.add(dto.getCarNumber());
        }
         req.setAttribute("photos" , PhotoUtil.getService().getAll(new PhotoCriteria(carNumbers)).data().getData());
         req.getRequestDispatcher("usersFrontendPages/ChoiceCarForOrder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carNumber = req.getParameter("choiceCarNumber");
        req.setAttribute("choiceCar",carNumber);
        req.getRequestDispatcher("usersFrontendPages/getOrderDetail.jsp").forward(req, resp);
    }
}
