package com.lovdmx.control.service;

import java.util.List;

import com.lovdmx.control.httpVo.excelExprot.dmx.ExcelExportDmx512DeviceVo;
import com.lovdmx.control.pojo.Dmx512DeviceDetails;

public interface Dmx512DeviceDetailsService extends BaseService<Dmx512DeviceDetails> {

	/**
	 * 
	 * @Function: Dmx512DeviceDetailsService.java
	 * @Description: 根据dmx512Mac 获取 当天信息
	 *
	 * @param: dmx512Mac mac地址
	 * @param: today 当天
	 * @return：Dmx512DeviceDetails
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月17日 下午4:28:31
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月17日
	 *        Administrator v1.0.0 修改原因
	 */
	Dmx512DeviceDetails findByDmx512MacAndToday(String dmx512Mac, String today);

	/**
	 * 
	 * @Function: Dmx512DeviceDetailsService.java
	 * @Description:获取 小于今天的所有Dmx512信息 (日期分组)
	 *
	 * @param: today 当天
	 * @return：List<ExcelExportDmx512DeviceVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月20日 上午10:32:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月20日
	 *        Administrator v1.0.0 修改原因
	 */
	List<ExcelExportDmx512DeviceVo> findLesstodayByToday(String today);

	/**
	 * 
	 * @Function: Dmx512DeviceDetailsService.java
	 * @Description:刪除 小于今天的所有Dmx512信息
	 *
	 * @param: today 当天
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月20日 上午10:32:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月20日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer deleteLesstodayByToday(String today);

}
