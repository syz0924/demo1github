<%@ page pageEncoding="UTF-8"%>
<input type="hidden" id="pageContext" value="${path }" />
<div class="lovdmx-header">
	<ul class="lovdmx-head-left lovdmx-nav fl_child clearFix">
		<li class="lovdmx-nav-item lovdmxadmin-flexible"><a
			href="javascript:;"><i
				class="lovdmx-icon icon-cebianlan iconfont" title="侧边伸缩"></i></a></li>
		<li class="lovdmx-nav-item lovdmxadmin-flexible"><a
			href="${path }/"><i class="lovdmx-icon iconfont icon-hulianwang"
				title="前台"></i></a></li>
		<li class="lovdmx-nav-item lovdmxadmin-flexible"><a
			href="javascript:history.go(0);"><i
				class="lovdmx-icon iconfont icon-shuaxin" title="刷新"></i></a></li>
		<li class="lovdmx-nav-item lovdmx-hide-xs"><input type="text"
			placeholder="搜索..." autocomplete="off"
			class="lovdmx-input lovdmx-input-search"></li>
		<span class="lovdmx-nav-bar"></span>
	</ul>
	<ul class="lovdmx-head-right lovdmx-nav">
		<li class="lovdmx-nav-item lovdmxadmin-flexible"><a
			href="${path }/lovdmx/ht/messageFeedback/index.do"><i
				class="lovdmx-icon iconfont icon-tongzhi1" title="通知"></i> <c:if
					test="${sessionScope.unreadMessageList.size() >0 }">
					<span class="lovdmx-badge-dot"></span>
				</c:if></a></li>
		<li class="lovdmx-nav-item lovdmxadmin-flexible"><a
			href="javascript:;"><i class="lovdmx-icon iconfont icon-biaoqian"
				title="标签"></i></a></li>
		<li class="lovdmx-nav-item lovdmxadmin-flexible"><a class="kz"
			href="javascript:;"><i
				class="lovdmx-icon iconfont icon-quanpingxianshi"></i></a></li>
		<li class="lovdmx-nav-item lovdmxadmin-flexible lovdmx_admin_info">
			<a href="javascript:;"><cite>${sessionScope.iadmin.nickName }</cite><span
				class="lovdmx-nav-more"></span></a>
			<dl class="lovdmx-nav-child lovdmx-anim">
				<dd>
					<a href="${path }/lovdmx/ht/manage/info.do?manageId=${sessionScope.iadmin.manageId }">基本信息</a>
				</dd>
				<dd>
					<a href="${path }/lovdmx/ht/manage/upadtePwd.do">修改密码</a>
				</dd>
				<dd>
					<a class="quit" href="${path }/lovdmx/ht/remove.do">退出</a>
				</dd>
			</dl>
		</li>
		<li class="lovdmx-nav-item lovdmxadmin-flexible"><a
			href="javascript:;"><i class="lovdmx-icon iconfont icon-ziyuan"
				title="信息"></i></a></li>
	</ul>
</div>