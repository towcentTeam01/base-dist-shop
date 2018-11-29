<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户信息管理</title>
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
		<li><a href="${ctx}/sys/sysMerchantInfo/">商户信息列表</a></li>
		<li class="active"><a href="${ctx}/sys/sysMerchantInfo/form?id=${sysMerchantInfo.id}">商户信息<shiro:hasPermission name="sys:sysMerchantInfo:edit">${not empty sysMerchantInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:sysMerchantInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysMerchantInfo" action="${ctx}/sys/sysMerchantInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				系统账号：
			</label>
			<div class="col-sm-3">
				<form:input path="sysUserId" htmlEscape="false" maxlength="11" class="form-control required"/>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				店铺编码：
			</label>
			<div class="col-sm-3">
				<form:input path="houseNumber" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				店铺名称：
			</label>
			<div class="col-sm-3">
				<form:input path="shopName" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				店铺类别：
			</label>
			<div class="col-sm-3">
				<form:select path="shopType" class="form-control ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				联系电话：
			</label>
			<div class="col-sm-3">
				<form:input path="phone" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				商家LOGO：
			</label>
			<div class="col-sm-3">
			    <sys:fileInput path="logo" value="${sysMerchantInfo.logo}" type="6" thumbSize="200X200"></sys:fileInput>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				开店地址/省/市/区：
			</label>
			<div class="col-sm-3">
			    <div class="col-sm-4" style="margin: 0px; padding: 0px;">
                    <sys:treeselect id="province" name="province.id" value="${sysMerchantInfo.province.id}" labelName="area.name" labelValue="${sysMerchantInfo.province.name}"
                        title="省" url="/sys/area/treeData" cssClass="form-control required"/>
                </div>
                <div class="col-sm-4" style="margin: 0px; padding: 0px;">
                    <sys:treeselect id="city" name="city.id" value="${sysMerchantInfo.city.id}" labelName="area.name" labelValue="${sysMerchantInfo.city.name}"
                        title="市" url="/sys/area/treeData" cssClass="form-control required"/>
                </div>
                <div class="col-sm-4" style="margin: 0px; padding: 0px;">
                    <sys:treeselect id="district" name="district.id" value="${sysMerchantInfo.district.id}" labelName="area.name" labelValue="${sysMerchantInfo.district.name}"
                        title="区" url="/sys/area/treeData" cssClass="form-control required"/>
                </div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				街道：
			</label>
			<div class="col-sm-3">
				<form:input path="street" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				详细地址：
			</label>
			<div class="col-sm-3">
				<form:input path="address" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				经度：
			</label>
			<div class="col-sm-3">
				<form:input path="longtitude" htmlEscape="false" maxlength="30" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				纬度：
			</label>
			<div class="col-sm-3">
				<form:input path="latitude" htmlEscape="false" maxlength="30" class="form-control "/>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				邮箱：
			</label>
			<div class="col-sm-3">
				<form:input path="email" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
			<span class="col-sm-4 help-block">联系人</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				qq号码：
			</label>
			<div class="col-sm-3">
				<form:input path="qq" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
			<span class="col-sm-4 help-block">联系人</span>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				微信号码：
			</label>
			<div class="col-sm-3">
				<form:input path="wxCode" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
			<span class="col-sm-4 help-block">联系人</span>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				微信二维码地址（联系人）：
			</label>
			<div class="col-sm-3">
				<form:input path="wxQrCode" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				客服电话：
			</label>
			<div class="col-sm-3">
				<form:input path="servicePhone" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				二维码地址(小店的二维码)：
			</label>
			<div class="col-sm-3">
				<form:input path="qrCode" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				法人姓名：
			</label>
			<div class="col-sm-3">
				<form:input path="selfEmployedName" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
            <label class="col-sm-2 control-label">
                                        身份证号码：
            </label>
            <div class="col-sm-3">
                <form:input path="applyIdCard" htmlEscape="false" maxlength="200" class="form-control "/>
            </div>
        </div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				身份证正面：
			</label>
			<div class="col-sm-3">
			    <sys:fileInput path="idCardFrontUrl" value="${sysMerchantInfo.idCardFrontUrl}" type="8" thumbSize="200X200"></sys:fileInput>
			</div>
		</div>
		<div class="form-group">
            <label class="col-sm-2 control-label">
                                        身份证反面：
            </label>
            <div class="col-sm-3">
                <sys:fileInput path="idCardBackUrl" value="${sysMerchantInfo.idCardBackUrl}" type="8" thumbSize="200X200"></sys:fileInput>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">
                                        手持身份证照片：
            </label>
            <div class="col-sm-3">
                <sys:fileInput path="handIdCardUrl" value="${sysMerchantInfo.handIdCardUrl}" type="8" thumbSize="200X200"></sys:fileInput>
            </div>
        </div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				营业执照照片：
			</label>
			<div class="col-sm-3">
			    <sys:fileInput path="bizLicUrl" value="${sysMerchantInfo.bizLicUrl}" type="8" thumbSize="200X200"></sys:fileInput>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				封面图：
			</label>
			<div class="col-sm-3">
			    <sys:fileInput path="coverUrl" value="${sysMerchantInfo.coverUrl}" type="8" thumbSize="200X200"></sys:fileInput>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				标签：
			</label>
			<div class="col-sm-3">
				<form:input path="alias" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
			<span class="col-sm-4 help-block">多个;分割</span>
		</div>
		<div class="form-group">
            <label class="col-sm-2 control-label">
                                       状态：
            </label>
            <div class="col-sm-3">
                <form:select path="applyStatus" class="form-control ">
                    <form:option value="" label="请选择"/>
                    <form:options items="${fns:getDictList('merchant_apply_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </div>
        </div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				启用标识：
			</label>
			<div class="col-sm-3">
				<form:select path="enabledFlag" class="form-control ">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			<div class="col-sm-offset-2 col-sm-3">
			<shiro:hasPermission name="sys:sysMerchantInfo:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>