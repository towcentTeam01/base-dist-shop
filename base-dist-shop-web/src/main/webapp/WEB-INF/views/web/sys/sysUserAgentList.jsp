<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>浏览器User-Agent管理</title>
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
		<li class="active"><a href="${ctx}/sys/sysUserAgent/">浏览器User-Agent列表</a></li>
		<shiro:hasPermission name="sys:sysUserAgent:edit"><li><a href="${ctx}/sys/sysUserAgent/form">浏览器User-Agent添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysUserAgent" action="${ctx}/sys/sysUserAgent/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>终端类型 ：</label>
				<form:select path="terminalType" class="form-control">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('terminal_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>浏览器信息：</label>
				<form:input path="explorerInfo" htmlEscape="false" maxlength="128" class="form-control"/>
			</li>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>终端类型</th>
				<th>User-Agent信息</th>
				<th>浏览器信息</th>
				<shiro:hasPermission name="sys:sysUserAgent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysUserAgent">
			<tr>
				<td><a href="${ctx}/sys/sysUserAgent/form?id=${sysUserAgent.id}">
					${fns:getDictLabel(sysUserAgent.terminalType, 'terminal_type', '')}
				</a></td>
				<td>
					${sysUserAgent.userAgent}
				</td>
				<td>
					${sysUserAgent.explorerInfo}
				</td>
				<shiro:hasPermission name="sys:sysUserAgent:edit"><td>
    				<a href="${ctx}/sys/sysUserAgent/form?id=${sysUserAgent.id}">修改</a>
					<a href="${ctx}/sys/sysUserAgent/delete?id=${sysUserAgent.id}" onclick="return confirmx('确认要删除该浏览器User-Agent吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>