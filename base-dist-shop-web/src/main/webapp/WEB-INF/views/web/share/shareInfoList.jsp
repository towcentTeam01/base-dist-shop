<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分享记录管理</title>
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
		<li class="active"><a href="${ctx}/share/shareInfo/">分享记录列表</a></li>
		<shiro:hasPermission name="share:shareInfo:edit"><li><a href="${ctx}/share/shareInfo/form">分享记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shareInfo" action="${ctx}/share/shareInfo/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>工号：</label>
				<form:input path="jobNo" htmlEscape="false" maxlength="20" class="form-control"/>
			</li>
			<li><label>分享信息：</label>
				<form:input path="shareDesc" htmlEscape="false" maxlength="512" class="form-control"/>
			</li>
			<li><label>分享标题：</label>
				<form:input path="shareTitle" htmlEscape="false" maxlength="128" class="form-control"/>
			</li>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工号</th>
				<th>用户</th>
				<th>商品编码</th>
				<th>分享图片</th>
				<th>分享标题</th>
				<th>分享信息</th>
				<th>分享链接</th>
				<th>创建时间</th>
				<shiro:hasPermission name="share:shareInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shareInfo">
			<tr>
				<td><a href="${ctx}/share/shareInfo/form?id=${shareInfo.id}">
					${shareInfo.jobNo}
				</a></td>
				<td>
					${shareInfo.createBy1.nickName}${shareInfo.createBy1.mobile}
				</td>
				<td>
					${shareInfo.goodsNo}
				</td>
				<td>
				  <img alt="" src="${shareInfo.shareImage}" height="60px" width="70px">
				</td>
				<td>
					${shareInfo.shareTitle}
				</td>
				<td>
					${shareInfo.shareDesc}
				</td>
			
				<td>
					${shareInfo.shareUrl}
				</td>
				<td>
					<fmt:formatDate value="${shareInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="share:shareInfo:edit"><td>
    				<a href="${ctx}/share/shareInfo/form?id=${shareInfo.id}">修改</a>
					<a href="${ctx}/share/shareInfo/delete?id=${shareInfo.id}" onclick="return confirmx('确认要删除该分享记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>