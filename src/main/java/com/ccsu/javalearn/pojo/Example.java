package com.ccsu.javalearn.pojo;

import java.util.Date;

/**
 * @Description 实例教程对象
 * @auther DuanXiaoping
 * @create 2019-11-30 16:02
 */
public class Example {
    /** 实例Id*/
    private long id;
    /** 依附的目录Id*/
    private long menuId;
    /** 实例题目*/
    private String title;
    /** 实例内容*/
    private String content;
    /** 实例讲解*/
    private String interpret;
    /** 运行结果*/
    private String runResult;
    /** 代码附件*/
    private String attachment;
    /** 浏览次数*/
    private int scanCount;
    /** 下载次数*/
    private int downloadCount;
    /** 创建时间*/
    private Date createTime;

    private long userId;

    public Example() {
    }

    public Example(long id,long userId, String title, String content, String interpret, String runResult) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.interpret = interpret;
        this.runResult = runResult;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInterpret() {
        return interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }

    public String getRunResult() {
        return runResult;
    }

    public void setRunResult(String runResult) {
        this.runResult = runResult;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Example{" +
                "id=" + id +
                ", menuId=" + menuId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", interpret='" + interpret + '\'' +
                ", runResult='" + runResult + '\'' +
                ", attachment='" + attachment + '\'' +
                ", scanCount=" + scanCount +
                ", downloadCount=" + downloadCount +
                ", createTime=" + createTime +
                '}';
    }
}
