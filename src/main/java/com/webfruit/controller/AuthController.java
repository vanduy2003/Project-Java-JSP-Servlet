package com.webfruit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.webfruit.model.Auth;
import jakarta.servlet.http.HttpSession;


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
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        // check phone and password != null hay khong
        if (email != null && password != null) {
            try {
                String check =  Auth.getInstance().checkLogin(email, password);
                // check login neu co ID tra ve thi login success

                if (!check.equals("Email hoặc mật khẩu không đúng") && !check.equals("Vui lòng đăng nhập lại sau, đã xảy ra lỗi phía Server") ) {
                    // login success
                    HttpSession session = req.getSession();
                    session.setAttribute("IDUser", check);
                    resp.sendRedirect("/Project_JSP_Servlet_war_exploded/trang-chu");
                } else {
                    // login fail
                    req.setAttribute("error", check);
                    req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
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
