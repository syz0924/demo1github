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

<title>中控后台-从录放精灵定时任务列表</title>

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
								href="${path }/lovdmx/ht/spriteTimedTask/index.do"><cite>从录放精灵定时任务列表</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-pd">
							<form class="lovdmx-form"
								action="${path }/lovdmx/ht/spriteTimedTask/index.do" method="GET">
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">项目列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select " >
											<c:choose>
												<c:when test="${empty task.projectId }">
													<div class="lovdmx-select-title">
														<input type="text" class="lovdmx-input lovdmx_unselect project_info"
															readonly value="请选择项目" data-value="-1" placeholder="请选择项目" /><input
															type="hidden" class="lovdmx-input" name="projectId" /><input
															type="hidden" class="lovdmx-input" name="projectName" /> <span
															class="lovdmx-edge"></span>
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
														<input type="text" class="lovdmx-input lovdmx_unselect project_info"
															readonly value="${task.projectName }"
															data-value="${task.projectId }" placeholder="请选择项目" /><input
															type="hidden" class="lovdmx-input"
															value='${task.projectId }' name="projectId" /><input
															type="hidden" class="lovdmx-input" value='${task.projectName }' name="projectName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择项目</dd>
														<c:forEach items="${projectList }" var="lst">
															<dd data-value="${lst.projectId }"
																class="${task.projectId eq lst.projectId?'lovdmx-this':'' }">${lst.projectName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">任务名称</label>
									<div class="lovdmx-input-block">
										<input type="text" name="taskName" value="${param.taskName }"
											class="lovdmx-input" placeholder="请输入" />
									</div>
								</div>

								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">任务模式</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty task.taskTypeId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect taskType_info"
															readonly value="所有" data-value="-1" placeholder="请选择" /><input
															type="hidden" class="lovdmx-input" name="taskType" /><input
															type="hidden" class="lovdmx-input" name="taskTypeId" />
														<span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">所有</dd>
														<c:forEach items="${taskTypeList }" var="lst">
															<dd data-value="${lst.index }">${lst.value }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect taskType_info"
															readonly value="${task.taskType }"
															data-value="${task.taskTypeId }" placeholder="请选择" /> <input
															type="hidden" class="lovdmx-input"
															value='${task.taskType }' name="taskType" /> <input
															type="hidden" class="lovdmx-input"
															value='${task.taskTypeId }' name="taskTypeId" /><span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">所有</dd>
														<c:forEach items="${taskTypeList }" var="lst">
															<dd data-value="${lst.index }"
																class="${task.taskTypeId eq lst.index?'lovdmx-this':'' }">${lst.value }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">循环模式</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty task.cyclicModeId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect cyclicMode_info"
															readonly value="所有" data-value="-1" placeholder="请选择" /><input
															type="hidden" class="lovdmx-input" name="cyclicMode" /><input
															type="hidden" class="lovdmx-input" name="cyclicModeId" />
														<span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">所有</dd>
														<c:forEach items="${cyclicModeList }" var="lst">
															<dd data-value="${lst.index }">${lst.value }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect cyclicMode_info"
															readonly value="${task.cyclicMode }"
															data-value="${task.cyclicModeId }" placeholder="请选择" />
														<input type="hidden" class="lovdmx-input"
															value='${task.cyclicMode }' name="cyclicMode" /> <input
															type="hidden" class="lovdmx-input"
															value='${task.cyclicModeId }' name="cyclicModeId" /><span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">所有</dd>
														<c:forEach items="${cyclicModeList }" var="lst">
															<dd data-value="${lst.index }"
																class="${task.cyclicModeId eq lst.index?'lovdmx-this':'' }">${lst.value }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">时间范围</label>
									<div class="lovdmx-input-block">
										<input type="text" name="timeScope" class="lovdmx-input"
											value="${param.timeScope }" id="test1" placeholder="HH:mm:ss"
											readonly="readonly" />
									</div>
								</div>

								<div class="lovdmx-inline">
									<button class="lovdmx-btn lovdmxadmin-btn" type="button"
										id="timedTask-query">
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
														<span>定时任务名称</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>任务md5</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>任务模式</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>循环模式</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>循环日期</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>开始日期</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>结束日期</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>开始时间</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>结束时间</span>
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
														<tr class="taskId${list.taskId }">
															<td class="table-checked lovdmx-unselect">
																<div class="lovdmx-table-cell lovdmxtable-cell-checkbox"
																	data-index="${list.taskId }">
																	<i
																		class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="taskId">${vs.count }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="taskName">${list.taskName }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="taskMd5">${list.taskMd5 }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="taskType">${list.taskType }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="cyclicMode">${list.cyclicMode }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="cyclicDate">${list.cyclicDate }</span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="startDate"><fmt:formatDate
																			value="${list.startDate}" pattern="yyyy-MM-dd" /></span>
																</div>
															</td>
															<td class="lovdmx-unselect">
																<div class="lovdmx-table-cell">
																	<span class="endDate"><fmt:formatDate
																			value="${list.endDate}" pattern="yyyy-MM-dd" /></span>
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
													href="${path }/lovdmx/ht/spriteTimedTask/index.do?pageNo=${pageInfo.pageNum -1}&projectId=${task.projectId }&projectName=${task.projectName }&taskName=${param.taskName }&taskType=${param.taskType}&taskTypeId=${param.taskTypeId}&cyclicMode=${param.cyclicMode}&cyclicModeId=${param.cyclicModeId}&timeScope=${param.timeScope}"
													class="lovdmx-page-prev"><i
													class="lovdmx-icon icon-zuobian iconfont"></i></a>
											</c:if>
											<c:forEach begin="${pageInfo.firstPage }" var="pageNo2"
												end="${pageInfo.lastPage }">
												<a
													href="${path }/lovdmx/ht/spriteTimedTask/index.do?pageNo=${pageNo2}&projectId=${task.projectId }&projectName=${task.projectName }&taskName=${param.taskName }&taskType=${param.taskType}&taskTypeId=${param.taskTypeId}&cyclicMode=${param.cyclicMode}&cyclicModeId=${param.cyclicModeId}&timeScope=${param.timeScope}"
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
													href="${path }/lovdmx/ht/spriteTimedTask/index.do?pageNo=${pageInfo.pageNum +1}&projectId=${task.projectId }&projectName=${task.projectName }&taskName=${param.taskName }&taskType=${param.taskType}&taskTypeId=${param.taskTypeId}&cyclicMode=${param.cyclicMode}&cyclicModeId=${param.cyclicModeId}&timeScope=${param.timeScope}"
													class="lovdmx-page-next"><i
													class="lovdmx-icon icon-youbian iconfont"></i></a>
											</c:if>
											<input type='hidden' name="pages" value='${pageInfo.pages }'>
											<input type='hidden' name="link"
												value='${path }/lovdmx/ht/spriteTimedTask/index.do?projectId=${task.projectId }&projectName=${task.projectName }&taskName=${param.taskName }&taskType=${param.taskType}&taskTypeId=${param.taskTypeId}&cyclicMode=${param.cyclicMode}&cyclicModeId=${param.cyclicModeId}&timeScope=${param.timeScope}'>

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
<script type="text/javascript"
	src="${path }/js/layui/lay/modules/laydate.js"></script>
<script type="text/javascript">
	layui.use('laydate', function() {
		var laydate = layui.laydate;

		//时间范围
		laydate.render({
			elem : '#test1',
			type : 'time',
			range : true
		});
	});

	$(function() {
		$("#timedTask-query").click(
				function() {

					var $form = $(".lovdmx-form");
					//获取jquery对象
					var $project = $(".lovdmx-select-title .project_info");
					var $taskType = $(".lovdmx-select-title .taskType_info");
					var $cyclicMode = $(".lovdmx-select-title .cyclicMode_info");
					//获取值
					var projectId=$project.attr("data-value");
					var projectName=$project.val();
					var taskType = $taskType.val();
					var taskTypeId = $taskType.attr("data-value");
					var cyclicMode=$cyclicMode.val();
					var cyclicModeId=$cyclicMode.attr("data-value");
					//赋值
					$form.find("input[name='projectName']").val(
							projectId == -1 ? null : projectName);
					$form.find("input[name='projectId']").val(
							projectId == -1 ? null : projectId);
					$form.find("input[name='taskType']").val(
							taskTypeId == -1 ? null : taskType);
					$form.find("input[name='taskTypeId']").val(
							taskTypeId == -1 ? null : taskTypeId);
					$form.find("input[name='cyclicMode']").val(
							cyclicModeId == -1 ? null : cyclicMode);
					$form.find("input[name='cyclicModeId']").val(
							cyclicModeId == -1 ? null : cyclicModeId);
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
</html>