package com.webfruit.model;
import java.sql.Connection;
import com.webfruit.model.DBUtil;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Auth {
    private Connection connection;
    public Auth () throws SQLException{
        connection = DBUtil.getConnection();
    }
    public static Auth getInstance() throws SQLException {
        return new Auth();
    }
    public boolean checkLogin(String phone, String password) {
        try {
            // check phone
            String query = String.format("SELECT * FROM nguoi_dung WHERE so_dien_thoai = '%s'", phone);
            Statement st = connection.createStatement();
            if (st.executeQuery(query).next()) {
                // check password
                query = String.format("SELECT * FROM nguoi_dung WHERE so_dien_thoai = '%s' AND mat_khau = '%s'", phone, password);
                if (st.executeQuery(query).next()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
