package com.ccsu.jsn.service;

import com.ccsu.jsn.pojo.Menu;

import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-03 9:59
 */
public interface IMenuService {
    List<Menu> getMenuList(long id);
}
