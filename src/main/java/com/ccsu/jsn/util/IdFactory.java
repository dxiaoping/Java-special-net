package com.ccsu.jsn.util;

import com.ccsu.jsn.dao.EnclosuresMapper;
import com.ccsu.jsn.dao.MenuMapper;
import com.ccsu.jsn.pojo.Enclosures;
import com.ccsu.jsn.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-17 17:26
 */
@Component
public class IdFactory {

    @Autowired
    private EnclosuresMapper enclosuresMapper;
    private Random random = new Random();

    public long createEnclo() {
        //1000001~9999999
        long max = 9999999;
        long min = 1000001;
        long id = min + (((long) (random.nextDouble() * (max - min))));
//        long id = random.nextLong(max - min + 1) + min;

        System.out.println("正在创建EncloId");
        System.out.println(enclosuresMapper);
        System.out.println(enclosuresMapper.getEnclosuresListByContentId(100001));
        System.out.println(enclosuresMapper.selectById(1007001));
        while (enclosuresMapper.selectById(id) != null) {
            System.out.println(enclosuresMapper.selectById(id));
            id = getRandomId(min, max);
        }
        return id;
    }

    public long createContent() {
        //1000001~9999999
        long max = 999999;
        long min = 100001;
        long id = getRandomId(min, max);
//        long id = random.nextLong(max - min + 1) + min;


        while (enclosuresMapper.selectById(id) != null) {
            System.out.println(enclosuresMapper.selectById(id));
            id = getRandomId(min, max);
        }
        return id;
    }

    private long getRandomId(long min, long max) {
        return min + (((long) (random.nextDouble() * (max - min))));
    }
}