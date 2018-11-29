<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠券领取管理</title>
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
		<li><a href="${ctx}/coupon/couponAct/">优惠券列表</a></li>
		<shiro:hasPermission name="coupon:couponAct:edit">
			<li><a href="${ctx}/coupon/couponAct/form">优惠券添加</a></li>
		</shiro:hasPermission>
		<li class="active"><a href="${ctx}/coupon/couponClaim?couponAct.id=${couponClaim.couponAct.id}">优惠券领取列表</a></li>
		<shiro:hasPermission name="coupon:couponClaim:edit"><li><a href="${ctx}/coupon/couponClaim/form">优惠券领取添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="couponClaim" action="${ctx}/coupon/couponClaim/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li style="display: none"><label>活动id：</label>
				<form:input path="couponAct.id" htmlEscape="false" maxlength="11" class="form-control"/>
			</li>
			<%--<li><label>会员id：</label>--%>
				<%--<sys:treeselect id="user" name="user.id" value="${couponClaim.user.id}" labelName="user.name" labelValue="${couponClaim.user.name}"--%>
					<%--title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>--%>
			<%--</li>--%>
			<li><label>会员昵称：</label>
				<form:input path="nickName" htmlEscape="false" maxlength="64" class="form-control"/>
			</li>
			<li><label>使用状态：</label>
				<form:select path="useFlag" class="form-control">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>活动名称</th>
				<th>会员昵称</th>
				<th>满减金额</th>
				<th>优惠券金额/折扣金额</th>
				<th>使用状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="coupon:couponClaim:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="couponClaim">
			<tr>
				<td><a href="${ctx}/coupon/couponClaim/form?id=${couponClaim.id}">
					${couponClaim.couponAct.actName}
				</a></td>
				<td>
					${couponClaim.nickName}
				</td>
				<td>
					${couponClaim.limitAmount}
				</td>
				<td>
					${couponClaim.amount}
				</td>
				<td>
					${fns:getDictLabel(couponClaim.useFlag, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${couponClaim.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="coupon:couponClaim:edit"><td>
    				<%--<a href="${ctx}/coupon/couponClaim/form?id=${couponClaim.id}">修改</a>--%>
					<%--<a href="${ctx}/coupon/couponClaim/delete?id=${couponClaim.id}" onclick="return confirmx('确认要删除该优惠券领取吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>