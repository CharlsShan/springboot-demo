package com.springboot.config;

import static org.slf4j.LoggerFactory.getLogger;

import java.text.ParseException;

import org.quartz.JobDataMap;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.springboot.quartz.StudentQuartz;
import com.springboot.service.StudentService;



/**
 * @author shanchenyang
 * @time 创建时间：2017年9月27日 下午8:04:42
 * 
 */
@Configuration
public class QuartzConfig {
	
	@Autowired
	private StudentService studentService;

	private static final Logger logger = getLogger(QuartzConfig.class);
	private JobDetailBean jobDetailBean;
	private CronTriggerBean cronTriggerBean;
	
	@Value("${quartz.cloneExpression}")
	private String cloneExpression;

	@Bean
	public JobDetailBean jobDetailBean() {
		jobDetailBean = new JobDetailBean();
		jobDetailBean.setJobClass(StudentQuartz.class);	
		JobDataMap map = new JobDataMap();
		map.put("studentService", studentService);
		jobDetailBean.setJobDataMap(map);
		return jobDetailBean;
	}
	@Bean
	public CronTriggerBean triggerBean() {
		cronTriggerBean = new CronTriggerBean();
		cronTriggerBean.setJobDetail(jobDetailBean);
		try {
			cronTriggerBean.setCronExpression(cloneExpression);
		} catch (ParseException e) {
			logger.error("分词克隆表达式解析异常");
		}
		return cronTriggerBean;
	}


	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		//new Trigger
		 Trigger[] triggers = new Trigger[1]; 
		 triggers[0] = cronTriggerBean;
		schedulerFactoryBean.setTriggers(triggers);
		return schedulerFactoryBean;
	}
	


}
