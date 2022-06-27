package com.cg.controller;


import com.cg.model.User;
import com.cg.service.IUserService;
import com.cg.service.UserServiceImpl;
import com.cg.utils.MySQLConUtils;
import com.cg.utils.ValidateUtils;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "CPUserServlet",urlPatterns = "/user")
public class    CPUserServlet extends HttpServlet {
    IUserService IUserService;
    @Override
    public void init() throws ServletException {
        IUserService =  new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action="";
        }
        switch (action){
            case "create":
                showCreatePage(req,resp);
                break;
            case "update":
                showUpdatePage(req,resp);
                break;
            case "delete":
                deleteUser(req,resp);
                break;
            case "list":
                showListPage(req,resp);
                break;
            default:
                showListPage(req,resp);
                break;

        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean success = IUserService.delete(id);
        if (success){
            req.setAttribute("success", true);
        }else {
            req.setAttribute("error", true);
        }
        List<User> users = IUserService.findAll();
        req.setAttribute("users",users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/list.jsp");
        dispatcher.forward(req,resp);
    }

    private void showUpdatePage(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/edit.jsp");
        String id = req.getParameter("id");
        User user = IUserService.findById(Integer.parseInt(id));
        if (user != null){
            req.setAttribute("user",user);
        }
        dispatcher.forward(req,resp);
    }

    private void showCreatePage(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/create.jsp");
        List<User> users = IUserService.findAll();
        req.setAttribute("users",users);
        dispatcher.forward(req,resp);
    }

    private void showListPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/list.jsp");
        List<User>  users = IUserService.findAll();
        req.setAttribute("users",users);
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                doCreate(req,resp);
                break;
            case "update":
                doUpdate(req,resp);
                break;
        }
    }
    private void doCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/create.jsp");
        String fullName = req.getParameter("fullName");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String address = req.getParameter("address");
        List<String> errors = new ArrayList<>();

        if (fullName.length() == 0){
            errors.add("Name is not valid");
        }else if (age >100 || age <0){
            errors.add("Age is not valid");
        }else if (!ValidateUtils.isEmailValid(email)) {
            errors.add("Email is not valid");
        }else if (mobile == null || mobile.length()>20) {
            errors.add("Mobile is not valid");
        }else {
            User user = new User(fullName,age,email,mobile,address);
            Map<String,String> result = IUserService.doCreate(user);
            String success = result.get("success");
            String message = result.get("message");

            if (success.equals("true")){
                req.setAttribute("success", true);
            }else {
                errors.add(message);
                req.setAttribute("error", true);
            }
        }
        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
        }
        dispatcher.forward(req,resp);

    }
    private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/edit.jsp");
        String id = req.getParameter("id");
        String fullName = req.getParameter("fullName");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String address = req.getParameter("address");

        List<String> errors = new ArrayList<>();

        if (fullName.length() == 0){
            errors.add("Name is not valid");
        }else if (age >100 || age <0){
            errors.add("Age is not valid");
        }else if (!ValidateUtils.isEmailValid(email)) {
            errors.add("Email is not valid");
        }else if (mobile == null || mobile.length()>20){
            errors.add("Mobile is not valid");
        }else {
            User user = new User(Integer.parseInt(id),fullName,age,email,mobile,address);
            boolean success = IUserService.update(user);
            if (success){
                req.setAttribute("success", true);
            }else {
                req.setAttribute("error", true);
            }
        }
        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
        }
        dispatcher.forward(req,resp);
        }
}
