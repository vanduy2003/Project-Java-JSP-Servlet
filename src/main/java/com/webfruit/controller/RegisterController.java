package com.webfruit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.webfruit.model.DBUtil;
import com.webfruit.model.Auth;

@WebServlet(name = "dang-ky", urlPatterns = {"/dang-ky"})
public class RegisterController extends HttpServlet {
    public RegisterController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phone = req.getParameter("sdt");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        if (username != null && email != null && phone != null && password != null && confirmPassword != null) {
            if (password.equals(confirmPassword)) {
                try {
                    boolean success = Auth.getInstance().registerUser(username, email, phone, password);
                    if (success) {
                        req.setAttribute("title", "Thành công");
                        req.setAttribute("message", "Đăng ký thành công!");
                        req.setAttribute("messageType", "success");
                        req.setAttribute("icon", "checkmark-circle");
                        req.setAttribute("redirect", true);
                        req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("title", "Thất bại");
                        req.setAttribute("message", "Đăng ký thất bại!");
                        req.setAttribute("messageType", "error");
                        req.setAttribute("icon", "close-circle");
                        req.setAttribute("redirect", false);
                        req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    req.setAttribute("title", "Lỗi");
                    req.setAttribute("message", "Có lỗi xảy ra trong quá trình đăng ký!");
                    req.setAttribute("messageType", "error");
                    req.setAttribute("icon", "close-circle");
                    req.setAttribute("redirect", false);
                    req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("title", "Lỗi");
                req.setAttribute("message", "Vui lòng xác nhận lại mật khẩu");
                req.setAttribute("messageType", "error");
                req.setAttribute("icon", "close-circle");
                req.setAttribute("redirect", false);
                req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("title", "Lỗi");
            req.setAttribute("message", "Tất cả các trường đều bắt buộc!");
            req.setAttribute("messageType", "error");
            req.setAttribute("icon", "close-circle");
            req.setAttribute("redirect", false);
            req.getRequestDispatcher("/views/web/sign-up.jsp").forward(req, resp);
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
