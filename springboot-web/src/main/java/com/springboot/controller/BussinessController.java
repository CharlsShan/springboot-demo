package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.domain.Result;
import com.springboot.util.ResultUtil;

@RequestMapping(value = "bussiness")
@RestController
public class BussinessController {

    @GetMapping("hello")
    public Result getHello() {
        return ResultUtil.success("hello");
    }
}
