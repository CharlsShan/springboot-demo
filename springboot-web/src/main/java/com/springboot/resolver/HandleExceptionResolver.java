package com.springboot.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;
import com.springboot.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * 全局异常处理器
 */
@Component
public class HandleExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(HandleExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("interceptor handle exception, {}", ex.getMessage(), ex);
        this.toOutputStream(request, response, ResultUtil.SERVER_EXCEPTION.getCode().toString(), ResultUtil.SERVER_EXCEPTION.getMsg());
        return null;
    }

    private void toOutputStream(HttpServletRequest request, HttpServletResponse response, String code, String errorMsg) {
        try (OutputStream os = response.getOutputStream()) {
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            os.write(new GsonBuilder().create().toJson(ResultUtil.SERVER_EXCEPTION).getBytes(Charset.forName("utf-8")));
            os.flush();
        } catch (IOException e) {
            logger.error("handler un-handler exception {} error, {}", e, e.getMessage(), e);
        }
    }
}
