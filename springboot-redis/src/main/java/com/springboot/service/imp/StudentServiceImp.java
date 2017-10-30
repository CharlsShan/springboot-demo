package com.springboot.service.imp;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.entity.Student;
import com.springboot.mapper.StudentMapper;
import com.springboot.service.StudentService;
import com.springboot.service.util.RedisUtils;

/**
 * @author shanchenyang
 * @time 创建时间：2017年10月11日 下午8:41:27
 * 
 */
@Service
public class StudentServiceImp implements StudentService {
	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private RedisUtils redisUtils;
	private static final Logger logger = getLogger(StudentServiceImp.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	public static final String REDIS_KEY = "REDIS_KEY";

	public List<Student> getAllStudents() {
		Boolean hasKey = redisUtils.hasKey(REDIS_KEY);

		if (hasKey) {
			String redisList = redisUtils.get(REDIS_KEY);
			JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, Student.class);
			try {
				return MAPPER.readValue(redisList, javaType);
			} catch (Exception e) {
				logger.error("json error");
			}
			return new ArrayList<Student>();
		} else {
			List<Student> selectAll = studentMapper.selectAll();

			String writeValueAsString = "";

			try {
				writeValueAsString = MAPPER.writeValueAsString(selectAll);
			} catch (JsonProcessingException e) {
				logger.error("json error");
			}
			redisUtils.set(REDIS_KEY, writeValueAsString);
			return selectAll;
		}

	}

}
