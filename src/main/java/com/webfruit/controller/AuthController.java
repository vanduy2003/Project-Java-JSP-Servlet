package com.webfruit.controller;

import com.webfruit.dao.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.webfruit.model.Auth;
import jakarta.servlet.http.HttpSession;
import java.util.Objects;

@WebServlet(name = "dang-nhap", urlPatterns = { "/dang-nhap", "/signout" })
public class AuthController extends HttpServlet {
    public AuthController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();// get path
        if (path.equals("/signout")) {
            HttpSession session = req.getSession();
            session.removeAttribute("user");
        }
        req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email") != null ? req.getParameter("email") : null;
        String password = req.getParameter("password") != null ? req.getParameter("password") : null;
        if (email != null && password != null) {
            try {
                String check = Auth.getInstance().checkLogin(email, password) == null ? "-1"  : Auth.getInstance().checkLogin(email, password);
                int checkInt = -1;
                try {
                     checkInt = Integer.parseInt(check);
                }catch (Exception e) {
                    e.printStackTrace();
                }

                if (checkInt != -1){
                    // login success
                    req.setAttribute("title", "Thành công");
                    req.setAttribute("message", "Đăng nhập thành công!");
                    req.setAttribute("messageType", "success");
                    req.setAttribute("icon", "checkmark-circle");
                    req.setAttribute("redirect", true);
                    User user = Auth.getInstance().getUserByID(check);
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    if (user.getVai_tro().trim().equals("admin")) {
                        resp.sendRedirect(req.getContextPath() + "/admin");
                    }else {
                        resp.sendRedirect(req.getContextPath() + "/trang-chu");
                    }
                } else {
                    // login failure
                    req.setAttribute("title", "Thất bại");
                    req.setAttribute("message", check);
                    req.setAttribute("messageType", "error");
                    req.setAttribute("icon", "close-circle");
                    req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("title", "Lỗi");
                req.setAttribute("message", "Đã xảy ra lỗi. Vui lòng thử lại.");
                req.setAttribute("messageType", "error");
                req.setAttribute("icon", "alert-circle");
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