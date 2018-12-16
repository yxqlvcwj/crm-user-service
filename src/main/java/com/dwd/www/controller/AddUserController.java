package com.dwd.www.controller;

import com.dwd.www.check.CookieCheck;
import com.dwd.www.db.domain.User;
import com.dwd.www.db.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Chen WenJie on 2018/12/15.
 */
@Slf4j
@Api(value = "v1",description = "用户添加接口")
@RestController
public class AddUserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CookieCheck cookieCheck;

    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/add/user",method = RequestMethod.POST)
    public Boolean addUser(HttpServletRequest request, @RequestBody User user){
        if(user==null){
            log.info("输入用户信息为空，添加用户失败");
           return null;
        }
        if(cookieCheck.verifyCookies(request)!=true){
            log.info("cookie校验失败");
            return false;
        }
        int result;
        log.info("输入用户信息："+user);
        try {
            result = userMapper.addUser(user);
            if(result>0){
                log.info("输入用户添加成功");
                return true;
            }else {
                log.info("输入用户添加失败");
                return false;
            }
        } catch (Exception e) {
            log.info("输入用户添加失败，请检查数据库");
            return false;
        }
    }
}
