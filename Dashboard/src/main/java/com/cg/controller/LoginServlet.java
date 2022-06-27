package com.cg.controller;

import com.cg.utils.MySQLConUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher  = req.getRequestDispatcher("/login/login.jsp");
        resp.getWriter().append("Served at: ").append(req.getContextPath());
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("emailid");
        String pass=req.getParameter("password");

        PreparedStatement stmt;
        ResultSet rs;
        Connection con;
        RequestDispatcher rd;
        int count =0;

        try
        {
            con= MySQLConUtils.getConnection();

            String sql="SELECT u.email, u.password FROM users AS u";
            stmt=con.prepareStatement(sql);

            rs=stmt.executeQuery();

            while(rs.next())
            {
                if(email.equals(rs.getString("email")) && pass.equals(rs.getString("password")))
                {
                    count=1;
                }
            }

            if(count==1)
            {
                req.setAttribute("status","Login Succesfully.... as "+email);
                rd=req.getRequestDispatcher("/login/login.jsp");
                rd.forward(req, resp);
                count=0;
            }

            else
            {
                req.setAttribute("status","Failed to Login..");
                rd=req.getRequestDispatcher("/login/login.jsp");
                rd.forward(req, resp);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
