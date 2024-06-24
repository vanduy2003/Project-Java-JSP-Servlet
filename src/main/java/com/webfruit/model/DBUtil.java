package com.webfruit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/web_ban_trai_cay";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    // close connection
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
