package com.springboot.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.entity.Classify;
import com.springboot.mapper.ClassifyMapper;
import com.springboot.service.ClassifyService;

/**
 * @author shan
 * @time 创建时间：2017年9月20日 下午7:22:00
 * 
 */
@Service
public class ClassifyServiceImp implements ClassifyService {
	@Autowired
	private ClassifyMapper classifyMapper;

	

	public List<Classify> getClassify() {
		
		return classifyMapper.selectAll();
	}


	public PageInfo<Classify> getPage(Integer pageNum) {
		
		//每页显示的数量
		int pageSize = 3;
		//从数据库查询分页信息
		List<Classify> listPage=null;
		
		//开始分页
		PageHelper.startPage(pageNum, 2);
	
			listPage=classifyMapper.selectAll();
		//要显示的导航数
		int  navigatePages=6;
		//生成分页数据
		PageInfo<Classify> pageInfo = new PageInfo(listPage, navigatePages);
		return pageInfo;
	}
}
