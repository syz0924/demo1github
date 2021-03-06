/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.lovdmx.control.httpVo 
 * @author: syz  
 * @date: 2019年3月19日 上午10:30:31 
 */
package com.lovdmx.control.vo;

import com.lovdmx.control.common.thread.ProjectAnalysisDeviceThread;
import com.lovdmx.control.common.thread.ProjectAnalysisOpCodeThread;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ProjectThreadInfoVo.java
 * @Description: 项目中公共线程
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年3月19日 上午10:30:31
 *
 */
public class ProjectThreadVo {
	// 项目 解析机架设备信息 线程
	private ProjectAnalysisDeviceThread projectAnalysisDeviceThread;
	// 项目 解析Opcode 业务 线程
	private ProjectAnalysisOpCodeThread projectAnalysisOpCodeThread;

	public ProjectThreadVo() {
	}

	public ProjectThreadVo(ProjectAnalysisDeviceThread projectAnalysisDeviceThread,
			ProjectAnalysisOpCodeThread projectAnalysisOpCodeThread) {
		super();
		this.projectAnalysisDeviceThread = projectAnalysisDeviceThread;
		this.projectAnalysisOpCodeThread = projectAnalysisOpCodeThread;
	}

	public ProjectAnalysisDeviceThread getProjectAnalysisDeviceThread() {
		return projectAnalysisDeviceThread;
	}

	public void setProjectAnalysisDeviceThread(ProjectAnalysisDeviceThread projectAnalysisDeviceThread) {
		this.projectAnalysisDeviceThread = projectAnalysisDeviceThread;
	}

	public ProjectAnalysisOpCodeThread getProjectAnalysisOpCodeThread() {
		return projectAnalysisOpCodeThread;
	}

	public void setProjectAnalysisOpCodeThread(ProjectAnalysisOpCodeThread projectAnalysisOpCodeThread) {
		this.projectAnalysisOpCodeThread = projectAnalysisOpCodeThread;
	}

}
