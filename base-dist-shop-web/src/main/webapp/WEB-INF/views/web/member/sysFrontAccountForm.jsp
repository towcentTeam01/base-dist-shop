<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
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
		<li><a href="${ctx}/member/sysFrontAccount/">会员列表</a></li>
		<li class="active"><a href="${ctx}/member/sysFrontAccount/form?id=${sysFrontAccount.id}">会员<shiro:hasPermission name="member:sysFrontAccount:edit">${not empty sysFrontAccount.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="member:sysFrontAccount:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysFrontAccount" action="${ctx}/member/sysFrontAccount/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">
				头像地址：
			</label>
			<div class="col-sm-3">
				<sys:fileInput path="portrait" value="${sysFrontAccount.portrait}" type="0" thumbSize="200X200"></sys:fileInput>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				帐号：
			</label>
			<div class="col-sm-3">
				<form:input path="account" htmlEscape="false" maxlength="20" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				手机号：
			</label>
			<div class="col-sm-3">
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				邮箱：
			</label>
			<div class="col-sm-3">
				<form:input path="email" htmlEscape="false" maxlength="100" class="form-control "/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				密码：
			</label>
			<div class="col-sm-3">
				<form:input path="password" htmlEscape="false" maxlength="32" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				交易密码：
			</label>
			<div class="col-sm-3">
				<form:input path="tradePassword" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div> --%>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				账户余额：
			</label>
			<div class="col-sm-3">
				<form:input path="amount" htmlEscape="false" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				冻结余额：
			</label>
			<div class="col-sm-3">
				<form:input path="freezeAmount" htmlEscape="false" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				收入总额：
			</label>
			<div class="col-sm-3">
				<form:input path="marginAmount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				已结算：
			</label>
			<div class="col-sm-3">
				<form:input path="settledAmount" htmlEscape="false" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				积分：
			</label>
			<div class="col-sm-3">
				<form:input path="inter" htmlEscape="false" maxlength="11" class="form-control "/>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				工号：
			</label>
			<div class="col-sm-3">
				<form:input path="jobNo" htmlEscape="false" maxlength="20" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="help-inline"><font color="red" style="vertical-align:middle;">* </font></span>
				用户等级：
			</label>
			<div class="col-sm-3">
				<form:select path="levelVip" class="form-control required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('level_vip')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				归属上级：
			</label>
			<div class="col-sm-3">
				<sys:tagSearchList
                    id="parent.id"
                    value="${sysFrontAccount.parent.id}"
                    name="parent.nickName"
                    labelValue="${sysFrontAccount.parent.nickName} ${sysFrontAccount.parent.mobile}"
                    url="${ctx}/member/sysFrontAccount/searchMemberList"
                    placeholder="选择会员"
                    cssClass="form-control">
            	</sys:tagSearchList>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				性别：
			</label>
			<div class="col-sm-3">
				<form:select path="sex" class="form-control required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				昵称：
			</label>
			<div class="col-sm-3">
				<form:input path="nickName" htmlEscape="false" maxlength="64" class="form-control "/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				关注openid：
			</label>
			<div class="col-sm-3">
				<form:input path="openId" htmlEscape="false" maxlength="60" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				微信小程序openid：
			</label>
			<div class="col-sm-3">
				<form:input path="miniOpenId" htmlEscape="false" maxlength="60" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				微信unionid：
			</label>
			<div class="col-sm-3">
				<form:input path="unionId" htmlEscape="false" maxlength="60" class="form-control "/>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				微信号：
			</label>
			<div class="col-sm-3">
				<form:input path="bindWx" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div>
		<c:if test="${not empty sysFrontAccount.id}">
			<div class="form-group">
				<label class="col-sm-2 control-label">
					微信二维码：
				</label>
				<div class="col-sm-3">
					<sys:fileInput path="wxQrCode" value="${sysFrontAccount.wxQrCode}" type="7" thumbSize="200X200"></sys:fileInput>
					<%-- <form:input path="wxQrCode" htmlEscape="false" maxlength="200" class="form-control "/> --%>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">
					专属海报：
				</label>
				<div class="col-sm-3">
					<sys:fileInput path="posterUrl" value="${sysFrontAccount.posterUrl}" type="7" thumbSize="200X200"></sys:fileInput>
					<%-- <form:input path="posterUrl" htmlEscape="false" maxlength="200" class="form-control "/> --%>
				</div>
			</div>
		</c:if>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				小程序二维码：
			</label>
			<div class="col-sm-3">
				<form:input path="miniQrCode" htmlEscape="false" maxlength="200" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				绑定的微博号：
			</label>
			<div class="col-sm-3">
				<form:input path="bindWeibo" htmlEscape="false" maxlength="32" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				绑定的QQ号：
			</label>
			<div class="col-sm-3">
				<form:input path="bindQq" htmlEscape="false" maxlength="12" class="form-control "/>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				账号状态：
			</label>
			<div class="col-sm-3">
				<form:select path="status" class="form-control ">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('lock_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				用户类型(0:客户)：
			</label>
			<div class="col-sm-3">
				<form:input path="userType" htmlEscape="false" maxlength="2" class="form-control "/>
			</div>
		</div> --%>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">
				备用字段：
			</label>
			<div class="col-sm-3">
				<form:input path="remark" htmlEscape="false" maxlength="500" class="form-control "/>
			</div>
		</div> --%>
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
			<shiro:hasPermission name="member:sysFrontAccount:edit">
			   <button type="submit" id="btnSubmit" class="button big">保存</button>&nbsp;&nbsp;
			</shiro:hasPermission>
			<button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
			</div>
		</div>
	</form:form>
</body>
</html>