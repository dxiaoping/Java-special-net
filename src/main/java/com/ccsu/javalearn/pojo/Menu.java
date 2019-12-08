package com.ccsu.javalearn.pojo;

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
    private int rank;
    private String description;
    private Date createTime;
    private long userId;
    private String creator;


    public Menu() {
    }

    public Menu(long id, long parentId, String name, int rank) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.rank = rank;
    }

    public Menu(long id, long parentId, String name, String description, long userId, String creator) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                '}';
    }
}
