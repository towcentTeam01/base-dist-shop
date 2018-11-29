<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公众号配置管理</title>
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
		<li><a href="${ctx}/config/sysWxConfig/">公众号配置列表</a></li>
		<li class="active"><a href="${ctx}/config/sysWxConfig/form?id=${sysWxConfig.id}">公众号配置<shiro:hasPermission name="config:sysWxConfig:edit">${not empty sysWxConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="config:sysWxConfig:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysWxConfig" action="${ctx}/config/sysWxConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				appid：
			</label>
			<div class="col-sm-3">
				<form:input path="appid" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				appsecret：
			</label>
			<div class="col-sm-3">
				<form:input path="wxAppsecret" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				token：
			</label>
			<div class="col-sm-3">
				<form:input path="wxToken" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				aeskey：
			</label>
			<div class="col-sm-3">
				<form:input path="wxAeskey" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				公众号备注：
			</label>
			<div class="col-sm-3">
				<form:input path="wxRemark" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
			<span class="col-sm-4 help-block">公众号名称等信息</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				是否是服务号：
			</label>
			<div class="col-sm-3">
				<form:select path="isService" class="form-control ">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				商户Id：
			</label>
			<div class="col-sm-3">
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div> --%>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="config:sysWxConfig:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>