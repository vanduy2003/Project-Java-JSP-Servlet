package com.webfruit.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

import com.webfruit.dao.Order;
import com.webfruit.dao.OrderDetail;
import com.webfruit.model.DBUtil;

public class HandlePay {
    private Connection connection;

    public HandlePay() throws SQLException {
        connection = DBUtil.getConnection();
    }

    public static HandlePay getInstance() throws SQLException {
        return new HandlePay();
    }

    // close connection
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // insert order
    public int insertOrder(Order order) {
        try {
            String query = "INSERT INTO dat_hang (ID_nguoi_dung,  ghi_chu, ma_giam_gia, trang_thai_dat_hang, dia_chi, sdt) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, order.getIdUser());
            preparedStatement.setString(2, order.getDesc());
            preparedStatement.setString(3, order.getDiscount());
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setString(5, order.getAddress());
            preparedStatement.setString(6, order.getPhone());
            preparedStatement.executeUpdate();

            // Retrieve the ID of the newly inserted order
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int newOrderId = rs.getInt(1);
                return newOrderId;
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    // insert order detail
    public boolean insertOrderDetail(OrderDetail orderDetail) {
        try {
            String query = "INSERT INTO chi_tiet_dat_hang (ID_nguoi_dung, ID_san_pham, so_luong, ghi_chu, ID_dat_hang) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderDetail.getId_user());
            preparedStatement.setInt(2, orderDetail.getId_product());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setString(4, orderDetail.getGhi_chu());
            preparedStatement.setInt(5, orderDetail.getId_order());

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // select all order
    public ArrayList<Order> selectAllOrder() {
        // select all order
        try {
            ArrayList<Order> orders = new ArrayList<>();
            String query = "SELECT * FROM dat_hang";
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery(query);
            while (res.next()) {
                Order order = new Order();
                order.setId(res.getInt("ID"));
                order.setDate(res.getString("ngay_them"));
                order.setIdUser(res.getInt("ID_nguoi_dung"));
                order.setDesc(res.getString("ghi_chu"));
                order.setDiscount(res.getString("ma_giam_gia"));
                order.setStatus(res.getString("trang_thai_dat_hang"));
                order.setAddress(res.getString("dia_chi"));
                order.setPhone(res.getString("sdt"));
                orders.add(order);
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
