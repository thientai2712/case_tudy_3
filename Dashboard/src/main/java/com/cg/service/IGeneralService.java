package com.cg.service;

import com.cg.model.Product;
import com.cg.model.User;

import java.util.List;

public interface IGeneralService <T>{
    List<T> findAll();
    boolean create(T t);
    boolean update(User user);
    boolean update(Product product);
    boolean delete (int id);
}
