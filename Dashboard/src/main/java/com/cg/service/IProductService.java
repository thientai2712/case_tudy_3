package com.cg.service;

import com.cg.model.Product;
import com.cg.model.User;

import java.util.List;
import java.util.Map;

public interface IProductService extends IGeneralService<Product>{
    Product findById(int productId);
    Map<String,String> doCreate(Product product);
    List<Product> searchByName(String name);
}
