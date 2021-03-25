package com.lovdmx.control.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.lovdmx.control.httpVo.excelExprot.dmx.Dmx512DeviceDetailsVo;

/**
 * 
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: ExcelExportUtils.java
 * @Description: excel工具类
 *
 * @version: v1.0.0
 * @author: syz
 * @date: 2019年9月18日 下午1:44:17
 *
 */
public class ExcelExportUtils {

	// excel表对象
	private HSSFWorkbook wb;
	// sheet 公共列的表头
	private String[] commHeadList;
	// 公共头内容
	private List<Object> commHeadDataList;
	// sheet各个列的表头
	private String[] headList;
	// 字体
	private String fontName = "宋体";
	// 字体大小
	private Short fontSize = 14;
	// 工作表行高    
	private Short rowHeight = 500;
	// 工作表列宽
	private Integer columnWidth = 30 * 260;
	// 样式
	private HSSFCellStyle cellStyle;

	public ExcelExportUtils(HSSFWorkbook wb, String[] headList) {
		super();
		this.wb = wb;
		this.headList = headList;
	}

	public ExcelExportUtils(HSSFWorkbook wb, String[] commHeadList, List<Object> commHeadDataList, String[] headList) {
		super();
		this.wb = wb;
		this.commHeadList = commHeadList;
		this.commHeadDataList = commHeadDataList;
		this.headList = headList;
	}

	public String[] getCommHeadList() {
		return commHeadList;
	}

	public void setCommHeadList(String[] commHeadList) {
		this.commHeadList = commHeadList;
	}

	public List<Object> getCommHeadDataList() {
		return commHeadDataList;
	}

	public void setCommHeadDataList(List<Object> commHeadDataList) {
		this.commHeadDataList = commHeadDataList;
	}

	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	public String[] getHeadList() {
		return headList;
	}

	public void setHeadList(String[] headList) {
		this.headList = headList;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public Short getFontSize() {
		return fontSize;
	}

	public void setFontSize(Short fontSize) {
		this.fontSize = fontSize;
	}

	public Short getRowHeight() {
		return rowHeight;
	}

	public void setRowHeight(Short rowHeight) {
		this.rowHeight = rowHeight;
	}

	public Integer getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(Integer columnWidth) {
		this.columnWidth = columnWidth;
	}

	/**
	 * 
	 * @Function: ExcelExportUtils.java
	 * @Description: 初始化 excel 字体样式
	 *
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月18日 下午2:26:41
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月18日
	 *        Administrator v1.0.0 修改原因
	 */
	public void initCellStyle() {
		// TODO 设置字体格式大小
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 14);// 设置字体大小
		cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
		cellStyle.setFont(font);
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

	/**
	 * 
	 * @Function: ExcelExportUtils.java
	 * @Description: 导入Excel 文件中
	 *
	 * @param:filePath 文件路径
	 * @param:fileName 文件名
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月18日 下午3:00:06
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月18日
	 *        Administrator v1.0.0 修改原因
	 */
	public boolean write(String filePath, String fileName) {
		// 文件路径
		File file = new File(filePath);
		// 输入流
		FileOutputStream bos = null;
		try {
			// 判断文件夹是否存在
			if (!file.exists() && !file.isDirectory()) {
				// 不存在 添加
				file.mkdirs();
			}
			// 写入文件流路径
			bos = new FileOutputStream(new File(file.getPath() + File.separator + fileName));
			wb.write(bos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (bos != null) {
				try {
					// 关闭
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bos = null;
			}
		}
		return true;
	}

	/**
	 * 
	 * @Function: ExcelExportUtils.java
	 * @Description: 关闭对象
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月20日 下午3:21:32
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月20日
	 *        Administrator v1.0.0 修改原因
	 */
	public static void close(Workbook wb) {
		// 关闭
		if (wb != null) {
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
