package com.ccsu.javalearn.controller;

import com.ccsu.javalearn.common.Const;
import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.pojo.Known;
import com.ccsu.javalearn.pojo.User;
import com.ccsu.javalearn.service.IKnownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-12-02 20:36
 */
@Controller
@RequestMapping("known")
public class KnownController {

    @Autowired
    private IKnownService knownService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(
            @RequestParam(value = "fileDoc", required = false) MultipartFile fileDoc,
            @RequestParam(value = "menuIId") long menuIId,
            @RequestParam(value = "knownId") long id,
            @RequestParam(value = "text") String text,
            @RequestParam(value = "fileImg",required = false) MultipartFile fileImg,
            HttpSession session
    ) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return Result.error("用户未登录");
        }
        System.out.println("控制测");
        Known known = new Known(id, menuIId, user.getPhone(), text);
        String path = session.getServletContext().getRealPath("upload");
        System.out.println(known);
        knownService.saveKnown(known, menuIId, fileDoc,fileImg, path);
        return Result.success(0);
    }

    @RequestMapping(value = "data", method = RequestMethod.POST)
    @ResponseBody
    public Result getExample(
            @RequestParam("menuId") long menuId,
            HttpSession session
    ) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        Result result = knownService.getKnown(menuId,user);

        return result;
    }

    @RequestMapping(value = "get_known_by_id",method = RequestMethod.POST)
    @ResponseBody
    public Result getExampleById(
            @RequestParam("id") long id,
            HttpSession session
    ){
        User user =(User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return Result.error("用户未登录");
        }
        if (user.getRole()== Const.Role.ROLE_STUDENT){
            return Result.error("学生不允许上传文件");
        }
        if(user.getRole()== Const.Role.ROLE_TEACHER && user.getPhone()!= knownService.getKnownById(id).getUserId()){
            return Result.error("不能修改其他教师的模块");
        }
        Known example = knownService.getKnownById(id);
        return Result.success(example);
    }
}
