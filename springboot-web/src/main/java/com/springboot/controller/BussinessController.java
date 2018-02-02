package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shanchenyang on 2018/2/1.
 */
@RequestMapping(value = "bussiness")
@RestController
public class BussinessController {

    @GetMapping("hello")
    public String getHello() {
        return "hello";
    }
}
