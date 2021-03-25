package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovdmx.control.common.exception.AccountNotExitException;
import com.lovdmx.control.common.exception.PasswordErrorException;
import com.lovdmx.control.common.exception.StatusNoExitException;
import com.lovdmx.control.common.utils.SecurityUtil;
import com.lovdmx.control.mapper.ManageMapper;
import com.lovdmx.control.pojo.Manage;
import com.lovdmx.control.service.ManageService;

@Service("manageService")
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService {

	@Autowired
	private ManageMapper manageMapper;

	public Manage login(String loginName, String password)
			throws AccountNotExitException, PasswordErrorException, StatusNoExitException, Exception {
		Manage manage = null;
		try {
			manage = manageMapper.findByLoginName(loginName);
			if (manage == null) {
				throw new AccountNotExitException("用户名不存在");
			} else {
				String salt = manage.getSalt();
				String pwdSalt = SecurityUtil.md5Encode(password + salt);
				if (!manage.getPassword().equals(pwdSalt)) {
					throw new PasswordErrorException("密码错误");
				} else {
					if (manage.getStatus()==0) {
						throw new StatusNoExitException("该用户以禁用");
					}
				}
			}
		} catch (AccountNotExitException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (PasswordErrorException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (StatusNoExitException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return manage;
	}

	@Override
	public Manage findByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return manageMapper.findByLoginName(loginName);
	}

	@Override
	public Integer batchDelete(Integer[] arrIds) {
		// TODO Auto-generated method stub
		return manageMapper.batchDelete(arrIds);
	}

	@Override
	public List<Manage> findByManageIdORLoginName(Manage manage) {
		// TODO Auto-generated method stub
		return manageMapper.findByManageIdORLoginName(manage);
	}

	@Override
	public boolean judgePassword(Integer manageId, String password) {
		Manage manage = manageMapper.selectByPrimaryKey(manageId);
		String salt = manage.getSalt();
		String pwdSalt = SecurityUtil.md5Encode(password + salt);
		if (!manage.getPassword().equals(pwdSalt)) {
			// 密码错误
			return false;
		}
		return true;
	}

}