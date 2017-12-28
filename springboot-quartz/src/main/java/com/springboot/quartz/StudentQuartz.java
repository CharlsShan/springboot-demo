package com.springboot.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.springboot.service.StudentService;

/**
 * @author shanchenyang
 * @time 创建时间：2017年9月27日 下午8:24:22
 * 
 */
@Service
public class StudentQuartz extends QuartzJobBean {
	@Autowired
	private StudentService studentService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("任务开始:");
		System.out.println("所有写生列表：" + studentService.getAllStudents());
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
}
