<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠券管理</title>
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
		<li><a href="${ctx}/coupon/couponAct/">优惠券列表</a></li>
		<li class="active"><a href="${ctx}/coupon/couponAct/form?id=${couponAct.id}">优惠券<shiro:hasPermission name="coupon:couponAct:edit">${not empty couponAct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="coupon:couponAct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="couponAct" action="${ctx}/coupon/couponAct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font	color="red">*</font></span>活动名称：
			</label>
			<div class="col-sm-3">
				<form:input path="actName" htmlEscape="false" maxlength="200" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font	color="red">*</font></span>开始时间：
			</label>
			<div class="col-sm-3">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${couponAct.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font	color="red">*</font></span>结束时间：
			</label>
			<div class="col-sm-3">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${couponAct.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font	color="red">*</font></span>类型：
			</label>
			<div class="col-sm-3">
				<form:select path="actType" class="form-control required">
					<form:options items="${fns:getDictList('act_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group gtype type_3" style="display: none;">
			<label class="col-sm-2 control-label">
				<span><font	color="red">*</font></span>满减金额：
			</label>
			<div class="col-sm-3">
				<form:input path="limitAmount" htmlEscape="false" class="form-control required money"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font	color="red">*</font></span><span id="typeLabel">优惠券金额</span>：
			</label>
			<div class="col-sm-3">
				<form:input path="amount" htmlEscape="false" class="form-control required money"/>
			</div>
			<span id="typeTip" class="col-sm-4 help-block" style="margin: 7px 0;">折扣率输入格式（0-10）</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font	color="red">*</font></span>总发行数量：
			</label>
			<div class="col-sm-3">
				<form:input path="totalQty" htmlEscape="false" maxlength="11" class="form-control required integer"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span><font	color="red">*</font></span>是否开启：
			</label>
			<div class="col-sm-3">
				<form:select path="openFlag" class="form-control required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				活动页地址：
			</label>
			<div class="col-sm-3">
				<form:input path="actUrl" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group" style="display: ${not empty couponAct.actQrCode ? 'block' : 'none'}">
			<label class="col-sm-2 control-label">
				活动页地址二维码：
			</label>
			<div class="col-sm-3">
				<img src="${couponAct.actQrCode}" />
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
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="coupon:couponAct:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>

	<script>
        $('#actType').change(function () {
            var val = $(this).val();
            $('.gtype').hide();
            $('.type_' + val).show();

            if(val == '3'){
               $('#limitAmount').addClass('required');
               $('#typeLabel').html('折扣率');
			}else{
                $('#limitAmount').removeClass('required');
                $('#typeLabel').html('优惠券金额');
            }
        });

	</script>

</body>
</html>

