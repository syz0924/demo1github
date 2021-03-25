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
	href="${path }/css/admin/login.css">
<link rel="stylesheet" type="text/css"
	href="${path }/css/iconfont/iconfont.css">
<script type="text/javascript" src="${path }/script/jquery.min.js"></script>
<script type="text/javascript" src="${path }/js/layui/layui.all.js"></script>
<script type="text/javascript" src="${path }/js/admin/jquery_init.js"></script>
<title>雷舞中控-后台管理系统</title>
</head>
<body>
	<div class="main">
		<div class="login">
			<form id="forms" class="loginform" method="post"
				action="${path }/lovdmx/ht/login.do">
				<div class="rlogo">
					雷舞中控-后台管理系统
				</div>
				<input type="hidden" id="pageContext" value="${path }" /> <font
					style="color: red; font-size: 16px">${sessionScope.flag }<c:remove
						var="flag" />
				</font>
				<%-- <div style="color: red;">
					<a href="${path }/lovdmx/ht/login.do?locale=zh_CN">中文<img
						src="" /></a> <a style="margin-left: 20px;"
						href="${path }/lovdmx/ht/login.do?locale=en_US">English<img
						src="" /></a>
				</div> --%>
				<div class="line">
					<input type="text" name="loginName" class="inputtxt" data-type="*"
						value='${loginName }'
						data-null="请填写账号"
						placeholder="账号" />
					<div class="validform-checktip"></div>
				</div>
				<div class="line">
					<input type="password" name="password" class="inputtxt"
						value='${password }' data-type="*"
						data-null="请填写密码"
						placeholder="密码" />
					<div class="validform-checktip"></div>
				</div>
				<div class="line yzm">
					<input type="text" class="inputtxt" name="code"
						data-null="请填写4位验证码"
						data-error="验证码错误"
						data-type="*"
						placeholder="验证码"
						style="width: 242px;">
					<div class="validform-checktip"></div>
					<img width="100" height="40" class="passcode" id="codeImage"
						onclick="this.src=this.src.split('?')[0] + '?'+new Date().getTime()"
						src="${path }/lovdmx/ht/captcha.do"
						style="border: 1px solid #ccc; float: right; cursor: pointer;"
						title="点击换一张">
				</div>
				<div class="line remember">
					<div class="remember-checked remember-ok">
						<input type="checkbox" name="check" hidden="hidden"
							checked="checked"> <span>记住我</span> <i
							class="icon iconfont icon-iconfontcheck"></i>
					</div>
				</div>
				<div class="login-btn">
					<input type="button" id="login_button"
						data-error="表单错误"
						data-submit="正在登录...."
						value="登录">
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path }/js/admin/login.js"></script>
</html>