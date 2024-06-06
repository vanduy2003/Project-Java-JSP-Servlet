package com.webfruit.controller;

import com.webfruit.model.Account;
import com.webfruit.model.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DAO {
    public Account login(String email, String password) {
        String query = "SELECT * FROM accounts WHERE Email = ? AND PasswordHash = ?";
        try {

            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString("AccountID"), rs.getString("Email"), rs.getString("PasswordHash"), rs.getString("Role"));
            }
        } catch (SQLException e) {
        }
        return null;
    }
}

