package com.ccsu.jsn.vo;

import com.ccsu.jsn.pojo.Content;
import com.ccsu.jsn.pojo.Enclosures;

import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-16 20:57
 */
public class ContentVo{

    private Content content;
    private List<Enclosures> enclosuresList;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<Enclosures> getEnclosuresList() {
        return enclosuresList;
    }

    public void setEnclosuresList(List<Enclosures> enclosuresList) {
        this.enclosuresList = enclosuresList;
    }
}
