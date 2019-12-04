package com.ccsu.javalearn.dao;

import com.ccsu.javalearn.pojo.Example;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleMapper {
    int insert(Example example);
    int update(Example example);
    Example selectById(long id);
    Example selectByMenuId(long menuId);
}
