package com.cg.controller;

import com.cg.model.User;
import com.cg.utils.MySQLConUtils;
import com.mysql.cj.MysqlConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
     String  emp_name;
    private static String SEARCH_BY_NAME = " " +
            "SELECT " +
            "p.name, " +
            "p.price, " +
            "p.quantity, " +
            "p.description " +
            "WHERE p.name = emp_name " +
            "ORDER BY p.name ;";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/search.jsp");

        dispatcher.forward(req,resp);
    }
}
