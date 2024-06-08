package com.webfruit.model;
import com.webfruit.dao.User;
import com.webfruit.model.DBUtil;

import java.sql.Connection;
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
        return false;
    }

    @Override
    public boolean update(User T) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public User selectById(String id) {
        return null;
    }

    @Override
    public ArrayList<User> selectAll() {
        return null;
    }
}
