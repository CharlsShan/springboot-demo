package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author shanchenyang
* @time 创建时间：2018年2月1日 下午6:02:50
* 
*/
@RequestMapping(value = "terminal")
@RestController
public class TerminalController {
    
	@GetMapping("world")
    public String getHello() {
        return "hello world";
    }
}
