package com.springboot.util;

import com.springboot.domain.Result;

public class ResultUtil {

    public static final Integer SUCCESS_CODE = 200;
    public static final Result SERVER_EXCEPTION = new Result(201, "服务器偷懒了");

    private ResultUtil() {
    }

    public static Result success() {
        return success("");
    }

    
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success(String msg, Object object) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }


    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(Integer code, String msg,Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result error( String msg) {
        Result result = new Result();
        result.setCode(10000);
        result.setMsg(msg);
        return result;
    }
}
