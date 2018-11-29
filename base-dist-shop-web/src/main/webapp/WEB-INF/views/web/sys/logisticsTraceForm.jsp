<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物流跟踪管理</title>
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
		<li><a href="${ctx}/sys/logisticsTrace/">物流跟踪列表</a></li>
		<li class="active"><a href="${ctx}/sys/logisticsTrace/form?id=${logisticsTrace.id}">物流跟踪<shiro:hasPermission name="sys:logisticsTrace:edit">${not empty logisticsTrace.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:logisticsTrace:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="logisticsTrace" action="${ctx}/sys/logisticsTrace/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				物流公司编号：
			</label>
			<div class="col-sm-3">
				<form:input path="logisticsNo" htmlEscape="false" maxlength="64" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				物流公司中文名称：
			</label>
			<div class="col-sm-3">
				<form:input path="logisticsName" htmlEscape="false" maxlength="128" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				物流单号：
			</label>
			<div class="col-sm-3">
				<form:input path="freightNumber" htmlEscape="false" maxlength="64" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				跟踪时间：
			</label>
			<div class="col-sm-3">
				<input name="traceTime" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${logisticsTrace.traceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				物流描述：
			</label>
			<div class="col-sm-3">
				<form:input path="traceDesc" htmlEscape="false" maxlength="512" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				物流坐标：
			</label>
			<div class="col-sm-3">
				<form:input path="location" htmlEscape="false" maxlength="128" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				是否签收 0 未签收 1已签收：
			</label>
			<div class="col-sm-3">
				<form:select path="isCheck" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_check')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="sys:logisticsTrace:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>