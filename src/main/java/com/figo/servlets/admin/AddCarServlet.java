package com.figo.servlets;


import com.figo.dtos.cars.CarCreateDTO;

import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.car.CarService;

import com.figo.utils.CarUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/addCar")
public class AddCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("adminsFrontendPages/adminAddCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carModel = req.getParameter("carModel");
        String carNumber = req.getParameter("carNumber");
        Integer countOfPlace = Integer.valueOf(req.getParameter("countOfPlace"));
        String carColor = req.getParameter("carColor");
        String carRegion = req.getParameter("carRegion");
        int carYear = Integer.parseInt(req.getParameter("carYear"));
        String pricePerDay = req.getParameter("pricePerDay");
        String additionInfo = req.getParameter("additionInfo");
        CarService carService = CarUtil.getService();
        CarCreateDTO carCreateDTO = new CarCreateDTO(carModel, carNumber, countOfPlace, carColor, carRegion, carYear, pricePerDay, additionInfo);
        Response<DataDTO<String>> dataDTOResponse = carService.create(carCreateDTO);
        if (dataDTOResponse.data().isSuccess()) {
            req.getRequestDispatcher("adminsFrontendPages/adminAddCarPhoto.jsp").forward(req, resp);

        } else {
            String errorMessage = dataDTOResponse.data().getError().getFriendlyMessage();
            req.setAttribute("errorAddCar", errorMessage);
            req.getRequestDispatcher("/adminsFrontendPages/adminAddCar.jsp").forward(req, resp);
        }
    }


}

