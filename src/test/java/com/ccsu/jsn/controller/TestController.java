package com.ccsu.jsn.controller;


import com.ccsu.jsn.dao.UserMapper;
import com.ccsu.jsn.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;


/**
 * *测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
// 只是测试数据层只需要指定mybatis配置文件即可
//@ContextConfiguration({ "classpath:config/spring/spring-mybatis.xml"})
// 业务层测试指定配置文件
//@ContextConfiguration({ "classpath:config/spring/spring-mybatis.xml", "classpath:config/spring/springmvc.xml" })
// 可以使用统配符号 *
@ContextConfiguration({ "classpath:config/*.xml"})
// 事务
//@Transactional(transactionManager = "transactionManager")
// 测试结束后事物是否回滚;默认true;
//@Rollback(value = false)
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void helloControllerTest() throws Exception {
        log.info("==========访问http://localhost:8080/ssm/hello/index=======================");
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/hello/index"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(contentAsString);
    }


    @Resource
    private UserMapper userMapper;
    @Test
    public void userMapperSelectByIdTest() {
        log.info("==========测试数据层使用=======================");
        User user = userMapper.selectByPhone(15574902295l);
        log.info("user={}",user);
    }
}