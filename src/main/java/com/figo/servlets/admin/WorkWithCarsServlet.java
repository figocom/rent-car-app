package com.figo.servlets.admin;

import com.figo.criteria.PhotoCriteria;
import com.figo.dtos.cars.CarDTO;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.car.CarService;
import com.figo.utils.serviceutil.CarUtil;
import com.figo.utils.serviceutil.PhotoUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showAllCars")
public class WorkWithCarsServlet extends HttpServlet {
    CarService service = CarUtil.getService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<DataDTO<List<CarDTO>>> all = service.getAll();
        List<CarDTO> data = all.data().getData();
        req.setAttribute("cars" ,data);
        List<String> carNumbers=new ArrayList<>();
        for (CarDTO dto : data) {
            carNumbers.add(dto.getCarNumber());
        }
        req.setAttribute("photos" , PhotoUtil.getService().getAll(new PhotoCriteria(carNumbers)).data().getData());
        req.getRequestDispatcher("adminsFrontendPages/workWithCars.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<DataDTO<List<CarDTO>>> all = service.getAll();
        String carNumber = req.getParameter("deleteCarNumber");
        String command = req.getParameter("command");
        if (command.equals("delete")) {
            Response<DataDTO<Boolean>> delete = service.delete(carNumber);
            if (!delete.data().isSuccess()){
                req.setAttribute("isDeleted", delete.data().getError().getFriendlyMessage());
            }
            List<CarDTO> data = all.data().getData();
            req.setAttribute("cars" ,data);
            List<String> carNumbers=new ArrayList<>();
            for (CarDTO dto : data) {
                carNumbers.add(dto.getCarNumber());
            }
            req.setAttribute("photos" , PhotoUtil.getService().getAll(new PhotoCriteria(carNumbers)).data().getData());
            req.getRequestDispatcher("adminsFrontendPages/workWithCars.jsp").forward(req, resp);

        }
        else if (command.equals("repair")){
            Response<DataDTO<CarDTO>> dataDTOResponse = service.get(carNumber);
            Response<DataDTO<Boolean>> update = service.update(dataDTOResponse.data().getData());
            if (!update.data().isSuccess()){
                req.setAttribute("isDeleted", update.data().getError().getFriendlyMessage());
            }
            List<CarDTO> data = all.data().getData();
            req.setAttribute("cars" ,data);
            List<String> carNumbers=new ArrayList<>();
            for (CarDTO dto : data) {
                carNumbers.add(dto.getCarNumber());
            }
            req.setAttribute("photos" , PhotoUtil.getService().getAll(new PhotoCriteria(carNumbers)).data().getData());
            req.getRequestDispatcher("adminsFrontendPages/workWithCars.jsp").forward(req, resp);
        }
    }
}
