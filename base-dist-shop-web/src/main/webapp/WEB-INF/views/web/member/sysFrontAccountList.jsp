<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
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
		<li class="active"><a href="${ctx}/member/sysFrontAccount/">会员列表</a></li>
		<shiro:hasPermission name="member:sysFrontAccount:edit"><li><a href="${ctx}/member/sysFrontAccount/form">会员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysFrontAccount" action="${ctx}/member/sysFrontAccount/" method="post" class="navbar-form form-search" role="form">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>工号：</label>
				<form:input path="jobNo" htmlEscape="false" maxlength="20" class="form-control"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="nickName" htmlEscape="false" maxlength="64" class="form-control"/>
			</li>
			<li><label>微信号：</label>
				<form:input path="bindWx" htmlEscape="false" maxlength="32" class="form-control"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${sysFrontAccount.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${sysFrontAccount.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>用户等级：</label>
				<form:select path="levelVip" class="form-control">
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
				<th>头像</th>
				<th>帐号</th>
				<th>昵称</th>
				<th>工号</th>
				<th>手机号</th>
				<th>性别</th>
				<th>账户余额</th>
				<th>冻结余额</th>
				<th>收入总额</th>
				<th>已结算</th>
				<th>积分</th>
				<th>用户等级</th>
				<th>归属上级</th>
				<th>微信号</th>
				<th>创建时间</th>
				<shiro:hasPermission name="member:sysFrontAccount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysFrontAccount">
			<tr>
				<td>
					<c:if test="${not empty sysFrontAccount.portrait}">
					<img src="${sysFrontAccount.portrait}" style="width:30px; height:30px; border-radius:50%; overflow:hidden;">
					</c:if>
					<c:if test="${empty sysFrontAccount.portrait}">
					<img src="${ctxStatic}/images/face_default2.png" style="width:30px; height:30px; border-radius:50%; overflow:hidden;">	
					</c:if>
				</td>
				<td><a href="${ctx}/member/sysFrontAccount/form?id=${sysFrontAccount.id}">
					${sysFrontAccount.account}
				</a></td>
				<td>
					${sysFrontAccount.nickName}
				</td>
				<td>
					${sysFrontAccount.jobNo}
				</td>
				<td>
					${sysFrontAccount.mobile}
				</td>
				<td>
					${fns:getDictLabel(sysFrontAccount.sex, 'sex', '')}
				</td>
				<td>
					${sysFrontAccount.amount}
				</td>
				<td>
					${sysFrontAccount.freezeAmount}
				</td>
				<td>
					${sysFrontAccount.marginAmount}
				</td>
				<td>
					${sysFrontAccount.settledAmount}
				</td>
				<td>
					${sysFrontAccount.inter}
				</td>
				<td>
					${fns:getDictLabel(sysFrontAccount.levelVip, 'level_vip', '')}
				</td>
				<td>
					${sysFrontAccount.parent.nickName}(${sysFrontAccount.parent.account})
				</td>
				<td>
					${sysFrontAccount.bindWx}
				</td>
				<td>
					<fmt:formatDate value="${sysFrontAccount.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="member:sysFrontAccount:edit"><td>
    				<a href="${ctx}/member/sysFrontAccount/form?id=${sysFrontAccount.id}">修改</a>
					<%-- <a href="${ctx}/member/sysFrontAccount/delete?id=${sysFrontAccount.id}" onclick="return confirmx('确认要删除该会员吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	${page}
</body>
</html>