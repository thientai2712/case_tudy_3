package com.cg.service;

import com.cg.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService extends IGeneralService<User>{
    boolean update(User user);

    User findById(int userId);

    List<User> findAll();

    Map<String,String> doCreate(User user);
}
