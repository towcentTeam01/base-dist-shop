<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品分享分类管理</title>
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
		<li><a href="${ctx}/share/shareCategory/">商品分享分类列表</a></li>
		<li class="active"><a href="${ctx}/share/shareCategory/form?id=${shareCategory.id}">商品分享分类<shiro:hasPermission name="share:shareCategory:edit">${not empty shareCategory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="share:shareCategory:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shareCategory" action="${ctx}/share/shareCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				分类编码：
			</label>
			<div class="col-sm-3">
				<form:input path="categoryNo" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				分享信息：
			</label>
			<div class="col-sm-3">
				<form:input path="shareDesc" htmlEscape="false" maxlength="512" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				分享图片：
			</label>
			<div class="col-sm-3">
				<form:input path="shareImage" htmlEscape="false" maxlength="256" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				分享标题：
			</label>
			<div class="col-sm-3">
				<form:input path="shareTitle" htmlEscape="false" maxlength="128" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商户id 为0时为平台：
			</label>
			<div class="col-sm-3">
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商品分类id：
			</label>
			<div class="col-sm-3">
				<form:input path="categoryId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="share:shareCategory:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>