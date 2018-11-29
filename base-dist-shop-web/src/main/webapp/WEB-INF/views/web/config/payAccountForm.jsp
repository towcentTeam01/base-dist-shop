<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>支付配置管理</title>
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
		<li><a href="${ctx}/config/payAccount/">支付配置列表</a></li>
		<li class="active"><a href="${ctx}/config/payAccount/form?id=${payAccount.id}">支付配置<shiro:hasPermission name="config:payAccount:edit">${not empty payAccount.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="config:payAccount:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="payAccount" action="${ctx}/config/payAccount/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				支付账号id：
			</label>
			<div class="col-sm-3">
				<form:select path="payId" class="form-control required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pay_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				支付平台的账号：
			</label>
			<div class="col-sm-3">
				<form:input path="partner" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				appid：
			</label>
			<div class="col-sm-3">
				<form:input path="appid" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				支付公钥：
			</label>
			<div class="col-sm-3">
				<form:input path="publicKey" htmlEscape="false" maxlength="1204" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				支付私钥：
			</label>
			<div class="col-sm-3">
				<form:input path="privateKey" htmlEscape="false" maxlength="2048" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				异步回调地址：
			</label>
			<div class="col-sm-3">
				<form:input path="notifyUrl" htmlEscape="false" maxlength="1024" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				同步回调地址：
			</label>
			<div class="col-sm-3">
				<form:input path="returnUrl" htmlEscape="false" maxlength="1024" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				收款账号：
			</label>
			<div class="col-sm-3">
				<form:input path="seller" htmlEscape="false" maxlength="256" class="form-control "/>
			</div>
			<span class="col-sm-4 help-block">针对支付宝</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				签名类型：
			</label>
			<div class="col-sm-3">
				<form:input path="signType" htmlEscape="false" maxlength="16" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				字符编码：
			</label>
			<div class="col-sm-3">
				<form:input path="inputCharset" htmlEscape="false" maxlength="16" class="form-control "/>
			</div>
			<span class="col-sm-4 help-block">枚举值，utf-8,gbk</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				支付类型：
			</label>
			<div class="col-sm-3">
				<form:input path="payType" htmlEscape="false" maxlength="16" class="form-control "/>
			</div>
			<span class="col-sm-4 help-block">枚举值，aliPay：支付宝，wxPay：微信</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				消息类型：
			</label>
			<div class="col-sm-3">
				<form:input path="msgType" htmlEscape="false" maxlength="8" class="form-control "/>
			</div>
			<span class="col-sm-4 help-block">text,xml,json</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				是否为测试环境：
			</label>
			<div class="col-sm-3">
				<form:input path="isTest" htmlEscape="false" maxlength="1" class="form-control required"/>
			</div>
			<span class="col-sm-4 help-block"></span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				创建时间：
			</label>
			<div class="col-sm-3">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${payAccount.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商户id：
			</label>
			<div class="col-sm-3">
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				公众号备注：
			</label>
			<div class="col-sm-3">
				<form:input path="wxRemark" htmlEscape="false" maxlength="50" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="config:payAccount:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>