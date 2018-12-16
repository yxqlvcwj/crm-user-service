package com.dwd.www.controller;

import com.dwd.www.db.domain.User;
import com.dwd.www.db.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Chen WenJie on 2018/12/15.
 */
@Slf4j
@RestController
@Api(value = "v1",description = "测试接口")
public class UserLoginController {
    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "登录校验接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response , @RequestBody User user){
        int count=0;
        if (user.getUserName() !=null&&user.getPassword()!=null &&!"".equals(user.getUserName())&&!"".equals(user.getPassword())){
            count = userMapper.UserLogin(user.getUserName(),user.getPassword());
        }else{
            return false;
        }
        if (count > 0){
            return true;
        }else {
            return false;
        }

    }

    @ApiOperation(value = "获取用户信息",httpMethod = "POST")
    @RequestMapping(value = "/login/getUserInfo",method = RequestMethod.POST)
    public User getUserInfo(HttpServletResponse response , @RequestBody User user){
        User userInfo;
        if (user.getUserName() !=null&&user.getPassword()!=null &&!"".equals(user.getUserName())&&!"".equals(user.getPassword())){
            userInfo = userMapper.getUserInfo(user.getUserName(),user.getPassword());
        }else{
            return null;
        }
        if (userInfo!=null){
            Cookie cookie = new Cookie("isLogin","true");
            response.addCookie(cookie);
        }else {
            return null;
        }
        return userInfo;
    }

}
