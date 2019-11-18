package com.ccsu.jsn.dao;

import com.ccsu.jsn.pojo.Enclosures;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnclosuresMapper {
    List<Enclosures> getEnclosuresListByContentId(long contentId);
    int insert(Enclosures enclosures);
    int selectById(long id);
}
