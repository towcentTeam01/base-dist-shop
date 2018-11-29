<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物流跟踪历史管理</title>
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
		<li class="active"><a href="${ctx}/sys/logisticsTraceHis/">物流跟踪历史列表</a></li>
		<shiro:hasPermission name="sys:logisticsTraceHis:edit"><li><a href="${ctx}/sys/logisticsTraceHis/form">物流跟踪历史添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="logisticsTraceHis" action="${ctx}/sys/logisticsTraceHis/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>物流公司编号：</label>
				<form:input path="logisticsNo" htmlEscape="false" maxlength="64" class="form-control"/>
			</li>
			<li><label>物流公司中文名称：</label>
				<form:input path="logisticsName" htmlEscape="false" maxlength="128" class="form-control"/>
			</li>
			<li><label>物流单号：</label>
				<form:input path="freightNumber" htmlEscape="false" maxlength="64" class="form-control"/>
			</li>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>物流公司编号</th>
				<th>物流公司中文名称</th>
				<th>物流单号</th>
				<th>跟踪时间</th>
				<th>物流描述</th>
				<shiro:hasPermission name="sys:logisticsTraceHis:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="logisticsTraceHis">
			<tr>
				<td><a href="${ctx}/sys/logisticsTraceHis/form?id=${logisticsTraceHis.id}">
					${logisticsTraceHis.logisticsNo}
				</a></td>
				<td>
					${logisticsTraceHis.logisticsName}
				</td>
				<td>
					${logisticsTraceHis.freightNumber}
				</td>
				<td>
					<fmt:formatDate value="${logisticsTraceHis.traceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${logisticsTraceHis.traceDesc}
				</td>
				<shiro:hasPermission name="sys:logisticsTraceHis:edit"><td>
    				<a href="${ctx}/sys/logisticsTraceHis/form?id=${logisticsTraceHis.id}">修改</a>
					<a href="${ctx}/sys/logisticsTraceHis/delete?id=${logisticsTraceHis.id}" onclick="return confirmx('确认要删除该物流跟踪历史吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>