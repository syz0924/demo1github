$(function() {
	var flag = false;
	$("body").click(function() {
		if (flag) {
			$(".lovdmx-form-select").removeClass("lovdmx-form-selected");
			flag = false;
		}

	});

	/* 折叠 */
	$(".lovdmx-scroll .lovdmx-nav-item > a")
			.click(
					function() {
						var flag = $(this).next(".lovdmx-nav-child").is(
								":visible");
						if (flag) {
							$(this).next().slideUp("fast").end("fast")
									.removeClass("selected");
							$(this).parents(".lovdmx-nav-item").removeClass(
									"lovdmx-nav-itemed");
						} else {
							$(".lovdmx-nav-item").removeClass(
									"lovdmx-nav-itemed");
							$(".lovdmx-nav-item").find("dl").slideUp("fast")
									.end("fast").removeClass("selected");
							$(this).parent(".lovdmx-nav-item").addClass(
									"lovdmx-nav-itemed");
							$(this).next(".lovdmx-nav-child").slideUp("fast");
							$(this).parents(".lovdmx-nav-item").removeClass(
									"selected");
							$(this).next().slideDown("fast").end().addClass(
									"selected");
						}
					});
	
	
	$(".lovdmx-scroll .lovdmx-nav-item dl dd > a").click(function(){
		var flag = $(this).next(".lovdmx-nav-child").is(":visible");
		
		if (flag) {
			$(this).next().slideUp("fast").removeClass("selected");
			$(this).parents("dl").find("dd").removeClass("lovdmx-nav-itemed");
		} else {
			$(this).parents("dl").find("dd").removeClass("lovdmx-nav-itemed");
			$(this).parents("dd").addClass("lovdmx-nav-itemed");
			//折叠所有展示的
			$(this).parents("dl").find("dd dl").slideUp("fast").removeClass("selected")
			$(this).next().slideDown("fast").end().addClass("selected");
			
		}
		
	});
	
	// 展示用户信息
	$(".lovdmx-header .lovdmx_admin_info").mouseenter(function() {
		$(this).find("a span").addClass("lovdmx-nav-mored");
		$(this).find("dl").addClass("block");
	}).mouseleave(function() {
		$(this).find(".lovdmx-nav-more").removeClass("lovdmx-nav-mored");
		$(this).find("dl").removeClass("block");
	});

	// 性别 展开
	$("body").on(
			"click",
			".lovdmx-select-title .lovdmx_unselect",
			function(e) {

				e = e || window.event; // event事件的兼容性写法
				
				if ($(this).parents(".lovdmx-form-select").is(
						"lovdmx-form-selected")) {
					flag = false;
				} else {
					flag = true;
				}
				$(this).parents(".lovdmx-form-select").toggleClass(
						"lovdmx-form-selected");

				if (e.stopPropagation) { // 能进来一定是支持该方法
					e.stopPropagation(); // 在主流浏览器中阻止
				} else {
					e.cancelBubble = true// true为阻止冒泡
				}

			});

	// 兼容火狐 获取event方法
	function getEvent() {
		if (window.event) {
			return window.event;
		}
		func = getEvent.caller;
		while (func != null) {
			var arg0 = func.arguments[0];
			if (arg0) {
				if ((arg0.constructor == Event
						|| arg0.constructor == MouseEvent || arg0.constructor == KeyboardEvent)
						|| (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
					return arg0;
				}
			}
			func = func.caller;
		}
		return null;
	}

	// 获取选中列表
	$("body").on(
			"click",
			".lovdmx-anim-upbit dd",
			function() {

				$(this).parent(".lovdmx-anim-upbit").find("dd").removeClass("lovdmx-this");
				$(this).addClass("lovdmx-this");
				$(this).parents(".lovdmx-form-select").removeClass(
						"lovdmx-form-selected");
				// 获取值
				var value = $(this).html();
				var index = $(this).attr("data-value");
				$(this).parents(".lovdmx-anim-upbit").prev(
						".lovdmx-select-title").find(".lovdmx_unselect").val(
						value).attr("data-value", index);
			});
	
	// 获取选中角色id
	$("body").on(
			"click",
			".lovdmx-anim-upbit.account-role dd",
			function() {

				$(this).parent(".lovdmx-anim-upbit").find("dd").removeClass("lovdmx-this");
				$(this).addClass("lovdmx-this");
				$(this).parents(".lovdmx-form-select").removeClass(
						"lovdmx-form-selected");
				// 获取值
				var value = $(this).html();
				var roleId = $(this).attr("data-value");
				if(roleId==1 || roleId==-1){
					$(this).parents(".lovdmx-form-item").next().addClass("none");
				}else{
					$(this).parents(".lovdmx-form-item").next().removeClass("none");
				}
			});
	
	// 点击性别
	$("body").on(
			"click",
			".lovdmx-form-radio",
			function() {

				$(this).addClass("lovdmx-form-radioed");
				$(this).find(".lovdmx-icon").addClass("icon-circleyuanquan")
						.removeClass("icon-circle2yuanquan");

				// 性别
				var sex = $(this).prev().val();
				if (sex == "男") {
					$(this).nextAll(".lovdmx-form-radio").removeClass(
							"lovdmx-form-radioed");
					$(this).nextAll(".lovdmx-form-radio").find(".lovdmx-icon")
							.addClass("icon-circle2yuanquan").removeClass(
									"icon-circleyuanquan");
				} else if (sex == "女") {
					$(this).prevAll(".lovdmx-form-radio").removeClass(
							"lovdmx-form-radioed");
					$(this).prevAll(".lovdmx-form-radio").find(".lovdmx-icon")
							.addClass("icon-circle2yuanquan").removeClass(
									"icon-circleyuanquan");
				}

			});

	// 复选框
	$("thead .lovdmxtable-cell-checkbox .icon-ok").click(
			function() {
				var objs = $("tbody .lovdmxtable-cell-checkbox");

				if (!($(this).parents(".lovdmxtable-cell-checkbox")
						.is('.lovdmxtable-cell-checked'))) {
					objs.addClass("lovdmxtable-cell-checked");
					$(this).parents(".lovdmxtable-cell-checkbox").addClass(
							"lovdmxtable-cell-checked");
				} else {
					objs.removeClass("lovdmxtable-cell-checked");
					$(this).parents(".lovdmxtable-cell-checkbox").removeClass(
							"lovdmxtable-cell-checked");
				}
			});
	
	//复选框
	$("body").on("click",".lovdmx-input-block .lovdmx-form-checkbox span,.lovdmx-input-block .lovdmx-form-checkbox i",function(){
		
		var $parent = $(this).parent(".lovdmx-form-checkbox");
		if($parent.hasClass("lovdmx-form-checked")){
			$parent.removeClass("lovdmx-form-checked");
			$parent.prev().attr("checked",null);
		}else{
			$parent.addClass("lovdmx-form-checked");
			$parent.prev().attr("checked","checked");
		}
		
	});

	$("body")
			.on(
					"click",
					"#lovdmx-popup-window .lovdmx-form-checkbox",
					function() {
						$(
								"#lovdmx-popup-window .lovdmx-input-block .lovdmx-form-checkbox")
								.removeClass("lovdmxtable-cell-checked");

						$(this).addClass("lovdmxtable-cell-checked");

						var parameterId = $(this).attr("data-id");
						$("#lovdmx-popup-window .new-parameter").addClass(
								"none");
						$(
								"#lovdmx-popup-window .upload-parameter"
										+ parameterId).removeClass("none");
					});

	// 单选框
	$("tbody .lovdmxtable-cell-checkbox .icon-ok").click(function() {
		var $checkbox = $(this).parents(".lovdmxtable-cell-checkbox");
		if (!($checkbox.is('.lovdmxtable-cell-checked'))) {
			$checkbox.addClass("lovdmxtable-cell-checked");
		} else {

			$checkbox.removeClass("lovdmxtable-cell-checked");
		}
	});

	$("body").on("click", ".close", close);
	$("body").on("click", "#lovdmx-shade2", close);

	// 拖拽功能(主要是触发三个事件：onmousedown\onmousemove\onmouseup)
	// 点击某物体时，用drag对象即可，move和up是全局区域，也就是整个文档通用，应该使用document对象而不是drag对象(否则，采用drag对象时物体只能往右方或下方移动)
	$("body").on("mousedown", "#drag", function(e) {

		var e = e || window.event; // 兼容ie浏览器

		var drag = document.getElementById('drag');
		var lov_popup = document.getElementById('lovdmx-popup-window');

		var diffX = e.clientX - lov_popup.offsetLeft;// 鼠标点击物体那一刻相对于物体左侧边框的距离=点击时的位置相对于浏览器最左边的距离-物体左边框相对于浏览器最左边的距离
		var diffY = e.clientY - lov_popup.offsetTop;
		/*
		 * 低版本ie bug:物体被拖出浏览器可是窗口外部时，还会出现滚动条，
		 * 解决方法是采用ie浏览器独有的2个方法setCapture()\releaseCapture(),这两个方法，
		 * 可以让鼠标滑动到浏览器外部也可以捕获到事件，而我们的bug就是当鼠标移出浏览器的时候，
		 * 限制超过的功能就失效了。用这个方法，即可解决这个问题。注：这两个方法用于onmousedown和onmouseup中
		 */

		if (typeof drag.setCapture != 'undefined') {
			drag.setCapture();
		}

		document.onmousemove = function(e) {
			var e = e || window.event; // 兼容ie浏览器

			var left = e.clientX - diffX;
			var top = e.clientY - diffY;

			if (left < 0) {
				left = 0;
			} else if (left > window.innerWidth - lov_popup.offsetWidth) {
				left = window.innerWidth - lov_popup.offsetWidth;
			}

			if (top < 0) {
				top = 0;
			} else if (top > window.innerHeight - lov_popup.offsetHeight) {
				top = window.innerHeight - lov_popup.offsetHeight;
			}

			// 移动时重新得到物体的距离，解决拖动时出现晃动的现象
			lov_popup.style.left = left + 'px';
			lov_popup.style.top = top + 'px';
		}

		document.onmouseup = function(e) { // 当鼠标弹起来的时候不再移动
			this.onmousemove = null;
			this.onmouseup = null; // 预防鼠标弹起来后还会循环（即预防鼠标放上去的时候还会移动）

			// 修复低版本ie bug
			if (typeof drag.releaseCapture != 'undefined') {
				drag.releaseCapture();
			}
		}

	});

	// 调整大小
	$("body")
			.on(
					"mousedown",
					"#lovdmx-resize",
					function(e) {
						e = e || window.event;

						var drag = document.getElementById('drag');
						var lov_popup = document
								.getElementById('lovdmx-popup-window');
						var resize = document.getElementById('lovdmx-resize');
						var lovdmx_content = document
								.getElementsByClassName('lovdmx-content')[0];

						var diffX = e.clientX - resize.offsetLeft;// 鼠标点击物体那一刻相对于物体左侧边框的距离=点击时的位置相对于浏览器最左边的距离-物体左边框相对于浏览器最左边的距离
						var diffY = e.clientY - resize.offsetTop;

						document.onmousemove = function(ev) {
							ev = ev || event;

							var width = ev.clientX - diffX;
							var height = ev.clientY - diffY;

							if (width < 300) {
								width = 300;
							} else if (width > ev.clientX - diffX
									+ resize.offsetWidth) {
								width = ev.clientX - diffX + resize.offsetWidth;
							}

							if (height < 120) {
								height = 120;
							} else if (height > ev.clientY - diffY
									+ resize.offsetHeight) {
								height = ev.clientY - diffY
										+ resize.offsetHeight;
							}

							lov_popup.style.width = width + 'px';
							lov_popup.style.height = height + 'px';
							lovdmx_content.style.height = (height - drag.offsetHeight)
									+ 'px';
						}

						document.onmouseup = function() {
							this.onmousemove = null;
							this.onmouseup = null; // 预防鼠标弹起来后还会循环（即预防鼠标放上去的时候还会移动）

						}
					});
	//扩展
	$("body").on("click",".kz",function(){
		
		if(isFullscreen()){
			$(this).find("i").addClass("icon-quanpingxianshi").removeClass("icon-quanpingsuoxiao");
			exitFullscreen();
		}else{
			$(this).find("i").addClass("icon-quanpingsuoxiao").removeClass("icon-quanpingxianshi");
			fullscreen();
		}
	});
	
	//添加 文本内容溢出按钮
	$(".lovdmx-table").on("mouseenter",".lovdmx-unselect",function(){
	//$(".lovdmx-table .lovdmx-unselect").hover(function(){
		//获取元素宽
		var width=$(this).width();
		//获取文本宽度
		var spanWidth=$(this).find("span").width();
		//添加30 内边距（左右各15）
		spanWidth +=40;
		var ss;
		//判断内容是否溢出
		if(spanWidth>=width){
			var obj = $(this).find("div");
			//添加 展示内容按钮 
			addTableGridDown(obj);
			
		}
	});
	
	//移除 文本内容溢出按钮
	$(".lovdmx-table").on("mouseleave",".lovdmx-unselect",function(){
		$(this).find(".lovdmx-table-grid-down").remove();
	});
	
	// 展示溢出的详细信息
	$("body").on("click",".lovdmx-table .lovdmx-unselect .lovdmx-table-grid-down",function(){
		//获取 展示溢出内容元素
		var $show_tip_content= $("#lovdmx-tips-content");
		//判断是否存在，不存在添加
		if($show_tip_content.length==0){
			showOverflowContentInfo();
			$show_tip_content= $("#lovdmx-tips-content");
		}
		//获取前一个兄弟元素
		var this_prev= $(this).prev();
		//距离浏览器顶部的距离
		var top=$(this_prev).offset().top;
		//距离浏览器左部的距离
		var left=$(this_prev).offset().left;
		//内容
		var content=$(this_prev).find("span").html();
		//获取 溢出内容元素 的高
		var height=$(this_prev).height();
		//加上内边距和边框
		top +=height+10;
		
		//赋值内容
		$show_tip_content.find(".lovdmx-table-tips-main").html(content);
		//赋值定位
		$show_tip_content.css({"top":top,"left":left});
		
	});
	
	//关闭 展示溢出信息
	$("body").on("click","#lovdmx-tips-content .lovdmx-icon-close",function(){
		$("#lovdmx-tips-content").remove();
	});	
	
	

});
// 关闭弹出框
function close() {
	$("#lovdmx-shade2").remove();
	$("#lovdmx-popup-window").remove();
}

// 删除
function mydel(obj) {
	$.tzAlert({
		title : "温馨提示 ！",
		content : "您确定要删除吗？",
		callback : function(ok) {
			if (ok) {
				// 路径
				var path = $(obj).attr("data-link");
				var del = $(obj).parents("tr");
				$(del).remove();
				$.ajax({
					type : "GET",
					url : path,
					success : function(msg) {
						loading(msg, 3);
					}
				});
			}
		}
	});
}
// 批量删除
function batchDelete(obj) {

	var list = $("tbody tr").find(".lovdmxtable-cell-checked");
	if (list.length == 0) {
		layer.alert("请选中要删除的序号");
		return;
	}
	// 路径
	var path = $(obj).attr("data-link");
	$.tzAlert({
		title : "温馨提示 ！",
		content : "您确定要删除吗？",
		callback : function(ok) {
			if (ok) {
				var list = $("tbody tr").find(".lovdmxtable-cell-checked");
				var arrIds = new Array();
				for (var i = 0; i < list.length; i++) {
					arrIds[i] = list[i].getAttribute("data-index");
					$(list[i]).parents("tr").remove();
				}
				$.ajax({
					type : "GET",
					url : path,
					data : "arrIds=" + arrIds,
					success : function(msg) {
						loading(msg, 3);
					}
				});
			}
		}
	});
}

// 提交分页
function submitPageNo() {

	var pages = $("input[name='pages']").val();

	var pageNo = $("input[name='pageNo']").val();
	if (pageNo > pages || pageNo <= 0) {
		layer.alert("分页必须大于0且小于" + (parseInt(pages) + 1) + "页");
		return;
	}
	var _href = $("input[name='link']").val();

	window.location.href = _href + "&pageNo=" + pageNo;
}

// 休眠
function sleep(n) {

	var start = new Date().getTime();

	while (true)
		if (new Date().getTime() - start > n)
			break;

}



var fullscreen=function(){
	
    elem=document.body;
    if(elem.webkitRequestFullScreen){
        elem.webkitRequestFullScreen();   
    }else if(elem.mozRequestFullScreen){
        elem.mozRequestFullScreen();
    }else if(elem.requestFullScreen){
        elem.requestFullscreen();
    }else{
        //浏览器不支持全屏API或已被禁用
    }
}
var exitFullscreen=function(){
    var elem=document;
    if(elem.webkitCancelFullScreen){
        elem.webkitCancelFullScreen();    
    }else if(elem.mozCancelFullScreen){
        elem.mozCancelFullScreen();
    }else if(elem.cancelFullScreen){
        elem.cancelFullScreen();
    }else if(elem.exitFullscreen){
        elem.exitFullscreen();
    }else{
        //浏览器不支持全屏API或已被禁用
    }
}
function isFullscreen(){
    return document.fullscreenElement    ||
           document.msFullscreenElement  ||
           document.mozFullScreenElement ||
           document.webkitFullscreenElement || false;
}

// 文本溢出 添加按钮
function addTableGridDown(obj){
	var addGridBut="";
	addGridBut +="<div class='lovdmx-table-grid-down'>";
		addGridBut +="<i class='lovdmx-icon lovdmx-icon-down lovdmx-icon iconfont icon-xiala1'><i/>"
	addGridBut +="</div>";
	$(obj).after(addGridBut);
}

//展示 文本溢出信息
function showOverflowContentInfo(){
	
	var addOverflowContent="";
	addOverflowContent +="<div class='lovdmx-layer lovdmx-layer-tips lovdmx-table-tips' id='lovdmx-tips-content'  style='z-index: 19891023; position: absolute;'>";
		addOverflowContent +="<div id='' class='lovdmx-layer-content'>";
			addOverflowContent +="<div class='lovdmx-table-tips-main' style='margin-top: -44px;'>11111@qq.com</div>";
			addOverflowContent +="<i class='lovdmx-icon lovdmx-table-tips-c lovdmx-icon-close iconfont icon-ic_close'></i>";
		addOverflowContent +="</div>";
		addOverflowContent +="<span class='lovdmx-layer-setwin'></span>";
	addOverflowContent +="</div>";
	$("body").append(addOverflowContent);
}



