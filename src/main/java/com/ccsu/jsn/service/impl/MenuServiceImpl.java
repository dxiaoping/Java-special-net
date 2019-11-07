package com.ccsu.jsn.service.impl;

import com.ccsu.jsn.dao.MenuMapper;
import com.ccsu.jsn.pojo.Menu;
import com.ccsu.jsn.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-03 10:03
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> getMenuList(long id) {

        return menuMapper.selectMenusByParentId(id);
//        return null;
    }
}
