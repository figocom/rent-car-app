package com.figo.servlets;


import com.figo.dtos.photos.PhotoDTO;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.photo.PhotoService;
import com.figo.utils.PhotoUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/FileUploadServlet")
@MultipartConfig

public class FileUploadServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("addPhoto", "addingPhotos");
        req.getRequestDispatcher("adminsFrontendPages/adminAddCarPhoto.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("addPhoto") != null) {
            String carNumber = request.getParameter("carNumber");
            List<String> urls = new ArrayList<>();
            for (Part p : request.getParts()) {
                System.out.println(p);
                if (p != null) {
                    p.getSubmittedFileName();
                    System.out.println(p.getSubmittedFileName());
                    if (p.getSubmittedFileName() != null && (!p.getSubmittedFileName().isBlank()) && (!p.getSubmittedFileName().isEmpty())) {
                        String imageName = p.getSubmittedFileName();
                        String uuid = String.valueOf(UUID.randomUUID());
                        String imageDbName = imageName.substring(0, imageName.lastIndexOf(".")) + uuid + imageName.substring(imageName.lastIndexOf("."));
                        //you should write directory name which is car Photos path
                        String directoryName = "D:/Javalessons/java review git/rent-car-app/src/main/webapp/carImages/";
                        String uploadPath = directoryName + imageDbName;
                        FileOutputStream stream = new FileOutputStream(uploadPath);
                        InputStream inputStream = p.getInputStream();
                        byte[] bytes = new byte[inputStream.available()];
                        inputStream.read(bytes);
                        inputStream.close();
                        stream.write(bytes);
                        stream.close();

                        urls.add(uploadPath);
                    }
                }

            }
            session.setAttribute("addPhoto", null);
            PhotoDTO photoDTO = PhotoDTO.childBuilder().carNumber(carNumber).urls(urls).build();
            PhotoService service = PhotoUtil.getService();
            Response<DataDTO<String>> dataDTOResponse = service.create(photoDTO);
            if (dataDTOResponse.data().isSuccess()) {
                request.setAttribute("successAddCarPhoto", "Car successfully added");
                request.getRequestDispatcher("adminsFrontendPages/adminMainPage.jsp").forward(request, response);
            }
            else {
                request.setAttribute("errorAddCar", dataDTOResponse.data().getError().getFriendlyMessage());
                request.getRequestDispatcher("adminsFrontendPages/adminAddCarPhoto.jsp").forward(request, response);
            }
        }
        else {
            request.getRequestDispatcher("adminsFrontendPages/adminMainPage.jsp").forward(request, response);
        }

    }
}
