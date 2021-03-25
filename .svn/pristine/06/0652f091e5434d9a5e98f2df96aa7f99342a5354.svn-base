package com.lovdmx.control.common.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.lovdmx.control.common.excel.ExcelExportUtils;
import com.lovdmx.control.common.utils.SocketUtils;
import com.lovdmx.control.service.Dmx512DeviceDetailsService;
import com.lovdmx.control.service.GatewayDeviceDetailsService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: JobExcelExportDmx512DeviceDetails.java
 * @Description: 任务 导出设备数据 到Excel 表格中
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年9月20日 下午3:08:49
 *
 */
public class JobExcelExportDeviceDetails extends SocketUtils {

	@Autowired
	private Dmx512DeviceDetailsService dmx512DeviceDetailsService;

	@Autowired
	private GatewayDeviceDetailsService gatewayDeviceDetailsService;

	/**
	 * 
	 * @Function: JobExcelExportDmx512DeviceDetails.java
	 * @Description: 导出数据到Excel 然后 删除导出的数据
	 *
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月20日 下午3:24:44
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月20日
	 *        Administrator v1.0.0 修改原因
	 */
	public void excelExport() {
		try {
			ExcelExportUtils.setDmx512DeviceDetailsService(dmx512DeviceDetailsService);
			ExcelExportUtils.setGatewayDeviceDetailsService(gatewayDeviceDetailsService);
			// 导出DMX512设备信息
			ExcelExportUtils.excelExportAndDeleteDmx512DeviceInfo();
			// 导出智能网关设备信息
			ExcelExportUtils.excelExportAndDeleteGatewayDeviceInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
