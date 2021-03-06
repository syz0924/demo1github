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
<title>中控后台-前台管理员</title>


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
							<a>主页</a> <span>/</span> <a><cite>用户</cite></a> <span>/</span> <a
								href="javascript:;"><cite>前端管理员</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-pd">
							<form class="lovdmx-form"
								action="${path }/lovdmx/ht/account/index.do" method="GET">
								
								
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">登录名</label>
									<div class="lovdmx-input-block">
										<input type="text" name="userName" value="${param.userName }"
											class="lovdmx-input" placeholder="请输入" />
									</div>
								</div>
								
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">项目列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty account.projectId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect select_project_list"
															readonly value="请选择项目" data-value="-1" placeholder="请选择项目" /><input
															type="hidden" class="lovdmx-input" name="projectId" /><input
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
															class="lovdmx-input lovdmx_unselect select_project_list"
															readonly value="${account.projectName }"
															data-value="${account.projectId }" placeholder="请选择项目" /><input
															type="hidden" class="lovdmx-input"
															value='${account.projectId }' name="projectId" /><input
															type="hidden" class="lovdmx-input"
															value='${account.projectName }' name="projectName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择项目</dd>
														<c:forEach items="${projectList }" var="lst">
															<dd data-value="${lst.projectId }"
																class="${account.projectId eq lst.projectId?'lovdmx-this':'' }">${lst.projectName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">角色列表</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty account.roleId }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect select_role_list"
															readonly value="请选择角色" data-value="-1" placeholder="请选择角色" /><input
															type="hidden" class="lovdmx-input" name="roleId" /><input
															type="hidden" class="lovdmx-input" name="roleName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">请选择角色</dd>
														<c:forEach items="${roleList }" var="lst">
															<dd data-value="${lst.roleId }">${lst.roleName }</dd>
														</c:forEach>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect select_role_list"
															readonly value="${account.roleName }"
															data-value="${account.roleId }" placeholder="请选择" /><input
															type="hidden" class="lovdmx-input"
															value='${account.roleId }' name="roleId" /><input
															type="hidden" class="lovdmx-input"
															value='${account.roleName }' name="roleName" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择角色</dd>
														<c:forEach items="${roleList }" var="lst">
															<dd data-value="${lst.roleId }"
																class="${account.roleId eq lst.roleId?'lovdmx-this':'' }">${lst.roleName }</dd>
														</c:forEach>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">在线状态</label>
									<div class="lovdmx-input-block">
										<div class="lovdmx-unselect lovdmx-form-select ">
											<c:choose>
												<c:when test="${empty account.isOnline }">
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect select_is_online"
															readonly value="请选择" data-value="-1" placeholder="请选择" /><input
															type="hidden" class="lovdmx-input" name="isOnline"
															value="-1" /> <span class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1" class="lovdmx-this">请选择</dd>
														<dd data-value="0">未在线</dd>
														<dd data-value="1">在线</dd>
													</dl>
												</c:when>
												<c:otherwise>
													<div class="lovdmx-select-title">
														<input type="text"
															class="lovdmx-input lovdmx_unselect select_is_online"
															readonly value="${account.isOnline eq 1 ?'在线':'未在线' }"
															data-value="${account.isOnline }" placeholder="请选择" /><input
															type="hidden" class="lovdmx-input"
															value='${account.isOnline }' name="isOnline" /> <span
															class="lovdmx-edge"></span>
													</div>
													<dl class="lovdmx-anim lovdmx-anim-upbit">
														<dd data-value="-1">请选择</dd>
														<dd data-value="0"
															class="${account.isOnline eq 0 ?'lovdmx-this':'' }">未在线</dd>
														<dd data-value="1"
															class="${account.isOnline eq 1 ?'lovdmx-this':'' }">在线</dd>
													</dl>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="lovdmx-inline">
									<button class="lovdmx-btn lovdmxadmin-btn" type="button"
										id="account-query">
										<i
											class="lovdmx-icon iconfont icon-sousuo lovdmxadmin-button-btn"></i>
									</button>
								</div>
							</form>
							<div class="lovdmx-table">
								<div class="table-btn">
									<button class="lovdmx-btn tableadmin-btn"
										data-link="${path}/lovdmx/ht/account/batchDelete.do"
										onclick="batchDelete(this);">批量删除</button>
									<button class="lovdmx-btn tableadmin-btn" id="account-add"
										data-link="${path }/lovdmx/ht/account/add.do">添加</button>
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
														<span>用户名</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>昵称</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>角色</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>项目</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>在线状态</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>账号状态</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>加入时间</span>
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
												<tr class="accountId${list.accountId }">
													<td class="table-checked lovdmx-unselect">
														<div class="lovdmx-table-cell lovdmxtable-cell-checkbox"
															data-index="${list.accountId }">
															<i
																class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="accountId">${vs.count }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="userName">${list.userName }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="nickName">${list.nickName }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="roleId">${list.roleId eq 1?"超级用户":"普通用户" }
															</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="projectName">${list.roleId eq 1?"所有":list.projectName }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="isOnline"> ${list.isOnline eq 1?"<button
																class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-green'>在线</button>":"<button
																class='lovdmx-btn lovdmx-btn-xs status lovdmx-btn-gray'>未在线</button>"}
															</span>
														</div>
													</td>
													<td class="lovdmx-unselect  account_status">
														<div class="lovdmx-table-cell">
															<input type='checkbox' class='none' lay-filter='switch'
																lay-verify='required' name='istop' lay-skin='switch'
																lay-text='ON|OFF'
																${list.status eq 1?'checked=checked':''} />
															<div
																class='status lovdmx-unselect lovdmx-form-switch ${list.status eq 1?'lovdmx-form-onswitch':''}'>
																<em>${list.status eq 1?'ON':'OFF'}</em><i></i>
															</div>
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
																data-link="${path }/lovdmx/ht/account/edit.do?accountId=${list.accountId}"
																class="lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs account-editPassword">
																<i class="icon-bianji lovdmx-icon iconfont"></i> 修改密码
															</a><a
																data-link="${path }/lovdmx/ht/account/edit.do?accountId=${list.accountId}"
																class="lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs account-edit">
																<i class="icon-bianji lovdmx-icon iconfont"></i> 编辑
															</a> <a class="lovdmx-btn lovdmx-btn-del lovdmx-btn-xs"
																data-link="${path}/lovdmx/ht/account/delete.do?accountId=${list.accountId}"
																onclick="mydel(this)"> <i
																class="icon-shanchu lovdmx-icon iconfont lovdmx-account-del"></i>
																删除
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
													href="${path }/lovdmx/ht/account/index.do?pageNo=${pageInfo.pageNum -1}&userName=${param.userName }&isOnline=${account.isOnline}&roleId=${account.roleId}&roleName=${account.roleName}&projectId=${account.projectId}&projectName=${account.projectName}"
													class="lovdmx-page-prev"><i
													class="lovdmx-icon icon-zuobian iconfont"></i></a>
											</c:if>
											<c:forEach begin="${pageInfo.firstPage }" var="pageNo2"
												end="${pageInfo.lastPage }">
												<a
													href="${path }/lovdmx/ht/account/index.do?pageNo=${pageNo2}&userName=${param.userName }&isOnline=${account.isOnline}&roleId=${account.roleId}&roleName=${account.roleName}&projectId=${account.projectId}&projectName=${account.projectName}"
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
													href="${path }/lovdmx/ht/account/index.do?pageNo=${pageInfo.pageNum +1}&userName=${param.userName }&isOnline=${account.isOnline}&roleId=${account.roleId}&roleName=${account.roleName}&projectId=${account.projectId}&projectName=${account.projectName}"
													class="lovdmx-page-next"><i
													class="lovdmx-icon icon-youbian iconfont"></i></a>
											</c:if>
											<input type='hidden' name="pages" value='${pageInfo.pages }'>
											<input type='hidden' name="link"
												value='${path }/lovdmx/ht/account/index.do?userName=${param.userName }&isOnline=${account.isOnline}&roleId=${account.roleId}&roleName=${account.roleName}&projectId=${account.projectId}&projectName=${account.projectName}'>

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
		$("#account-query").click(
				function() {

					var $form = $(".lovdmx-form");
					//在线状态
					var isOnline = $(".lovdmx-select-title .select_is_online")
							.attr("data-value");
					//角色id
					var roleId = $(".lovdmx-select-title .select_role_list")
							.attr("data-value");
					//角色名称
					var roleName = $(".lovdmx-select-title .select_role_list")
							.val();
					//角色id
					var projectId = $(
							".lovdmx-select-title .select_project_list").attr(
							"data-value");
					//角色名称
					var projectName = $(
							".lovdmx-select-title .select_project_list").val();

					$form.find("input[name='isOnline']").val(
							isOnline == -1 ? null : isOnline);
					$form.find("input[name='roleId']").val(
							roleId == -1 ? null : roleId);
					$form.find("input[name='roleName']").val(
							roleId == -1 ? null : roleName);
					$form.find("input[name='projectId']").val(
							projectId == -1 ? null : projectId);
					$form.find("input[name='projectName']").val(
							projectId == -1 ? null : projectName);
					$form.submit();
				});
	});
</script>

<script type="text/javascript" src="${path }/js/sg/tz_util.js"></script>
<script type="text/javascript" src="${path }/js/sg/sg.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_popup.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_blur.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_onClick.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_submit.js"></script>
</html>