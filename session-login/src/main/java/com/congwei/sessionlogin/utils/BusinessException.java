package com.congwei.sessionlogin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BusinessException extends RuntimeException{

    private Integer code;

    private String message;

}
