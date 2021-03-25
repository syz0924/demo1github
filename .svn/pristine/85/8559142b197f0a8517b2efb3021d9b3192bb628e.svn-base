package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.mapper.RealtimeTasksMapper;
import com.lovdmx.control.pojo.RealtimeTasks;
import com.lovdmx.control.service.RealtimeTasksService;

@Service("realtimeTasksService")
public class RealtimeTasksServiceImpl extends BaseServiceImpl<RealtimeTasks> implements RealtimeTasksService {

	@Autowired
	private RealtimeTasksMapper realtimeTasksMapper;

	@Override
	public RealtimeTasks findByTaskName(Integer projectId, String taskName) {
		// TODO Auto-generated method stub
		return realtimeTasksMapper.findByTaskName(projectId, taskName);
	}

	@Override
	public List<RealtimeTasks> findByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return realtimeTasksMapper.findByProjectId(projectId);
	}

	@Override
	public PageInfo<RealtimeTasks> queryPageListByWhereByTaskName(RealtimeTasks realtimeTasks, Integer pageNo,
			Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<RealtimeTasks> list = this.realtimeTasksMapper
				.findRealtimeTaskJoinProjectByProjectIdOrTaskName(realtimeTasks);

		PageInfo<RealtimeTasks> pageInfo = new PageInfo<RealtimeTasks>(list);
		return pageInfo;
	}

}
