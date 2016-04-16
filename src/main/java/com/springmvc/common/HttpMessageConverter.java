package com.springmvc.common;

import com.springmvc.vo.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * Created by ajshu on 2016/4/16.
 * 自定义数据转换类
 * 需要在MyMvcConfig里配置：1.重写configMessageConverters;重写extendsMessageConverters
 * 两者区别是前者覆盖自带的消息转换类，后者不覆盖
 */
public class HttpMessageConverter extends AbstractHttpMessageConverter<User>{

    /*处理请求数据*/
    @Override
    protected User readInternal(Class<? extends User> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }
    /*如何输出到response*/
    @Override
    protected void writeInternal(User t, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }



    @Override
    protected boolean supports(Class<?> aClass) {
        return false;
    }
}
