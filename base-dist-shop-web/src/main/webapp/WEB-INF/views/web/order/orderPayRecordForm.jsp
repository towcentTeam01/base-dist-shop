<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易记录管理</title>
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
		<li><a href="${ctx}/order/orderPayRecord/">交易记录列表</a></li>
		<li class="active"><a href="${ctx}/order/orderPayRecord/form?id=${orderPayRecord.id}">交易记录<shiro:hasPermission name="order:orderPayRecord:edit">${not empty orderPayRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="order:orderPayRecord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orderPayRecord" action="${ctx}/order/orderPayRecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				用户：
			</label>
			<div class="col-sm-3">
				<sys:tagSearchList
                    id="createBy1.id"
                    value="${orderPayRecord.createBy1.id}"
                    name="createBy1.nickName"
                    labelValue="${orderPayRecord.createBy1.nickName} ${orderPayRecord.createBy1.mobile}"
                    url="${ctx}/member/sysFrontAccount/searchMemberList"
                    placeholder="选择会员"
                    cssClass="form-control">
            	</sys:tagSearchList>
			</div>
		</div>	
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				支付交易号：
			</label>
			<div class="col-sm-3">
				<form:input path="payRecordNo" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				业务类型：
			</label>
			<div class="col-sm-3">
				<form:select path="bizType" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				订单号：
			</label>
			<div class="col-sm-3">
				<form:input path="order.orderNo" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				支付方式：
			</label>
			<div class="col-sm-3">
				<form:select path="payType" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				支付时间：
			</label>
			<div class="col-sm-3">
				<input name="payDate" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${orderPayRecord.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				支付金额：
			</label>
			<div class="col-sm-3">
				<form:input path="payAmount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				支付状态：
			</label>
			<div class="col-sm-3">
				<form:select path="payStatus" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				余额金额：
			</label>
			<div class="col-sm-3">
				<form:input path="balanceAmount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				支付积分：
			</label>
			<div class="col-sm-3">
				<form:input path="interAmount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				网关支付金额：
			</label>
			<div class="col-sm-3">
				<form:input path="gatewayAmount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				第三方支付流水号：
			</label>
			<div class="col-sm-3">
				<form:input path="thirdPaySn" htmlEscape="false" maxlength="64" class="form-control "/>
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
			<%-- <shiro:hasPermission name="order:orderPayRecord:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission> --%>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>