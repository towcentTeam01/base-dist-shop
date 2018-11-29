<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>海报模板管理</title>
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
		<li><a href="${ctx}/sys/sysPosterTemplate/">海报模板列表</a></li>
		<li class="active"><a href="${ctx}/sys/sysPosterTemplate/form?id=${sysPosterTemplate.id}">海报模板<shiro:hasPermission name="sys:sysPosterTemplate:edit">${not empty sysPosterTemplate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:sysPosterTemplate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysPosterTemplate" action="${ctx}/sys/sysPosterTemplate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
			    <span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				模板名称：
			</label>
			<div class="col-sm-3">
				<form:input path="title" htmlEscape="false" maxlength="50" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
			    <span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				模板图片：
			</label>
			<div class="col-sm-3">
			    <sys:fileInput path="picUrl" value="${sysPosterTemplate.picUrl}" type="9" thumbSize="200X200"></sys:fileInput>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				排序号：
			</label>
			<div class="col-sm-3">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="form-control required integer"/>
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
			<shiro:hasPermission name="sys:sysPosterTemplate:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>