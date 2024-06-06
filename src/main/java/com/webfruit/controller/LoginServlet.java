package com.webfruit.controller;

import com.webfruit.model.Account;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.webfruit.model.DBUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        DAO dao = new DAO();
        Account account = dao.login(email, password);
        if (account == null) {
            response.sendRedirect("Login.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
