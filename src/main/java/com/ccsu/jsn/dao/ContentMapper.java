package com.ccsu.jsn.dao;

import com.ccsu.jsn.pojo.Content;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentMapper {
    Content selectByParentId(long parentId);
    int insert(Content content);
    int update(Content content);
}
