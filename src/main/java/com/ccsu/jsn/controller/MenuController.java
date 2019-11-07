package com.ccsu.jsn.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.pojo.Menu;
import com.ccsu.jsn.service.IMenuService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-03 9:43
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private DruidDataSource druidDataSource;
//    @Autowired
//    private CommonsMultipartResolver multipartResolver;

    @RequestMapping("data")
    @ResponseBody
    public Result<List<Menu>> menu(@RequestParam("menuId") int menuId) {
        System.out.println(menuId);
        long id = menuId;
        return Result.success(menuService.getMenuList(id));
    }

    @RequestMapping("menu_view")
    public String menuView() {
        return "menu";
    }
}
