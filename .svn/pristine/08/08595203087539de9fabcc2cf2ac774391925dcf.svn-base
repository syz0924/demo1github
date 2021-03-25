package com.lovdmx.control.service;


import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

public interface BaseService<T> {

	/**
	 * 根据id查询数据
	 * 
	 * @param id
	 * @return
	 */
	T findById(Serializable id);

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 分页查询数据列表
	 * 
	 * @param page
	 * @param rows
	 * 
	 * @return
	 */
	PageInfo<T> queryPageListByWhere(Integer page, Integer rows);

	/**
	 * 新增数据
	 * 
	 * @param t
	 * @return
	 */
	Integer save(T t);

	/**
	 * 更新数据
	 * 
	 * @param t
	 * @return
	 */
	Integer update(T t);

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteById(Serializable id);


}