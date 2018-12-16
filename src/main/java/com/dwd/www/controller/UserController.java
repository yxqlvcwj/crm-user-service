package com.dwd.www.controller;

import com.dwd.www.check.CookieCheck;
import com.dwd.www.db.domain.User;
import com.dwd.www.db.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试接口
 * @Api Swagger2 使用注解
 * Created by Chen WenJie on 2018/12/15.
 */
@Slf4j
@RestController
@Api(value = "v1",description = "测试接口")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CookieCheck cookieCheck;

    @ApiOperation(value="post方法获取用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/get-userInfo-for-post" ,method = RequestMethod.POST)
    public User getUserInfoForPost(HttpServletRequest request, @RequestBody String id){
        if(cookieCheck.verifyCookies(request)!=true){
            log.info("cookie校验失败");
            return null;
        }
       User user =  userMapper.findUserById(id);
       if(user!=null){
           log.info("用户信息为:"+user);
           return user;
       }else{
           return null;
       }
    }

    @ApiOperation(value="get方法获取用户信息接口",httpMethod = "GET")
    @RequestMapping(value = "/get-userInfo-for-get",method = RequestMethod.GET)
    public User getUserInfoForGet(HttpServletRequest request, @RequestParam String id){
        if(cookieCheck.verifyCookies(request)!=true){
            log.info("cookie校验失败");
            return null;
        }
        User user =  userMapper.findUserById(id);
        if(user!=null){
            log.info("用户信息为:"+user);
            return user;
        }else{
            return null;
        }
    }
}
