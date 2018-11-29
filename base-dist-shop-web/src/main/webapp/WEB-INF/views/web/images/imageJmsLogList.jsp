<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片日志管理</title>
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
		<li class="active"><a href="${ctx}/images/imageJmsLog/">图片日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="imageJmsLog" action="${ctx}/images/imageJmsLog/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品编码：</label>
				<form:input path="goodsNo" htmlEscape="false" maxlength="30" class="form-control"/>
			</li>
			<li><label>商户：</label>
				<form:input path="shopName" htmlEscape="false" maxlength="11" class="form-control"/>
			</li>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品编码</th>
				<th>消息体</th>
				<th>状态</th>
				<th>执行次数</th>
				<th>注备</th>
				<th>更新时间</th>
				<shiro:hasPermission name="images:imageJmsLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="imageJmsLog">
			<tr>
				<td><a href="${ctx}/images/imageJmsLog/form?id=${imageJmsLog.id}">
					${imageJmsLog.goodsNo}
				</a></td>
				<td>
					${imageJmsLog.message}
				</td>
				<td>
					${imageJmsLog.status}
				</td>
				<td>
					${imageJmsLog.actionnum}
				</td>
				<td>
					${imageJmsLog.remark}
				</td>
				<td>
					<fmt:formatDate value="${imageJmsLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="images:imageJmsLog:edit"><td>
    				<a href="${ctx}/images/imageJmsLog/form?id=${imageJmsLog.id}">修改</a>
					<a href="${ctx}/images/imageJmsLog/delete?id=${imageJmsLog.id}" onclick="return confirmx('确认要删除该图片日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>