<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品分类管理</title>
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
		<li><a href="${ctx}/goods/goodsCategory/list?id=${goodsCategory.parent.id}&parentIds=${goodsCategory.parentIds}">商品分类列表</a></li>
		<li class="active"><a href="${ctx}/goods/goodsCategory/form?id=${goodsCategory.id}">商品分类<shiro:hasPermission name="goods:goodsCategory:edit">${not empty goodsCategory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="goods:goodsCategory:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="goodsCategory" action="${ctx}/goods/goodsCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="merchantId" id="txt_merchantId"/>
		<sys:message content="${message}"/>	
		<c:if test="${goodsCategory.parentId != 0 || goodsCategory.id == null || goodsCategory.id == ''}">
		<div class="form-group">
			<label class="col-sm-2 control-label">父级：</label>
			<div class="col-sm-3">
			      <sys:treeselect id="goodsCategory" name="parent.id" value="${goodsCategory.parent.id}" labelName="parent.categoryName" labelValue="${goodsCategory.parent.categoryName}"
					title="商品分类" url="/goods/goodsCategory/treeData/${goodsCategory.merchantId }" extId="${goodsCategory.id}" cssClass="form-control"/>
			</div>
		</div>	
		</c:if>
		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">分类编码：</label>
			<div class="col-sm-3">
				<form:input path="categoryNo" id="txt_categoryNo" htmlEscape="false" maxlength="20" class="form-control required" onchange="checkCategoryNo()"/>
			</div>
			<div id="txt_categoryNo_error_msg" class="col-sm-3" style="color: red;"></div>
		</div> --%>
		<div class="form-group">
			<label class="col-sm-2 control-label">分类名称：</label>
			<div class="col-sm-3">
				<form:input path="categoryName" htmlEscape="false" maxlength="100" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">排序：</label>
			<div class="col-sm-3">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="form-control  digits"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">图片：</label>
			<div class="col-sm-3">
				<sys:fileInput path="categoryIcon" value="${goodsCategory.categoryIcon}" type="4" thumbSize="80X80"></sys:fileInput>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注：</label>
			<div class="col-sm-3">
				<form:input path="remarks" htmlEscape="false" maxlength="500" class="form-control "/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-5 col-sm-offset-2">
				<shiro:hasPermission name="goods:goodsCategory:edit">
					<button id="btnSubmit" type="submit" class="button big">保 存</button>&nbsp;
				</shiro:hasPermission>
				<button id="btnCancel" type="button" class="button big" onclick="history.go(-1)">返 回</button>
			</div>
		</div>
	</form:form>
	
	<script>
	   /* function checkCategoryNo(){
		   var categoryNo = $("#txt_categoryNo").val();
		   if(categoryNo.trim().length>0){
			   $.post("${ctx}/goods/goodsCategory/check/categoryNo/"+$("#txt_merchantId").val()+"/"+categoryNo, function(data){
				   if(data){
					   $("#txt_categoryNo_error_msg").html("");
					   $("#btnSubmit").prop("disabled",false);
				   }else{
					   $("#txt_categoryNo_error_msg").html("分类编码【"+categoryNo+"】已存在，请 不要重复输入");
					   $("#btnSubmit").prop("disabled",true);
				   }
			   });
		   }else{
			   $("#txt_categoryNo").val("");
		   }
	   } */
	</script>
</body>
</html>