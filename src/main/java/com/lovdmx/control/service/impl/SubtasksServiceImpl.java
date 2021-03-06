package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.VideoOrLmxTaskNameVo;
import com.lovdmx.control.mapper.SubtasksMapper;
import com.lovdmx.control.pojo.Subtasks;
import com.lovdmx.control.service.SubtasksService;

@Service("subtasksService")
public class SubtasksServiceImpl extends BaseServiceImpl<Subtasks> implements SubtasksService {

	@Autowired
	private SubtasksMapper subtasksMapper;

	@Override
	public List<Subtasks> findByParentId(Integer parentId) {
		return subtasksMapper.findByParentId(parentId);
	}

	@Override
	public Subtasks findBySubtaskName(String subtaskName, Integer projectId) {
		// TODO Auto-generated method stub
		return subtasksMapper.findBySubtaskName(subtaskName, projectId);
	}

	@Override
	public List<VideoOrLmxTaskNameVo> findSpiltVideoOrLmxDataByIds(String[] arrayIds) {
		// TODO Auto-generated method stub
		return subtasksMapper.findSpiltVideoOrLmxDataByIds(arrayIds);
	}

	@Override
	public List<Subtasks> findByIds(String[] arrayIds) {
		// TODO Auto-generated method stub
		return subtasksMapper.findByIds(arrayIds);
	}

	@Override
	public List<Subtasks> findByIdsAndFileType(String[] arrayIds, String fileType) {
		return subtasksMapper.findByIdsAndFileType(arrayIds, fileType);
	}

	@Override
	public PageInfo<Subtasks> queryPageListByWhereOrSubtaskNameOrFileTypeOrProjectId(Subtasks subtask, Integer pageNo,
			Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<Subtasks> list = this.subtasksMapper.findByOrSubtaskNameOrFileTypeOrProjectId(subtask);

		PageInfo<Subtasks> pageInfo = new PageInfo<Subtasks>(list);
		return pageInfo;
	}

}