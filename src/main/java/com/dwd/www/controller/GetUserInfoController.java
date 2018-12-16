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
 * Created by Chen WenJie on 2018/12/15.
 */
@Slf4j
@RestController
@Api(value = "v1",description = "获取用户信息接口")
public class GetUserInfoController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CookieCheck cookieCheck;

    @ApiOperation(value = "获取用户信息接口", httpMethod = "GET")
    @RequestMapping(value = "/get-user-info", method = RequestMethod.GET)
    public User getUserInfo(HttpServletRequest request, @RequestParam("id") String id) {
        User user = userMapper.findUserInfoById(id);
        if(cookieCheck.verifyCookies(request)!=true){
            log.info("cookie校验失败");
            return null;
        }
        if (user != null) {
            log.info("用户信息为:" + user);
            return user;
        } else {
            log.info("用户信息未查询到");
            return null;
        }
    }
}