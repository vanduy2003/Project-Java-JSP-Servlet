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

    @Override
    public boolean insert(User T) {
        // insert user to database
        try {
            String query = String.format("INSERT INTO user (ho_dem, ten, so_dien_thoai, ngay_sinh, chi_tieu, mat_khau, email, dia_chi, vai_tro) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    T.getHo_dem(), T.getTen(), T.getSo_dien_thoai(), T.getNgay_sinh(), T.getChi_tieu(), T.getMat_khau(), T.getEmail(), T.getDia_chi(), T.getVai_tro());
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
            String query = String.format("UPDATE user SET ho_dem = '%s', ten = '%s', so_dien_thoai = '%s', ngay_sinh = '%s', chi_tieu = '%s', mat_khau = '%s', email = '%s', dia_chi = '%s', vai_tro = '%s' WHERE id = '%d'",
                    T.getHo_dem(), T.getTen(), T.getSo_dien_thoai(), T.getNgay_sinh(), T.getChi_tieu(), T.getMat_khau(), T.getEmail(), T.getDia_chi(), T.getVai_tro(), T.getId());
            Statement statement = connection.createStatement();
            return  statement.executeUpdate(query)  > 0;
       }catch (Exception ex) {
           ex.printStackTrace();
           return false;
       }
    }

    @Override
    public boolean delete(int id) {
        try {
            String query = String.format("DELETE FROM user WHERE id = %d", id);
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
        return null;
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
}
