package com.lovdmx.control.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lovdmx.control.httpVo.AlarmDeviceInfoVo;
import com.lovdmx.control.httpVo.AlarmDmx512DeviceInfoVo;
import com.lovdmx.control.httpVo.AlarmGatewayDeviceInfoVo;
import com.lovdmx.control.httpVo.SolveAlarmData;
import com.lovdmx.control.pojo.Err;

public interface ErrService extends BaseService<Err> {
	/**
	 * 
	 * @Function: ErrService.java
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
	Integer batchInsert(List<Err> list);

	/**
	 * @Function: ErrService.java
	 * @Description: 根据设备类型获取 报警数据
	 *
	 * @param:deviceType 设备类型
	 * @return：List<Err>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月23日 下午2:13:32
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月23日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Err> findAlarmDataByDeviceType(String deviceType);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 批量修改 报警信息
	 *
	 * @param: list 集合
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月26日 下午4:19:12
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月26日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchUpdate(List<Err> updateErrList);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 批量修改 报警状态
	 *
	 * @param: list 集合
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月27日 下午3:03:57
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月27日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchUpdateResoutionStateByErrIdList(List<Err> updateErrStatusList);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 根据项目ID，获取报警类型为离线状态的设备详情（注:判断报警设备 是否在线）
	 *
	 * @param: projectId 项目ID
	 * @return：List<AlarmDeviceInfoVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月27日 下午2:03:25
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月27日
	 *        Administrator v1.0.0 修改原因
	 */
	List<AlarmDeviceInfoVo> findErrTypteOffLineAlarmInfoByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 根据项目ID,获取设备类型为DMX512报警信息 (注:温度,湿度,烟雾)
	 *
	 * @param:projectId 项目id
	 * @return：List<AlarmDmx512DeviceInfoVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年9月9日 上午11:23:15
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月9日
	 *        Administrator v1.0.0 修改原因
	 */
	List<AlarmDmx512DeviceInfoVo> findErrTypeDmx512RdmtsAlarmInfoByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 根据项目ID，获取报警类型为(Video,Lmx,Task)的设备详情（注:判断(Video,Lmx,Task)
	 *               已发布的数量和反馈是否相等）
	 *
	 * @param: projectId 项目ID
	 * @return：List<SolveAlarmData>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月27日 下午2:03:25
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月27日
	 *        Administrator v1.0.0 修改原因
	 */
	List<SolveAlarmData> findErrTypteFileAlarmInfoByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 根据项目ID获取所有报警信息
	 *
	 * @param:projectId 项目ID
	 * @return：List<Err>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月27日 下午5:25:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月27日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Err> findByProjectId(Integer projectId);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 根据参数 获取报警数据
	 *
	 * @param:projectId 项目ID
	 * @param:deviceType 设备类型
	 * @param: resolutionState 解决状态
	 * @param: startTime 开始时间
	 * @param: endTime 结束时间
	 * @return：List<Err>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年3月27日 下午5:25:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年3月27日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Err> findConditionQueryAlarmData(Integer projectId, String deviceType, Integer resolutionState,
			String startTime, String endTime);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 根据项目id和文件MD5值和错误类型获取，获取丢失所有机架id（,）逗号隔开
	 *
	 * @param: projectId 项目ID
	 * @param: fileMd5 文件md5值
	 * @param: errType 错误类型
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月5日 下午4:47:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月5日
	 *        Administrator v1.0.0 修改原因
	 */
	String findLoseGroupCounatRackIdByTypeMd5AndErrType(Integer projectId, String fileMd5, String errType);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 根据项目id和任务名和错误类型获取，获取丢失所有机架id（,）逗号隔开
	 *
	 * @param: projectId 项目ID
	 * @param: taskName 任务名
	 * @param: errType 错误类型
	 * @return：String
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月5日 下午4:47:59
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月5日
	 *        Administrator v1.0.0 修改原因
	 */
	String findLoseGroupCounatRackIdByTaskNameAndErrType(Integer projectId, String taskName, String errType);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 根据条件查询 分组获取报警日期的数量
	 *
	 * @param:projectId 项目ID
	 * @param:deviceType 设备类型
	 * @param: resolutionState 解决状态
	 * @param: startTime 开始时间
	 * @param: endTime 结束时间
	 * @return：List<Err>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年5月8日 下午5:04:04
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月8日
	 *        Administrator v1.0.0 修改原因
	 */
	List<Err> findAlarmDateNumber(Integer projectId, String deviceType, Integer resolutionState, String startTime,
			String endTime);

	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 批量修改 报警状态和结束时间 根据 设备类型和错误类型和类型MD5值
	 *
	 * @param: err 错误对象
	 * @return：Integer
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年7月29日 上午11:49:54
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月29日
	 *        Administrator v1.0.0 修改原因
	 */
	Integer batchUpdateResoutionStateByDeviceTypeAndErrTypeAndTypeMd5(Err err);
	
	/**
	 * 
	 * @Function: ErrService.java
	 * @Description: 根据项目ID,获取 网关设备(传感器)报警信息 (注:温度,烟雾)
	 *
	 * @param:projectId 项目id
	 * @return：List<AlarmGatewayDeviceInfoVo>
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: syz
	 * @date: 2019年10月31日 下午午17:22:15
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年9月9日
	 *        Administrator v1.0.0 修改原因
	 */
	List<AlarmGatewayDeviceInfoVo> findErrTypeGatewayDeviceAlarmInfoByProjectId(@Param("projectId") Integer projectId);

}