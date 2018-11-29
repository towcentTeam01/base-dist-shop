<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品分享分类管理</title>
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
		<li class="active"><a href="${ctx}/share/shareCategory/">商品分享分类列表</a></li>
		<shiro:hasPermission name="share:shareCategory:edit"><li><a href="${ctx}/share/shareCategory/form">商品分享分类添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shareCategory" action="${ctx}/share/shareCategory/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分类编码：</label>
				<form:input path="categoryNo" htmlEscape="false" maxlength="20" class="form-control"/>
			</li>
			<li><label>分享信息：</label>
				<form:input path="shareDesc" htmlEscape="false" maxlength="512" class="form-control"/>
			</li>
			<li><label>分享标题：</label>
				<form:input path="shareTitle" htmlEscape="false" maxlength="128" class="form-control"/>
			</li>
			<li><label>商品分类id：</label>
				<form:input path="categoryId" htmlEscape="false" maxlength="11" class="form-control"/>
			</li>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分类编码</th>
				<th>分享信息</th>
				<th>分享图片</th>
				<th>分享标题</th>
				<th>商品分类id</th>
				<shiro:hasPermission name="share:shareCategory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shareCategory">
			<tr>
				<td><a href="${ctx}/share/shareCategory/form?id=${shareCategory.id}">
					${shareCategory.categoryNo}
				</a></td>
				<td>
					${shareCategory.shareDesc}
				</td>
				<td>
					${shareCategory.shareImage}
				</td>
				<td>
					${shareCategory.shareTitle}
				</td>
				<td>
					${shareCategory.categoryId}
				</td>
				<shiro:hasPermission name="share:shareCategory:edit"><td>
    				<a href="${ctx}/share/shareCategory/form?id=${shareCategory.id}">修改</a>
					<a href="${ctx}/share/shareCategory/delete?id=${shareCategory.id}" onclick="return confirmx('确认要删除该商品分享分类吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>