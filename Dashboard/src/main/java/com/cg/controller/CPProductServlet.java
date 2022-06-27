package com.cg.controller;

import com.cg.model.Product;
import com.cg.model.User;
import com.cg.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CPProductServlet", urlPatterns = "/product")
public class CPProductServlet extends HttpServlet {
    ProductService productService;
    @Override
    public void init() throws ServletException {
        productService =new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create" :
                showCreatePage(req,resp);
                break;
            case "update" :
                showUpdatePage(req,resp);
                break;
            case "search" :
                showSearchPage(req,resp);
                break;
            case "delete" :
                deleteProduct(req,resp);
                break;
            default:
                showListPage(req,resp);
                break;
        }
    }

    private void showSearchPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/search.jsp");
        String name = req.getParameter("name");
        List<Product> productList = productService.searchByName(name);
        req.setAttribute("productList",productList);
        dispatcher.forward(req,resp);
    }

    private void showCreatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/create.jsp");
        List<Product> productList = new ArrayList<>();
        req.setAttribute("productList",productList);
        dispatcher.forward(req,resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean success = productService.delete(id);
        if (success){
            req.setAttribute("success", true);
        }else {
            req.setAttribute("error", true);
        }
        List<Product> productList = productService.findAll();
        req.setAttribute("productList",productList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/list.jsp");
        dispatcher.forward(req,resp);
    }

    private void showUpdatePage(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/edit.jsp");
        String id = req.getParameter("id");
        Product product = productService.findById(Integer.parseInt(id));
        if (product != null){
            req.setAttribute("product",product);
        }

        dispatcher.forward(req,resp);
    }

    private void showListPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/list.jsp");
        List<Product> productList = productService.findAll();
        req.setAttribute("productList",productList);
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        System.out.println(this.getClass() + " doPost: ");
        switch (action){
            case "create" :
                doCeate(req,resp);
                break;
            case "update" :
                doUpdate(req,resp);
                break;
        }
    }

    private void doCeate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/create.jsp");
        String fullName = req.getParameter("fullName");
        double price = Integer.parseInt(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        List<String> errors = new ArrayList<>();
        if (fullName.length() == 0){
            errors.add("Product name can not empty");
        }else  if (price < 0 || price > 1000000000){
            errors.add("Price is not valid");
        }else if (quantity < 0 || quantity > 10000){
            errors.add("Quantity is not valid");
        }else {
            Product product = new Product(fullName,price,quantity,description);
            boolean success =  productService.create(product);
            if (success) {
                req.setAttribute("success", true);
            }
            else {
                req.setAttribute("error", true);
            }
        }
        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
        }
        dispatcher.forward(req,resp);
    }

    private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/edit.jsp");
        int id = Integer.parseInt(req.getParameter("id"));
        String productName = req.getParameter("productName");
        double price = Integer.parseInt(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        List<String> errors = new ArrayList<>();
        if (productName.length() == 0){
            errors.add("Product name is not empty");
        }else if (price < 1 || price >10000000){
            errors.add("price is not valid");
        }else if (quantity < 0 || quantity >10000){
            errors.add("quantity is not valid");
        }else {
            Product product = new Product(id,productName,price,quantity,description);
            boolean success =  productService.update(product);
            if (success) {
                req.setAttribute("success", true);
            }
            else {
                req.setAttribute("error", true);
            }
        }
        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
        }
        dispatcher.forward(req,resp);
    }
}
