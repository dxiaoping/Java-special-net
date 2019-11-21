package com.ccsu.jsn.controller;

import com.ccsu.jsn.common.Const;
import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-10-28 19:04
 */
@Controller
@RequestMapping("login")
public class TestController {

    @RequestMapping(value = "/remove_session", method = RequestMethod.GET)
    public void removeSession(HttpSession session) {
        session.invalidate();
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "hello";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    @ResponseBody
    public String index() {
        return "index";
    }


    @RequestMapping(value = "get_user", produces = {"application/json; charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public Result<User> getUser() {
        User user = new User();
        user.setPhone(15574902299l);
        user.setPassword("qweasdzxc");
        user.setRole(Const.Role.ROLE_ADMIN);
        System.out.println(user);
        return Result.success(user);
    }
}
