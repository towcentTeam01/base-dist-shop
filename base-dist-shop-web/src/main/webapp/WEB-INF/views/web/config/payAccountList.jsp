<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>支付配置管理</title>
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
		<li class="active"><a href="${ctx}/config/payAccount/">支付配置列表</a></li>
		<shiro:hasPermission name="config:payAccount:edit"><li><a href="${ctx}/config/payAccount/form">支付配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="payAccount" action="${ctx}/config/payAccount/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>支付账号：</label>
				<form:select path="payId" class="form-control">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pay_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>支付平台的账号：</label>
				<form:input path="partner" htmlEscape="false" maxlength="32" class="form-control"/>
			</li>
			<li><label>应用id：</label>
				<form:input path="appid" htmlEscape="false" maxlength="32" class="form-control"/>
			</li>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>支付账号id</th>
				<th>支付合作id</th>
				<th>应用id</th>
				<!-- <th>支付公钥</th>
				<th>支付私钥</th> -->
				<th>异步回调地址</th>
				<th>同步回调地址</th>
				<th>收款账号</th>
				<th>签名类型</th>
				<th>字符编码</th>
				<th>支付类型</th>
				<th>消息类型</th>
				<th>是否为测试环境</th>
				<shiro:hasPermission name="config:payAccount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payAccount">
			<tr>
				<td><a href="${ctx}/config/payAccount/form?id=${payAccount.id}">
					${fns:getDictLabel(payAccount.payId, 'pay_id', '')}
				</a></td>
				<td>
					${payAccount.partner}
				</td>
				<td>
					${payAccount.appid}
				</td>
				<%-- <td>
					${payAccount.publicKey}
				</td>
				<td>
					${payAccount.privateKey}
				</td> --%>
				<td>
					${payAccount.notifyUrl}
				</td>
				<td>
					${payAccount.returnUrl}
				</td>
				<td>
					${payAccount.seller}
				</td>
				<td>
					${payAccount.signType}
				</td>
				<td>
					${payAccount.inputCharset}
				</td>
				<td>
					${payAccount.payType}
				</td>
				<td>
					${payAccount.msgType}
				</td>
				<td>
					${payAccount.isTest}
				</td>
				<shiro:hasPermission name="config:payAccount:edit"><td>
    				<a href="${ctx}/config/payAccount/form?id=${payAccount.id}">修改</a>
					<a href="${ctx}/config/payAccount/delete?id=${payAccount.id}" onclick="return confirmx('确认要删除该支付配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>