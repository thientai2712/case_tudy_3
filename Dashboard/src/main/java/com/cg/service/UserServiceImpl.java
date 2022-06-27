package com.cg.service;

import com.cg.model.Product;
import com.cg.model.User;
import com.cg.utils.MySQLConUtils;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements IUserService {
    private static String SELECT_ALL_USERS = "" +
            "SELECT " +
            "u.id, " +
            "u.full_name, " +
            "u.age, " +
            "u.email, " +
            "u.mobile, " +
            "u.address " +
            "FROM users AS u ;";
    private static String SELECT_USER_BY_ID = "" +
            "SELECT " +
            "u.id, " +
            "u.full_name, " +
            "u.age, " +
            "u.email, " +
            "u.mobile, " +
            "u.address " +
            "FROM users AS u " +
            "Where u.id = ? ;";
    private static String INSERT_USER = "" +
            "INSERT INTO users(full_name,age,email,mobile,address) " +
            "VALUES(?,?,?,?,?);";
    private static String UPDATE_USER_BY_ID = "" +
            "UPDATE users AS u " +
            "SET " +
            "u.full_name = ?, " +
            "u.age = ?, " +
            "u.email = ?, " +
            "u.mobile = ?," +
            "u.address = ? " +
            "WHERE u.id = ? ;";
    private static String DELETE_USER_BY_ID = "" +
            "DELETE FROM users AS u " +
            "WHERE u.id = ?;";
    private static String SP_INSERT_USER = "{CALL sp_insert_user(?, ?, ?, ?, ?, ?, ?)}";
    private static String LOGIN_USER = "" +
            "SELECT " +
            "u.email, " +
            "u.password " +
            "FROM users AS u;";


    @Override
    public boolean create(User user) {
        boolean success = false;
        try {
            Connection connection = MySQLConUtils.getConnection();
            CallableStatement statement = connection.prepareCall(SP_INSERT_USER);
            statement.setString(1, user.getFullName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getMobile());
            statement.setString(5, user.getAddress());
            statement.execute();


            success = statement.getBoolean("success");
            String message = statement.getString("message");


            success = true;
        } catch (SQLException ex) {
            MySQLConUtils.printSQLException(ex);
        }
        return success;
    }

    @Override
    public boolean delete(int id) {
        boolean success = false;
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Delete?", "Delete", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            try {
                Connection connection = MySQLConUtils.getConnection();
                PreparedStatement statement = connection.prepareCall(DELETE_USER_BY_ID);
                statement.setInt(1, id);
                statement.execute();
                success = true;
            } catch (SQLException ex) {
                MySQLConUtils.printSQLException(ex);
            }
        }
        return success;
    }

    @Override
    public boolean update(User user) {
        boolean success = false;
        try {
            Connection connection = MySQLConUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(UPDATE_USER_BY_ID);
            statement.setString(1, user.getFullName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getMobile());
            statement.setString(5, user.getAddress());
                statement.setInt(6, user.getId());
                statement.execute();
            success = true;
        } catch (SQLException ex) {
            MySQLConUtils.printSQLException(ex);
        }
        return success;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public User findById(int userId) {
        User user = null;
        try {
            Connection connection = MySQLConUtils.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareCall(SELECT_USER_BY_ID);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                user = new User(id, fullName, age, email, mobile, address);
            }
        } catch (SQLException e) {
            MySQLConUtils.printSQLException(e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = MySQLConUtils.getConnection();
             PreparedStatement statement = connection.prepareCall(SELECT_ALL_USERS);) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                String address = rs.getString("address");
                userList.add(new User(id, fullName, age, email, mobile, address));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Map<String, String> doCreate(User user) {
        Map<String, String> result = new HashMap<>();
        try {
            Connection connection = MySQLConUtils.getConnection();
            CallableStatement statement = connection.prepareCall(SP_INSERT_USER);
            statement.setString(1, user.getFullName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getMobile());
            statement.setString(5, user.getAddress());
            statement.registerOutParameter(6, Types.BOOLEAN);
            statement.registerOutParameter(7, Types.VARCHAR);
            statement.execute();

            Boolean success = statement.getBoolean("success");
            String message = statement.getString("message");
            System.out.println(success);
            System.out.println(message);
            result.put("success", success.toString());
            result.put("message", message);

        } catch (SQLException ex) {
            MySQLConUtils.printSQLException(ex);
        }
        return result;
    }
}
