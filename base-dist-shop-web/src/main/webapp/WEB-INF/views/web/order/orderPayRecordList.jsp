<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易记录管理</title>
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
		<li class="active"><a href="${ctx}/order/orderPayRecord/">交易记录列表</a></li>
		<%-- <shiro:hasPermission name="order:orderPayRecord:edit"><li><a href="${ctx}/order/orderPayRecord/form">交易记录添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="orderPayRecord" action="${ctx}/order/orderPayRecord/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户：</label>
				<sys:tagSearchList
                    id="createBy1.id"
                    value="${orderPayRecord.createBy1.id}"
                    name="createBy1.nickName"
                    labelValue="${orderPayRecord.createBy1.nickName} ${orderPayRecord.createBy1.mobile}"
                    url="${ctx}/member/sysFrontAccount/searchMemberList"
                    placeholder="选择会员"
                    cssClass="form-control">
            	</sys:tagSearchList>
			</li>
			<li><label>订单号：</label>
				<form:input path="order.orderNo" htmlEscape="false" maxlength="20" class="form-control"/>
			</li>
			<li><label>支付交易号：</label>
				<form:input path="payRecordNo" htmlEscape="false" maxlength="64" class="form-control"/>
			</li>
			<li><label>业务类型：</label>
				<form:select path="bizType" class="form-control">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('biz_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>支付方式：</label>
				<form:select path="payType" class="form-control">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>支付时间：</label>
				<input name="beginPayDate" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${orderPayRecord.beginPayDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endPayDate" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${orderPayRecord.endPayDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>支付交易号</th>
				<th>业务类型</th>
				<th>订单号</th>
				<th>支付方式</th>
				<th>支付金额</th>
				<th>支付状态</th>
				<th>余额金额</th>
				<th>支付积分</th>
				<th>第三方支付流水号</th>
				<th>注备</th>
				<th>用户</th>
				<th>支付时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="order:orderPayRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderPayRecord">
			<tr>
				<td><a href="${ctx}/order/orderPayRecord/form?id=${orderPayRecord.id}">
					${orderPayRecord.payRecordNo}
				</a></td>
				<td>
					${fns:getDictLabel(orderPayRecord.bizType, 'biz_type', '')}
				</td>
				<td>
					${orderPayRecord.order.orderNo}
				</td>
				<td>
					${fns:getDictLabel(orderPayRecord.payType, 'pay_type', '')}
				</td>
				<td>
					${orderPayRecord.payAmount}
				</td>
				<td>
					${fns:getDictLabel(orderPayRecord.payStatus, 'pay_status', '')}
				</td>
				<td>
					${orderPayRecord.balanceAmount}
				</td>
				<td>
					${orderPayRecord.interAmount}
				</td>
				<td>
					${orderPayRecord.thirdPaySn}
				</td>
				<td>
					${orderPayRecord.remarks}
				</td>
				<td>
					${orderPayRecord.createBy1.nickName} ${orderPayRecord.createBy1.mobile}
				</td>
				<td>
					<fmt:formatDate value="${orderPayRecord.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${orderPayRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="order:orderPayRecord:edit"><td>
    				<a href="${ctx}/order/orderPayRecord/form?id=${orderPayRecord.id}">查看</a>
					<%-- <a href="${ctx}/order/orderPayRecord/delete?id=${orderPayRecord.id}" onclick="return confirmx('确认要删除该交易记录吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>