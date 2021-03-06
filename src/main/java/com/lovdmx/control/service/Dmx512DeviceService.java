package com.lovdmx.control.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lovdmx.control.pojo.Dmx512Device;

public interface Dmx512DeviceService extends BaseService<Dmx512Device> {

	/**
	 * 
	 * @Function: SpriteDeviceMapper.java
	 * @Description: 批量插入
	 *
	 * @param: list 集合
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月28日 下午2:16:25
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月28日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchInsert(List<Dmx512Device> list);

	/**
	 * 
	 * @Function: Dmx512DeviceService.java
	 * @Description: 查询 dmx512控制盒信息 分页
	 *
	 * @param:dmx512Device dmx512控制盒信息
	 * @param pageNo 页
	 * @param rows   行
	 * @return：PageInfo<Dmx512Device>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月5日 上午10:09:22
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月5日
	 *        Administrator v1.0.0 修改原因
	 */
	PageInfo<Dmx512Device> queryPageListByWhereRtrMac(Dmx512Device dmx512Device, Integer pageNo, Integer rows);

	/**
	 * 
	 * @Function: Dmx512DeviceService.java
	 * @Description: 根据设备Mac 获取控制盒信息
	 *
	 * @param:dmx512Mac 控制盒mac
	 * @return：Dmx512Device
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月17日 上午11:09:10
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月17日
	 *        Administrator v1.0.0 修改原因
	 */
	Dmx512Device findByDmx512Mac(String dmx512Mac);

	/**
	 * 
	 * @Function: Dmx512DeviceService.java
	 * @Description: 根据RTRMAC 获取所有DMX512信息
	 *
	 * @param: rtrMac RTRMAC地址
	 * @return：List<Dmx512Device>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月1日 下午5:12:00
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月1日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Dmx512Device> findByRtrMac(String rtrMac);
}