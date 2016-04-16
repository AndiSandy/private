package com.springmvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ajshu on 2016/4/16.
 * 控制器的全局配置
 */
@ControllerAdvice
public class ExceptionHandlerAdivce {

    /*定义全局异常处理页面*/
    @ExceptionHandler
    public ModelAndView exception(Exception e , WebRequest request){
        ModelAndView modelAndView = new ModelAndView("public/error");
        modelAndView.addObject("errorMessage",e.getMessage());
        return modelAndView;
    }
    //@ModelAttribute

}
