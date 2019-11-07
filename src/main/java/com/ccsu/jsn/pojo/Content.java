package com.ccsu.jsn.pojo;

import java.util.Date;

/**
 * @Description 映射知识点内容，目录的三级索引
 * @auther DuanXiaoping
 * @create 2019-10-29 16:52
 */
public class Content {
    private long id;
    private long parentId;
    private long userId;
    private String text;//文本或文本
    private String img;//图片
    private String video;//视频
    private String code;//代码
    private Date createTime;
    private Date updateTime;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public void set(String key, String value) {
        switch (key){
            case "img":
                this.img=value;
                break;
            case "video":
                this.video=value;
                break;
            case "code":
                this.code=value;
                break;
            case "text":
                this.text=value;
                break;
        }
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", img='" + img + '\'' +
                ", video='" + video + '\'' +
                ", code='" + code + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
