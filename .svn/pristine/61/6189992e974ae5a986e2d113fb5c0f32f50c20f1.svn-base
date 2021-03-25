package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.mapper.TimedTasksMapper;
import com.lovdmx.control.pojo.TimedTasks;
import com.lovdmx.control.service.TimedTasksService;

@Service("timedTasksService")
public class TimedTasksServiceImpl extends BaseServiceImpl<TimedTasks> implements TimedTasksService {

	@Autowired
	private TimedTasksMapper timedTasksMapper;

	@Override
	public List<TimedTasks> findByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return timedTasksMapper.findByProjectId(projectId);
	}

	@Override
	public TimedTasks findByTaskName(String taskName) {
		// TODO Auto-generated method stub
		return timedTasksMapper.findByTaskName(taskName);
	}

	@Override
	public TimedTasks findByTaskMd5(String taskMd5, Integer projectId) {
		// TODO Auto-generated method stub
		return timedTasksMapper.findByTaskMd5(taskMd5, projectId);
	}

	@Override
	public PageInfo<TimedTasks> queryPageListByWhereTimedTasks(TimedTasks timedTasks, Integer pageNo,
			Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<TimedTasks> list = this.timedTasksMapper.findByTimedTasks(timedTasks);

		PageInfo<TimedTasks> pageInfo = new PageInfo<TimedTasks>(list);
		return pageInfo;
	}

	@Override
	public List<TimedTasks> queryListByConditionIf(TimedTasks timedTasks) {
		// TODO Auto-generated method stub
		return timedTasksMapper.queryListByConditionIf(timedTasks);
	}

	@Override
	public Integer batchDelete(List<Integer> list) {
		// TODO Auto-generated method stub
		return timedTasksMapper.batchDelete(list);
	}

	@Override
	public Integer batchUpdateRackIds(List<TimedTasks> list) {
		// TODO Auto-generated method stub
		return timedTasksMapper.batchUpdateRackIds(list);
	}

}
