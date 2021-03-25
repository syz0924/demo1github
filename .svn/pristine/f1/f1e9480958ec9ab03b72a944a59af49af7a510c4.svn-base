package com.lovdmx.control.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.Manage;

public interface ManageService extends BaseService<Manage> {

	/**
	 * 
	 * @Function: ManageService.java
	 * @Description: 登录
	 *
	 * @param:loginName 账号
	 *                      password 密码
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午10:56:01
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	Manage login(String loginName, String password) throws Exception;

	/**
	 * 
	 * @Function: ManageMapper.java
	 * @Description: 根据登录账号 获取账号信息
	 *
	 * @param:loginName 账号名
	 * @return：Manage
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午10:51:39
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	Manage findByLoginName(@Param("loginName") String loginName);

	/**
	 * 
	 * @Function: ManageMapper.java
	 * @Description: 批量删除
	 *
	 * @param:manageIds 多个管理员ID
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午10:48:36
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	Integer batchDelete(@Param("arrIds") Integer[] arrIds);

	/**
	 * 
	 * @Function: ManageMapper.java
	 * @Description: 模糊查询
	 *
	 * @param: manage
	 *             账号信息
	 * @return：List<Manage>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午10:49:13
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	List<Manage> findByManageIdORLoginName(Manage manage);

	/**
	 * 
	 * @Function: ManageService.java
	 * @Description: 判断密码是否相等
	 *
	 * @param:manageId 账号id
	 *                     password 密码
	 * @return: boolean
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午10:59:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2019年6月18日 Administrator v1.0.0 修改原因
	 */
	boolean judgePassword(Integer manageId, String password);
}