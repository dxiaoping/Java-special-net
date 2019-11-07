package com.ccsu.jsn.controller;

import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-05 16:19
 */
@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping("login")
    @ResponseBody
    public Result login(@RequestParam("phone") long phone,
                        @RequestParam("password") String password){


        return userService.login(phone,password);
    }
}
