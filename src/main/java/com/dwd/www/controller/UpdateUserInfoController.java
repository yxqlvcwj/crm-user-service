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
@Api(value = "v1",description = "修改用户信息接口")
@RestController
public class UpdateUserInfoController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CookieCheck cookieCheck;

    @ApiOperation(value = "修改用户信息",httpMethod = "POST")
    @RequestMapping(value = "/update-userInfo",method = RequestMethod.POST)
    public Boolean updateUserInfoById(HttpServletRequest request,@RequestBody User user){
        if(cookieCheck.verifyCookies(request)!=true){
            log.info("cookie校验失败");
            return false;
        }
        int result = 0;
        if(user==null){
            return false;
        }else {
            if (cookieCheck.verifyCookies(request) == true) {
                log.info("cookie校验通过");
                result = userMapper.updateUserInfo(user);
            }else {
                log.info("cookie校验失败");
                return false;
            }
        }
        if (result > 0 ){
            return true;
        }else {
            return false;
        }
    }
}
