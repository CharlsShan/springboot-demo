package com.springboot.aop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.springboot.domain.Result;

@ControllerAdvice(basePackages = "com.springboot")
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private static final Logger logger = LoggerFactory.getLogger(MyResponseBodyAdvice.class);
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(obj instanceof Result){
            logger.info("接口对应方法:{},返回结果:{}",methodParameter.getMethod().getName(),obj);
        }
        return obj;
    }
}
