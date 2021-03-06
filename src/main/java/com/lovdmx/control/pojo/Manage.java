package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Manage.java
 * @Description: 管理员表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月15日 上午10:36:45
 *
 */
public class Manage implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -958694724096708502L;
	// 编号
	private Integer manageId;
	// 账号
	private String loginName;
	// 昵称
	private String nickName;
	// 密码
	private String password;
	// 加延
	private String salt;
	//手机
	private String phone;
	//邮件
	private String email;
	// 状态 （0禁用 1启用）
	private Short status;
	// 创建日期
	private Date createTime;

	public Integer getManageId() {
		return manageId;
	}

	public void setManageId(Integer manageId) {
		this.manageId = manageId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}