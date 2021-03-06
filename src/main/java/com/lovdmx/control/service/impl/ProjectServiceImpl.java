package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.httpVo.AlarmDataVo;
import com.lovdmx.control.httpVo.AlarmDeviceInfoVo;
import com.lovdmx.control.httpVo.Dmx512SensorAlarmInfoVo;
import com.lovdmx.control.httpVo.GatewayDeviceSensorAlarmInfoVo;
import com.lovdmx.control.mapper.ProjectMapper;
import com.lovdmx.control.pojo.Project;
import com.lovdmx.control.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public Project findProjectRackDeviceInfo(Integer projectId) {
		return projectMapper.findProjectRackDeviceInfo(projectId);
	}

	@Override
	public List<AlarmDataVo> findRTRNotExistVideoByParentId(Integer projectId) {
		// TODO Auto-generated method stub
		return projectMapper.findRTRNotExistVideoByParentId(projectId);
	}

	@Override
	public List<AlarmDataVo> findSpriteNotExistLmxByParentId(Integer projectId) {
		// TODO Auto-generated method stub
		return projectMapper.findSpriteNotExistLmxByParentId(projectId);
	}

	@Override
	public List<AlarmDataVo> findSpriteNotExistTasksByParentId(Integer projectId) {
		// TODO Auto-generated method stub
		return projectMapper.findSpriteNotExistTasksByParentId(projectId);
	}

	@Override
	public List<AlarmDeviceInfoVo> findProjectRackDeviceAlarmInfo(Integer projectId) {
		// TODO Auto-generated method stub
		return projectMapper.findProjectRackDeviceAlarmInfo(projectId);
	}

	@Override
	public List<Project> findProjectIdOrProjectName(Project project) {
		// TODO Auto-generated method stub
		return projectMapper.findProjectIdOrProjectName(project);
	}

	@Override
	public Project findByProjectName(String projectName) {
		// TODO Auto-generated method stub
		return projectMapper.findByProjectName(projectName);
	}

	@Override
	public List<AlarmDataVo> findSpriteNotExistDeleteTasksByParentId(Integer projectId) {
		// TODO Auto-generated method stub
		return projectMapper.findSpriteNotExistDeleteTasksByParentId(projectId);
	}

	@Override
	public List<Dmx512SensorAlarmInfoVo> findDMX512NotExistTemperatureAlarmByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return projectMapper.findDMX512NotExistTemperatureAlarmByProjectId(projectId);
	}

	@Override
	public List<Dmx512SensorAlarmInfoVo> findDMX512NotExistSmokeAlarmByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return projectMapper.findDMX512NotExistSmokeAlarmByProjectId(projectId);
	}

	@Override
	public List<GatewayDeviceSensorAlarmInfoVo> findGatewayDeviceNotExistTemperatureAlarmByProjectId(
			Integer projectId) {
		// TODO Auto-generated method stub
		return projectMapper.findGatewayDeviceNotExistTemperatureAlarmByProjectId(projectId);
	}

	@Override
	public List<GatewayDeviceSensorAlarmInfoVo> findGatewayDeviceNotExistSmokeAlarmByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return projectMapper.findGatewayDeviceNotExistSmokeAlarmByProjectId(projectId);
	}
}