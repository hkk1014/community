package com.hk.community.community.controller;

import com.hk.community.community.mapper.UserMapper;
import com.hk.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 作者: 何康先生
 * 时间: 2020-03-14 10:44
 * 描述:
 **/
@Controller
public class IndexController {
    @Autowired
    private UserMapper  userMapper;
    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
        for (Cookie cookie:cookies){
        if("token".equals(cookie.getName())){
            String token = cookie.getValue();
            User user = userMapper.findByToken(token);
            if(user !=null){
                request.getSession().setAttribute("user",user);
            }
            break;
        }
        }
        }
        return "index";
    }
}