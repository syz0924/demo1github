package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.pojo.DelTasks;
import com.lovdmx.control.service.DelTasksService;

@Service("delTasksService")
public class DelTasksServiceImpl extends BaseServiceImpl<DelTasks> implements DelTasksService {

	@Autowired
	private DelTasksService delTasksService;
	
	@Override
	public Integer batchInsert(List<DelTasks> list) {
		// TODO Auto-generated method stub
		return delTasksService.batchInsert(list);
	}

}
