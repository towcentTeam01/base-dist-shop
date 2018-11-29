<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户信息管理</title>
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
		<li class="active"><a href="${ctx}/sys/sysMerchantInfo/">商户信息列表</a></li>
		<shiro:hasPermission name="sys:sysMerchantInfo:edit"><li><a href="${ctx}/sys/sysMerchantInfo/form">商户信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysMerchantInfo" action="${ctx}/sys/sysMerchantInfo/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>店铺编码：</label>
				<form:input path="houseNumber" htmlEscape="false" maxlength="64" class="form-control"/>
			</li>
			<li><label>店铺名称：</label>
				<form:input path="shopName" htmlEscape="false" maxlength="200" class="form-control"/>
			</li>
			<%-- <li><label>店铺类别：</label>
				<form:select path="shopType" class="form-control">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('shop_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li><label>状态：</label>
			    <form:select path="applyStatus" class="form-control">
                    <form:option value="" label="请选择"/>
                    <form:options items="${fns:getDictList('merchant_apply_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</li>
			<li><label>联系电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="form-control"/>
			</li>
			<li><label>邮箱：</label>
				<form:input path="email" htmlEscape="false" maxlength="100" class="form-control"/>
			</li>
			<li><label>法人姓名：</label>
				<form:input path="selfEmployedName" htmlEscape="false" maxlength="20" class="form-control"/>
			</li>
			<li><label>身份证号码：</label>
				<form:input path="applyIdCard" htmlEscape="false" maxlength="200" class="form-control"/>
			</li>
			<li><label>启用标识：</label>
				<form:select path="enabledFlag" class="form-control">
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
				<th>店铺编码</th>
				<th>店铺名称</th>
				<!-- <th>店铺类别</th> -->
				<th>联系电话</th>
				<th>开店地址</th>
				<th>客服电话</th>
				<th>状态</th>
				<th>启用标识</th>
				<th>注备</th>
				<th>更新时间</th>
				<shiro:hasPermission name="sys:sysMerchantInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysMerchantInfo">
			<tr>
				<td>
				    <a href="${ctx}/sys/sysMerchantInfo/form?id=${sysMerchantInfo.id}">
					${sysMerchantInfo.houseNumber}
				    </a>	
				</td>
				<td>
					${sysMerchantInfo.shopName}
				</td>
				<%-- <td>
					${fns:getDictLabel(sysMerchantInfo.shopType, 'shop_type', '')}
				</td> --%>
				<td>
					${sysMerchantInfo.phone}
				</td>
				<td>
					${sysMerchantInfo.province.name}${sysMerchantInfo.city.name}${sysMerchantInfo.district.name}${sysMerchantInfo.street}${sysMerchantInfo.address}
				</td>
				<td>
					${sysMerchantInfo.servicePhone}
				</td>
				<td>
					${fns:getDictLabel(sysMerchantInfo.applyStatus, 'merchant_apply_status', '')}
				</td>
				<td>
					${fns:getDictLabel(sysMerchantInfo.enabledFlag, 'yes_no', '')}
				</td>
				<td>
					${sysMerchantInfo.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sysMerchantInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sys:sysMerchantInfo:edit"><td>
    				<a href="${ctx}/sys/sysMerchantInfo/form?id=${sysMerchantInfo.id}">修改</a>
					<a href="${ctx}/sys/sysMerchantInfo/delete?id=${sysMerchantInfo.id}" onclick="return confirmx('确认要删除该商户信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>