package com.ccsu.javalearn.common;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-10-29 9:51
 */
public class Const {

    public static final String CURRENT_USER="CURRENT_USER";
    public static final String CURRENT_CONTENT="CURRENT_CONTENT";
    public static final long NEW_EXAMPLE=9999999999l;

    public interface Role{
        int ROLE_STUDENT = 2; //学生用户
        int ROLE_TEACHER = 1; //教师用户
        int ROLE_ADMIN = 0;//管理员
    }

    public interface ResourceType{
        int KNOWN_IMG = 3; //知识模块图片
        int KNOWN_DOC = 2; //知识模块文档
        int RESOURCE_ORDINARY = 1;//相关资源模块普通资源
    }


}
