package com.ccsu.javalearn.service;

import com.ccsu.javalearn.common.Result;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    public String upload(MultipartFile file,String path);
}
