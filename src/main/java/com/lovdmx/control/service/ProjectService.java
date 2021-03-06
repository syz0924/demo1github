package com.lovdmx.control.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.httpVo.AlarmDataVo;
import com.lovdmx.control.httpVo.AlarmDeviceInfoVo;
import com.lovdmx.control.httpVo.Dmx512SensorAlarmInfoVo;
import com.lovdmx.control.httpVo.GatewayDeviceSensorAlarmInfoVo;
import com.lovdmx.control.pojo.Project;

public interface ProjectService extends BaseService<Project> {

	/**
	 * @Function: ProjectService.java
	 * @Description: 根据项目ID获取项目中所有报警机架设备信息(机架,RTR设备,录放精灵设备,DMX512设备信息)
	 *
	 * @param: projectId 项目ID
	 * @return：List<AlarmDeviceInfoVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月25日 下午1:53:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月25日
	 *        Administrator v1.0.0 修改原因
	 */
	List<AlarmDeviceInfoVo> findProjectRackDeviceAlarmInfo(Integer projectId);

	/**
	 * @Function: ProjectService.java
	 * @Description: 根据项目ID获取项目中所有机架设备信息(机架,RTR设备,录放精灵设备,DMX512设备信息)
	 *
	 * @param: projectId 项目ID
	 * @return：Project
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年2月25日 下午1:53:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年2月25日
	 *        Administrator v1.0.0 修改原因
	 */
	Project findProjectRackDeviceInfo(Integer projectId);

	/**
	 * 
	 * @Function: ProjectService.java
	 * @Description: 根据项目id ，获取丢失视频文件信息，根据MAC地址分组
	 *
	 * @param:projectId 项目ID
	 * @return：List<AlarmDataVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月7日 下午4:37:53 findRTRNotExistVideoGroupCounatRackIdByParentId
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月7日
	 *        Administrator v1.0.0 修改原因
	 */
	List<AlarmDataVo> findRTRNotExistVideoByParentId(Integer projectId);

	/**
	 * 
	 * @Function: ProjectService.java
	 * @Description: 根据项目id ，获取丢失灯光文件信息，根据MAC地址分组
	 *
	 * @param:projectId 项目ID
	 * @return：List<AlarmDataVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月7日 下午4:37:53
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月7日
	 *        Administrator v1.0.0 修改原因
	 */
	List<AlarmDataVo> findSpriteNotExistLmxByParentId(Integer projectId);

	/**
	 * 
	 * @Function: ProjectService.java
	 * @Description: 根据项目id ，获取丢失任务信息，根据MAC地址分组
	 *
	 * @param:projectId 项目ID
	 * @return：List<AlarmDataVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月7日 下午4:37:53
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月7日
	 *        Administrator v1.0.0 修改原因
	 */
	List<AlarmDataVo> findSpriteNotExistTasksByParentId(Integer projectId);

	/**
	 * 
	 * @Function: ManageMapper.java
	 * @Description: 模糊查询
	 *
	 * @param: project 项目信息
	 * @return：List<Project>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月18日 上午10:49:13
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月18日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Project> findProjectIdOrProjectName(Project project);

	/**
	 * 
	 * @Function: ProjectService.java
	 * @Description: 根据项目名获取项目信息
	 *
	 * @param:projectName 项目名
	 * @return：Project
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年6月20日 下午3:28:52
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月20日
	 *        Administrator v1.0.0 修改原因
	 */
	Project findByProjectName(String projectName);

	/**
	 * 
	 * @Function: ProjectService.java
	 * @Description: 根据项目id ，获取丢失删除任务信息，根据MAC地址分组
	 *
	 * @param:projectId 项目ID
	 * @return：List<AlarmDataVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月29日 下午2:36:45
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月7日
	 *        Administrator v1.0.0 修改原因
	 */
	List<AlarmDataVo> findSpriteNotExistDeleteTasksByParentId(Integer projectId);

	/**
	 * 
	 * @Function: ProjectService.java
	 * @Description: 根据项目id, 查看DMX512 温度报警数据(过滤 已存在的报警数据)
	 *
	 * @param:projectId 项目ID
	 * @return：List<Dmx512SensorAlarmInfoVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月9日 下午2:23:02
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月9日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Dmx512SensorAlarmInfoVo> findDMX512NotExistTemperatureAlarmByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: ProjectService.java
	 * @Description: 根据项目id, 查看网关设备（传感器） 温度报警数据(过滤 已存在的报警数据)
	 *
	 * @param:projectId 项目ID
	 * @return：List<GatewayDeviceSensorAlarmInfoVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年10月31日 上午11:32:02
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月9日
	 *        Administrator v1.0.0 修改原因
	 */
	List<GatewayDeviceSensorAlarmInfoVo> findGatewayDeviceNotExistTemperatureAlarmByProjectId(
			@Param("projectId") Integer projectId);

	/**
	 * 
	 * @Function: ProjectService.java
	 * @Description: 根据项目id, 查看DMX512 烟雾报警数据(过滤 已存在的报警数据)
	 *
	 * @param:projectId 项目ID
	 * @return：List<Dmx512SensorAlarmInfoVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月9日 下午2:23:02
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月9日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Dmx512SensorAlarmInfoVo> findDMX512NotExistSmokeAlarmByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: ProjectService.java
	 * @Description: 根据项目id, 查看网关设备(传感器) 烟雾报警数据(过滤 已存在的报警数据)
	 *
	 * @param:projectId 项目ID
	 * @return：List<GatewayDeviceSensorAlarmInfoVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年10月31日 上午11:32:02
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月9日
	 *        Administrator v1.0.0 修改原因
	 */
	List<GatewayDeviceSensorAlarmInfoVo> findGatewayDeviceNotExistSmokeAlarmByProjectId(
			@Param("projectId") Integer projectId);

}