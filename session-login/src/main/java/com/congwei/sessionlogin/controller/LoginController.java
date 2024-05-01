package com.congwei.sessionlogin.controller;

import com.congwei.sessionlogin.utils.Response;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @PostMapping("/login")
    public Response<User> login(HttpSession session, @RequestBody User user) {
        if (user.getUsername().equals("admin") && user.getPassword().equals("1234")) {
            session.setAttribute("user", user);
            return Response.success(user);
        }
        return Response.fail(401, "用户名或者密码错误");
    }

    @DeleteMapping("/logout")
    public Response<Void> logout(HttpSession session) {
        session.removeAttribute("user");
        return Response.success(null);
    }

    /**
     * 必须登录才能访问
     */
    @GetMapping("/hello")
    public Response<String> hello() {
        return Response.success("hello");
    }

}
