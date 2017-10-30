package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springboot.entity.Classify;
import com.springboot.service.ClassifyService;


/**
 * @author shan
 * @time 创建时间：2017年9月14日 下午2:12:18
 * @RestController = @Controller + @ResponseBody
 */
@RestController
public class ClassifyController {

	@Autowired
	private ClassifyService classifyService;

	/**@GetMapping("/getData") = @RequestMapping(value="/getData",method=RequestMethod.GET)
	 * 有Get就有post put delete
	 */
	@GetMapping("/getData" )
	public List<Classify> getClassify() {
		return classifyService.getClassify();
	}
	
	@GetMapping("/getPage/{id}")
	public PageInfo<Classify> getClassify(@PathVariable("id") Integer id) {
		return classifyService.getPage(id);
	}	
}