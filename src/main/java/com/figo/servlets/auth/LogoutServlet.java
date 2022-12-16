package com.figo.servlets.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        if (session.getAttribute("user")!=null){
            session.setAttribute("user",null);
        }
        if (session.getAttribute("admin")!=null){
            session.setAttribute("admin" , null);
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
