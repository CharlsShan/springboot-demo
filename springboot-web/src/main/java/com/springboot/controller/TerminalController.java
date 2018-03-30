package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.domain.Result;
import com.springboot.util.ResultUtil;

@RequestMapping(value = "terminal")
@RestController
public class TerminalController {
    
	@GetMapping("world")
    public Result getHello() {
        return ResultUtil.success("hello world");
    }
}
