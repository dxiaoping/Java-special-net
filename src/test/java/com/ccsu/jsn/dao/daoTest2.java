package com.ccsu.jsn.dao;

import com.ccsu.javalearn.common.Const;
import com.ccsu.javalearn.dao.ExampleMapper;
import com.ccsu.javalearn.dao.KnownMapper;
import com.ccsu.javalearn.dao.ResourceMapper;
import com.ccsu.javalearn.pojo.Example;
import com.ccsu.javalearn.pojo.Known;
import com.ccsu.javalearn.pojo.Menu;
import com.ccsu.javalearn.dao.MenuMapper;
import com.ccsu.javalearn.pojo.Resource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-30 15:13
 */
public class daoTest2 {
    String resource = "config/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    public daoTest2() throws IOException {
    }

    @Test
    public void menuTest() {
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);

        Menu menu = new Menu(10099, 106, "测试", 2);

        System.out.println(menu);
        System.out.println(menuMapper.insert(menu));
        sqlSession.commit();
    }

    @Test
    public void exampleTest() {
        ExampleMapper exampleMapper = sqlSession.getMapper(ExampleMapper.class);

        Example example = new Example(10000l,5824l,"title","content","interpret", "runResult");
example.setAttachment("5465");
        System.out.println(example);
        System.out.println(exampleMapper.insert(example));
//        sqlSession.commit();
    }

    @Test
    public void resourceTest() {
        ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);

//        Resource resource = new Resource(6665,151515l, "qqqqqqqq", "qqqqqqqqqq", Const.ResourceType.RESOURCE_ORDINARY);
//        resource.setName("qqqqqq");
//        resource.setCreateTime(new Date());
//        System.out.println(resource);
//
//        System.out.println(resourceMapper.insert(resource));
        System.out.println(resourceMapper.selectResourceByMenuId(5092));
//        sqlSession.commit();
    }

    @Test
    public void knownTest() {
        KnownMapper knownMapper = sqlSession.getMapper(KnownMapper.class);

        Known known1 = new Known(12321l,465454l,15555555l);
        known1.setName("sfadsfea");
        known1.setCreateTime(new Date());
        System.out.println(known1);
        System.out.println(knownMapper.insert(known1));
//        sqlSession.commit();
    }
}
