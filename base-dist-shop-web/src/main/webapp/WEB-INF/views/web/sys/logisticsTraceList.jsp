<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物流跟踪管理</title>
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
		<li class="active"><a href="${ctx}/sys/logisticsTrace/">物流跟踪列表</a></li>
		<shiro:hasPermission name="sys:logisticsTrace:edit"><li><a href="${ctx}/sys/logisticsTrace/form">物流跟踪添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="logisticsTrace" action="${ctx}/sys/logisticsTrace/" method="post" class="navbar-form form-search" role="form">
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
			<li><label>是否签收：</label>
				<form:select path="isCheck" class="form-control">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('is_check')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>是否签收</th>
				<shiro:hasPermission name="sys:logisticsTrace:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="logisticsTrace">
			<tr>
				<td><a href="${ctx}/sys/logisticsTrace/form?id=${logisticsTrace.id}">
					${logisticsTrace.logisticsNo}
				</a></td>
				<td>
					${logisticsTrace.logisticsName}
				</td>
				<td>
					${logisticsTrace.freightNumber}
				</td>
				<td>
					<fmt:formatDate value="${logisticsTrace.traceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${logisticsTrace.traceDesc}
				</td>
				<td>
					${fns:getDictLabel(logisticsTrace.isCheck, 'is_check', '')}
				</td>
				<shiro:hasPermission name="sys:logisticsTrace:edit"><td>
    				<a href="${ctx}/sys/logisticsTrace/form?id=${logisticsTrace.id}">修改</a>
					<a href="${ctx}/sys/logisticsTrace/delete?id=${logisticsTrace.id}" onclick="return confirmx('确认要删除该物流跟踪吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>