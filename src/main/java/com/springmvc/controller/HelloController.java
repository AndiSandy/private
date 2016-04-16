package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hasee on 2016/4/10.
 */
@Controller
public class HelloController {

    @RequestMapping("/index")
    @ResponseBody
    public String hello(){
        return "index";
    }
}
