$(function() {
	// 账号
	$("input[name='loginName']").blur(
			function() {
				var loginName = $(this).val();
				if (loginName == "" || loginName == null
						|| loginName == undefined) {
					$(this).addClass("validform-error");
					var data_null = $(this).attr("data-null");
					$(this).next(".validform-checktip").html(data_null)
							.addClass("validform-wrong").removeClass(
									"validform-right");
					return;
				} else {
					$(this).removeClass("validform-error");
					$(this).next(".validform-checktip").html("").removeClass(
							"validform-wrong").addClass("validform-right");
				}
			});
	// 密码
	$("input[name='password']")
			.blur(
					function() {
						var password = $(this).val();
						if (password == "" || password == null
								|| password == undefined) {
							$(this).addClass("validform-error");
							var data_null = $(this).attr("data-null");
							$(this).next(".validform-checktip").html(data_null)
									.addClass("validform-wrong").removeClass(
											"validform-right");
							return;
						} else {
							$(this).removeClass("validform-error");
							$(this).next(".validform-checktip").html("")
									.removeClass("validform-wrong").addClass(
											"validform-right");
						}
					});
	// 验证码
	$("input[name='code']").blur(
			function() {
				var code = $(this).val();
				if (code == "" || code == null || code == undefined
						|| code.length != 4) {
					$(this).addClass("validform-error");
					var data_null = $(this).attr("data-null");
					$(this).next(".validform-checktip").html(data_null)
							.addClass("validform-wrong").removeClass(
									"validform-right");
					return;
				} else {
					$(this).removeClass("validform-error");
					$(this).next(".validform-checktip").html("").removeClass(
							"validform-wrong").addClass("validform-right");
				}
			});
	// 点击 记住我
	$(".remember-checked span,.remember-checked .icon").click(
			function() {
				if ($(this).parent("div").hasClass("remember-ok")) {
					$(this).parent("div").find("input[name='check']").prop(
							"checked", null);
				} else {
					$(this).parent("div").find("input[name='check']").prop(
							"checked", "checked");
				}
				$(this).parent("div").toggleClass("remember-ok");

			});

	// 提交
	$("#login_button").click(
			function() {
				var loginName = encodeURIComponent($("input[name='loginName']")
						.val());
				var password = encodeURIComponent($("input[name='password']")
						.val());
				var code = encodeURIComponent($("input[name='code']").val());
				//提交信息
				var data_submit = $(this).attr("data-submit");
				// 验证码的值
				var code = $("input[name='code']").val();
				if (loginName == '' || password == '' || code == ''
						|| code.length != 4) {
					var data_error = $(this).attr("data-error");
					layer.msg(data_error, {
						icon : 2
					});
					return;
				} else {
					$.ajax({
						type : "GET",
						url : $("#pageContext").val()
								+ "/lovdmx/ht/areEqualsCode.do",
						data : "code=" + code,
						success : function(data) {
							if (data == 0) {
								var data_error = $("input[name='code']").attr(
										"data-error");
								$("input[name='code']").addClass(
										"validform-error").next(
										".validform-checktip").html(data_error)
										.addClass("validform-wrong")
										.removeClass("validform-right");
								$("input[name='code']").val("");
								// 获取新的验证码
								chageCode();
								layer.msg(data_error, {
									icon : 2
								});
								return;
							} else {
								$("#forms").submit();
								
								var loadT = layer.msg(data_submit, {
									icon : 16,
									time : 0,
									shade : [ 0.3, '#000' ]
								});
							}
						}
					});
				}

			});

});

function chageCode() {
	$('#codeImage').attr('src', 'captcha.do?' + Math.random());// 链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
}