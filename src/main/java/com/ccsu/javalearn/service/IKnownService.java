package com.ccsu.javalearn.service;

import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.pojo.Known;
import com.ccsu.javalearn.pojo.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-12-02 21:16
 */
public interface IKnownService {
    Result saveKnown(Known known, long menuIId, MultipartFile fileDoc,MultipartFile fileImg, String path);
    Result getKnown(long menuId, User user);
    Known getKnownById(long id);
}
