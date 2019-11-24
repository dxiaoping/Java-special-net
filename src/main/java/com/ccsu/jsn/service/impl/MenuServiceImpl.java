package com.ccsu.jsn.service.impl;

import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.dao.MenuMapper;
import com.ccsu.jsn.pojo.Menu;
import com.ccsu.jsn.service.IMenuService;
import com.ccsu.jsn.util.IdFactory;
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
    @Autowired
    private IdFactory idFactory;

    @Override
    public List<Menu> getMenuList(long id) {

        return menuMapper.selectMenusByParentId(id);
//        return null;
    }

    @Override
    public Result addNewMenu(long parentMenuId, String name, String href) {
        long id = idFactory.createIIMenuId();
        Menu menu = new Menu(id, parentMenuId, name, href);
        int res = menuMapper.insert(menu);
        if (res > 0) {
            return Result.success(0);
        } else {
            return Result.error("新建知识点模块时发生错误");
        }

    }
}
