<%@ page pageEncoding="UTF-8"%>
<div class="lovdmx-side lovdmx-side-menu">
	<div class="lovdmx-scroll">
		<div class="lovdmx-logo">
			<span>LOVDMX中控后台</span>
		</div>
		<ul class="lovdmx-nav lovdmx-nav-tree">
			<li class="lovdmx-nav-item"><a href="javascript:"> <i
					class="lovdmx-icon icon-shouye iconfont"></i> <cite>主页</cite> <span
					class="lovdmx-nav-more"></span>
			</a>
				<dl class="lovdmx-nav-child">
					<dd>
						<a href="javascrpit:void(0);" data-href="slideshow/index.do">控制台</a>
					</dd>
				</dl></li>
			<li class="lovdmx-nav-item"><a href="javascript:"> <i
					class="iconfont icon-user lovdmx-icon"></i> <cite>用户</cite> <span
					class="lovdmx-nav-more"></span>
			</a>
				<dl class="lovdmx-nav-child">
					<dd>
						<a href="${path }/lovdmx/ht/manage/index.do"
							data-href="manage/index.do">后台管理员列表</a>
					</dd>
					<dd>
						<a href="${path }/lovdmx/ht/account/index.do"
							data-href="account/index.do">前台管理员列表</a>
					</dd>
				</dl></li>
			<li class="lovdmx-nav-item"><a href="javascript:"> <i
					class="iconfont icon-quanxian lovdmx-icon"></i> <cite>权限</cite> <span
					class="lovdmx-nav-more"></span>
			</a>
				<dl class="lovdmx-nav-child">
					<dd>
						<a href="${path }/lovdmx/ht/limit/role/index.do"
							data-href="role/index.do">角色列表</a>
					</dd>
					<dd>
						<a href="${path }/lovdmx/ht/limit/power/index.do"
							data-href="power/index.do">权限功能列表</a>
					</dd>
				</dl></li>
			<li class="lovdmx-nav-item"><a href="javascript:"> <i
					class="iconfont icon-xiangmu lovdmx-icon"></i> <cite>项目管理</cite> <span
					class="lovdmx-nav-more"></span>
			</a>
				<dl class="lovdmx-nav-child">
					<dd>
						<a href="${path }/lovdmx/ht/project/index.do"
							data-href="project/index.do">项目列表</a>
					</dd>
				</dl></li>

			<li class="lovdmx-nav-item"><a href="javascript:"> <i
					class="iconfont icon-jichushuju-jiguitucopy lovdmx-icon"></i> <cite>机柜管理</cite>
					<span class="lovdmx-nav-more"></span>
			</a>
				<dl class="lovdmx-nav-child">
					<dd>
						<a href="${path }/lovdmx/ht/rackDevice/index.do"
							data-href="rackDevice/index.do">机柜列表</a>
					</dd>
				</dl></li>
			<li class="lovdmx-nav-item"><a href="javascript:"> <i
					class="iconfont icon-jiankong lovdmx-icon"></i> <cite>监控管理</cite>
					<span class="lovdmx-nav-more"></span>
			</a>
				<dl class="lovdmx-nav-child">
					<dd>
						<a href="${path }/lovdmx/ht/monitoringDevice/index.do"
							data-href="monitoringDevice/index.do">监控列表</a>
					</dd>
				</dl></li>
			<li class="lovdmx-nav-item"><a href="javascript:"> <i
					class="iconfont icon-Devices lovdmx-icon"></i> <cite>服务器设备</cite> <span
					class="lovdmx-nav-more"></span>
			</a>
				<dl class="lovdmx-nav-child">
					<dd>
						<a href="${path }/lovdmx/ht/rtrDevice/index.do"
							data-href="rtrDevice/index.do">RTR设备列表</a>
					</dd>
					<dd>
						<a href="${path }/lovdmx/ht/spriteDevice/index.do"
							data-href="spriteDevice/index.do">录放精灵设备列表</a>
					</dd>
					<dd>
						<a href="${path }/lovdmx/ht/dmx512Device/index.do"
							data-href="dmx512Device/index.do">Dmx512控制盒列表</a>
					</dd>
					<dd>
						<a class="section" href="javascript:void(0);">任务模块<span
							class="lovdmx-nav-more"></span></a>
						<dl class="lovdmx-nav-child">
							<dd>
								<a href="${path }/lovdmx/ht/subtask/index.do"
									data-href="subtask/index.do">子任务列表</a>
							</dd>
							<dd>
								<a href="${path }/lovdmx/ht/spriteTimedTask/index.do"
									data-href="spriteTimedTask/index.do">定时任务列表</a>
							</dd>
						</dl>
					</dd>
					<dd>
						<a class="section" href="javascript:void(0);">上传文件模块<span
							class="lovdmx-nav-more"></span></a>
						<dl class="lovdmx-nav-child">
							<dd>
								<a href="${path }/lovdmx/ht/uploadEdlmx/index.do"
									data-href="uploadEdlmx/index.do">上传灯光文件列表</a>
							</dd>
							<dd>
								<a href="${path }/lovdmx/ht/uploadVideo/index.do"
									data-href="uploadVideo/index.do">上传视频文件列表</a>
							</dd>
						</dl>
					</dd>
				</dl></li>
			<li class="lovdmx-nav-item"><a href="javascript:"> <i
					class="iconfont icon-beikongshuiwupingtaimenhu-tubiao_zhinengwangguan lovdmx-icon"></i> <cite>智能网关</cite> <span
					class="lovdmx-nav-more"></span>
			</a>
				<dl class="lovdmx-nav-child">
					<dd>
						<a href="${path }/lovdmx/ht/intelligentGateway/index.do"
							data-href="intelligentGateway/index.do">智能网关列表</a>
					</dd>
					<dd>
						<a class="section" href="javascript:void(0);">继电器模块<span
							class="lovdmx-nav-more"></span></a>
						<dl class="lovdmx-nav-child">
							<dd>
								<a href="${path }/lovdmx/ht/relay/index.do"
									data-href="relay/index.do">继电器列表</a>
							</dd>
						</dl>
					</dd>
					<dd>
						<a class="section" href="javascript:void(0);">继电器任务<span
							class="lovdmx-nav-more"></span></a>
						<dl class="lovdmx-nav-child">
							<dd>
								<a href="${path }/lovdmx/ht/relayRealtimeTask/index.do"
									data-href="relayRealtimeTask/index.do">实时任务列表</a>
							</dd>
							<dd>
								<a href="${path }/lovdmx/ht/relayTimedTask/index.do"
									data-href="relayTimedTask/index.do">定时任务列表</a>
							</dd>
							<dd>
								<a href="${path }/lovdmx/ht/relayTaskDetails/index.do"
									data-href="relayTaskDetails/index.do">定时任务详情列表</a>
							</dd>
						</dl>
					</dd>
				</dl></li>
				<li class="lovdmx-nav-item"><a href="javascript:"> <i
					class="iconfont icon-chengxuprogram1 lovdmx-icon"></i> <cite>程序</cite> <span
					class="lovdmx-nav-more"></span>
			</a>
				<dl class="lovdmx-nav-child">
					<dd>
						<a class="section" href="javascript:void(0);">智能网关<span
							class="lovdmx-nav-more"></span></a>
						<dl class="lovdmx-nav-child">
							<dd>
								<a href="${path }/lovdmx/ht/relay/applicationIndex.do"
									data-href="relay/applicationIndex.do">更新继电器程序</a>
							</dd>
						</dl>
					</dd>
				</dl></li>
			<li class="lovdmx-nav-item"><a href="javascript:"> <i
					class="iconfont icon-shezhi lovdmx-icon"></i> <cite>设置</cite> <span
					class="lovdmx-nav-more"></span>
			</a>
				<dl class="lovdmx-nav-child">
					<dd>
						<a class="section" href="javascript:void(0);">我的设置 <span
							class="lovdmx-nav-more"></span></a>
						<dl class="lovdmx-nav-child">
							<dd>
								<a
									href="${path }/lovdmx/ht/manage/info.do?manageId=${sessionScope.iadmin.manageId }"
									data-href="manage/info.do">基本资料</a>
							</dd>
							<dd>
								<a href="${path }/lovdmx/ht/manage/upadtePwd.do"
									data-href="manage/upadtePwd.do">修改密码</a>
							</dd>
						</dl>
					</dd>
				</dl></li>
		</ul>
	</div>
</div>