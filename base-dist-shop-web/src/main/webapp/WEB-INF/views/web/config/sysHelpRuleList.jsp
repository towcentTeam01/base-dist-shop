<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分销规则描述管理</title>
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
		<li class="active"><a href="${ctx}/config/sysHelpRule/">分销规则描述列表</a></li>
		<shiro:hasPermission name="config:sysHelpRule:edit"><li><a href="${ctx}/config/sysHelpRule/form">分销规则描述添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysHelpRule" action="${ctx}/config/sysHelpRule/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>正文标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="50" class="form-control"/>
			</li>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>正文标题</th>
				<th style="width: 60px;text-align: center;">描述图片</th>
				<th>排序号</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="config:sysHelpRule:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysHelpRule">
			<tr>
				<td><a href="${ctx}/config/sysHelpRule/form?id=${sysHelpRule.id}">
					${sysHelpRule.title}
				</a></td>
				<td>
				    <img style="width: 60px;height: 60px; object-fit: contain; background-color: #e5e1e1;" src="${sysHelpRule.picUrl}"/>
				</td>
				<td>
					${sysHelpRule.sort}
				</td>
				<td>
					<fmt:formatDate value="${sysHelpRule.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sysHelpRule.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="config:sysHelpRule:edit"><td>
    				<a href="${ctx}/config/sysHelpRule/form?id=${sysHelpRule.id}">修改</a>
					<a href="${ctx}/config/sysHelpRule/delete?id=${sysHelpRule.id}" onclick="return confirmx('确认要删除该等级规则描述吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>