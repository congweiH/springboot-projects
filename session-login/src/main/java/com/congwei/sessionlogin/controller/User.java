package com.congwei.sessionlogin.controller;

import lombok.Getter;

@Getter
public class User {
    private String username;
    // 真实场景是加密类型，而不是简单的String类型
    private String password;
}
