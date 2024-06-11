package com.webfruit.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.webfruit.model.Auth;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "dang-nhap", urlPatterns = { "/dang-nhap" })
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
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("Check email" + email);
        System.out.println("Check password" + password);

        if (email != null && password != null) {
            try {
                String check = Auth.getInstance().checkLogin(email, password);
                if (Integer.parseInt(check) > 0){
                    // login success
                    req.setAttribute("title", "Thành công");
                    req.setAttribute("message", "Đăng nhập thành công!");
                    req.setAttribute("messageType", "success");
                    req.setAttribute("icon", "checkmark-circle");
                    req.setAttribute("redirect", true);
                    req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
                } else {
                    // login failure
                    req.setAttribute("title", "Thất bại");
                    req.setAttribute("message", "Đăng nhập thất bại. Vui lòng thử lại.");
                    req.setAttribute("messageType", "error");
                    req.setAttribute("icon", "close-circle");
                    req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
                }

            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi đăng nhập!");
                e.printStackTrace();
                req.setAttribute("title", "Lỗi");
                req.setAttribute("message", "Đã xảy ra lỗi. Vui lòng thử lại.");
                req.setAttribute("messageType", "error");
                req.setAttribute("icon", "alert-circle");
                req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
            }
        } else {
            // email hoặc password null
            req.setAttribute("title", "Lỗi");
            req.setAttribute("message", "Email và mật khẩu không được để trống.");
            req.setAttribute("messageType", "error");
            req.setAttribute("icon", "alert-circle");
            req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
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