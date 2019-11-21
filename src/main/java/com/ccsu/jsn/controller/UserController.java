package com.ccsu.jsn.controller;

import com.ccsu.jsn.common.Const;
import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.pojo.User;
import com.ccsu.jsn.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-05 16:19
 */
@Controller
@RequestMapping("user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public Result login(@RequestParam("phone") long phone,
                        @RequestParam("password") String password,
                        HttpSession session
    ) {

        logger.info("phone={},password={}", phone, password);
        Result result = userService.login(phone, password);

        System.out.println();
        if (result.getData() instanceof User) {
            session.setAttribute(Const.CURRENT_USER, (User) result.getData());
        }

        return result;
//        return Result.success(0);
    }


    @RequestMapping("register")
    @ResponseBody
    public Result register(@RequestParam("phone") long phone,
                           @RequestParam("password") String password,
                           @RequestParam("name") String name,
                           @RequestParam("role") int role,
                           HttpSession session) {

        if (role < Const.Role.ROLE_STUDENT) {
            System.out.println("注册用户非学生");
            User admin = (User) session.getAttribute(Const.CURRENT_USER);
            logger.info("获取登录用户；{}",admin);
            if (admin == null){
                return Result.error("请登录管理员");
            }
            if (admin.getRole() != Const.Role.ROLE_ADMIN) {
                return Result.error("用户非管理员");
            }
        }
        User user = new User(phone, name, password, role);
        return userService.register(user);
    }

    @RequestMapping(value = "/out_login", method = RequestMethod.GET)
    @ResponseBody
    public Result outLogin(HttpSession session) {
        session.invalidate();
        if (session.getAttribute(Const.CURRENT_USER) == null){
            return Result.success(0);
        }else {
            return Result.error("注销失败");
        }
    }
}
