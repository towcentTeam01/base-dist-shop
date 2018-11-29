<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>钱包明细管理</title>
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
		<li class="active"><a href="${ctx}/member/sysAmountRecord/">钱包明细列表</a></li>
		<%-- <shiro:hasPermission name="member:sysAmountRecord:edit"><li><a href="${ctx}/member/sysAmountRecord/form">钱包明细添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="sysAmountRecord" action="${ctx}/member/sysAmountRecord/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户：</label>
				<sys:tagSearchList
                    id="user.id"
                    value="${sysAmountRecord.user.id}"
                    name="user.nickName"
                    labelValue="${sysAmountRecord.user.nickName} ${sysAmountRecord.user.mobile}"
                    url="${ctx}/member/sysFrontAccount/searchMemberList"
                    placeholder="选择会员"
                    cssClass="form-control">
            	</sys:tagSearchList>
			</li>
			<li><label>支出收入类型：</label>
				<form:select path="type" class="form-control">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('spending_income_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>交易时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${sysAmountRecord.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${sysAmountRecord.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户</th>
				<th>支出收入类型</th>
				<th>此次交易金额</th>
				<th>钱包余额</th>
				<th>订单金额</th>
				<th>订单title</th>
				<th>备注</th>
				<th>交易时间</th>
				<shiro:hasPermission name="member:sysAmountRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysAmountRecord">
			<tr>
				<td><a href="${ctx}/member/sysAmountRecord/form?id=${sysAmountRecord.id}">
					${sysAmountRecord.user.nickName} ${sysAmountRecord.user.mobile} 
				</a></td>
				<td>
					${fns:getDictLabel(sysAmountRecord.type, 'spending_income_type', '')}
				</td>
				<td>
					${sysAmountRecord.amount}
				</td>
				<td>
					${sysAmountRecord.amountAfter}
				</td>
				<td>
					${sysAmountRecord.orderAmount}
				</td>
				<td>
					${sysAmountRecord.orderTitle}
				</td>
				<td>
					${sysAmountRecord.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sysAmountRecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="member:sysAmountRecord:edit"><td>
    				<a href="${ctx}/member/sysAmountRecord/form?id=${sysAmountRecord.id}">查看</a>
					<%-- <a href="${ctx}/member/sysAmountRecord/delete?id=${sysAmountRecord.id}" onclick="return confirmx('确认要删除该钱包明细吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>