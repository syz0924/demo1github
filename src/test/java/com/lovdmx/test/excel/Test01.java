package com.lovdmx.test.excel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.lovdmx.control.common.utils.DateUtils;
import com.lovdmx.control.common.utils.ExcelExportUtils;
import com.lovdmx.control.httpVo.excelExprot.dmx.Dmx512DeviceDetailsVo;

public class Test01 {

	@Test
	public void demo01() throws Exception {
		List<Dmx512DeviceDetailsVo> dataList = new ArrayList<Dmx512DeviceDetailsVo>();

		Random r = new Random(100);
		for (int i = 0; i < 60000; i++) {
			Dmx512DeviceDetailsVo Dmx512DeviceDetails = new Dmx512DeviceDetailsVo(r.nextInt(100), r.nextInt(100),
					r.nextInt(2) == 1 ? "无" : "报警", DateUtils.currentlyStrDate1(new Date()));
			dataList.add(Dmx512DeviceDetails);
		}

		String[] headList = new String[] { "创建时间", "温度", "湿度", "报警" };

		String[] commHeadList = new String[] { "项目名", "机架名", "MAC" };
		List<Object> list = new ArrayList<Object>();
		list.add("雷舞");
		list.add("节点一");
		list.add("00E0ACCE350D");

		HSSFWorkbook wb = new HSSFWorkbook();
		ExcelExportUtils excelExportUtils = new ExcelExportUtils(wb, commHeadList, list, headList);

		excelExportUtils.initCellStyle();
		excelExportUtils.createSheet("工作1", "用户信息表", dataList);
		excelExportUtils.write("E:\\cjsworkspace", "demo.xls");

	}
}
