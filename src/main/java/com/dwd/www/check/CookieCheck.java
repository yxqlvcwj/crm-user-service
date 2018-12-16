package com.dwd.www.check;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * cookie校验接口
 * Created by Chen WenJie on 2018/12/15.
 */
@Component
public class CookieCheck {

    public Boolean verifyCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)) {
            return false;
        }
            for(Cookie cookie : cookies) {
                if ("isLogin".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                    return true;
                }
             }
       return false;
    }
}
