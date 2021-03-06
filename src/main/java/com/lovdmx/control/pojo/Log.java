package com.lovdmx.control.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Log.java
 * @Description: 操作日志
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年2月21日 下午2:36:59
 *
 */
public class Log implements Serializable {
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 6295083638708503752L;
	// 编号
	private Integer logId;
	// 项目ID
	private Integer projectId;
	// 账号ID
	private Integer accountId;
	// 操作模式
	private String operationMode;
	// 详情
	private String content;
	// 操作时间
	private Date operationTime;
	// 操作日期
	private Date operationDate;

	/**
	 * @Function: Log.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月5日 下午4:56:35
	 */
	public Log(Integer projectId, Integer accountId, String operationMode, String content, Date operationTime,
			Date operationDate) {
		super();
		this.projectId = projectId;
		this.accountId = accountId;
		this.operationMode = operationMode;
		this.content = content;
		this.operationTime = operationTime;
		this.operationDate = operationDate;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(String operationMode) {
		this.operationMode = operationMode == null ? null : operationMode.trim();
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
}