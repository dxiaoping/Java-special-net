package com.ccsu.jsn.dao;

import com.ccsu.jsn.pojo.Content;
import com.ccsu.jsn.pojo.Menu;
import com.ccsu.jsn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-02 13:42
 */
public class daoTest {


    String resource = "config/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    public daoTest() throws IOException {
    }

    @Test
    public void test() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);


        Menu menu = menuMapper.selectByName("链表");
        System.out.println(menu);

        List<Menu> menuList = menuMapper.selectMenusByParentId(101);
        System.out.println(menuList);

        User user1 = new User();
        user1.setPhone(15574829924l);
        user1.setName("xxxxxx");
        user1.setPassword("789456132");
        user1.setRole(1);
        System.out.println(userMapper.updateUserByPhone(user1));
//        user1.setCreateTime(DAteSystem.currentTimeMillis());
        User user = userMapper.selectByPhone(15574829924l);
//        User user = userDao.selectByPhone(15574902295l);
        System.out.println(user);
        sqlSession.commit();
    }

    @Test
    public void testContent() throws IOException {

        ContentMapper contentMapper = sqlSession.getMapper(ContentMapper.class);
        Content content = new Content();
        content.setId(100001);
        content.setParentId(1001);
        content.setUserId(15574902295l);
        content.setText("textUrl");
        content.setImg("imgUrl");
        content.setVideo("videoUrl");
        content.setCode("codeUrl");

        System.out.println(contentMapper.update(content));
//        sqlSession.commit();
//        System.out.println(contentMapper.selectByParentId(1001));

    }
}
