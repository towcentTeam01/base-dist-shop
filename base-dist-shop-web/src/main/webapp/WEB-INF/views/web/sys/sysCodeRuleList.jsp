<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>编码规则管理</title>
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
		<li class="active"><a href="${ctx}/sys/sysCodeRule/">编码规则列表</a></li>
		<shiro:hasPermission name="sys:sysCodeRule:edit"><li><a href="${ctx}/sys/sysCodeRule/form">编码规则添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysCodeRule" action="${ctx}/sys/sysCodeRule/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>请求名称：</label>
				<form:input path="requestName" htmlEscape="false" maxlength="60" class="form-control"/>
			</li>
			<li><label>前缀：</label>
				<form:input path="prefix" htmlEscape="false" maxlength="10" class="form-control"/>
			</li>
			<li><label>重置模式：</label>
				<form:select path="resetMode" class="form-control ">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('reset_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否允许断号：</label>
				<form:select path="allowBreakNo" class="form-control ">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('allow_break_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否抽象：</label>
				<form:select path="isAbstract" class="form-control ">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('is_abstract')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>请求code</th>
				<th>请求名称</th>
				<th>前缀</th>
				<th>当前序号</th>
				<th>序号长度</th>
				<th>重置模式</th>
				<th>重置时间</th>
				<th>是否允许断号</th>
				<th>是否抽象</th>
				<shiro:hasPermission name="sys:sysCodeRule:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysCodeRule">
			<tr>
				<td><a href="${ctx}/sys/sysCodeRule/form?id=${sysCodeRule.id}">
					${sysCodeRule.requestId}
				</a></td>
				<td>
					${sysCodeRule.requestName}
				</td>
				<td>
					${sysCodeRule.prefix}
				</td>
				<td>
					${sysCodeRule.currentSerialNo}
				</td>
				<td>
					${sysCodeRule.serialNoLength}
				</td>
				<td>
					${fns:getDictLabel(sysCodeRule.resetMode, 'reset_mode', '')}
				</td>
				<td>
					<fmt:formatDate value="${sysCodeRule.resetTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(sysCodeRule.allowBreakNo, 'allow_break_no', '')}
				</td>
				<td>
					${fns:getDictLabel(sysCodeRule.isAbstract, 'is_abstract', '')}
				</td>
				<shiro:hasPermission name="sys:sysCodeRule:edit"><td>
    				<a href="${ctx}/sys/sysCodeRule/form?id=${sysCodeRule.id}">修改</a>
					<a href="${ctx}/sys/sysCodeRule/delete?id=${sysCodeRule.id}" onclick="return confirmx('确认要删除该编码规则吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>