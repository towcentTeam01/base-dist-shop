<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>邀请介绍管理</title>
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
		<li><a href="${ctx}/config/sysHelpInviteIntro/">邀请介绍列表</a></li>
		<li class="active"><a href="${ctx}/config/sysHelpInviteIntro/form?id=${sysHelpInviteIntro.id}">邀请介绍<shiro:hasPermission name="config:sysHelpInviteIntro:edit">${not empty sysHelpInviteIntro.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="config:sysHelpInviteIntro:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysHelpInviteIntro" action="${ctx}/config/sysHelpInviteIntro/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font color="red">*</font></span>标题：
			</label>
			<div class="col-sm-3">
				<form:input path="title" htmlEscape="false" maxlength="50" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font color="red">*</font></span>描述图片：
			</label>
			<div class="col-sm-3">
				<sys:fileInput path="picUrl" value="${sysHelpInviteIntro.picUrl}" type="8" thumbSize="200X200"></sys:fileInput>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font color="red">*</font></span>排序号：
			</label>
			<div class="col-sm-3">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="form-control integer required"/>
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
			<shiro:hasPermission name="config:sysHelpInviteIntro:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>