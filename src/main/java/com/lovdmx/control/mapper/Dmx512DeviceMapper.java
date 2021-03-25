package com.lovdmx.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.pojo.Dmx512Device;

public interface Dmx512DeviceMapper extends BaseMapper<Dmx512Device> {

	/**
	 * 
	 * @Function: Dmx512DeviceMapper.java
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
	Integer batchInsert(@Param("list") List<Dmx512Device> list);

	/**
	 * 
	 * @Function: Dmx512DeviceMapper.java
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
	Dmx512Device findByDmx512Mac(@Param("dmx512Mac") String dmx512Mac);

	/**
	 * 
	 * @Function: Dmx512DeviceMapper.java
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
	List<Dmx512Device> findByRtrMac(@Param("rtrMac") String rtrMac);

	/**
	 * 
	 * @Function: Dmx512DeviceMapper.java
	 * @Description: 根据dmx512设备对象 条件查询
	 *
	 * @param: dmx512Device 对象
	 * @return：List<Dmx512Device>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年11月22日 下午4:21:01
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年11月22日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Dmx512Device> findByDmx512Device(Dmx512Device dmx512Device);

}