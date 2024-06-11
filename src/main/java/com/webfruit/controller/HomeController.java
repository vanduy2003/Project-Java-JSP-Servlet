package com.webfruit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//Import libraries from model and dao
import com.webfruit.model.Auth;
import com.webfruit.dao.User;


import java.io.IOException;

@WebServlet(name = "trang-chu", urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {
    public HomeController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String IDUser = (String) session.getAttribute("IDUser");
       if (IDUser != null) {
           try {
               User user = Auth.getInstance().getUserByID(IDUser);
               session.setAttribute("fullname", user.getHo_va_ten());
           }catch (Exception ex) {
                ex.printStackTrace();
           }
       }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp);
    }
}
