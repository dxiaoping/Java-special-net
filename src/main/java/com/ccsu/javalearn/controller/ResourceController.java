package com.ccsu.javalearn.controller;

import com.ccsu.javalearn.common.Const;
import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.dao.ResourceMapper;
import com.ccsu.javalearn.pojo.Resource;
import com.ccsu.javalearn.pojo.User;
import com.ccsu.javalearn.service.IResourceService;
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
 * @create 2019-11-30 23:19
 */
@Controller
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private ResourceMapper resourceMapper;

    @RequestMapping(value = "data",method = RequestMethod.POST)
    @ResponseBody
    public Result getResources(
            @RequestParam("menuId") long menuId
    ){
        Result result = resourceService.getResourceList(menuId);
        return result;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public Result addResources(
            @RequestParam("menuId") long menuId,
            @RequestParam("description") String description,
            @RequestParam("fileResource") MultipartFile file,
            HttpSession session
    ){
        User user =(User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return Result.error("用户未登录");
        }
        String path = session.getServletContext().getRealPath("upload");
        Resource resource = new Resource(menuId,user.getPhone(),description,path,Const.ResourceType.RESOURCE_ORDINARY);
        System.out.println(resource);
        int res = resourceService.addResource(file,resource);
        return Result.success(0);
    }

    @RequestMapping(value = "addCount",method = RequestMethod.POST)
    @ResponseBody
    public Result addCount(
            @RequestParam("resId") long resId,
            HttpSession session
    ){
        Resource resource = new Resource();
        resource.setId(resId);
        resource.setDownloadCount(resourceMapper.selectById(resId).getDownloadCount()+1);
        resourceMapper.update(resource);
        return Result.success(0);
    }
}
