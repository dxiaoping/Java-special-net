package com.ccsu.javalearn.pojo;

import java.util.Date;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-12-02 20:36
 */
public class Known {
    /**知识点Id*/private long id;
    /**目录id*/private long menuId;
    /**用户手机*/private long userId;
    /**知识名*/private String name;
    /**知识讲解*/private String text;
    /**文档地址*/private String attachment;
    /**浏览次数*/private int scanCount;
    /**下载次数*/private int downloadCount;
    /**创建时间*/private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public int getScanCount() {
        return scanCount;
    }

    public void setScanCount(int scanCount) {
        this.scanCount = scanCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Known() {
    }

    public Known(long id, long menuId, long userId,String text) {
        this.id = id;
        this.menuId = menuId;
        this.userId = userId;
        this.text = text;
    }

    public Known(long id, long menuId, long userId) {
        this.id = id;
        this.menuId = menuId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "konwn{" +
                "id=" + id +
                ", menuId=" + menuId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", attachment='" + attachment + '\'' +
                ", scanCount=" + scanCount +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
