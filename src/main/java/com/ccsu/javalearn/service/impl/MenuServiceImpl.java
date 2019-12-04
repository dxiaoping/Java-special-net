package com.ccsu.javalearn.service.impl;

import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.dao.MenuMapper;
import com.ccsu.javalearn.pojo.Menu;
import com.ccsu.javalearn.service.IMenuService;
import com.ccsu.javalearn.util.IdFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Result addNewMenu(long parentMenuId, String name) {
        long id = idFactory.createIIMenuId();
        int rank = menuMapper.selectById(parentMenuId).getRank() + 1;
        Menu menu = new Menu(id, parentMenuId, name, rank);
        int res = menuMapper.insert(menu);
        if (res > 0) {
//            如果是基础或进阶模块则创建相应实例教程
            if (parentMenuId == 101 || parentMenuId ==102){
                id = idFactory.createIIMenuId();
                Menu exampleMenu = new Menu(id, 103l, name+"实例教程", rank);
                res = menuMapper.insert(exampleMenu);
                if (res < 1){
                    return Result.error("新建实例教程模块失败");
                }
            }
            return Result.success(id);
        } else {
            return Result.error("新建知识点模块时发生错误");
        }
    }
}
