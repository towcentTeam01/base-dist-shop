<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理服务器管理</title>
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
		<li><a href="${ctx}/sys/sysProxyInfo/">代理服务器列表</a></li>
		<li class="active"><a href="${ctx}/sys/sysProxyInfo/form?id=${sysProxyInfo.id}">代理服务器<shiro:hasPermission name="sys:sysProxyInfo:edit">${not empty sysProxyInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:sysProxyInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysProxyInfo" action="${ctx}/sys/sysProxyInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				代理服务器IP地址：
			</label>
			<div class="col-sm-3">
				<form:input path="proxyIp" htmlEscape="false" maxlength="32" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				代理服务器端口：
			</label>
			<div class="col-sm-3">
				<form:input path="proxyPort" htmlEscape="false" maxlength="8" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				代理服务器物理位置：
			</label>
			<div class="col-sm-3">
				<form:input path="serverAddr" htmlEscape="false" maxlength="128" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				协议类型（HTTP/HTTPS）：
			</label>
			<div class="col-sm-3">
				<form:input path="type" htmlEscape="false" maxlength="8" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				速度 单位秒：
			</label>
			<div class="col-sm-3">
				<form:input path="speed" htmlEscape="false" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				连接时间：
			</label>
			<div class="col-sm-3">
				<form:input path="connectTime" htmlEscape="false" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				存活时间：
			</label>
			<div class="col-sm-3">
				<form:input path="surviveTime" htmlEscape="false" maxlength="64" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				验证时间：
			</label>
			<div class="col-sm-3">
				<input name="verifyTime" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${sysProxyInfo.verifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				过期时间：
			</label>
			<div class="col-sm-3">
				<input name="expirationTime" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${sysProxyInfo.expirationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="sys:sysProxyInfo:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>