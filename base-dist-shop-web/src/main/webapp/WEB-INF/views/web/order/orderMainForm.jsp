<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li><a href="${ctx}/order/orderMain/">订单列表</a></li>
		<li class="active"><a href="${ctx}/order/orderMain/form?id=${orderMain.id}">订单<shiro:hasPermission name="order:orderMain:edit">${not empty orderMain.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="order:orderMain:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orderMain" action="${ctx}/order/orderMain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				订单类型：
			</label>
			<div class="col-sm-3">
				<form:select path="orderType" class="form-control required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				订单号：
			</label>
			<div class="col-sm-3">
				<form:input path="orderNo" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				订单状态：
			</label>
			<div class="col-sm-3">
				<form:select path="orderStatus" class="form-control required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				支付状态：
			</label>
			<div class="col-sm-3">
				<form:select path="payStatus" class="form-control required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				付款方式：
			</label>
			<div class="col-sm-3">
				<form:select path="payWay" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_way')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				总金额：
			</label>
			<div class="col-sm-3">
				<form:input path="totalAmount" htmlEscape="false" class="form-control "/>
			</div>
			<span class="col-sm-4 help-block">商品总金额-优惠金额+运费</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				余额支付：
			</label>
			<div class="col-sm-3">
				<form:input path="amount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				线上实付金额(总)：
			</label>
			<div class="col-sm-3">
				<form:input path="payAmount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				积分支付数额：
			</label>
			<div class="col-sm-3">
				<form:input path="payInter" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				优惠金额：
			</label>
			<div class="col-sm-3">
				<form:input path="couponAmount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				收货人姓名：
			</label>
			<div class="col-sm-3">
				<form:input path="consigneeName" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				收货人联系方式：
			</label>
			<div class="col-sm-3">
				<form:input path="mobilePhone" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				收货详细地址：
			</label>
			<div class="col-sm-3">
				<form:input path="consigneeAddr" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商品总数量：
			</label>
			<div class="col-sm-3">
				<form:input path="totalQty" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				运费：
			</label>
			<div class="col-sm-3">
				<form:input path="freightFee" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				运单号：
			</label>
			<div class="col-sm-3">
				<form:input path="freightNumber" htmlEscape="false" maxlength="50" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				物流公司id：
			</label>
			<div class="col-sm-3">
				<form:input path="logisticsNo" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				物流公司名称：
			</label>
			<div class="col-sm-3">
				<form:input path="logisticsName" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				是否评论：
			</label>
			<div class="col-sm-3">
				<form:select path="isEval" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				售后备注：
			</label>
			<div class="col-sm-3">
				<form:input path="saleAfterRemarks" htmlEscape="false" maxlength="2000" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				售后申请时间：
			</label>
			<div class="col-sm-3">
				<input name="saleAfterDate" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${orderMain.saleAfterDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				注备：
			</label>
			<div class="col-sm-3">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="600" class="form-control input-xxlarge "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				支付时间：
			</label>
			<div class="col-sm-3">
				<input name="payDate" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${orderMain.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				发货时间：
			</label>
			<div class="col-sm-3">
				<input name="deliveryTime" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${orderMain.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
			<shiro:hasPermission name="order:orderMain:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>