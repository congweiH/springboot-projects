package com.congwei.sessionlogin.advice;

import com.congwei.sessionlogin.utils.BusinessException;
import com.congwei.sessionlogin.utils.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Response<Object> exceptionHandler(BusinessException e) {
        return Response.fail(e.getCode(), e.getMessage());
    }

}
