package com.lovdmx.control.mapper;


import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {
	/**
	 * 功能： 查询信息
	 * 
	 */
	public List<T> findAll();

	/**
	 * 功能： 修改信息
	 * 
	 */
	public Integer update(T t);

	/**
	 * 功能： 保存信息
	 * 
	 */
	public Integer insert(T t);

	/**
	 * 功能： 删除信息
	 * 
	 */
	public Integer deleteByPrimaryKey(Serializable id);

	/**
	 * 功能： 查询单条信息
	 * 
	 */
	public T selectByPrimaryKey(Serializable id);
}