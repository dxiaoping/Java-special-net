package com.ccsu.jsn.controller;

import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.service.IFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URLEncoder;


/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-10-29 17:10
 */
@Controller
@RequestMapping("file")
@PropertySource("classpath:config/application.properties")
public class FileController {

    @Autowired
    private IFileService fileService;

    @RequestMapping(value = "upload", method = RequestMethod.POST)
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

    @RequestMapping("download")

    public ResponseEntity<byte[]> filedownload(HttpServletRequest request,
                                                String url,String fileName) throws Exception {

        System.out.println(url);
        System.out.println(fileName);
        String path = request.getServletContext().getRealPath("/");
        System.out.println(path);
        System.out.println(File.separator);
        File file = new File(path + url);
//        filename = this.getFilename(request, filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", getFilename(request, fileName));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }

    public String getFilename(HttpServletRequest request, String filename) throws Exception {
        String[] IEBrowserKeyWord = {"MSIE", "Trident", "Edge"};
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWord) {
            if (userAgent.contains(keyWord)) {
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }

    //    @RequestMapping("upload_view")
    public String gotoUploadView() {


        return "upload";
    }

}
