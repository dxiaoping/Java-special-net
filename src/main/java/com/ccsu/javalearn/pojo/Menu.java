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

    public Menu() {
    }

    public Menu(long id, long parentId, String name, int rank) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.rank = rank;
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
