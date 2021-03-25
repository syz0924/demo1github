jQuery(function($) {
	// 初始化内容

	// URL地址
	var pathname = window.location.pathname;
	// 过滤问号后面的信息
	var pathname = pathname.split("?")[0];

	var _list = $(".lovdmx-side .lovdmx-nav .lovdmx-nav-item dl dd a");
	
	// 临时 解决方法 （后期修改）
	if(pathname=="/lovdmx_web/lovdmx/ht/messageFeedback/detail.do"){
		pathname="/lovdmx_web/lovdmx/ht/messageFeedback/index.do";
	}
	
	var _element;

	for (var i = 0; i < _list.length; i++) {
		//判断 URL 地址是否包含 adress地址
		var address = $(_list[i]).attr("data-href");
		if (pathname.indexOf(address) >= 0) {
			//获取 选中的对象
			_element = $(_list[i]);
			break;
		}
	}
	$(_element).addClass("lovdmx-this");
	var nav_parent = $(_element).parents(".lovdmx-nav-item");
	// 展开
	$(nav_parent).addClass("lovdmx-nav-itemed");
	$(_element).parents(".lovdmx-nav-child").parents("dd").addClass(
			"lovdmx-nav-itemed");
	$(_element).parents(".lovdmx-nav-child").slideDown("fast").end().addClass(
			"selected");

});