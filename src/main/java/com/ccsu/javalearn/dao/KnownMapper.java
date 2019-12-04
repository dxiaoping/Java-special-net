package com.ccsu.javalearn.dao;

import com.ccsu.javalearn.pojo.Known;
import org.springframework.stereotype.Repository;

@Repository
public interface KnownMapper {
    Known selectByMenuId (long menuId);
    int insert(Known known);
    int update(Known known);
    Known selectById(long id);
}
