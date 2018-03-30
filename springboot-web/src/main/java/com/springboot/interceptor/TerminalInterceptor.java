package com.springboot.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TerminalInterceptor extends HandlerInterceptorAdapter {

    public TerminalInterceptor(){
        System.out.println("init TerminalInterceptor");
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("TerminalInterceptor preHandle enter");
        return true;
    }
}
