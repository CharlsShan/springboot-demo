package com.springboot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
* @author shanchenyang
* @time 创建时间：2017年12月12日 下午8:26:56
* 
*/
@WebFilter(filterName="MyFilter",urlPatterns="/*")
public class MyFilter implements Filter{
	@Override
    public void init(FilterConfig arg0) throws ServletException {
		System.out.println("filter init!");
    }
	/**
	 * 处理跨域问题
	 */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        httpResponse.setHeader("Access-Control-Allow-Method", "POST,OPTIONS");
    	filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    	System.out.println("filter destory");
    }
}
