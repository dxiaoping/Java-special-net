package com.ccsu.javalearn.dao;

import com.ccsu.javalearn.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectByPhone(long phone);
    User selectByName(String name);

    int insert(User user);
    int updateUserByPhone(User user);
    int checkPhone(long phone);
}
