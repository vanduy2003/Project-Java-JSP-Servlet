package com.webfruit.controller;

import com.sun.mail.imap.protocol.ID;
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
import com.webfruit.model.UserModel;



@WebServlet(name = "admin", urlPatterns = {"/admin", "/admin/quan-ly-san-pham", "/admin/quan-ly-nguoi-dung"})
public class AdminController extends HttpServlet {
    public AdminController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String IDUser = (String) session.getAttribute("IDUser");
        if (IDUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang-nhap");
            return;
        }
        try {
            int countUser = UserModel.getInstance().CountUser();
            System.out.println("Count user: " + countUser);
            req.setAttribute("countUser", countUser);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        // check url
        String url = req.getRequestURI();
        System.out.println("Get url: " + url);
        if (url.equalsIgnoreCase(req.getContextPath() + "/admin")) {
            req.setAttribute("home", "home");
            req.removeAttribute("quanLySanPham");
            req.removeAttribute("quanLyNguoiDung");
        } else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham")) {
            req.setAttribute("quanLySanPham", "quanLySanPham");
            req.removeAttribute("home");
        }else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung")) {
            System.out.println("Quan ly nguoi dung");
            req.setAttribute("quanLyNguoiDung", "quanLyNguoiDung");
            req.removeAttribute("home");
            req.removeAttribute("quanLySanPham");
        }
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
