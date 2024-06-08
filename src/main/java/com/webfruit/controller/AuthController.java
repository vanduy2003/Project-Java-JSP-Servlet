package com.webfruit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.webfruit.model.Auth;


@WebServlet(name = "dang-nhap", urlPatterns = {"/dang-nhap"})
public class AuthController extends HttpServlet {
    public AuthController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        System.out.println("Check phone" + phone);
        System.out.println("Check password" + password);
        // check phone and password != null hay khong
        if (phone != null && password != null) {
            try {
                Boolean check =  Auth.getInstance().checkLogin(phone, password);
                if (check) {
                    // login success
                    resp.sendRedirect("/Project_JSP_Servlet_war_exploded/");
                } else {
                    log("Đăng nhập thất bại!");
                }
            }catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi đăng nhập!");
                e.printStackTrace();
            }
        } else {
            // phone or password is null
            resp.sendRedirect("/dang-nhap");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
