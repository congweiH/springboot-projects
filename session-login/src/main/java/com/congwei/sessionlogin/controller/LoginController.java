package com.congwei.sessionlogin.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class LoginController {

    @PostMapping("/login")
    public void login(HttpSession httpSession, @RequestBody User user) throws Exception {
        // 真实场景是密码从数据库查询，而不是简单的 1234
        log.info("login");
        if (!"1234".equals(user.getPassword())) {
            throw new Exception("用户名或密码不正确");
        }
        // 登录成功，将信息加入 session 中
        httpSession.setAttribute("user", user);
    }

    @DeleteMapping("/logout")
    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
    }

    /**
     * 必须登录才能访问
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
