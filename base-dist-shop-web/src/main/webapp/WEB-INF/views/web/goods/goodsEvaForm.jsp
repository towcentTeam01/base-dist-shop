<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品评价管理</title>
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
		<li><a href="${ctx}/goods/goodsEva/">商品评价列表</a></li>
		<li class="active"><a href="${ctx}/goods/goodsEva/form?id=${goodsEva.id}">商品评价<shiro:hasPermission name="goods:goodsEva:edit">${not empty goodsEva.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="goods:goodsEva:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="goodsEva" action="${ctx}/goods/goodsEva/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商品id：
			</label>
			<div class="col-sm-3">
				<form:input path="goods.id" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				订单id：
			</label>
			<div class="col-sm-3">
				<form:input path="order.id" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				订单详情id：
			</label>
			<div class="col-sm-3">
				<form:input path="orderDtlId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				评价内容：
			</label>
			<div class="col-sm-3">
				<form:input path="evaContent" htmlEscape="false" maxlength="500" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				会员id：
			</label>
			<div class="col-sm-3">
				<sys:treeselect id="user" name="user.id" value="${goodsEva.user.id}" labelName="user.name" labelValue="${goodsEva.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				评价图片：
			</label>
			<div class="col-sm-3">
				<form:input path="evaUrls" htmlEscape="false" maxlength="1000" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				评价星级：
			</label>
			<div class="col-sm-3">
				<form:input path="evaStar" htmlEscape="false" maxlength="1" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商家回复：
			</label>
			<div class="col-sm-3">
				<form:input path="replyContent" htmlEscape="false" maxlength="500" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商家回复时间：
			</label>
			<div class="col-sm-3">
				<input name="replyDate" type="text" readonly="readonly" maxlength="20" class="form-control input-medium Wdate "
					value="<fmt:formatDate value="${goodsEva.replyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				是否匿名：
			</label>
			<div class="col-sm-3">
				<form:input path="isHideName" htmlEscape="false" maxlength="2" class="form-control "/>
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
				<form:input path="merchantId" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="goods:goodsEva:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>