package com.springboot.service;

import java.util.List;

import com.springboot.entity.Student;

/**
* @author shan
* @time 创建时间：2017年9月20日 下午7:21:38
* 
*/
public interface StudentService {

	/**创建得到所有的分类信息*/
	List<Student> getAllStudents();

}
