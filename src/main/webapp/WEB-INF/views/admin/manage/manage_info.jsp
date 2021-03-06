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
<title>雷舞后台-后台管理员</title>

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
						<div class="lovdmx-row lovdmx-col-space15">
							<div class="lovdmx-col-md12">
								<div class="lovdmx-card">
									<div class="lovdmx-card-header">设置我的资料</div>
									<div class="lovdmx-card-body">
										<div class="lovdmx-form">
											<input type="hidden" value="${manageInfo.manageId }" name="manageId" />
											<div class="lovdmx-form-item">
												<label class="lovdmx-form-label">用户名</label>
												<div class="lovdmx-input-inline">
													<input type="text" name="loginName"
														value="${manageInfo.loginName }" readonly="readonly"
														class="lovdmx-input" />
												</div>
												<div class="lovdmx-form-mid lovdmx-word-aux">不可修改。一般用于后台登入名</div>
											</div>
											<div class="lovdmx-form-item">
												<label class="lovdmx-form-label">昵称</label>
												<div class="lovdmx-input-inline">
													<input type="text" name="nickName"
														value="${manageInfo.nickName }" class="lovdmx-input" />
												</div>
											</div>
											<div class="lovdmx-form-item">
												<label class="lovdmx-form-label">手机</label>
												<div class="lovdmx-input-inline">
													<input type="text" name="phone"
														value="${manageInfo.phone }" class="lovdmx-input" />
												</div>
											</div>
											<div class="lovdmx-form-item">
												<label class="lovdmx-form-label">邮箱</label>
												<div class="lovdmx-input-inline">
													<input type="text" name="email"
														value="${manageInfo.email }" class="lovdmx-input" />
												</div>
											</div>
											<div class="lovdmx-form-item">
												<div class="lovdmx-input-block">
													<button class="lovdmx-btn" id="update-manage">确认修改</button>
													<button class="lovdmx-btn lovdmx-btn-primary"
														onclick="javascript:history.go(0);">重新填写</button>
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
		</div>
	</div>
</body>

<script type="text/javascript">
	$(function() {
		$("#update-manage")
				.click(
						function() {

							//jquery 对象
							var $manageId = $("input[name='manageId']");
							var $nickName = $("input[name='nickName']");
							var $phone = $("input[name='phone']");
							var $email = $("input[name='email']");

							//值
							var manageId = $manageId.val();
							var nickName = $nickName.val();
							var phone = $phone.val();
							var email = $email.val();

							if (nickName == "" || nickName == null
									|| nickName == undefined) {
								layer.msg('必填项不能为空', {
									icon : 5
								});
								$nickName.addClass("lovdmx-form-danger")
										.focus();
								return;
							}

							var myreg = /^1[34578]\d{9}$/;
							if (phone != "" && phone != null
									&& phone != undefined) {
								if (!myreg.test(phone)) {
									layer.msg('请输入有效的手机号码！', {
										icon : 5
									});

									$phone.addClass("lovdmx-form-danger").val(
											"").focus();
									return false;
								}
							}

							varreg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
							if (email != "" && email != null
									&& email != undefined) {
								if (!varreg.test(email)) {
									layer.msg('邮箱格式不正确，请重新输入！', {
										icon : 5
									});
									$email.addClass("lovdmx-form-danger").val(
											"").focus();
									return false;
								}
							}

							$.ajax({
								type : "POST",
								url : $("#pageContext").val()
										+ "/lovdmx/ht/manage/updateInfo.do",
								data : "manageId=" + manageId + "&nickName="
										+ nickName + "&phone=" + phone
										+ "&email=" + email,
								success : function(msg) {
									layer.alert(msg, {
										icon : 6
									});

								}
							});

						});
	});
</script>


<script type="text/javascript" src="${path }/js/sg/tz_util.js"></script>
<script type="text/javascript" src="${path }/js/sg/sg.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_popup.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_blur.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_submit.js"></script>
</html>