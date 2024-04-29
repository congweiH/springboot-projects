package com.congwei.checkcode.controller;

import com.congwei.checkcode.CreateImageCode;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CheckCodeController {

    @GetMapping("/code")
    public void getCode(HttpServletResponse response, HttpSession httpSession) throws IOException {
        CreateImageCode createImageCode = new CreateImageCode();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        httpSession.setAttribute("check-code", createImageCode.getCode());
        createImageCode.write(response.getOutputStream());
    }

    @PostMapping("/vertify")
    public void vertify(HttpSession httpSession, @RequestBody Vertify vertify) throws Exception {
        String expected = (String) httpSession.getAttribute("check-code");

        System.out.println(expected);
        System.out.println(vertify.getCode());

        if (!expected.equals(vertify.getCode())) {
            throw new Exception("wrong code");
        }

        System.out.println("correct code");
    }

}
