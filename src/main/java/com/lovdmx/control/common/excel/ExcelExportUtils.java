package com.lovdmx.control.common.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lovdmx.control.common.utils.DateUtils;
import com.lovdmx.control.common.utils.SocketUtils;
import com.lovdmx.control.httpVo.excelExprot.dmx.Dmx512DeviceDateVo;
import com.lovdmx.control.httpVo.excelExprot.dmx.Dmx512DeviceMacVo;
import com.lovdmx.control.httpVo.excelExprot.dmx.ExcelExportDmx512DeviceVo;
import com.lovdmx.control.httpVo.excelExprot.gatewayDevice.ExcelExportGatewayDeviceVo;
import com.lovdmx.control.httpVo.excelExprot.gatewayDevice.GatewayDeviceDateVo;
import com.lovdmx.control.httpVo.excelExprot.gatewayDevice.GatewayDeviceMacVo;
import com.lovdmx.control.service.Dmx512DeviceDetailsService;
import com.lovdmx.control.service.GatewayDeviceDetailsService;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ExcelExportUtils.java
 * @Description: Excel 导出公共类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年11月2日 上午11:47:14
 *
 */
public class ExcelExportUtils {
	// DMX512设备详情
	private static Dmx512DeviceDetailsService dmx512DeviceDetailsService;
	// 智能网关设备详情
	private static GatewayDeviceDetailsService gatewayDeviceDetailsService;

	public static void setDmx512DeviceDetailsService(Dmx512DeviceDetailsService dmx512DeviceDetailsService) {
		ExcelExportUtils.dmx512DeviceDetailsService = dmx512DeviceDetailsService;
	}

	public static void setGatewayDeviceDetailsService(GatewayDeviceDetailsService gatewayDeviceDetailsService) {
		ExcelExportUtils.gatewayDeviceDetailsService = gatewayDeviceDetailsService;
	}

	/**
	 * 
	 * @Function: JobExcelExportDmx512DeviceDetails.java
	 * @Description: 导出数据DMX512设备数据到Excel 然后 删除导出的数据
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
	public static void excelExportAndDeleteDmx512DeviceInfo() {
		// 获取 今天之前的所有数据
		List<ExcelExportDmx512DeviceVo> dataList = dmx512DeviceDetailsService
				.findLesstodayByToday(DateUtils.currentlyStrDate(new Date()));
		// 公共头
		String[] commHeadList = new String[] { "项目名称", "节点名称", "RTR MAC地址" };
		// 公共头数据
		List<Object> commDataList = new ArrayList<Object>();
		// sheet各个列的表头
		String[] headList = new String[] { "创建时间", "温度", "湿度", "报警" };
		String title = "DMX512设备历史详情";
		// 遍历所有RTR
		for (ExcelExportDmx512DeviceVo excelExportDmx512DeviceVo : dataList) {
			// 清空公共头数据
			commDataList.clear();
			// 赋值公共数据
			commDataList.add(excelExportDmx512DeviceVo.getProjectName());
			commDataList.add(excelExportDmx512DeviceVo.getRackName());
			commDataList.add(excelExportDmx512DeviceVo.getRtrMac());
			// 遍历Dmx512日期
			for (Dmx512DeviceDateVo dmx512DeviceDateVo : excelExportDmx512DeviceVo.getDmx512DateList()) {
				//创建一个Excel文件
				HSSFWorkbook wb = new HSSFWorkbook();
				// 初始化构造函数
				ExcelExportDmx512Device excelExportDmx512 = new ExcelExportDmx512Device(wb, commHeadList, commDataList,
						headList);
				// 初始化 excel 字体样式
				excelExportDmx512.initCellStyle();
				// 遍历当前下的Dmx512信息
				for (Dmx512DeviceMacVo dmx512DeviceMacVo : dmx512DeviceDateVo.getDmx512MacList()) {
					// 创建工作目录
					excelExportDmx512.createSheet(dmx512DeviceMacVo.getDmx512Mac(), title,
							dmx512DeviceMacVo.getDmx512DeviceDetailsVoList());
				}
				// 写入
				excelExportDmx512.write(SocketUtils.basePath + File.separator + "export" + File.separator
						+ excelExportDmx512DeviceVo.getProjectName() + File.separator
						+ excelExportDmx512DeviceVo.getRackName() + File.separator + "excel" + File.separator
						+ "dmx512", dmx512DeviceDateVo.getDmx512Date() + ".xls");
				// 关闭
				ExcelExport.close(wb);
			}
		}

		// 删除 今天之前的所有数据
		dmx512DeviceDetailsService.deleteLesstodayByToday(DateUtils.currentlyStrDate(new Date()));
	}

	/**
	 * 
	 * @Function: JobExcelExportDmx512DeviceDetails.java
	 * @Description: 导出数据网关设备数据到Excel 然后 删除导出的数据
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
	public static void excelExportAndDeleteGatewayDeviceInfo() {
		List<ExcelExportGatewayDeviceVo> dataList = gatewayDeviceDetailsService
				.findLesstodayByToday(DateUtils.currentlyStrDate(new Date()));

		// 公共头
		String[] commHeadList = new String[] { "项目名称", "节点名称", "智能网关 MAC地址" };
		// 公共头数据
		List<Object> commDataList = new ArrayList<Object>();
		// sheet各个列的表头
		String[] headList = new String[] { "创建时间", "温度", "湿度", "报警" };
		String title = "智能网关设备历史详情";
		// 遍历所有网关
		for (ExcelExportGatewayDeviceVo excelExportGatewayDeviceVo : dataList) {
			// 清空公共头数据
			commDataList.clear();
			// 赋值公共数据
			commDataList.add(excelExportGatewayDeviceVo.getProjectName());
			commDataList.add(excelExportGatewayDeviceVo.getRackName());
			commDataList.add(excelExportGatewayDeviceVo.getGatewayMac());
			// 遍历网关设备日期
			for (GatewayDeviceDateVo gatewayDeviceDateVo : excelExportGatewayDeviceVo.getGatewayDeviceDateList()) {
				//创建一个Excel文件
				HSSFWorkbook wb = new HSSFWorkbook();
				// 初始化构造函数
				ExcelExportGatewayDevice excelExportGatewayDevice = new ExcelExportGatewayDevice(wb, commHeadList,
						commDataList, headList);
				// 初始化 excel 字体样式
				excelExportGatewayDevice.initCellStyle();
				// 遍历当前下的网关设备MAC信息
				for (GatewayDeviceMacVo gatewayDeviceMacVo : gatewayDeviceDateVo.getGatewayDeviceMacList()) {
					// 创建工作目录
					excelExportGatewayDevice.createSheet(gatewayDeviceMacVo.getGatewayDeviceMac(), title,
							gatewayDeviceMacVo.getGatewayDeviceDetailsVoList());
				}
				// 写入
				excelExportGatewayDevice.write(SocketUtils.basePath + File.separator + "export" + File.separator
						+ excelExportGatewayDeviceVo.getProjectName() + File.separator
						+ excelExportGatewayDeviceVo.getRackName() + File.separator + "excel" + File.separator
						+ "gatewayDevice", gatewayDeviceDateVo.getGatewayDeviceDate() + ".xls");
				// 关闭
				ExcelExport.close(wb);
			}
		}

		// 删除 今天之前的所有数据
		gatewayDeviceDetailsService.deleteLesstodayByToday(DateUtils.currentlyStrDate(new Date()));
	}

}
