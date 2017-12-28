package com.springboot.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.entity.Student;
import com.springboot.service.StudentService;

/**
 * @author shanchenyang
 * @time 创建时间：2017年10月11日 下午8:41:27
 * 
 */
@Service
public class StudentServiceImp implements StudentService {
	public List<Student> getAllStudents() {
		Student firstStudent = new Student(1, "tom");
		Student secondStudent = new Student(2, "lilei");
		List<Student> list = new ArrayList<>();
		list.add(firstStudent);
		list.add(secondStudent);
		return list;
	}

}
