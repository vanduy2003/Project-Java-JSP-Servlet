package com.webfruit.model;
import java.sql.*;

public class Auth {
    private static Auth instance;
    private Connection connection;
    public Auth () throws SQLException{
        connection = DBUtil.getConnection();
    }
    public static Auth getInstance() throws SQLException {
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }
    public boolean checkLogin(String email, String password) {
        String query = "SELECT * FROM nguoi_dung WHERE email = ? AND mat_khau = ?";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, email);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean registerUser(String username, String email, String phone, String password) {
        String query = "INSERT INTO nguoi_dung (ho_dem, ten, so_dien_thoai, email, mat_khau) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            String[] nameParts = username.split(" ", 2);
            String hoDem = nameParts.length > 1 ? nameParts[0] : "";
            String ten = nameParts.length > 1 ? nameParts[1] : nameParts[0];
            ps.setString(1, hoDem);
            ps.setString(2, ten);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, password);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean emailExists(String email) {
        String query = "SELECT COUNT(*) FROM nguoi_dung WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(String email, String newPassword) {
        String query = "UPDATE nguoi_dung SET mat_khau = ? WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
