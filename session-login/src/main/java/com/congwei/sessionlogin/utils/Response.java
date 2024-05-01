package com.congwei.sessionlogin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> r = new Response<>();
        r.code = 200;
        r.data = data;
        return r;
    }

    public static <T> Response<T> fail(int code, String message) {
        Response<T> r = new Response<>();
        r.code = code;
        r.message = message;
        return r;
    }

}
