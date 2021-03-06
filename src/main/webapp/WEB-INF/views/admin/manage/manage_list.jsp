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
<title>中控后台-用户管理员</title>

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
								href="javascript:;"><cite>后台管理员</cite></a>
						</div>
					</div>
					<div class="lovdmx-fluid">
						<div class="lovdmx-pd">
							<form class="lovdmx-form"
								action="${path }/lovdmx/ht/manage/index.do"
								method="GET">
								<div class="lovdmx-inline">
									<label class="lovdmx-form-label">登录名</label>
									<div class="lovdmx-input-block">
										<input type="text" name="loginName"
											value="${param.loginName }" class="lovdmx-input"
											placeholder="请输入" />
									</div>
								</div>
								<div class="lovdmx-inline">
									<button class="lovdmx-btn lovdmxadmin-btn" type="submit">
										<i
											class="lovdmx-icon iconfont icon-sousuo lovdmxadmin-button-btn"></i>
									</button>
								</div>
							</form>
							<div class="lovdmx-table">
								<div class="table-btn">
									<button class="lovdmx-btn tableadmin-btn"
										data-link="${path}/lovdmx/ht/manage/batchDelete.do"
										onclick="batchDelete(this);">批量删除</button>
									<button class="lovdmx-btn tableadmin-btn" id="manage-add">添加</button>
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
														<span>手机</span>
													</div>
												</th>
												<th class="lovdmx-unselect">
													<div class="lovdmx-table-cell">
														<span>邮箱</span>
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
											<c:forEach items="${manageList }" var="list" varStatus="vs">
												<tr class="manageId${list.manageId }">
													<td class="table-checked lovdmx-unselect">
														<div class="lovdmx-table-cell lovdmxtable-cell-checkbox"
															data-index="${list.manageId }">
															<i
																class="icon-iconfontcheck lovdmx-icon iconfont icon-ok"></i>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="manageId">${vs.count }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="loginName">${list.loginName }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="nickName">${list.nickName }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="phone">${list.phone }</span>
														</div>
													</td>
													<td class="lovdmx-unselect">
														<div class="lovdmx-table-cell">
															<span class="email">${list.email }</span>
														</div>
													</td>
													<td class="lovdmx-unselect manage_status">
														<div class="lovdmx-table-cell">
															<input type='checkbox' class='none' lay-filter='switch'
																	lay-verify='required' name='istop' lay-skin='switch'
																	lay-text='ON|OFF' ${list.status eq 1?'checked=checked':''} />
																<div class='lovdmx-unselect lovdmx-form-switch ${list.status eq 1?'lovdmx-form-onswitch':''}'>
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
																data-link="${path }/lovdmx/ht/manage/edit.do?manageId=${list.manageId}"
																class="lovdmx-btn lovdmx-btn-edit lovdmx-btn-xs manage-edit">
																<i class="icon-bianji lovdmx-icon iconfont"></i> 编辑
															</a> <a class="lovdmx-btn lovdmx-btn-del lovdmx-btn-xs"
																data-link="${path}/lovdmx/ht/manage/delete.do?manageId=${list.manageId}"
																onclick="mydel(this)"> <i
																class="icon-shanchu lovdmx-icon iconfont lovdmx-manage-del"></i>
																删除
															</a>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript" src="${path }/js/sg/tz_util.js"></script>
<script type="text/javascript" src="${path }/js/sg/sg.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_popup.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_blur.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_onClick.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_submit.js"></script>
</html>