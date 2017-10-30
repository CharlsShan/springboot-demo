package com.springboot.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springboot.entity.Classify;

/**
* @author shan
* @time 创建时间：2017年9月20日 下午7:21:38
* 
*/
public interface ClassifyService {

	/**创建得到所有的分类信息*/
	List<Classify> getClassify();
		
	/**创建分页信息*/
	public PageInfo<Classify> getPage(Integer pageNum);

}
