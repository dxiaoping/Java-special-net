package com.ccsu.jsn.service.impl;

import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.dao.UserMapper;
import com.ccsu.jsn.pojo.User;
import com.ccsu.jsn.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-05 16:28
 */
@Service
public class UserServiceImpl implements IUserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private UserMapper userMapper;

    User user = null;

    @Override
    public Result login(long phone, String password) {
        user = userMapper.selectByPhone(phone);
        if (user == null) {
            return Result.error("用户不存在");
        }

        if (!password.equals(user.getPassword())){
            return Result.error("用户密码错误");
        }

        logger.info("用户:{}登录成功 时间：{}",user.getName(),dateFormat.format(new Date()));
        return Result.success(user);
    }

    @Override
    public Result register(User user) {

        Result paramCheck = this.checkParameter(user);
        if (!paramCheck.getMsg().equals("success")){
            return paramCheck;
        }

        int insertResult = userMapper.insert(user);
        if (insertResult == 0){
            return Result.error("注册失败");
        }
        return Result.success(0);
    }

    private Result checkParameter(User user){
        if (userMapper.checkPhone(user.getPhone())==0){
            return Result.error("用户已经存在");
        }
        if (user.getPassword()==null || user.getPassword().equals("")){
            return Result.error("密码为空");
        }
        return Result.success(0);
    }
}
