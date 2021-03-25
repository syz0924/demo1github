package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.mapper.SpriteTasksMapper;
import com.lovdmx.control.pojo.SpriteTasks;
import com.lovdmx.control.service.SpriteTasksService;

@Service("spriteTasksService")
public class SpriteTasksServiceImpl extends BaseServiceImpl<SpriteTasks> implements SpriteTasksService {

	@Autowired
	private SpriteTasksMapper spriteTasksMapper;

	@Override
	public Integer updateAllStatusBySpriteMacAndStatus(String spriteMac, Integer status) {
		// TODO Auto-generated method stub
		return spriteTasksMapper.updateAllStatusBySpriteMacAndStatus(spriteMac, status);
	}

	@Override
	public Integer updateAllStatusByTaskIdListAndSpirteMacAndStatus(List<String> taskIds, String spriteMac,
			Integer status) {
		// TODO Auto-generated method stub
		return spriteTasksMapper.updateAllStatusByTaskIdListAndSpirteMacAndStatus(taskIds, spriteMac, status);
	}

	@Override
	public Integer batchDeleteByTaskIdList(List<String> taskIds) {
		// TODO Auto-generated method stub
		return spriteTasksMapper.batchDeleteByTaskIdList(taskIds);
	}
}