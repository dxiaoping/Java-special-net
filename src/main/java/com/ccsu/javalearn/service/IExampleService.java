package com.ccsu.javalearn.service;

import com.ccsu.javalearn.pojo.Example;
import com.ccsu.javalearn.common.Result;
import org.springframework.web.multipart.MultipartFile;

public interface IExampleService {
    public Result saveExample(Example example, long menuIId, MultipartFile file,String path);
    public Example getExample(long menuId);
    public Example getExampleById(long id);

}
