package com.ccsu.jsn.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-10-31 21:49
 */
public interface IFileService {
    String upload(MultipartFile file,String path);
}
