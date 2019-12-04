package com.ccsu.javalearn.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URLEncoder;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-12-01 12:11
 */
@Controller
@RequestMapping("file")
public class FileController {
    @RequestMapping("download")

    public ResponseEntity<byte[]> filedownload(HttpServletRequest request,
                                               String url, String fileName) throws Exception {
        String path = request.getServletContext().getRealPath("/");
        File file = new File(path + url);
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

}
