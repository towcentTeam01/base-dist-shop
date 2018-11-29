<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品分类管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, rootId = "${not empty goodsCategory.id ? goodsCategory.id : '0'}";
			addRow("#treeTableList", tpl, data, rootId, true);
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						// image: (row.categoryIcon == '')?'':'<img alt="" src="' + row.categoryIcon + '" height="16" width="16">', 
						pid: (root?0:pid), 
						row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/goods/goodsCategory/list?id=${goodsCategory.id}&parentIds=${goodsCategory.parentIds}">商品分类列表</a></li>
		<shiro:hasPermission name="goods:goodsCategory:edit"><li><a href="${ctx}/goods/goodsCategory/form?parent.id=${goodsCategory.id}">商品分类添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>分类名称</th><th>分类编码</th><th>排序号</th><th>备注</th><shiro:hasPermission name="goods:goodsCategory:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/goods/goodsCategory/form?id={{row.id}}">{{row.categoryName}}</a></td>
			<td>{{row.categoryNo}}</td>
			<td>{{row.sort}}</td>
			<td>{{row.remarks}}</td>
			<shiro:hasPermission name="goods:goodsCategory:edit"><td>
				<a href="${ctx}/goods/goodsCategory/form?id={{row.id}}">修改</a>
				<a href="${ctx}/goods/goodsCategory/delete?id={{row.id}}" onclick="return confirmx('要删除该分类及所有子分类项吗？', this.href)">删除</a>
				<a href="${ctx}/goods/goodsCategory/form?parent.id={{row.id}}">添加下级分类</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>