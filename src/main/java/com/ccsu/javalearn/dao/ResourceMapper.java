package com.ccsu.javalearn.dao;

import com.ccsu.javalearn.pojo.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResourceMapper {
    List<Resource> selectResourceByMenuId(long menuId);
//    List<Resource> selectImgByMenuId(long menuId);
    Resource selectById(long id);
    int insert(Resource resource);
    int update(Resource resource);
}
