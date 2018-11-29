<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理服务器管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/sysProxyInfo/">代理服务器列表</a></li>
		<shiro:hasPermission name="sys:sysProxyInfo:edit"><li><a href="${ctx}/sys/sysProxyInfo/form">代理服务器添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysProxyInfo" action="${ctx}/sys/sysProxyInfo/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>代理服务器物理位置：</label>
				<form:input path="serverAddr" htmlEscape="false" maxlength="128" class="form-control"/>
			</li>
			<li><label>协议类型（HTTP/HTTPS）：</label>
				<form:input path="type" htmlEscape="false" maxlength="8" class="form-control"/>
			</li>
			<li><label>存活时间：</label>
				<form:input path="surviveTime" htmlEscape="false" maxlength="64" class="form-control"/>
			</li>
			<li><label>过期时间：</label>
				<input name="beginExpirationTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${sysProxyInfo.beginExpirationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endExpirationTime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${sysProxyInfo.endExpirationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>代理服务器IP地址</th>
				<th>代理服务器端口</th>
				<th>代理服务器物理位置</th>
				<th>协议类型（HTTP/HTTPS）</th>
				<th>速度 单位秒</th>
				<th>连接时间</th>
				<th>存活时间</th>
				<th>验证时间</th>
				<th>过期时间</th>
				<shiro:hasPermission name="sys:sysProxyInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysProxyInfo">
			<tr>
				<td><a href="${ctx}/sys/sysProxyInfo/form?id=${sysProxyInfo.id}">
					${sysProxyInfo.proxyIp}
				</a></td>
				<td>
					${sysProxyInfo.proxyPort}
				</td>
				<td>
					${sysProxyInfo.serverAddr}
				</td>
				<td>
					${sysProxyInfo.type}
				</td>
				<td>
					${sysProxyInfo.speed}
				</td>
				<td>
					${sysProxyInfo.connectTime}
				</td>
				<td>
					${sysProxyInfo.surviveTime}
				</td>
				<td>
					<fmt:formatDate value="${sysProxyInfo.verifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sysProxyInfo.expirationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sys:sysProxyInfo:edit"><td>
    				<a href="${ctx}/sys/sysProxyInfo/form?id=${sysProxyInfo.id}">修改</a>
					<a href="${ctx}/sys/sysProxyInfo/delete?id=${sysProxyInfo.id}" onclick="return confirmx('确认要删除该代理服务器吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>