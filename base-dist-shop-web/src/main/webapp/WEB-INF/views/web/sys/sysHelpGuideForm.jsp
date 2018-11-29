<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统教程管理</title>
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
		<li><a href="${ctx}/sys/sysHelpGuide/">系统教程列表</a></li>
		<li class="active"><a href="${ctx}/sys/sysHelpGuide/form?id=${sysHelpGuide.id}">系统教程<shiro:hasPermission name="sys:sysHelpGuide:edit">${not empty sysHelpGuide.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:sysHelpGuide:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysHelpGuide" action="${ctx}/sys/sysHelpGuide/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				教程类别：
			</label>
			<div class="col-sm-3">
				<form:select path="guideType" class="form-control required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('guide_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				指南标题：
			</label>
			<div class="col-sm-3">
				<form:input path="title" htmlEscape="false" maxlength="50" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				指南图片：
			</label>
			<div class="col-sm-3">
			    <sys:fileInput path="picUrl" value="${sysHelpGuide.picUrl}" type="8" thumbSize="200X200"></sys:fileInput>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				排序号：
			</label>
			<div class="col-sm-3">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="form-control integer required"/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				商户Id：
			</label>
			<div class="col-sm-3">
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control required"/>
			</div>
		</div> --%>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="sys:sysHelpGuide:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>