<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单明细管理</title>
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
		<li><a href="${ctx}/order/orderDtl/">订单明细列表</a></li>
		<li class="active"><a href="${ctx}/order/orderDtl/form?id=${orderDtl.id}">订单明细<shiro:hasPermission name="order:orderDtl:edit">${not empty orderDtl.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="order:orderDtl:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orderDtl" action="${ctx}/order/orderDtl/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				订单id：
			</label>
			<div class="col-sm-3">
				<form:input path="orderId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商品id：
			</label>
			<div class="col-sm-3">
				<form:input path="goodsId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商品名称：
			</label>
			<div class="col-sm-3">
				<form:input path="goodsName" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商品图片：
			</label>
			<div class="col-sm-3">
				<form:input path="goodsPicUrl" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				规格：
			</label>
			<div class="col-sm-3">
				<form:input path="spec" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				数量：
			</label>
			<div class="col-sm-3">
				<form:input path="qty" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				单价(元)：
			</label>
			<div class="col-sm-3">
				<form:input path="price" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				金额=数量*单价(元)：
			</label>
			<div class="col-sm-3">
				<form:input path="amount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				兑换积分：
			</label>
			<div class="col-sm-3">
				<form:input path="integral" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				评价标识(0:未评价 1:已评价) yes_no：
			</label>
			<div class="col-sm-3">
				<form:input path="evalFlag" htmlEscape="false" maxlength="1" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商户id：
			</label>
			<div class="col-sm-3">
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				规格id：
			</label>
			<div class="col-sm-3">
				<form:input path="specId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="order:orderDtl:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>