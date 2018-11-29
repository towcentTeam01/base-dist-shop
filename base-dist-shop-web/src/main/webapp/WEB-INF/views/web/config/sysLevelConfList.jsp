<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>等级配置管理</title>
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
		<li class="active"><a href="${ctx}/config/sysLevelConf/">等级配置列表</a></li>
		<%-- <shiro:hasPermission name="config:sysLevelConf:edit"><li><a href="${ctx}/config/sysLevelConf/form">等级配置添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="sysLevelConf" action="${ctx}/config/sysLevelConf/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>等级别名：</label>
				<form:input path="levelName" htmlEscape="false" maxlength="200" class="form-control"/>
			</li>
			<li><label>用户等级：</label>
				<form:select path="level" class="form-control">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('level_vip')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>会员等级别名</th>
				<th>用户等级</th>
				<th>升级所需锁粉数</th>
				<th>升级所需直推订单数</th>
				<th>升级所需费用</th>
				<th>是否默认等级</th>
				<th>直推佣金比例</th>
				<th>注备</th>
				<th>更新者</th>
				<th>更新时间</th>
				<shiro:hasPermission name="config:sysLevelConf:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysLevelConf">
			<tr>
				<td><a href="${ctx}/config/sysLevelConf/form?id=${sysLevelConf.id}">
					${sysLevelConf.levelName}
				</a></td>
				<td>
					${fns:getDictLabel(sysLevelConf.level, 'level_vip', '')}
				</td>
				<td>
					${sysLevelConf.lockFans}
				</td>
				<td>
					${sysLevelConf.recOrder}
				</td>
				<td>
					${sysLevelConf.payFee}
				</td>
				<td>
					${fns:getDictLabel(sysLevelConf.defaultFlag, 'yes_no', '')}
				</td>
				<td>
					${sysLevelConf.directBkge}
				</td>
				<td>
					${sysLevelConf.remarks}
				</td>
				<td>
					${sysLevelConf.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${sysLevelConf.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="config:sysLevelConf:edit"><td>
    				<a href="${ctx}/config/sysLevelConf/form?id=${sysLevelConf.id}">修改</a>
					<a href="${ctx}/config/sysLevelConf/delete?id=${sysLevelConf.id}" onclick="return confirmx('确认要删除该等级配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>