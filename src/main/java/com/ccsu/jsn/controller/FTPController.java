package com.ccsu.jsn.controller;

import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-10-29 17:10
 */
@Controller
@RequestMapping("file")
@PropertySource("classpath:config/application.properties")
public class FTPController {

    @Autowired
    private IFileService fileService;

    @RequestMapping(value = "upload" ,method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> upload(
            @RequestParam(value = "upload_file") MultipartFile file,
            HttpServletRequest request
    ) {

        String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println(path);
//        fileService.upload(file,path);
        System.out.println("进入控制层");
//            Map fileMap = new HashMap();
//            fileMap.put("uri", targetFileName);
//            fileMap.put("url", url);

//==================================
//        String originalFilename = file.getOriginalFilename();
//        String dirPath = request.getServletContext().getRealPath("/upload/");
//        File filePath = new File(dirPath);
//        if (!filePath.exists()) {
//            filePath.mkdirs();
//        }
//
//        String newFilename = originalFilename;
//        try {
//            file.transferTo(new File(dirPath + "_" + newFilename));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.error("error");
//        }
        return Result.success(0);
    }

//    @RequestMapping("download")
    public Result<Integer> download(String path) {

        return Result.success(0);
    }

//    @RequestMapping("upload_view")
    public String gotoUploadView() {


        return "upload";
    }

}
