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

public class ProductService implements IProductService{
    private static String SELECT_ALL_PRODUCTS = "" +
            "SELECT " +
            "p.id, " +
            "p.name, " +
            "p.price, " +
            "p.quantity, " +
            "p.description, " +
            "p.createdAt, " +
            "p.updatedAt " +
            "FROM products AS p ;";

    private static String SELECT_PRODUCT_BY_ID = "" +
            "SELECT " +
            "p.id, " +
            "p.name, " +
            "p.price, " +
            "p.quantity, " +
            "p.description, " +
            "p.createdAt, " +
            "p.updatedAt " +
            "FROM products AS p " +
            "WHERE p.id = ? ;";
    private static String SP_UPDATE_PRODUCT_BY_ID = "{CALL sp_update_product(?, ?, ?, ?, ?, ?)}";
//            +
//            "UPDATE products AS p " +
//            "SET " +
//            "p.name = ?, " +
//            "p.price = ?, " +
//            "p.quantity = ?, " +
//            "p.description = ? " +
//            "WHERE p.id = ?;";
    private static  String DELETE_PRODUCT_BY_ID = "" +
            "DELETE FROM products AS p " +
            "WHERE p.id = ?;";
    private static String SP_INSERT_PRODUCT = "{CALL sp_insert_product(?, ?, ?, ?, ?, ?)}";
    private static String SEARCH_BY_DATE = "" +
            "SELECT" +
            "p.name, " +
            "p.price, " +
            "p.quantity, " +
            "p.description " +
           "FROM products AS p " +
           "WHERE p.name = ? ;";
    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try(Connection connection = MySQLConUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(SELECT_ALL_PRODUCTS);)
        {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String product_name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("createdAt");
                Date updatedAt = rs.getDate("updatedAt");
                productList.add(new Product(id,product_name,price,quantity,description,createdAt,updatedAt));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return productList;

    }

    @Override
    public boolean create(Product product) {
        boolean success = false;
        try
        {
            Connection connection = MySQLConUtils.getConnection();
            CallableStatement statement = connection.prepareCall(SP_INSERT_PRODUCT);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getDescription());
            statement.registerOutParameter(5, Types.BOOLEAN);
            statement.registerOutParameter(6, Types.VARCHAR);
            statement.execute();
            success = statement.getBoolean("success");
            //String message = statement.getString("message");

        }catch (SQLException ex){
            MySQLConUtils.printSQLException(ex);
        }
        return success;
    };

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean update(Product product) {

        boolean success = false;
        try {
            Connection connection = MySQLConUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(SP_UPDATE_PRODUCT_BY_ID);
            statement.setInt(1, product.getId() );
            statement.setString(2, product.getName() );
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity() );
            statement.setString(5, product.getDescription() );


            System.out.println(this.getClass() + " update() preparedstatement: " + statement);
            statement.execute();
            success = true;

        }catch (SQLException ex){
            MySQLConUtils.printSQLException(ex);
        }
        return success;
    }
    @Override
    public List<Product> searchByName(String name) {
        List<Product> productList = new ArrayList<>();

        try {
            Connection connection = MySQLConUtils.getConnection();

            CallableStatement statement = connection.prepareCall(SEARCH_BY_DATE);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String fullName = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");

                productList.add(new Product(fullName, price, quantity, description));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public boolean delete(int id) {
        boolean success = false;
        int opt = JOptionPane.showConfirmDialog(null,"Are you sure to Delete?","Delete", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            try {
                Connection connection = MySQLConUtils.getConnection();
                PreparedStatement statement = connection.prepareCall(DELETE_PRODUCT_BY_ID);
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
    public Product findById(int productId) {
        Product product = null;
        try {
            Connection connection = MySQLConUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(SELECT_PRODUCT_BY_ID);
            statement.setInt(1,productId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String productName = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("createdAt");
                Date updatedAt = rs.getDate("updatedAt");
                product = new Product(id,productName,price,quantity,description,createdAt,updatedAt);

            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return product;
    }

    @Override
    public Map<String, String> doCreate(Product product) {
        Map<String,String> result = new HashMap<>();
        try{
            Connection connection = MySQLConUtils.getConnection();
            CallableStatement statement = connection.prepareCall(SP_INSERT_PRODUCT);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getDescription());
            statement.registerOutParameter(5, Types.BOOLEAN);
            statement.registerOutParameter(6, Types.VARCHAR);
            statement.execute();
            Boolean success = statement.getBoolean("success");
            String message = statement.getString("message");
            result.put("success", success.toString());
            result.put("message", message);

        }catch (SQLException ex){
            MySQLConUtils.printSQLException(ex);
        }
        return result;
    }
}
