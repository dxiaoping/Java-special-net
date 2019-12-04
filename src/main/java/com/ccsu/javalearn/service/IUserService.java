package com.ccsu.javalearn.service;


import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.pojo.User;

public interface IUserService {
    Result<User> login(long phone, String password);
    Result register(User user);
}
