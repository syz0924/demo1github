<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="lovdmx" uri="www.lovdmx.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${path }/image/lei.jpg"
	type="image/x-icon" />
<link href="${path }/css/skin/jeDate-test.css" rel="stylesheet"
	type="text/css">
<link href="${path }/css/skin/jedate.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="${path }/js/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="${path }/css/public.css">
<link rel="stylesheet" type="text/css"
	href="${path }/css/iconfont/iconfont.css">
<link type="text/css" rel="stylesheet" href="${path }/js/sg/css/sg.css"></link>
<link rel="stylesheet" href="${path }/css/admin/style.css">
<link rel="stylesheet" href="${path }/css/admin/popup.css">
<script type="text/javascript" src="${path }/script/jquery.min.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_init.js"></script>
<script type="text/javascript" src="${path }/js/layui/layui.all.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery.js"></script>

<title>中控后台-继电器定时任务详情列表</title>

</head>
<body>

	<div class="lovdmx">
		<div class="lovdmx-admin">
			<!-- 侧边菜单 -->
			<%@include file="/common/admin/left.jsp"%>

			<!-- 头部 -->
			<%@include file="/common/admin/header.jsp"%>


			<!-- 主体 -->
			<div class="lovdmx-body">
				<div class="lovdmx-bodyinfo lovdmx-show">
					<div class="lovdmx-card lovdmxadmin-header">
						<div class="lovdmx-breadcrumb">
							<a>主页</a> <span>/</span> <a
								href="${path }/lovdmx/ht/relayTaskDetails/index.do"><cite>继电器定时任务详情列表</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-pd">
							<form class="lovdmx-form"
								action="${path }/lovdmx/ht/relayTaskDetails/index.do"
								method="GET">
								<div class="lovdmx-inline" id="project_select">
									<label class="lovdmx-form-label">项目列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty relayTaskDetailsVo.projectId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect project_info"
															readonly value="请选择项目" data-value="-1"
															placeholder="请选择项目" /><input type="hidden"
															class="lovdmx-input" name="projectId" /><input
															type="hidden" class="lovdmx-input" name="projectName" />
														<span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">请选择项目</dd>
														<c:forEach items="${projectList }" var="lst">
															<dd data-value="${lst.projectId }">${lst.projectName }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect project_info"
															readonly value="${relayTaskDetailsVo.projectName }"
															data-value="${relayTaskDetailsVo.projectId }"
															placeholder="请选择项目" /><input type="hidden"
															class="lovdmx-input"
															value='${relayTaskDetailsVo.projectId }' name="projectId" /><input
															type="hidden" class="lovdmx-input"
															value='${relay.projectName }' name="projectName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择项目</dd>
														<c:forEach items="${projectList }" var="lst">
															<dd data-value="${lst.projectId }"
																class="${relayTaskDetailsVo.projectId eq lst.projectId?'lovdmx-this':'' }">${lst.projectName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="lovdmx-inline rack_gateway_select" id="rack_select">
									<label class="lovdmx-form-label">节点列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty relayTaskDetailsVo.projectId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect rack_info" readonly
															value="请选择项目" data-value="-1" placeholder="请选择项目" /><input
															type="hidden" class="lovdmx-input" name="rackId" /><input
															type="hidden" class="lovdmx-input" name="rackName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit" id="">
														<dd data-value="-1" class="lovdmx-this">请选择项目</dd>
														<c:forEach items="${rackDeviceList }" var="lst">
															<dd data-value="${lst.rackId }">${lst.rackName }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect rack_info" readonly
															value="${relayTaskDetailsVo.rackName }"
															data-value="${relayTaskDetailsVo.rackId }"
															placeholder="请选择节点" /><input type="hidden"
															class="lovdmx-input"
															value='${relayTaskDetailsVo.rackId }' name="rackId" /><input
															type="hidden" class="lovdmx-input"
															value='${relayTaskDetailsVo.rackName }' name="rackName" />
														<span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1"
															class="${relayTaskDetailsVo.rackId == null?'lovdmx-this':''}">请选择节点</dd>
														<c:forEach items="${rackDeviceList }" var="lst">
															<dd data-value="${lst.rackId }"
																class="${relayTaskDetailsVo.rackId eq lst.rackId?'lovdmx-this':'' }">${lst.rackName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="lovdmx-inline gateway_relay_select"
									id="intelligentGateway_select">
									<label class="lovdmx-form-label">网关列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty relayTaskDetailsVo.rackId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect intelligentGateway_info"
															readonly value="请选择节点" data-value="-1"
															placeholder="请选择节点" /> <input type="hidden"
															class="lovdmx-input" name=gatewayMac /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">请选择节点</dd>
														<c:forEach items="${intelligentGatewayList }" var="lst">
															<dd data-value="${lst.mac }">${lst.mac }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect intelligentGateway_info"
															readonly value="${relayTaskDetailsVo.gatewayMac }"
															data-value="${relayTaskDetailsVo.gatewayMac }"
															placeholder="请选择智能网关" /> <input type="hidden"
															class="lovdmx-input"
															value='${relayTaskDetailsVo.gatewayMac }' name=gatewayMac />
														<span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1"
															class="${relayTaskDetailsVo.gatewayMac == ''?'lovdmx-this':''}">请选择智能网关</dd>
														<c:forEach items="${intelligentGatewayList }" var="lst">
															<dd data-value="${lst.mac }"
																class="${relayTaskDetailsVo.gatewayMac eq lst.mac?'lovdmx-this':'' }">${lst.mac }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="lovdmx-inline" id="relay_select">
									<label class="lovdmx-form-label">继电器列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty relayTaskDetailsVo.gatewayMac }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect relay_info" readonly
															value="请选择智能网关" data-value="-1" placeholder="请选择智能网关" />
														<input type="hidden" class="lovdmx-input" name=relayId /><input
															type="hidden" class="lovdmx-input" name=rs485toNetIp /><input
															type="hidden" class="lovdmx-input" name=slaveAddr /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit" id="relay_select">
														<dd data-value="-1" class="lovdmx-this">请选择智能网关</dd>
														<c:forEach items="${relayList }" var="lst">
															<dd data-value="${lst.id }">${lst.rs485toNetIp }===${lst.slaveAddr }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect relay_info" readonly
															value="${relayTaskDetailsVo.rs485toNetIp }===${relayTaskDetailsVo.slaveAddr }"
															data-value="${relayTaskDetailsVo.relayId }"
															placeholder="请选择继电器" /> <input type="hidden"
															class="lovdmx-input" name=relayId /> <input
															type="hidden" class="lovdmx-input" name=rs485toNetIp /><input
															type="hidden" class="lovdmx-input" name=slaveAddr /><span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit" id="relay_select">
														<c:forEach items="${relayList }" var="lst">
															<dd data-value="${lst.id }"
																class="${lst.id eq relayTaskDetailsVo.relayId?'lovdmx-this':'' }">${lst.rs485toNetIp }===${lst.slaveAddr }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>



								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">项目列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty relayTaskDetailsVo.weekId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect week_info"
															readonly value="请选择星期" data-value="-1"
															placeholder="请选择星期" /><input type="hidden"
															class="lovdmx-input" name="weekId" /><input
															type="hidden" class="lovdmx-input" name="week" />
														<span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">请选择星期</dd>
														<c:forEach items="${weekList }" var="lst">
															<dd data-value="${lst.wId }">${lst.wWeek }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect week_info"
															readonly value="${relayTaskDetailsVo.week }"
															data-value="${relayTaskDetailsVo.weekId }"
															placeholder="请选择星期" /><input type="hidden"
															class="lovdmx-input"
															value='${relayTaskDetailsVo.weekId }' name="weekId" /><input
															type="hidden" class="lovdmx-input"
															value='${relay.week }' name="week" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择星期</dd>
														<c:forEach items="${weekList }" var="lst">
															<dd data-value="${lst.wId }"
																class="${lst.wId eq relayTaskDetailsVo.weekId?'lovdmx-this':'' }">${lst.wWeek }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="lovdmx-inline">
									<button class="lovdmx-btn lovdmxadmin-btn" type="button"
										id="relay-query">
										<i
											class="lovdmx-icon iconfont icon-sousuo lovdmxadmin-button-btn"></i>
									</button>
								</div>
							</form>
							<div class="lovdmx-table">
								<div class="table-btn">
									<%-- <button class="lovdmx-btn tableadmin-btn"
										data-link="${path}/lovdmx/ht/intelligentGateway/batchDelete.do"
										onclick="batchDelete(this);">批量删除</button> --%>
									<%-- <button class="lovdmx-btn tableadmin-btn"
										data-link="${path }/lovdmx/ht/intelligentGateway/add.do"
										id="intelligentGateway-add">添加</button> --%>
								</div>
								<div
									class="lovdmx-table-border lovdmx-table-view lovdmx-table-box">
									<table cellspacing="0" cellpadding="0" border="0" class="table">
										<thead>
											<tr>
												<th class="table-checked lovdmx-unselect">
													<div class="lovdmx-table-cell lovdmxtable-cell-checkbox">
														<i class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>编号</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>网关MAC</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>机架名</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>任务名</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>星期</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>回路操作列表</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>开启时间</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>关闭时间</span>
													</div>
												</th>
												<th class="table-last lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>操作</span>
													</div>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${empty pageInfo.list }">
													<tr>
														<td colspan="9" style="color: red; font-size: 16px;">没有数据！！</td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${pageInfo.list }" var="list"
														varStatus="vs">
														<tr class="relayTaskDetailsId${list.detlId }">
															<td class="table-checked lovdmx-unselect">
																<div class="lovdmx-table-cell lovdmxtable-cell-checkbox"
																	data-index="${list.detlId }">
																	<i
																		class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="relayTaskDetailsId">${vs.count }</span>
																</div>
															</td>
															<td class="lovdmx-unselect table-content">
																<div class="lovdmx-table-cell">
																	<span class="gatewayMac">${list.gatewayMac }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="rackName">${list.rackName }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="taskName">${list.taskName }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="week">${list.week }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="loopNums">${list.loopNums }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="startTime"><fmt:formatDate
																			value="${list.startTime}" pattern="HH:mm:ss" /></span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="endTime"><fmt:formatDate
																			value="${list.endTime}" pattern="HH:mm:ss" /></span>
																</div>
															</td>
															<td class="table-last lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<a
																		class="lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled">
																		<i class="icon-shanchu lovdmx-icon iconfont"></i> 编辑
																	</a><a
																		class="lovdmx-btn lovdmx-btn-del lovdmx-btn-xs lovdmx-btn-disabled">
																		<i class="icon-shanchu lovdmx-icon iconfont"></i> 删除
																	</a>
																</div>
															</td>
														</tr>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</tbody>
									</table>
								</div>

								<div class="lovdmx-table-page">
									<div id="table-page">
										<div class="lovdmx-box lovdmx-page-default lovdmx-lovpage">

											<c:if test="${pageInfo.isFirstPage || pageInfo.size<=0}">
												<a href="javascript:void(0);"
													class="lovdmx-page-prev lovdmx-disabled }"><i
													class="lovdmx-icon icon-zuobian iconfont"></i></a>
											</c:if>

											<c:if test="${!pageInfo.isFirstPage && pageInfo.size>0}">
												<a
													href="${path }/lovdmx/ht/relayTaskDetails/index.do?pageNo=${pageInfo.pageNum -1}&rackId=${relayTaskDetailsVo.rackId}&rackName=${relayTaskDetailsVo.rackName}&gatewayMac=${relayTaskDetailsVo.gatewayMac }&relayId=${relayTaskDetailsVo.relayId}&rs485toNetIp=${relayTaskDetailsVo.rs485toNetIp}&slaveAddr=${relayTaskDetailsVo.slaveAddr}"
													class="lovdmx-page-prev"><i
													class="lovdmx-icon icon-zuobian iconfont"></i></a>
											</c:if>
											<c:forEach begin="${pageInfo.firstPage }" var="pageNo2"
												end="${pageInfo.lastPage }">
												<a
													href="${path }/lovdmx/ht/relayTaskDetails/index.do?pageNo=${pageNo2}&rackId=${relayTaskDetailsVo.rackId}&rackName=${relayTaskDetailsVo.rackName}&gatewayMac=${relayTaskDetailsVo.gatewayMac }&relayId=${relayTaskDetailsVo.relayId}&rs485toNetIp=${relayTaskDetailsVo.rs485toNetIp}&slaveAddr=${relayTaskDetailsVo.slaveAddr}"
													class="lovdmx-page-num ${pageInfo.pageNum eq pageNo2?'lovdmx-page-curr':'' } "
													data-page="${pageNo2 }">${pageNo2 }</a>
											</c:forEach>

											<c:if test="${pageInfo.isLastPage || pageInfo.size==0}">
												<a href="javascript:void(0)"
													class="lovdmx-page-next lovdmx-disabled"><i
													class="lovdmx-icon icon-youbian iconfont"></i></a>
											</c:if>

											<c:if test="${!pageInfo.isLastPage && pageInfo.size>0}">
												<a
													href="${path }/lovdmx/ht/relayTaskDetails/index.do?pageNo=${pageInfo.pageNum +1}&rackId=${relayTaskDetailsVo.rackId}&rackName=${relayTaskDetailsVo.rackName}&gatewayMac=${relayTaskDetailsVo.gatewayMac }&relayId=${relayTaskDetailsVo.relayId}&rs485toNetIp=${relayTaskDetailsVo.rs485toNetIp}&slaveAddr=${relayTaskDetailsVo.slaveAddr}"
													class="lovdmx-page-next"><i
													class="lovdmx-icon icon-youbian iconfont"></i></a>
											</c:if>
											<input type='hidden' name="pages" value='${pageInfo.pages }'>
											<input type='hidden' name="link"
												value='${path }/lovdmx/ht/relayTaskDetails/index.do?rackId=${relayTaskDetailsVo.rackId}&rackName=${relayTaskDetailsVo.rackName}&gatewayMac=${relayTaskDetailsVo.gatewayMac }&relayId=${relayTaskDetailsVo.relayId}&rs485toNetIp=${relayTaskDetailsVo.rs485toNetIp}&slaveAddr=${relayTaskDetailsVo.slaveAddr}'>

											<span class="lovdmx-page-skip"> 到第 <input type="text"
												name="pageNo" min="1" value="${pageInfo.pageNum }"
												class="lovdmx-input"
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
												页
												<button type="button" class="lovdmx-page-btn"
													onclick="submitPageNo();">确定</button>
											</span> <span class="lovdmx-page-count"
												data-count="${pageInfo.total }">共${pageInfo.total }条</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	$(function() {

		//清除 数据		
		$("body")
				.on(
						"click",
						"#project_select .lovdmx-anim-upbit dd,#rack_select .lovdmx-anim-upbit dd",
						function() {

							console
									.log($(this).parents('#project_select').length);

							if ($(this).parents('#project_select').length == 1) {
								var $intelligentGateway_select = $("#intelligentGateway_select .lovdmx-anim-upbit");
								//移除 节点之前数据
								$intelligentGateway_select.children().remove();
								var $intelligentGateway_info = $(".intelligentGateway_info");
								$intelligentGateway_info
										.append("<dd data-value='-1' class='lovdmx-this'>请选择节点</dd>");
								//更新				
								$intelligentGateway_info.val("请选择节点");
								$intelligentGateway_info.attr("data-value",
										"-1");
							}

							var $relay_select = $("#relay_select .lovdmx-anim-upbit");
							//移除 节点之前数据
							$relay_select.children().remove();
							var $relay_info = $(".relay_info");
							$relay_info
									.append("<dd data-value='-1' class='lovdmx-this'>请选择智能网关</dd>");
							//更新				
							$relay_info.val("请选择智能网关");
							$relay_info.attr("data-value", "-1");

						});

		$("#relay-query")
				.click(
						function() {

							var $form = $(".lovdmx-form");
							//网关 MAC地址
							var gatewayMac = $(
									".lovdmx-select-title .intelligentGateway_info")
									.attr("data-value");

							//继电器id 
							var relayId = $(".lovdmx-select-title .relay_info")
									.attr("data-value");
							//切割获取485ip和 从站地址
							var relayInfo = $(
									".lovdmx-select-title .relay_info").val();
							//项目
							var projectId = $(
									".lovdmx-select-title .project_info").attr(
									"data-value");
							var projectName = $(
									".lovdmx-select-title .project_info").val();
							//节点
							var rackId = $(".lovdmx-select-title .rack_info")
									.attr("data-value");
							var rackName = $(".lovdmx-select-title .rack_info")
									.val();
							//星期
							var weekId = $(".lovdmx-select-title .week_info")
									.attr("data-value");
							var week = $(".lovdmx-select-title .week_info")
									.val();

							if (gatewayMac == -2) {
								layer.msg('该节点没有智能网关设备,请重新选择机柜列表', {
									icon : 5
								});
								return;
							} else if (relayId == -2) {
								layer.msg('该智能网关没有继电器设备,请重新选择智能网关列表', {
									icon : 5
								});
								return;
							}
							//分割
							var splitRelay = relayInfo.split("===");

							$form.find("input[name='relayId']").val(
									relayId == -1 ? null : relayId);
							$form.find("input[name='rs485toNetIp']").val(
									relayId == -1 ? null : splitRelay[0]);
							$form.find("input[name='slaveAddr']").val(
									relayId == -1 ? null : splitRelay[1]);
							$form.find("input[name='gatewayMac']").val(
									gatewayMac == -1 ? null : gatewayMac);
							$form.find("input[name='rackId']").val(
									rackId == -1 ? null : rackId);
							$form.find("input[name='rackName']").val(
									rackId == -1 ? null : rackName);
							$form.find("input[name='projectId']").val(
									projectId == -1 ? null : projectId);
							$form.find("input[name='projectName']").val(
									projectId == -1 ? null : projectName);
							$form.find("input[name='weekId']").val(
									weekId == -1 ? null : weekId);
							$form.find("input[name='week']").val(
									weekId == -1 ? null : week);

							$form.submit();
						});
	});
</script>

<script type="text/javascript" src="${path }/js/sg/tz_util.js"></script>
<script type="text/javascript" src="${path }/js/sg/sg.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_popup.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_blur.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_submit.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_upload.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_onClick.js"></script>
<script type="text/javascript"
	src="${path }/js/admin/jquery_selected_list.js"></script>
</html>