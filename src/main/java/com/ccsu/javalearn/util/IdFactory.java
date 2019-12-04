package com.ccsu.javalearn.util;

//import com.ccsu.javalearn.dao.ContentMapper;
//import com.ccsu.javalearn.dao.EnclosuresMapper;
import com.ccsu.javalearn.dao.ExampleMapper;
import com.ccsu.javalearn.dao.MenuMapper;
import com.ccsu.javalearn.dao.ResourceMapper;
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
    private ResourceMapper resourceMapper;
//
    @Autowired
    private ExampleMapper exampleMapper;

    @Autowired
    private MenuMapper menuMapper;
    private Random random = new Random();

    public long createResource() {
        //1000001~9999999
        long max = 9999999;
        long min = 1000001;
        long id = getRandomId(min, max);
//        long id = random.nextLong(max - min + 1) + min;
        while (resourceMapper.selectById(id) != null) {
            id = getRandomId(min, max);
        }
        return id;
    }

    public long createExample() {
        //1000001~9999999
        long max = 999999;
        long min = 100001;
        long id = getRandomId(min, max);

        while (exampleMapper.selectById(id) != null) {
            id = getRandomId(min, max);
        }
        return id;
    }

    public long createIIMenuId() {
        //1000001~9999999
        long max = 9999;
        long min = 1000;
        long id = getRandomId(min, max);
        while (menuMapper.selectById(id) != null) {
            id = getRandomId(min, max);
        }
        return id;
    }

    private long getRandomId(long min, long max) {
        return min + (((long) (random.nextDouble() * (max - min))));
    }
}