<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公众号配置管理</title>
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
		<li class="active"><a href="${ctx}/config/sysWxConfig/">公众号配置列表</a></li>
		<shiro:hasPermission name="config:sysWxConfig:edit"><li><a href="${ctx}/config/sysWxConfig/form">公众号配置添加</a></li></shiro:hasPermission>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="sysWxConfig" action="${ctx}/config/sysWxConfig/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>appid</th>
				<th>appsecret</th>
				<th>token</th>
				<th>aeskey</th>
				<th>公众号备注</th>
				<th>是否是服务号</th>
				<shiro:hasPermission name="config:sysWxConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysWxConfig">
			<tr>
				<td><a href="${ctx}/config/sysWxConfig/form?id=${sysWxConfig.id}">
					${sysWxConfig.appid}
				</a></td>
				<td>
					${sysWxConfig.wxAppsecret}
				</td>
				<td>
					${sysWxConfig.wxToken}
				</td>
				<td>
					${sysWxConfig.wxAeskey}
				</td>
				<td>
					${sysWxConfig.wxRemark}
				</td>
				<td>
					${fns:getDictLabel(sysWxConfig.isService, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="config:sysWxConfig:edit"><td>
    				<a href="${ctx}/config/sysWxConfig/form?id=${sysWxConfig.id}">修改</a>
					<a href="${ctx}/config/sysWxConfig/delete?id=${sysWxConfig.id}" onclick="return confirmx('确认要删除该公众号配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>