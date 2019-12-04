package com.ccsu.javalearn.pojo;

import java.util.Date;

/**
 * @Description 相关资源对象
 * @auther DuanXiaoping
 * @create 2019-11-30 16:03
 */
public class Resource {
   /** 资源id*/
   private long id;
   /** 资源名*/
   private String name;
   /** 依附目录Id*/
   private long menuId;
   /** 资源描述*/
   private String description;
   /** 资源路径*/
   private String url;
   /** 是否外链*/
   private int resType;
   /** 资源下载次数*/
   private int downloadCount;
   /** 资源上传时间*/
   private Date createTime;

   private long userId;
    public Resource() {
    }

    public Resource(long menuId,long userId, String description, String url,int resType) {
        this.menuId = menuId;
        this.userId = userId;
        this.description = description;
        this.url = url;
        this.resType = resType;
    }

    public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public long getMenuId() {
      return menuId;
   }

   public void setMenuId(long menuId) {
      this.menuId = menuId;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public int getResType() {
      return resType;
   }

   public void setResType(int resType) {
      this.resType = resType;
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
      return "Resource{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", menuId=" + menuId +
              ", description='" + description + '\'' +
              ", url='" + url + '\'' +
              ", resType=" + resType +
              ", downloadCount=" + downloadCount +
              ", createTime=" + createTime +
              '}';
   }
}
