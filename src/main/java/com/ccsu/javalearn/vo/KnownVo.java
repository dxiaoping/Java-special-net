package com.ccsu.javalearn.vo;

import com.ccsu.javalearn.pojo.Known;
import com.ccsu.javalearn.pojo.Resource;

import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-12-03 22:00
 */
public class KnownVo {
    private List<Resource> resources;
    private Known known;

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public Known getKnown() {
        return known;
    }

    public void setKnown(Known known) {
        this.known = known;
    }
}
