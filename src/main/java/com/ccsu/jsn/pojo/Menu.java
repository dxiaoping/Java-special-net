package com.ccsu.jsn.pojo;

import java.util.Date;

/**
 * @Description 映射每一个知识点，目录的二级索引
 * @auther DuanXiaoping
 * @create 2019-10-29 16:50
 */
public class Menu {
    private long id;
    private long parentId;
    private String name;
    private String href;
    private Date createTime;
    private Date updateTime;

    public Menu() {
    }

    public Menu(long id, long parentId, String name, String href) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.href = href;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                '}';
    }
}
