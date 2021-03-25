package com.lovdmx.control.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.mapper.BaseMapper;
import com.lovdmx.control.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseMapper<T> mapper;

	public T findById(Serializable id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	public List<T> findAll() {
		return this.mapper.findAll();
	}

	public PageInfo<T> queryPageListByWhere(Integer page, Integer rows) {
		// 设置分页参数
		if (page == null) {
			page = 1;
		}
		PageHelper.startPage(page, rows);
		List<T> list = this.mapper.findAll();

		PageInfo<T> pageInfo = new PageInfo<T>(list);
		return pageInfo;
	}

	public Integer save(T t) {

		return this.mapper.insert(t);
	}

	public Integer update(T t) {

		return this.mapper.update(t);
	}

	public Integer deleteById(Serializable id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

}
