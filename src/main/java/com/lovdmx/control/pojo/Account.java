package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Account.java
 * @Description: 账号表
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月21日 下午2:38:23
 *
 */
public class Account implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 2645852275371500973L;
	// 编号
	private Integer accountId;
	// 账户登录名
	private String userName;
	// 昵称
	private String nickName;
	// 密码
	private String password;
	// 加延
	private String salt;
	// 状态
	private Short status;
	// 项目ID
	private Integer projectId;
	// 角色ID
	private Integer roleId;
	// 角色名
	private String roleName;
	// 項目名
	private String projectName;
	// 在线状态
	private Integer isOnline;
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date modifyTime;
	// 是否保存密码
	private boolean check;

	public Account(String userName, Integer projectId) {
		super();
		this.userName = userName;
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password=" + password + ", check=" + check + "]";
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Account() {
		super();
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}