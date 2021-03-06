package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.httpVo.AlarmDeviceInfoVo;
import com.lovdmx.control.httpVo.AlarmDmx512DeviceInfoVo;
import com.lovdmx.control.httpVo.AlarmGatewayDeviceInfoVo;
import com.lovdmx.control.httpVo.SolveAlarmData;
import com.lovdmx.control.mapper.ErrMapper;
import com.lovdmx.control.pojo.Err;
import com.lovdmx.control.service.ErrService;

@Service("errService")
public class ErrServiceImpl extends BaseServiceImpl<Err> implements ErrService {

	@Autowired
	private ErrMapper errMapper;

	@Override
	public Integer batchInsert(List<Err> list) {
		return errMapper.batchInsert(list);
	}

	@Override
	public List<Err> findAlarmDataByDeviceType(String deviceType) {
		return errMapper.findAlarmDataByDeviceType(deviceType);
	}

	@Override
	public Integer batchUpdate(List<Err> updateErrList) {
		return errMapper.batchUpdate(updateErrList);
	}

	@Override
	public Integer batchUpdateResoutionStateByErrIdList(List<Err> updateErrStatusList) {
		return errMapper.batchUpdateResoutionStateByErrIdList(updateErrStatusList);
	}

	@Override
	public List<AlarmDeviceInfoVo> findErrTypteOffLineAlarmInfoByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return errMapper.findErrTypteOffLineAlarmInfoByProjectId(projectId);
	}

	@Override
	public List<AlarmDmx512DeviceInfoVo> findErrTypeDmx512RdmtsAlarmInfoByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return errMapper.findErrTypeDmx512RdmtsAlarmInfoByProjectId(projectId);
	}

	@Override
	public List<SolveAlarmData> findErrTypteFileAlarmInfoByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return errMapper.findErrTypteFileAlarmInfoByProjectId(projectId);
	}

	@Override
	public List<Err> findByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return errMapper.findByProjectId(projectId);
	}

	@Override
	public List<Err> findConditionQueryAlarmData(Integer projectId, String deviceType, Integer resolutionState,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		return errMapper.findConditionQueryAlarmData(projectId, deviceType, resolutionState, startTime, endTime);
	}

	@Override
	public String findLoseGroupCounatRackIdByTypeMd5AndErrType(Integer projectId, String fileMd5, String errType) {
		// TODO Auto-generated method stub
		return errMapper.findLoseGroupCounatRackIdByTypeMd5AndErrType(projectId, fileMd5, errType);
	}

	@Override
	public String findLoseGroupCounatRackIdByTaskNameAndErrType(Integer projectId, String taskName, String errType) {
		// TODO Auto-generated method stub
		return errMapper.findLoseGroupCounatRackIdByTaskNameAndErrType(projectId, taskName, errType);
	}

	@Override
	public List<Err> findAlarmDateNumber(Integer projectId, String deviceType, Integer resolutionState,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		return errMapper.findAlarmDateNumber(projectId, deviceType, resolutionState, startTime, endTime);
	}

	@Override
	public Integer batchUpdateResoutionStateByDeviceTypeAndErrTypeAndTypeMd5(Err err) {
		// TODO Auto-generated method stub
		return errMapper.batchUpdateResoutionStateByDeviceTypeAndErrTypeAndTypeMd5(err);
	}

	@Override
	public List<AlarmGatewayDeviceInfoVo> findErrTypeGatewayDeviceAlarmInfoByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return errMapper.findErrTypeGatewayDeviceAlarmInfoByProjectId(projectId);
	}

}