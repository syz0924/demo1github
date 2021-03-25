package com.lovdmx.control.common.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class ExcelExport<T> {

	// excel表对象
	protected HSSFWorkbook wb;
	// sheet 公共列的表头
	protected String[] commHeadList;
	// 公共头内容
	protected List<Object> commHeadDataList;
	// sheet各个列的表头
	protected String[] headList;
	// 字体
	protected String fontName = "宋体";
	// 字体大小
	protected Short fontSize = 14;
	// 工作表行高
	protected Short rowHeight = 500;
	// 工作表列宽
	protected Integer columnWidth = 30 * 260;
	// 样式
	protected HSSFCellStyle cellStyle;

	public ExcelExport() {
		super();
	}

	public ExcelExport(HSSFWorkbook wb, String[] headList) {
		super();
		this.wb = wb;
		this.headList = headList;
	}

	public ExcelExport(HSSFWorkbook wb, String[] commHeadList, List<Object> commHeadDataList, String[] headList) {
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
	public abstract void createSheet(String sheetName, String title, List<T> dataList);

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
