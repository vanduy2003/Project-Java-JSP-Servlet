package com.webfruit.model;
import java.sql.*;
import com.webfruit.dao.User;

public class Auth {
    private static Auth instance;
    private Connection connection;


    public Auth () throws SQLException{
        connection = DBUtil.getConnection();
    }


    public static Auth getInstance() throws SQLException {
      return new Auth();
    }

    public String checkLogin(String email, String password) {
        try  {
            String query = String.format("SELECT ID FROM nguoi_dung WHERE email = '%s' AND mat_khau = '%s'", email, password);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return rs.getString("ID");
            } else {
                return "Email hoặc mật khẩu không đúng";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Vui lòng đăng nhập lại sau, đã xảy ra lỗi phía Server";
        }
    }

    public User getUserByID (String ID) {
        try {
            String query = String.format("SELECT * FROM nguoi_dung WHERE ID = '%d'", Integer.parseInt(ID));
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery(query);
            if (res.next()) {
               User user = new User();
               user.setId(res.getInt("ID"));
               user.setTen(res.getString("ten"));
                user.setHo_dem(res.getString("ho_dem"));
                user.setHo_va_ten(res.getString("ho_va_ten"));
                user.setSo_dien_thoai(res.getString("so_dien_thoai"));
                user.setEmail(res.getString("email"));
                user.setDia_chi(res.getString("dia_chi"));
                user.setNgay_sinh(res.getString("ngay_sinh"));
                user.setChi_tieu(res.getFloat("chi_tieu"));
                user.setVai_tro(res.getString("vai_tro"));
                return user;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public boolean registerUser(String username, String email, String phone, String password) {
        String query = "INSERT INTO nguoi_dung (ho_dem, ten, ho_va_ten, so_dien_thoai, email, mat_khau) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            String[] nameParts = username.split(" ", 2);
            String hoDem = nameParts.length > 1 ? nameParts[0] : "";
            String ten = nameParts.length > 1 ? nameParts[1] : nameParts[0];
            ps.setString(1, hoDem);
            ps.setString(2, ten);
            ps.setString(3, hoDem + " " + ten);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, password);
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
