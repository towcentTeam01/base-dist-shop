<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>浏览器User-Agent管理</title>
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
		<li><a href="${ctx}/sys/sysUserAgent/">浏览器User-Agent列表</a></li>
		<li class="active"><a href="${ctx}/sys/sysUserAgent/form?id=${sysUserAgent.id}">浏览器User-Agent<shiro:hasPermission name="sys:sysUserAgent:edit">${not empty sysUserAgent.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:sysUserAgent:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysUserAgent" action="${ctx}/sys/sysUserAgent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				终端类型：
			</label>
			<div class="col-sm-3">
				<form:select path="terminalType" class="form-control required">
					<form:options items="${fns:getDictList('terminal_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				User-Agent信息：
			</label>
			<div class="col-sm-5">
				<form:textarea path="userAgent" htmlEscape="false" maxlength="512" rows="5" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				浏览器信息：
			</label>
			<div class="col-sm-5">
				<form:textarea path="explorerInfo" htmlEscape="false" maxlength="128" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="sys:sysUserAgent:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>