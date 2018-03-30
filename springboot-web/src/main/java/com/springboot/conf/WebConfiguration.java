package com.springboot.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.springboot.interceptor.BussinessInterceptor;
import com.springboot.interceptor.TerminalInterceptor;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(bussinessInterceptor()).addPathPatterns("/bussiness/**");
        registry.addInterceptor(terminalInterceptor()).addPathPatterns("/terminal/**");
    }

    @Bean
    public BussinessInterceptor bussinessInterceptor() {
        return new BussinessInterceptor();
    }
    @Bean
    public TerminalInterceptor terminalInterceptor() {
        return new TerminalInterceptor();
    }

    /**
     * 视图控制器配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/").setViewName("/bussiness/hello");
    }
    
    /**
     * 跨域CORS配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST","GET","OPTIONS")
                .allowedOrigins("*");
    }
}
