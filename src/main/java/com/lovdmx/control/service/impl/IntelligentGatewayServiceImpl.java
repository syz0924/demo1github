package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.mapper.IntelligentGatewayMapper;
import com.lovdmx.control.pojo.IntelligentGateway;
import com.lovdmx.control.service.IntelligentGatewayService;

@Service("intelligentGatewayService")
public class IntelligentGatewayServiceImpl extends BaseServiceImpl<IntelligentGateway>
		implements IntelligentGatewayService {

	@Autowired
	private IntelligentGatewayMapper intelligentGatewayMapper;

	@Override
	public List<IntelligentGateway> findRackJoinIntelligentGatewayByProjectId(Integer projectId) {
		return intelligentGatewayMapper.findRackJoinIntelligentGatewayByProjectId(projectId);
	}

	@Override
	public Integer updateByMac(IntelligentGateway intelligentGateway) {
		// TODO Auto-generated method stub
		return intelligentGatewayMapper.updateByMac(intelligentGateway);
	}

	@Override
	public IntelligentGateway findByMac(String gatewayMac) {
		// TODO Auto-generated method stub
		return intelligentGatewayMapper.findByMac(gatewayMac);
	}

	@Override
	public PageInfo<IntelligentGateway> queryPageListByWhereRackIdOrMacOrIsOnline(IntelligentGateway intelligentGateway,
			Integer pageNo, Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<IntelligentGateway> list = this.intelligentGatewayMapper.findByRackIdOrMacOrIsOnline(intelligentGateway);

		PageInfo<IntelligentGateway> pageInfo = new PageInfo<IntelligentGateway>(list);
		return pageInfo;
	}

	@Override
	public List<IntelligentGateway> findByRackId(Integer rackId) {
		// TODO Auto-generated method stub
		return intelligentGatewayMapper.findByRackId(rackId);
	}

}
