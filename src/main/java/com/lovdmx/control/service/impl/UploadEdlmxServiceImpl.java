package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.httpVo.LightFile;
import com.lovdmx.control.mapper.UploadEdlmxMapper;
import com.lovdmx.control.pojo.UploadEdlmx;
import com.lovdmx.control.service.UploadEdlmxService;

@Service("uploadEdlmxService")
public class UploadEdlmxServiceImpl extends BaseServiceImpl<UploadEdlmx> implements UploadEdlmxService {

	@Autowired
	private UploadEdlmxMapper uploadEdlmxMapper;

	@Override
	public UploadEdlmx findByMd5(String md5) {
		return uploadEdlmxMapper.findByMd5(md5);
	}

	@Override
	public UploadEdlmx findWebUploadLastInfoByProjectIdAndUploadRole(Integer projectId, Integer uploadRole) {
		return uploadEdlmxMapper.findWebUploadLastInfoByProjectIdAndUploadRole(projectId, uploadRole);
	}

	@Override
	public List<UploadEdlmx> findByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return uploadEdlmxMapper.findByProjectId(projectId);
	}

	@Override
	public List<UploadEdlmx> findNotSpiltByIds(String[] arrayId) {
		// TODO Auto-generated method stub
		return uploadEdlmxMapper.findNotSpiltByIds(arrayId);
	}

	@Override
	public List<UploadEdlmx> findSpiltByIds(String[] arrayId) {
		// TODO Auto-generated method stub
		return uploadEdlmxMapper.findSpiltByIds(arrayId);
	}

	@Override
	public Integer findCountByProjectIdAndRtrLoaded(Integer projectId, Integer status) {
		// TODO Auto-generated method stub
		return uploadEdlmxMapper.findCountByProjectIdAndRtrLoaded(projectId, status);
	}

	@Override
	public List<LightFile> findByLmxProjectId(Integer projectId) {
		return uploadEdlmxMapper.findByLmxProjectId(projectId);
	}

	@Override
	public List<UploadEdlmx> findByMd5s(String[] arryMd5) {
		return uploadEdlmxMapper.findByMd5s(arryMd5);
	}

	@Override
	public PageInfo<UploadEdlmx> queryPageListByWhereByOrRtrLoadedOrUploadRoleOrProjectId(UploadEdlmx uploadEdlmx, Integer pageNo,
			Integer rows) {
		// ??????????????????
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<UploadEdlmx> list = this.uploadEdlmxMapper.findByOrRtrLoadedOrUploadRoleOrProjectId(uploadEdlmx);

		PageInfo<UploadEdlmx> pageInfo = new PageInfo<UploadEdlmx>(list);
		return pageInfo;
	}

}