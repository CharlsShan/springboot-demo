package com.springboot.service.util;

/**
 * @author shanchenyang
 * @time 创建时间：2017年10月13日 下午7:25:00
 * 
 */
public interface Function<E, T> {
	public T callback(E e);

}
