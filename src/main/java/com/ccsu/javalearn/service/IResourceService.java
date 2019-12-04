package com.ccsu.javalearn.service;

import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.pojo.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IResourceService {
    Result getResourceList(long menuId);
    int addResource(MultipartFile file, Resource resource);
}
