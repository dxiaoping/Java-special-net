package com.ccsu.javalearn.vo;

import com.ccsu.jsn.pojo.User;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-23 17:13
 */
public class RefreshVo {
    private User user;
    private ContentVo contentVo;

    public RefreshVo() {
    }

    public RefreshVo(User user, ContentVo contentVo) {
        this.user = user;
        this.contentVo = contentVo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContentVo getContentVo() {
        return contentVo;
    }

    public void setContentVo(ContentVo contentVo) {
        this.contentVo = contentVo;
    }
}
