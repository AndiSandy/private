package com.springmvc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by hasee on 2016/4/10.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.springmvc.controller")
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    /*spring视图解析路径的配置*/
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;

    }
    /*重写此方法设置访问静态资源路径*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }

    /*重写此方法注册拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        //registry.addInterceptor(demoInterceptor());
    }
    /*重写此方法配置页面跳转*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }
    /*配置上传文件的支持*/
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

//    public DruidDataSource druidDataSource(){
//        DruidDataSource druidDataSource  = new DruidDataSource();
//        druidDataSource.setConnectProperties();
//        return  druidDataSource;
//    }

    /*@Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();//注册器类，继承HandlerInterceptorAdapter,作用：实现对每个请求处理前后进行相关的业务处理
    }*/
}
