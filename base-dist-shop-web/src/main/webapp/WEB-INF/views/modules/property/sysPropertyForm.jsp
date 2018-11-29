<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统配置管理</title>
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
	<li><a href="${ctx}/property/sysProperty/">系统配置列表</a></li>
	<li class="active"><a href="${ctx}/property/sysProperty/form?id=${sysProperty.id}">系统配置<shiro:hasPermission name="property:sysProperty:edit">${not empty sysProperty.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="property:sysProperty:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="sysProperty" action="${ctx}/property/sysProperty/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<div class="form-group">
		<label class="col-sm-2 control-label">
			属性名：
		</label>
		<div class="col-sm-3">
			<form:input path="propertyName" htmlEscape="false" maxlength="150" class="form-control "/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">
			属性值：
		</label>
		<div class="col-sm-3">
			<form:input path="propertyValue" htmlEscape="false" maxlength="1000" class="form-control "/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">
			排序：
		</label>
		<div class="col-sm-3">
			<form:input path="sort" htmlEscape="false" maxlength="11" class="form-control "/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">
			备注：
		</label>
		<div class="col-sm-3">
			<form:input path="remark" htmlEscape="false" maxlength="500" class="form-control "/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="property:sysProperty:edit">
				<button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
		</div>
	</div>
</form:form>
</body>
</html>