package com.ccsu.jsn.service;


import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.pojo.User;

public interface IUserService {
    Result<User> login(long phone, String password);
    Result register(User user);
}
