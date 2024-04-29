package com.congwei.sessionlogin.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(HttpSession httpSession, @RequestBody User user) throws Exception {
        // 真实场景是密码从数据库查询，而不是简单的 1234
        if (!"1234".equals(user.getPassword())) {
            throw new Exception("用户名或密码不正确");
        }
        // 登录成功，将信息加入 session 中
        httpSession.setAttribute("user", user);
    }

    /**
     * 必须登录才能访问
     */
    @GetMapping("/hello")
    public String hello(HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            // 如果session中拿不到用户信息，则表示没有登录
            throw new Exception("未登录");
        }

        return "hello";
    }

}
