package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.RackAndGatewayAndRelayTaskDetailsAndWeekVo;
import com.lovdmx.control.mapper.RelayTaskDetailsMapper;
import com.lovdmx.control.pojo.RelayTaskDetails;
import com.lovdmx.control.service.RelayTaskDetailsService;

@Service("relayTaskDetailsService")
public class RelayTaskDetailsServiceImpl extends BaseServiceImpl<RelayTaskDetails> implements RelayTaskDetailsService {

	@Autowired
	private RelayTaskDetailsMapper relayTaskDetailsMapper;

	@Override
	public List<RelayTaskDetails> findByRelayId(Integer relayId) {
		// TODO Auto-generated method stub
		return relayTaskDetailsMapper.findByRelayId(relayId);
	}

	@Override
	public Integer batchInsert(List<RelayTaskDetails> relayTaskDetailList) {
		// TODO Auto-generated method stub
		return relayTaskDetailsMapper.batchInsert(relayTaskDetailList);
	}

	@Override
	public Integer deleteByTaskIdAndArrayRelayId(Integer taskId, String[] arrayRelayId) {
		// TODO Auto-generated method stub
		return relayTaskDetailsMapper.deleteByTaskIdAndArrayRelayId(taskId, arrayRelayId);
	}

	@Override
	public PageInfo<RackAndGatewayAndRelayTaskDetailsAndWeekVo> queryPageListByWhereRelayTimedTaskDetails(
			RackAndGatewayAndRelayTaskDetailsAndWeekVo relayTaskDetailsVo, Integer pageNo, Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<RackAndGatewayAndRelayTaskDetailsAndWeekVo> list = this.relayTaskDetailsMapper
				.findByRelayRelayTimedTaskDetails(relayTaskDetailsVo);

		PageInfo<RackAndGatewayAndRelayTaskDetailsAndWeekVo> pageInfo = new PageInfo<RackAndGatewayAndRelayTaskDetailsAndWeekVo>(
				list);
		return pageInfo;
	}

}
