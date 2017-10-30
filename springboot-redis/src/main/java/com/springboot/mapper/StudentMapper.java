package com.springboot.mapper;

import com.springboot.entity.Student;
import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    Student selectByPrimaryKey(Integer id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);
}