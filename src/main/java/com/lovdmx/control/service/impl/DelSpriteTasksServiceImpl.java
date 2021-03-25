package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.pojo.DelSpriteTasks;
import com.lovdmx.control.service.DelSpriteTasksService;

@Service("delSpriteTasksService")
public class DelSpriteTasksServiceImpl extends BaseServiceImpl<DelSpriteTasks> implements DelSpriteTasksService {

	@Autowired
	private DelSpriteTasksService delSpriteTasksService;
	
	@Override
	public Integer batchInsert(List<DelSpriteTasks> list) {
		// TODO Auto-generated method stub
		return delSpriteTasksService.batchInsert(list);
	}

}
