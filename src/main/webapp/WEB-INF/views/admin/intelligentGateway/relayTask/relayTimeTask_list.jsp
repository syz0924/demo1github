<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${path }/image/lei.jpg"
	type="image/x-icon" />
<link type="text/css" rel="stylesheet"
	href="${path }/css/skin/jedate.css"></link>
<link type="text/css" rel="stylesheet"
	href="${path }/css/skin/jeDate-test.css"></link>
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
<title>中控后台-继电器定时任务</title>


<style type="text/css">
</style>

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
							<a>主页</a> <span>/</span><a href="${path }/lovdmx/ht/relayTimedTask/index.do"><cite>继电器定时任务</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-pd">
							<form class="lovdmx-form"
								action="${path }/lovdmx/ht/relayTimedTask/index.do" method="GET">
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">项目列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<div class="lovdmx-select-title">
												<input type="text"
													class="lovdmx-input lovdmx_unselect project_info" readonly
													value="${timedTasks.projectName eq null?'请选择项目':timedTasks.projectName }"
													data-value="${timedTasks.projectId eq null?'-1':timedTasks.projectId }"
													placeholder="请选择项目" /> <input type="hidden"
													class="lovdmx-input" name=projectName /> <input
													type="hidden" class="lovdmx-input" name=projectId /> <span
													class="lovdmx-edge"></span>
											</div>
											<dl class="lovdmx-anim lovdmx-anim-upbit" id="project_select">
												<dd data-value="-1"
													class="${timedTasks.projectId eq null? 'lovdmx-this':'' }">请选择项目</dd>
												<c:forEach items="${projectList }" var="lst">
													<dd data-value="${lst.projectId }"
														class="${timedTasks.projectId eq lst.projectId?'lovdmx-this':'' }">${lst.projectName }</dd>
												</c:forEach>
											</dl>
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
									<label class="lovdmx-form-label">开启时间</label>
									<div class="lovdmx-input-block">
										<input type="text" name="openTime" class="lovdmx-input"
											value="${param.openTime }" id="test_time_hour1"
											placeholder="HH:mm:ss" readonly="readonly" />
									</div>
								</div>
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">关闭时间</label>
									<div class="lovdmx-input-block">
										<input type="text" name="closeTime" id="test_time_hour2"
											value="${param.closeTime }" class="lovdmx-input"
											readonly="readonly" placeholder="HH:mm:ss" />
									</div>
								</div>
								<div class="lovdmx-inline">
									<button class="lovdmx-btn lovdmxadmin-btn" type="button" id="timeTask-query">
										<i
											class="lovdmx-icon iconfont icon-sousuo lovdmxadmin-button-btn"></i>
									</button>
								</div>
							</form>
							<div class="lovdmx-table">
								<div class="table-btn">
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
														<span>项目名</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>任务名</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>任务MD5</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>机柜列表</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>星期列表</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>延时时间(ms)</span>
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
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>创建时间</span>
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
															<span class="projectName">${list.projectName }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="taskName">${list.taskName }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="taskMd5">${list.taskMd5}</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell table-content">
															<span class="rackNames">${list.rackNames }</span>
														</div>
													</td>
													<td class="lovdmx-unselect table-content">
														<div class="lovdmx-table-cell">
															<span class="weeks">${list.weeks }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="delayTime">${list.delayTime }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="createTime"><fmt:formatDate
																	value="${list.startTime}" pattern="HH:mm:ss" /></span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="createTime"><fmt:formatDate
																	value="${list.endTime}" pattern="HH:mm:ss" /></span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="createTime"><fmt:formatDate
																	value="${list.createTime}"
																	pattern="yyyy-MM-dd HH:mm:ss" /></span>
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
													href="${path }/lovdmx/ht/relayTimedTask/index.do?pageNo=${pageInfo.pageNum -1}&projectId=${timedTasks.projectId }&projectName=${timedTasks.projectName }&taskName=${param.taskName }&openTime=${param.openTime}&closeTime=${param.closeTime}"
													class="lovdmx-page-prev"><i
													class="lovdmx-icon icon-zuobian iconfont"></i></a>
											</c:if>
											<c:forEach begin="${pageInfo.firstPage }" var="pageNo2"
												end="${pageInfo.lastPage }">
												<a
													href="${path }/lovdmx/ht/relayTimedTask/index.do?pageNo=${pageNo2}&projectId=${timedTasks.projectId }&projectName=${timedTasks.projectName }&taskName=${param.taskName }&openTime=${param.openTime}&closeTime=${param.closeTime}"
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
													href="${path }/lovdmx/ht/relayTimedTask/index.do?pageNo=${pageInfo.pageNum +1}&projectId=${timedTasks.projectId }&projectName=${timedTasks.projectName }&taskName=${param.taskName }&openTime=${param.openTime}&closeTime=${param.closeTime}"
													class="lovdmx-page-next"><i
													class="lovdmx-icon icon-youbian iconfont"></i></a>
											</c:if>
											<input type='hidden' name="pages" value='${pageInfo.pages }'>
											<input type='hidden' name="link"
												value='${path }/lovdmx/ht/relayTimedTask/index.do?projectId=${timedTasks.projectId }&projectName=${timedTasks.projectName }&taskName=${param.taskName }&openTime=${param.openTime}&closeTime=${param.closeTime}'>

											<span class="lovdmx-page-skip"> 到第 <input type="text"
												name="pageNo" min="1" value="${pageInfo.pageNum }"
												class="lovdmx-input"
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
												页
												<button type="button" class="lovdmx-page-btn"
													onclick="submitPageNo();">确定1</button>
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
		$("#timeTask-query").click(
				function() {

					var $form = $(".lovdmx-form");
					//RTR MAC地址
					var projectId = $(".lovdmx-select-title .project_info")
							.attr("data-value");
					//机柜名
					var projectName = $(".lovdmx-select-title .project_info")
							.val();

					$form.find("input[name='projectId']").val(
							projectId == -1 ? null : projectId);
					$form.find("input[name='projectName']").val(
							projectId == -1 ? null : projectName);
					$form.submit();
				});
	});
</script>


<script type="text/javascript" src="${path }/js/skin/jedate-test.js"></script>
<script type="text/javascript" src="${path }/js/skin/jquery.jedate.js"></script>
<script type="text/javascript" src="${path }/js/sg/tz_util.js"></script>
<script type="text/javascript" src="${path }/js/sg/sg.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_popup.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_blur.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_onClick.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_submit.js"></script>
</html>