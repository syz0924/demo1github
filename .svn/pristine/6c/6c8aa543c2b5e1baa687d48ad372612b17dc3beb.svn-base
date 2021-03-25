package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.mapper.RtrVideoMapper;
import com.lovdmx.control.pojo.RtrVideo;
import com.lovdmx.control.service.RtrVideoService;

@Service("rtrVideoService")
public class RtrVideoServiceImpl extends BaseServiceImpl<RtrVideo> implements RtrVideoService {

	@Autowired
	private RtrVideoMapper rtrVideoMapper;

	@Override
	public Integer batchInsert(List<RtrVideo> rtrVideoList) {
		return rtrVideoMapper.batchInsert(rtrVideoList);
	}
}