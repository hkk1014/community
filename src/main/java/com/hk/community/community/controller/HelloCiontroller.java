package com.hk.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 作者: 何康先生
 * 时间: 2020-03-14 10:44
 * 描述:
 **/
@Controller
public class HelloCiontroller {
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name")String name, Model model){
        model.addAttribute("name",name);
        return  "hello";
    }
}
