package com.dwd.www.controller;

import com.dwd.www.check.CookieCheck;
import com.dwd.www.db.domain.User;
import com.dwd.www.db.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Chen WenJie on 2018/12/15.
 */
@Slf4j
@Api(value = "v1",description = "获取用户列表接口")
@RestController
public class GetUserListController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CookieCheck cookieCheck;

    @ApiOperation(value = "获取用户列表",httpMethod = "GET")
    @RequestMapping(value = "/get-user-list",method = RequestMethod.GET)
    public List<User> getUserList(HttpServletRequest request){
        if(cookieCheck.verifyCookies(request)!=true){
            log.info("cookie校验失败");
            return null;
        }
        List<User> list =userMapper.getUserList();
        if (list.size()>0){
            return list;
        }else {
            return null;
        }
    }

}
