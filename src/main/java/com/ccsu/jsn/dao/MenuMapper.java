package com.ccsu.jsn.dao;

import com.ccsu.jsn.pojo.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-02 16:35
 */
@Repository
public interface MenuMapper {
    Menu selectById(long id);
    Menu selectByName(String name);
    List<Menu> selectMenusByParentId(long parentId);
}
