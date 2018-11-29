<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提现申请管理</title>
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
		<li class="active"><a href="${ctx}/member/sysWithdrawApply/">提现申请列表</a></li>
		<%-- <shiro:hasPermission name="member:sysWithdrawApply:edit"><li><a href="${ctx}/member/sysWithdrawApply/form">提现申请添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="sysWithdrawApply" action="${ctx}/member/sysWithdrawApply/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请人：</label>
				<sys:tagSearchList
                    id="createBy1.id"
                    value="${sysWithdrawApply.createBy1.id}"
                    name="createBy1.nickName"
                    labelValue="${sysWithdrawApply.createBy1.nickName} ${sysWithdrawApply.createBy1.mobile}"
                    url="${ctx}/member/sysFrontAccount/searchMemberList"
                    placeholder="选择会员"
                    cssClass="form-control">
            	</sys:tagSearchList>
			</li>
			<li><label>提现状态：</label>
				<form:select path="status" class="form-control">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('withdraw_apply_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${sysWithdrawApply.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${sysWithdrawApply.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>提现金额</th>
				<th>提现状态</th>
				<th>备注</th>
				<th>申请人</th>
				<th>申请时间</th>
				<!-- <th>更新人</th> -->
				<th>更新时间</th>
				<shiro:hasPermission name="member:sysWithdrawApply:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysWithdrawApply">
			<tr>
				<td><a href="${ctx}/member/sysWithdrawApply/form?id=${sysWithdrawApply.id}">
					${sysWithdrawApply.amount}
				</a></td>
				<td>
					${fns:getDictLabel(sysWithdrawApply.status, 'withdraw_apply_status', '')}
				</td>
				<td>
					${sysWithdrawApply.remarks}
				</td>
				<td>
					${sysWithdrawApply.createBy1.nickName}${sysWithdrawApply.createBy1.mobile}
				</td>
				<td>
					<fmt:formatDate value="${sysWithdrawApply.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${sysWithdrawApply.updateBy.id}
				</td> --%>
				<td>
					<fmt:formatDate value="${sysWithdrawApply.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="member:sysWithdrawApply:edit"><td>
    				<a href="${ctx}/member/sysWithdrawApply/form?id=${sysWithdrawApply.id}">审核</a>
					<%-- <a href="${ctx}/member/sysWithdrawApply/delete?id=${sysWithdrawApply.id}" onclick="return confirmx('确认要删除该提现申请吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>