package com.webfruit.model;
import com.webfruit.dao.User;
import com.webfruit.model.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
public class UserModel implements CommonDao <User>{
    private Connection connection;

    public UserModel() throws SQLException {
         connection = DBUtil.getConnection();
    }

    public static UserModel getInstance() throws SQLException {
        return new UserModel();
    }

    // close connection
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean insert(User T) {
        // insert user to database
        try {
            String query = String.format("INSERT INTO nguoi_dung (ho_dem, ten, ho_va_ten, so_dien_thoai, ngay_sinh, chi_tieu, mat_khau, email, dia_chi, vai_tro) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    T.getHo_dem(), T.getTen(), T.getHo_va_ten(), T.getSo_dien_thoai(), T.getNgay_sinh(), T.getChi_tieu(), T.getMat_khau(), T.getEmail(), T.getDia_chi(), T.getVai_tro());
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query) > 0;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User T) {
        try {
            String query = String.format("UPDATE nguoi_dung SET ho_va_ten = '%s', so_dien_thoai = '%s', ngay_sinh = '%s', chi_tieu = '%f', mat_khau = '%s', email = '%s', dia_chi = '%s', vai_tro = '%s' WHERE id = %d",
                    T.getHo_va_ten(), T.getSo_dien_thoai(), T.getNgay_sinh(), T.getChi_tieu(), T.getMat_khau(), T.getEmail(), T.getDia_chi(), T.getVai_tro(), T.getId());
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query) > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(int id) {
        try {
            String query = String.format("DELETE FROM nguoi_dung WHERE id = %d", id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User selectById(String id) {
        return null;
    }

    @Override
    public ArrayList<User> selectAll() {
        try {
            String query = String.format("SELECT * FROM nguoi_dung");
            Statement st = connection.createStatement();
            ResultSet rt = st.executeQuery(query);
            ArrayList<User> listUser = new ArrayList<>();
            while (rt.next()) {
                User user = new User();
                user.setId(rt.getInt("id"));
                user.setHo_dem(rt.getString("ho_dem"));
                user.setTen(rt.getString("ten"));
                user.setHo_va_ten(rt.getString("ho_va_ten"));
                user.setSo_dien_thoai(rt.getString("so_dien_thoai"));
                user.setNgay_sinh(rt.getString("ngay_sinh"));
                user.setChi_tieu(rt.getFloat("chi_tieu"));
                user.setMat_khau(rt.getString("mat_khau"));
                user.setEmail(rt.getString("email"));
                user.setDia_chi(rt.getString("dia_chi"));
                user.setVai_tro(rt.getString("vai_tro"));
                listUser.add(user);
            }
            return listUser;
        }catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }
    }

    public int CountUser() {
        try {
            String query = "SELECT COUNT(*) as sl FROM nguoi_dung";
            Statement statement = connection.createStatement();
            ResultSet rt =  statement.executeQuery(query);
            if (rt.next()) {
                return rt.getInt("sl");
            }
            return 0;
        }catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    // select user by id
    public User selectById(int id) {
        try {
            String query = String.format("SELECT * FROM nguoi_dung WHERE ID = %d", id);
            Statement statement = connection.createStatement();
            ResultSet rt = statement.executeQuery(query);
            if (rt.next()) {
                User user = new User();
                user.setId(rt.getInt("id"));
                user.setHo_dem(rt.getString("ho_dem"));
                user.setTen(rt.getString("ten"));
                user.setHo_va_ten(rt.getString("ho_va_ten"));
                user.setSo_dien_thoai(rt.getString("so_dien_thoai"));
                user.setNgay_sinh(rt.getString("ngay_sinh"));
                user.setChi_tieu(rt.getFloat("chi_tieu"));
                user.setMat_khau(rt.getString("mat_khau"));
                user.setEmail(rt.getString("email"));
                user.setDia_chi(rt.getString("dia_chi"));
                user.setVai_tro(rt.getString("vai_tro"));
                return user;
            }
            return null;
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    // update user by id
    public boolean updateUser(User user) {
        try {
            String query = String.format("UPDATE nguoi_dung SET  ho_va_ten = '%s', so_dien_thoai = '%s', ngay_sinh = '%s', chi_tieu = '%s', mat_khau = '%s', email = '%s', dia_chi = '%s', vai_tro = '%s' WHERE id = %d",
                     user.getHo_va_ten(), user.getSo_dien_thoai(), user.getNgay_sinh(), user.getChi_tieu(), user.getMat_khau(), user.getEmail(), user.getDia_chi(), user.getVai_tro(), user.getId());
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query) > 0;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
