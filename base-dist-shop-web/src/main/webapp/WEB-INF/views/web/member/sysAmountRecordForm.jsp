<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>钱包明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/sysAmountRecord/">钱包明细列表</a></li>
		<li class="active"><a href="${ctx}/member/sysAmountRecord/form?id=${sysAmountRecord.id}">钱包明细<shiro:hasPermission name="member:sysAmountRecord:edit">${not empty sysAmountRecord.id?'查看':'添加'}</shiro:hasPermission><shiro:lacksPermission name="member:sysAmountRecord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysAmountRecord" action="${ctx}/member/sysAmountRecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				用户：
			</label>
			<div class="col-sm-3">
				<sys:tagSearchList
                    id="user.id"
                    value="${sysAmountRecord.user.id}"
                    name="user.nickName"
                    labelValue="${sysAmountRecord.user.nickName} ${sysAmountRecord.user.mobile}"
                    url="${ctx}/member/sysFrontAccount/searchMemberList"
                    placeholder="选择会员"
                    cssClass="form-control">
            	</sys:tagSearchList>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				交易号：
			</label>
			<div class="col-sm-3">
				<form:input path="dealNo" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				支出收入类型：
			</label>
			<div class="col-sm-3">
				<form:select path="type" class="form-control required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('spending_income_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				交易金额：
			</label>
			<div class="col-sm-3">
				<form:input path="amount" htmlEscape="false" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				钱包余额：
			</label>
			<div class="col-sm-3">
				<form:input path="amountAfter" htmlEscape="false" class="form-control required"/>
			</div>
			<span class="col-sm-4 help-block">发生此次交易后，钱包剩余的余额</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				订单金额：
			</label>
			<div class="col-sm-3">
				<form:input path="orderAmount" htmlEscape="false" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				订单title：
			</label>
			<div class="col-sm-3">
				<form:input path="orderTitle" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				备注：
			</label>
			<div class="col-sm-3">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="form-control input-xxlarge "/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				商户id：
			</label>
			<div class="col-sm-3">
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div> --%>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<%-- <shiro:hasPermission name="member:sysAmountRecord:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission> --%>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>