package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.Account;

public interface AccountMapper extends BaseMapper<Account> {

	/**
	 * 
	 * @Function: AccountMapper.java
	 * @Description: 该函数的功能描述
	 *
	 * @param userName 登录名
	 * @param password 密码
	 * @return：Account
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月30日 下午3:37:52
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月30日
	 *        Administrator v1.0.0 修改原因
	 */
	Account login(@Param("userName") String userName, @Param("password") String password) throws Exception;

	/**
	 * 
	 * @Function: AccountMapper.java
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
	Account findByUserName(@Param("userName") String userName);

	/**
	 * 
	 * @Function: AccountMapper.java
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
	Integer updateAllOnlineStatus(@Param("onlineStatus") Integer onlineStatus);

	/**
	 * 
	 * @Function: AccountMapper.java
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
	 * @Function: AccountMapper.java
	 * @Description: 条件查询
	 *
	 * @param: account 账号对象
	 * @return：List<Account>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月21日 上午11:32:58
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月21日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Account> findByAccount(Account account);

}