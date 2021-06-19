package com.example.tokendemo2.interceptor;

import com.example.tokendemo2.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
//        String token = request.getHeader("token");
        String cookie = request.getHeader("Cookie");

        if (cookie ==null) {
            response.getWriter().print("用户未登录，请登录后操作！");
//            System.out.println(token);
            return false;
        }else{

            String token=cookie.substring(6);
            Object loginStatus = redisService.get(token);
            if( Objects.isNull(loginStatus)){
                response.getWriter().print("登录超时，请重新登录!");
                return false;
            }

        }
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }
}
