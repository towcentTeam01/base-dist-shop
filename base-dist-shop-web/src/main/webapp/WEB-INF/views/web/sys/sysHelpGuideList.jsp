<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统教程管理</title>
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
		<li class="active"><a href="${ctx}/sys/sysHelpGuide/">系统教程列表</a></li>
		<shiro:hasPermission name="sys:sysHelpGuide:edit"><li><a href="${ctx}/sys/sysHelpGuide/form">系统教程添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysHelpGuide" action="${ctx}/sys/sysHelpGuide/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>教程类别：</label>
				<form:select path="guideType" class="form-control">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('guide_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>指南标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="50" class="form-control"/>
			</li>
			<%-- <li><label>商户Id：</label>
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control"/>
			</li> --%>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>指南标题</th>
				<th style="width: 60px;text-align: center;">教程图片</th>
				<th>教程类别</th>
				<th>排序号</th>
				<th>更新时间</th>
				<shiro:hasPermission name="sys:sysHelpGuide:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysHelpGuide">
			<tr>
				<td><a href="${ctx}/sys/sysHelpGuide/form?id=${sysHelpGuide.id}">
					${sysHelpGuide.title}
				</a></td>
				<td>
				    <img style="width: 60px;height: 60px; object-fit: contain; background-color: #e5e1e1;" src="${sysHelpGuide.picUrl}"/>
				</td>
				<td>
					${fns:getDictLabel(sysHelpGuide.guideType, 'guide_type', '')}
				</td>
				<td>
					${sysHelpGuide.sort}
				</td>
				<td>
					<fmt:formatDate value="${sysHelpGuide.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sys:sysHelpGuide:edit"><td>
    				<a href="${ctx}/sys/sysHelpGuide/form?id=${sysHelpGuide.id}">修改</a>
					<a href="${ctx}/sys/sysHelpGuide/delete?id=${sysHelpGuide.id}" onclick="return confirmx('确认要删除该系统教程吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>