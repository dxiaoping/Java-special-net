package com.ccsu.javalearn.service;

import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.pojo.Menu;

import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-03 9:59
 */
public interface IMenuService {
    List<Menu> getMenuList(long id);
    Result addNewMenu(long parentMenuId, String name);
}
