package com.webfruit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import com.webfruit.dao.User;
import com.webfruit.model.Auth;



@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {
    public AdminController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check section xem co khong, neu co thi chuyen qua trang admin
        // neu khong thi chuyen qua trang login
        HttpSession session = req.getSession();
        String IDUser = (String) session.getAttribute("IDUser");
        if (IDUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang-nhap");
            return;
        }

//        User user = Auth.getInstance().getUserByID(IDUser);
        req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
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
}
