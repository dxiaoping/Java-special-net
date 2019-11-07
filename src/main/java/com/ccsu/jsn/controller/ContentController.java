package com.ccsu.jsn.controller;

import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.service.IContentService;
import com.ccsu.jsn.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 控制知识点主题的上传下载浏览
 * @auther DuanXiaoping
 * @create 2019-11-05 15:54
 */

@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    private IContentService contentService;

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam(value = "upload_file") MultipartFile file,
                         @RequestParam(value = "id") long id,
                         HttpServletRequest request){
        /**
         * 将文件上传
         * 成功后将路径信息写入数据库
         */
        System.out.println("已进入上传控制器");
        String path = request.getSession().getServletContext().getRealPath("upload");
        contentService.upload(file,path);
        System.out.println("已结束上传");
        return Result.success(0);
    }
}
