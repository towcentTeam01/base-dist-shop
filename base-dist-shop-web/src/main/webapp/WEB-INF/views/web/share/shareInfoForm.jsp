<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分享记录管理</title>
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
		<li><a href="${ctx}/share/shareInfo/">分享记录列表</a></li>
		<li class="active"><a href="${ctx}/share/shareInfo/form?id=${shareInfo.id}">分享记录<shiro:hasPermission name="share:shareInfo:edit">${not empty shareInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="share:shareInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shareInfo" action="${ctx}/share/shareInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				工号：
			</label>
			<div class="col-sm-3">
				<form:input path="jobNo" htmlEscape="false" maxlength="20" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				商户id：
			</label>
			<div class="col-sm-3">
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商品ID：
			</label>
			<div class="col-sm-3">
				<form:input path="productId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				分享信息：
			</label>
			<div class="col-sm-3">
				<form:input path="shareDesc" htmlEscape="false" maxlength="512" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				分享图片：
			</label>
			<div class="col-sm-3">
				<form:input path="shareImage" htmlEscape="false" maxlength="256" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				分享链接：
			</label>
			<div class="col-sm-3">
				<form:input path="shareUrl" htmlEscape="false" maxlength="256" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				浏览量：
			</label>
			<div class="col-sm-3">
				<form:input path="viewNum" htmlEscape="false" maxlength="11" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				成交量：
			</label>
			<div class="col-sm-3">
				<form:input path="tradeNum" htmlEscape="false" maxlength="11" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				分享标题：
			</label>
			<div class="col-sm-3">
				<form:input path="shareTitle" htmlEscape="false" maxlength="128" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="share:shareInfo:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>