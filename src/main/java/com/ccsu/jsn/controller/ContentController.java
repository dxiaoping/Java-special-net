package com.ccsu.jsn.controller;

import com.ccsu.jsn.common.Const;
import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.pojo.User;
import com.ccsu.jsn.service.IContentService;
import com.ccsu.jsn.service.IFileService;
import com.ccsu.jsn.service.impl.ContentServiceImpl;
import com.ccsu.jsn.vo.ContentVo;
import com.ccsu.jsn.vo.RefreshVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @Description 控制知识点主题的上传下载浏览
 * @auther DuanXiaoping
 * @create 2019-11-05 15:54
 */

@Controller
@RequestMapping("content")
public class ContentController {
    Logger logger = LoggerFactory.getLogger(ContentController.class);
    @Autowired
    private IContentService contentService;

    /**上传展示内容主体*/
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam(value = "upload_file") MultipartFile file,
                         @RequestParam(value = "id") long id,
                         HttpSession session){
        /**
         * 将文件上传
         * 成功后将路径信息写入数据库
         */
        String path = session.getServletContext().getRealPath("upload");
        logger.warn("上传的路径为：{}",path);
        User user =(User) session.getAttribute(Const.CURRENT_USER);
        if (user.getRole()==Const.Role.ROLE_STUDENT){
            return Result.error("学生不允许上传文件");
        }
        logger.warn("登陆的用户为：{}",user);
        logger.warn("前端传入的id为：{}",id);
//        System.out.println("在控制层"+path);
        contentService.upload(file,path,id,user.getPhone());
//        System.out.println("已结束上传");
        return Result.success(0);
    }

    @RequestMapping(value = "get_content",method = RequestMethod.POST)
    @ResponseBody
    public Result getContent(@RequestParam("menuId") long menuId,
                             HttpSession session

    ){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        System.out.println("id号为："+menuId);
        Result result = contentService.getContent(menuId,user);
        ContentVo contentVo = (ContentVo) result.getData();
        session.setAttribute(Const.CURRENT_CONTENT,contentVo);
        return result;
    }

    @RequestMapping(value = "upload_enclosure",method = RequestMethod.POST)
    @ResponseBody
    public Result uploadEnclosure(
            @RequestParam(value = "enclosure") MultipartFile file,
            @RequestParam(value = "contentId") long contentId,
            HttpSession session

    ){
        String path = session.getServletContext().getRealPath("upload");
        User user =(User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return Result.error("用户未登录");
        }
        logger.info("已经进入方法：{}，contentId={}",Thread.currentThread().getStackTrace()[1].getMethodName(),contentId);

        return contentService.uploadEnclosure(file,contentId,path,user.getPhone());
    }


    @RequestMapping(value = "refresh",method = RequestMethod.POST)
    @ResponseBody
    public Result refresh(
        HttpSession session
    ){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ContentVo contentVo = (ContentVo) session.getAttribute(Const.CURRENT_CONTENT);
        RefreshVo refreshVo = new RefreshVo(user,contentVo);
        return Result.success(refreshVo);
    }

    @RequestMapping(value = "content_view")
    public String gotoContentView(
//            @RequestParam("menuId") long menuId
    ){
        return "content";
    }
}
