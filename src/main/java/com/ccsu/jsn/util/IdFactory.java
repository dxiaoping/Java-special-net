package com.ccsu.jsn.util;

import com.ccsu.jsn.dao.EnclosuresMapper;
import com.ccsu.jsn.dao.MenuMapper;
import com.ccsu.jsn.pojo.Enclosures;
import com.ccsu.jsn.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-17 17:26
 */
public class IdFactory {

    @Autowired
    private static EnclosuresMapper enclosuresMapper;
    private static Random random = new Random();

    public static long createEnclo() {
        //1000001~9999999
        long max = 9999999;
        long min = 1000001;
        long id = min + (((long) (random.nextDouble() * (max - min))));
//        long id = random.nextLong(max - min + 1) + min;

        if (enclosuresMapper.selectById(id) > 0) {

        }
        return id;
    }
    public static long createContent() {
        //1000001~9999999
        long max = 999999;
        long min = 100001;
        long id = min + (((long) (random.nextDouble() * (max - min))));
//        long id = random.nextLong(max - min + 1) + min;

        if (enclosuresMapper.selectById(id) > 0) {

        }
        return id;
    }
}