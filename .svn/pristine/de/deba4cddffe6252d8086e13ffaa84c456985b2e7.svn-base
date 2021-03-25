package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.mapper.TasksMapper;
import com.lovdmx.control.pojo.Tasks;
import com.lovdmx.control.service.TasksService;

@Service("tasksService")
public class TasksServiceImpl extends BaseServiceImpl<Tasks> implements TasksService {

	@Autowired
	private TasksMapper tasksMapper;

	@Override
	public List<Tasks> findByProjectId(Integer projectId) {
		return tasksMapper.findByProjectId(projectId);
	}

	@Override
	public Integer findCountByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return tasksMapper.findCountByProjectId(projectId);
	}

	@Override
	public Tasks findByProjectIdAndTaskName(Integer projectId, String taskName) {
		return tasksMapper.findByProjectIdAndTaskName(projectId, taskName);
	}

	@Override
	public Tasks findByProjectIdAndTaskMd5(Integer projectId, String taskMd5) {
		// TODO Auto-generated method stub
		return tasksMapper.findByProjectIdAndTaskMd5(projectId, taskMd5);
	}

	@Override
	public Integer updateStatusByTaskMd5(Tasks tasks) {
		// TODO Auto-generated method stub
		return tasksMapper.updateStatusByTaskMd5(tasks);
	}

	@Override
	public Integer updateAllStatusByProjectIdAndStatus(Integer projectId, Integer status) {
		return tasksMapper.updateAllStatusByProjectIdAndStatus(projectId, status);
	}

	@Override
	public String findTaskIdByTaskMd5List(List<String> taskMd5List) {
		// TODO Auto-generated method stub
		return tasksMapper.findTaskIdByTaskMd5List(taskMd5List);
	}

	@Override
	public Integer batchDeleteByTaskMd5List(List<String> taskMd5List) {
		// TODO Auto-generated method stub
		return tasksMapper.batchDeleteByTaskMd5List(taskMd5List);
	}

	@Override
	public List<Tasks> findTaskListByTaskMd5List(List<String> taskMd5List) {
		// TODO Auto-generated method stub
		return tasksMapper.findTaskListByTaskMd5List(taskMd5List);
	}

	@Override
	public PageInfo<Tasks> queryPageListByWhereTasks(Tasks task, Integer pageNo, Integer rows) {
		// TODO Auto-generated method stub
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<Tasks> list = this.tasksMapper.findByOrTasks(task);

		PageInfo<Tasks> pageInfo = new PageInfo<Tasks>(list);
		return pageInfo;
	}

}