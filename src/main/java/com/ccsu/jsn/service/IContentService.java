package com.ccsu.jsn.service;

import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.pojo.Content;
import com.ccsu.jsn.pojo.User;
import org.springframework.web.multipart.MultipartFile;

public interface IContentService {
    Result upload(MultipartFile file, String path,long id,long userId);
    Result uploadEnclosure(MultipartFile file,long contentId,String path,long userId);
    Result getContent(long menuId, User user);
    Content getContent(long contentId);
}
