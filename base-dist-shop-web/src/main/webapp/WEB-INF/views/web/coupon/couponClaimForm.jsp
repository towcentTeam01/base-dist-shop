<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠券领取管理</title>
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
		<li><a href="${ctx}/coupon/couponClaim/">优惠券领取列表</a></li>
		<li class="active"><a href="${ctx}/coupon/couponClaim/form?id=${couponClaim.id}">优惠券领取<shiro:hasPermission name="coupon:couponClaim:edit">${not empty couponClaim.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="coupon:couponClaim:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="couponClaim" action="${ctx}/coupon/couponClaim/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				活动id：
			</label>
			<div class="col-sm-3">
				<form:input path="actId" htmlEscape="false" maxlength="11" class="form-control  digits"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				会员id：
			</label>
			<div class="col-sm-3">
				<sys:treeselect id="user" name="user.id" value="${couponClaim.user.id}" labelName="user.name" labelValue="${couponClaim.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				会员昵称：
			</label>
			<div class="col-sm-3">
				<form:input path="nickName" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				满减金额：
			</label>
			<div class="col-sm-3">
				<form:input path="limitAmount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				优惠券金额/折扣金额：
			</label>
			<div class="col-sm-3">
				<form:input path="amount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				使用状态：
			</label>
			<div class="col-sm-3">
				<form:select path="useFlag" class="form-control required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('use_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商户id：
			</label>
			<div class="col-sm-3">
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control  digits"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				订单id：
			</label>
			<div class="col-sm-3">
				<form:input path="orderId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="coupon:couponClaim:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>