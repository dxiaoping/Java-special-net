package com.ccsu.javalearn.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-02 21:03
 */
public class MD5Util {
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    private static final String salt = "HelloWorld";
    public static String inputPsd2formPsd(String inputPsd){
        String str = salt.charAt(1) + salt.charAt(4) + inputPsd +salt.charAt(3) +salt.charAt(7);
        return md5(str);
    }

    @Test
    public void test(){
        String formPsd = md5("123456");
        System.out.println(formPsd);
        System.out.println(md5(formPsd));
    }
}
