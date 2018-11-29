<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>等级配置管理</title>
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
		<li><a href="${ctx}/config/sysLevelConf/">等级配置列表</a></li>
		<li class="active"><a href="${ctx}/config/sysLevelConf/form?id=${sysLevelConf.id}">等级配置<shiro:hasPermission name="config:sysLevelConf:edit">${not empty sysLevelConf.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="config:sysLevelConf:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysLevelConf" action="${ctx}/config/sysLevelConf/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font color="red">*</font></span>会员等级别名：
			</label>
			<div class="col-sm-3">
				<form:input path="levelName" htmlEscape="false" maxlength="200" class="form-control required"/>
			</div>
			<span class="col-sm-4 help-block">会员等级别名等同于会员等级名称</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font color="red">*</font></span>用户等级：
			</label>
			<div class="col-sm-3">
				<form:select path="level" class="form-control required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('level_vip')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font color="red">*</font></span>升级所需锁粉数：
			</label>
			<div class="col-sm-3">
				<form:input path="lockFans" htmlEscape="false" maxlength="11" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font color="red">*</font></span>升级所需直推订单数：
			</label>
			<div class="col-sm-3">
				<form:input path="recOrder" htmlEscape="false" maxlength="11" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font color="red">*</font></span>升级所需费用：
			</label>
			<div class="col-sm-3">
				<form:input path="payFee" htmlEscape="false" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font color="red">*</font></span>是否默认等级：
			</label>
			<div class="col-sm-3">
				<form:select path="defaultFlag" class="form-control required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				直推佣金比例：
			</label>
			<div class="col-sm-3">
				<form:input path="directBkge" htmlEscape="false" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				注备：
			</label>
			<div class="col-sm-3">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="500" class="form-control input-xxlarge "/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				商户id：
			</label>
			<div class="col-sm-3">
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div> --%>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="config:sysLevelConf:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>