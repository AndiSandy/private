package com.springmvc.Interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ajshu on 2016/4/14.
 * 1、日志记录：记录请求信息的日志，以便进行信息监控、信息统计、计算PV（Page View）等。
 * 2、权限检查：如登录检测，进入处理器检测检测是否登录，如果没有直接返回到登录页面；
 * 3、性能监控：有时候系统在某段时间莫名其妙的慢，可以通过拦截器在进入处理器之前记录开始时间，在处理完后计算处理时间
 * 4、通用行为：读取cookie得到用户信息并将用户对象放入请求，从而方便后续流程使用
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

    /*请求发生前执行（返回true执行postHandle,返回false，跳过postHandle 直接执行afterCompletion）*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }
    /*请求完成后执行*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
    /*视图渲染后执行（最后执行）*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
