package com.example.tokendemo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private RedisService redisService;

    public String login(String username, String password,HttpServletResponse response) {


        if (Objects.equals("zyx", username) && Objects.equals("123", password)) {
            String token = UUID.randomUUID().toString();
            redisService.set(token, username);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "用户：" + username + "登录成功，token是：" + token;
        } else {

            return "用户名或密码错误，登录失败！";
        }
    }

//    @UserLoginToken
//    @ApiOperation(value = "登陆", notes = "登陆")
//    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
//    public Object login(User user, HttpServletResponse response) {
//        JSONObject jsonObject = new JSONObject();
//        User userForBase = new User();
//        userForBase.setId(userService.findByUsername(user.getUsername()).getId());
//        userForBase.setUsername(userService.findByUsername(user.getUsername()).getUsername());
//        userForBase.setPassword(userService.findByUsername(user.getUsername()).getPassword());
//        if (!userForBase.getPassword().equals(user.getPassword())) {
//            jsonObject.put("message", "登录失败,密码错误");
//            return jsonObject;
//        } else {
//            String token = tokenService.getToken(userForBase);
//            jsonObject.put("token", token);
//
//            Cookie cookie = new Cookie("token", token);
//            cookie.setPath("/");
//            response.addCookie(cookie);
//            return jsonObject;
//
//        }
//    }

    public String logout(HttpServletRequest request) {
        String cookie = request.getHeader("Cookie");
        String token=cookie.substring(6);
        Boolean delete = redisService.delete(token);
        if (!delete) {
            return "注销失败，请检查是否登录！";
        }
        return "注销成功！";
    }
}
