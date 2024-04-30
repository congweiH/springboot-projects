package com.congwei.sessionlogin.interceptor;

import com.congwei.sessionlogin.controller.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class AccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器");
        User user = (User) request.getSession().getAttribute("user");
        log.info("user: {}", user);
        // 没有登录，则拦截
        if (user == null) {
            log.error("未登录");
            throw new Exception("未登录");
        }
        return true;
    }
}
