package com.lovdmx.control.common.excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.lovdmx.control.httpVo.excelExprot.dmx.Dmx512DeviceDetailsVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: Dmx512ExcelExport.java
 * @Description: Excel导出 dmx512设备信息
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年11月2日 上午10:32:55
 *
 */
public class ExcelExportDmx512Device extends ExcelExport<Dmx512DeviceDetailsVo> {

	public ExcelExportDmx512Device(HSSFWorkbook wb, String[] headList) {
		super();
		this.wb = wb;
		this.headList = headList;
	}

	public ExcelExportDmx512Device(HSSFWorkbook wb, String[] commHeadList, List<Object> commHeadDataList, String[] headList) {
		super();
		this.wb = wb;
		this.commHeadList = commHeadList;
		this.commHeadDataList = commHeadDataList;
		this.headList = headList;
	}
	
	/**
	 * 
	 * @Function: ExcelExportUtils.java
	 * @Description: 创建工作表
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月18日 下午2:27:55
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月18日
	 *        Administrator v1.0.0 修改原因
	 */
	public void createSheet(String sheetName, String title, List<Dmx512DeviceDetailsVo> dataList) {
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		row1.setHeight((short) rowHeight);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);

		// 设置单元格内容
		cell.setCellStyle(cellStyle);
		cell.setCellValue(title);

		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headList.length - 1));

		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		row2.setHeight(rowHeight);

		// 添加公共头
		for (int i = 0; i < commHeadList.length; i++) {
			sheet.setColumnWidth(headList.length + 1 + i, columnWidth);
			HSSFCell cellCommHead = row1.createCell(headList.length + 1 + i);
			cellCommHead.setCellStyle(cellStyle);
			cellCommHead.setCellValue(commHeadList[i]);
		}

		// 添加公共头数据
		for (int i = 0; i < commHeadDataList.size(); i++) {
			HSSFCell cellCommData = row2.createCell(headList.length + 1 + i);
			cellCommData.setCellStyle(cellStyle);
			cellCommData.setCellValue(commHeadDataList.get(i).toString());
		}

		// 创建单元格并设置单元格内容
		for (int i = 0; i < headList.length; i++) {
			sheet.setColumnWidth(i, columnWidth);
			HSSFCell cell1 = row2.createCell(i);
			cell1.setCellStyle(cellStyle);
			cell1.setCellValue(headList[i]);
		}

		int b = 2;
		// 数据导入单元格
		for (Dmx512DeviceDetailsVo dmx : dataList) {
			HSSFRow row = sheet.createRow(b);
			row.setHeight(rowHeight);
			for (int i = 0; i < headList.length; i++) {
				HSSFCell cell1 = row.createCell(i);
				cell1.setCellStyle(cellStyle);
				if (i == 0) {
					cell1.setCellValue(dmx.getCreateTime());
				} else if (i == 1) {
					cell1.setCellValue(dmx.getTemperature());
				} else if (i == 2) {
					cell1.setCellValue(dmx.getHumidity());
				} else if (i == 3) {
					cell1.setCellValue(dmx.getSmokeAlarmSate());
				}
			}
			b++;
		}
	}
	
}
