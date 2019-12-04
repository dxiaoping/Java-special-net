package com.ccsu.javalearn.service.impl;

import com.ccsu.javalearn.service.IFileService;
import com.ccsu.javalearn.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-30 21:03
 */
@Service
public class FileServiceImpl implements IFileService {
    Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String upload(MultipartFile file, String path) {

        String fileName = file.getOriginalFilename();

        String fileSuffix = fileName.substring(fileName.lastIndexOf('.') + 1);

        String uploadFileName = UUID.randomUUID().toString() + "." + fileSuffix;
        System.out.println("开始上传");
        logger.info("开始上传文件，上传的文件名：{}，上传的路径：{}，新文件名：{} ", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path, uploadFileName);

        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return "";
        }
        return "/upload/"+uploadFileName;
    }
}
