package com.ccsu.javalearn.controller;

import com.ccsu.javalearn.common.Const;
import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.dao.MenuMapper;
import com.ccsu.javalearn.pojo.Menu;
import com.ccsu.javalearn.pojo.User;
import com.ccsu.javalearn.service.IMenuService;
import com.ccsu.javalearn.util.IdFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-30 15:34
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

//    @Autowired
//    private CommonsMultipartResolver multipartResolver;

    @RequestMapping("data")
    @ResponseBody
    public Result<List<Menu>> getMenu(@RequestParam("menuId") int menuId) {
//        System.out.println(menuId);
        long id = menuId;
        return Result.success(menuService.getMenuList(id));
    }

    @RequestMapping(value = "add_menu", method = RequestMethod.POST)
    @ResponseBody
    public Result addMenu(
            @RequestParam("parentMenuId") long parentMenuId,
            @RequestParam("knowledgeName") String name,
            HttpSession session
    ) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return Result.error("用户未登录");
        }
        if (user.getRole() == Const.Role.ROLE_STUDENT) {
            return Result.error("学生不允许添加模块");
        }
        return menuService.addNewMenu(parentMenuId, name);
    }

    @RequestMapping(value = "refresh", method = RequestMethod.POST)
    @ResponseBody
    public Result refresh(
            HttpSession session
    ) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);

//        ContentVo contentVo = (ContentVo) session.getAttribute(Const.CURRENT_CONTENT);
//        RefreshVo refreshVo = new RefreshVo(user,contentVo);
        return Result.success(user);
    }

    @RequestMapping("menu_view")
    public String menuView() {
        return "menu";
    }


    @Autowired
    private MenuMapper menuMapper;

    @RequestMapping("get_menu2")
    @ResponseBody
    public Result getmenu2(
            @RequestParam("id") long id
    ) {
        List<Menu> menus = menuMapper.selectMenusByParentId(id);
        return Result.success(menus);
    }

    @Autowired
    private IdFactory idFactory;

    @RequestMapping(value = "sb_add_menu", method = RequestMethod.POST)
    @ResponseBody
    public Result sbAddMenu(
            @RequestParam("menuName") String menuName,
            @RequestParam("menuDescription") String menuDescription,
            @RequestParam("parentId") long parentId,
            HttpSession session
    ) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return Result.error("用户未登录");
        }
        if (user.getRole() == Const.Role.ROLE_STUDENT) {
            return Result.error("学生不允许添加模块");
        }

        Menu menu = new Menu(idFactory.createIIMenuId(), parentId, menuName, menuDescription, user.getPhone(), user.getName());
        menuMapper.insert(menu);
        return Result.success(0);
    }

    @RequestMapping(value = "sb_delete_menu", method = RequestMethod.GET)
    @ResponseBody
    public Result sb_delete_menu(
            @RequestParam("id") long id,
            HttpSession session
    ) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return Result.error("用户未登录");
        }
        if (user.getRole() == Const.Role.ROLE_STUDENT) {
            return Result.error("学生不允许添加模块");
        }
        menuMapper.delete(id);
        return Result.success(0);
    }

    @RequestMapping(value = "sb_edit_menu", method = RequestMethod.POST)
    @ResponseBody
    public Result sb_edit_menu(
            @RequestParam("id") long id,
            @RequestParam("menuName") String menuName,
            @RequestParam("menuDescription") String menuDescription,
            HttpSession session
    ) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return Result.error("用户未登录");
        }
        if (user.getRole() == Const.Role.ROLE_STUDENT) {
            return Result.error("学生不允许添加模块");
        }
        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(menuName);
        menu.setDescription(menuDescription);
        menuMapper.update(menu);
        return Result.success(0);
    }

    @RequestMapping(value = "getMenu", method = RequestMethod.POST)
    @ResponseBody
    public Result getMenu(
            @RequestParam("id") long id,
            HttpSession session
    ) {


        Menu menu = menuMapper.selectById(id);

        return Result.success(menu);
    }

}