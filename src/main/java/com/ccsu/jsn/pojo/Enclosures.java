package com.ccsu.jsn.pojo;

import java.util.Date;

/**
 * @Description 附件的承载类，依附于内容Content
 * @auther DuanXiaoping
 * @create 2019-11-02 18:21
 */


public class Enclosures {
    private long id;
    private long contentId;
    private long userId;
    private String name;
    private String url;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Enclosures{" +
                "id=" + id +
                ", contentId=" + contentId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
