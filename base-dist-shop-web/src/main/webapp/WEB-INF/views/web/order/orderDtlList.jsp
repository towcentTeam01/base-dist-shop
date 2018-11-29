<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单明细管理</title>
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
		<li class="active"><a href="${ctx}/order/orderDtl/">订单明细列表</a></li>
		<shiro:hasPermission name="order:orderDtl:edit"><li><a href="${ctx}/order/orderDtl/form">订单明细添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orderDtl" action="${ctx}/order/orderDtl/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单id：</label>
				<form:input path="orderId" htmlEscape="false" maxlength="11" class="form-control"/>
			</li>
			<li><label>商品id：</label>
				<form:input path="goodsId" htmlEscape="false" maxlength="11" class="form-control"/>
			</li>
			<li><label>评价标识(0:未评价 1:已评价) yes_no：</label>
				<form:input path="evalFlag" htmlEscape="false" maxlength="1" class="form-control"/>
			</li>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单id</th>
				<th>商品id</th>
				<th>商品名称</th>
				<th>商品图片</th>
				<th>规格</th>
				<th>数量</th>
				<th>单价(元)</th>
				<th>金额=数量*单价(元)</th>
				<th>兑换积分</th>
				<th>评价标识(0:未评价 1:已评价) yes_no</th>
				<shiro:hasPermission name="order:orderDtl:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderDtl">
			<tr>
				<td><a href="${ctx}/order/orderDtl/form?id=${orderDtl.id}">
					${orderDtl.orderId}
				</a></td>
				<td>
					${orderDtl.goodsId}
				</td>
				<td>
					${orderDtl.goodsName}
				</td>
				<td>
					${orderDtl.goodsPicUrl}
				</td>
				<td>
					${orderDtl.spec}
				</td>
				<td>
					${orderDtl.qty}
				</td>
				<td>
					${orderDtl.price}
				</td>
				<td>
					${orderDtl.amount}
				</td>
				<td>
					${orderDtl.integral}
				</td>
				<td>
					${orderDtl.evalFlag}
				</td>
				<shiro:hasPermission name="order:orderDtl:edit"><td>
    				<a href="${ctx}/order/orderDtl/form?id=${orderDtl.id}">修改</a>
					<a href="${ctx}/order/orderDtl/delete?id=${orderDtl.id}" onclick="return confirmx('确认要删除该订单明细吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>