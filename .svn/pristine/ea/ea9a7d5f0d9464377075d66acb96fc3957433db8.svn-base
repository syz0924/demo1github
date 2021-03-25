package com.lovdmx.control.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.common.exception.AccountNotExitException;
import com.lovdmx.control.common.exception.PasswordErrorException;
import com.lovdmx.control.common.exception.StatusNoExitException;
import com.lovdmx.control.pojo.Account;

public interface AccountService extends BaseService<Account> {

	/**
	 * 
	 * @Function: AccountService.java
	 * @Description: 该函数的功能描述
	 *
	 * @param userName 登录名
	 * @param password 密码
	 * @return：Account
	 * @throws：AccountNotExitException, PasswordErrorException,
	 *                                  StatusNoExitException
	 * 
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月30日 下午3:37:52
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月30日
	 *        Administrator v1.0.0 修改原因
	 */
	Account login(String userName, String password)
			throws AccountNotExitException, PasswordErrorException, StatusNoExitException;

	/**
	 * 
	 * @Function: AccountService.java
	 * @Description: 根据账号名,查询账号信息
	 *
	 * @param userName 登录名
	 * @return：Account
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月30日 下午3:38:50
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月30日
	 *        Administrator v1.0.0 修改原因
	 */
	Account findByUserName(String userName);

	/**
	 * 
	 * @Function: AccountService.java
	 * @Description: 修改所有在线状态
	 *
	 * @param:onlineStatus 在线状态
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月28日 下午3:41:22
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月28日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer updateAllOnlineStatus(Integer onlineStatus);

	/**
	 * 
	 * @Function: AccountService.java
	 * @Description: 模糊查询 获取所有账号信息
	 *
	 * @param: account 账号信息
	 * @return：List<Account>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 下午2:17:46
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Account> findByAccountIdORUserName(Account account);

	/**
	 * 
	 * @Function: AccountService.java
	 * @Description: 判断密码是否一致
	 *
	 * @param: accountId 账号id password 密码
	 * @return：boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 下午2:20:49
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	boolean judgePassword(Integer accountId, String password);

	/**
	 * 
	 * @Function: AccountService.java
	 * @Description: 分页查询 （根据account条件查询）
	 *
	 * @param: account 账号对象
	 * @param: pageNo 页
	 * @param: rows 行
	 * @return：Page<Account>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月21日 上午11:29:11
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月21日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<Account> queryPageListByAccount(Account account, Integer pageNo, Integer rows);

}