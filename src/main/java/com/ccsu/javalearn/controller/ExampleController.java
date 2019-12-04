package com.ccsu.javalearn.controller;

import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.dao.ExampleMapper;
import com.ccsu.javalearn.pojo.Example;
import com.ccsu.javalearn.service.IExampleService;
import com.ccsu.javalearn.common.Const;
import com.ccsu.javalearn.pojo.User;
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
 * @create 2019-11-30 16:42
 */
@Controller
@RequestMapping("example")
public class ExampleController {

    @Autowired
    private IExampleService exampleService;

    @Autowired
    private ExampleMapper exampleMapper;


    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(
            @RequestParam(value = "fileCode",required=false) MultipartFile file,
            @RequestParam(value = "menuIId") long menuIId,
            @RequestParam(value = "exampleId")long id,
            @RequestParam(value = "title") String title ,
            @RequestParam(value = "content") String content ,
            @RequestParam(value = "runResult") String runResult ,
            @RequestParam(value = "interpret") String interpret,
            HttpSession session
            ){

        User user =(User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return Result.error("用户未登录");
        }
        Example example = new Example(id,user.getPhone(),title,content,interpret,runResult);
        String path = session.getServletContext().getRealPath("upload");
        System.out.println(example);
        exampleService.saveExample(example,menuIId,file,path);
        System.out.println("控制测");
        return Result.success(0);
    }

    @RequestMapping(value = "data",method = RequestMethod.POST)
    @ResponseBody
    public Result getExample(
            @RequestParam("menuId") long menuId
    ){
        Example example = exampleService.getExample(menuId);
        return Result.success(example);
    }


    @RequestMapping(value = "get_example_by_id",method = RequestMethod.POST)
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
        if(user.getRole()== Const.Role.ROLE_TEACHER && user.getPhone()!= exampleService.getExampleById(id).getUserId()){
            return Result.error("不能修改其他教师的模块");
        }
        Example example = exampleService.getExampleById(id);
        return Result.success(example);
    }

    @RequestMapping(value = "addCount",method = RequestMethod.POST)
    @ResponseBody
    public Result addCount(
            @RequestParam("exampleId") long exampleId,
            HttpSession session
    ){
        Example example = new Example();
        example.setId(exampleId);
        example.setDownloadCount(exampleMapper.selectById(exampleId).getDownloadCount()+1);
        exampleMapper.update(example);
        return Result.success(0);
    }

}
