package com.example.tokendemo2.controller;

import com.example.tokendemo2.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping({"/", ""})
    public String login(String username, String password, HttpServletResponse response) {
        return loginService.login(username, password,response);
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {

        return loginService.logout(request);
    }
}