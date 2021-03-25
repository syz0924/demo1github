package com.lovdmx.control.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovdmx.control.common.exception.AccountInOnlineExitException;
import com.lovdmx.control.common.exception.AccountNotExitException;
import com.lovdmx.control.common.exception.PasswordErrorException;
import com.lovdmx.control.common.exception.StatusNoExitException;
import com.lovdmx.control.common.utils.SecurityUtil;
import com.lovdmx.control.mapper.AccountMapper;
import com.lovdmx.control.pojo.Account;
import com.lovdmx.control.service.AccountService;

@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public Account login(String userName, String password)
			throws AccountNotExitException, PasswordErrorException, StatusNoExitException {
		Account account = null;
		try {
			account = accountMapper.findByUserName(userName);
			if (account == null) {
				throw new AccountNotExitException("用户名不存在");
			} else {
				String salt = account.getSalt();
				String pwdSalt = SecurityUtil.md5Encode(password + salt);
				if (!account.getPassword().equals(pwdSalt)) {
					throw new PasswordErrorException("密码错误");
				} else {
					if (account.getStatus() == 0) {
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
		} catch (AccountInOnlineExitException e) {
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return account;
	}

	@Override
	public Account findByUserName(String userName) {
		return accountMapper.findByUserName(userName);
	}

	@Override
	public Integer updateAllOnlineStatus(Integer onlineStatus) {
		return accountMapper.updateAllOnlineStatus(onlineStatus);
	}

	@Override
	public List<Account> findByAccountIdORUserName(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.findByAccountIdORUserName(account);
	}

	@Override
	public boolean judgePassword(Integer accountId, String password) {
		Account account = accountMapper.selectByPrimaryKey(accountId);
		String salt = account.getSalt();
		String pwdSalt = SecurityUtil.md5Encode(password + salt);
		if (!account.getPassword().equals(pwdSalt)) {
			// 密码错误
			return false;
		}
		return true;
	}

	@Override
	public PageInfo<Account> queryPageListByAccount(Account account, Integer pageNo, Integer rows) {
		// 设置分页参数
		if (pageNo == null) {
			pageNo = 1;
		}
		PageHelper.startPage(pageNo, rows);
		List<Account> list = this.accountMapper.findByAccount(account);

		PageInfo<Account> pageInfo = new PageInfo<Account>(list);
		return pageInfo;
	}

}