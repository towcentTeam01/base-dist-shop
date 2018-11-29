<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商品管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/goods/goods/">商品列表</a></li>
    <shiro:hasPermission name="goods:goods:edit">
        <li><a href="${ctx}/goods/goods/form">商品添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="goods" action="${ctx}/goods/goods/" method="post"
           class="navbar-form form-search" role="form">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>商品分类：</label>
            <sys:treeselect id="goodsCategory" name="goodsCategory.id" value="${goods.goodsCategory.id}"
                            labelName="goodsCategory.categoryName" labelValue="${goods.goodsCategory.categoryName}"
                            title="商品分类" url="/goods/goodsCategory/treeData/${goods.merchantId }" allowClear="true"
                            cssClass="form-control required"/>
        </li>
        <li><label>商品编码：</label>
            <form:input path="goodsNo" htmlEscape="false" maxlength="50" class="form-control"/>
        </li>
        <li><label>商品名称：</label>
            <form:input path="goodsName" htmlEscape="false" maxlength="100" class="form-control"/>
        </li>
        <li><label>商品类型：</label>
            <form:select path="goodsType" class="form-control">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('goods_type')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>商品状态：</label>
            <form:select path="goodsStatus" class="form-control">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('goods_status')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li class="btns">
            <button type="submit" id="btnSubmit" class="button big"><span class="magnifier icon"></span>查询</button>
        </li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th style="width: 50px;text-align: center;">图片</th>
        <th>商品编码</th>
        <th>商品分类</th>
        <th style="width: 20%">商品名称</th>
        <th>商品类型</th>
        <th>商品状态</th>
        <th>商品价格</th>
        <th>兑换积分</th>
        <th>销量</th>
        <th>评价数量</th>
        <th>好评率</th>
        <th>更新时间</th>
        <shiro:hasPermission name="goods:goods:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="goods">
        <tr>
            <td>
                <img style="width: 50px;height: 50px; object-fit: contain; background-color: #e5e1e1;" src="${goods.picUrl}"/>
            </td>
            <td><a href="${ctx}/goods/goods/form?id=${goods.id}">
                    ${goods.goodsNo}</a>
            </td>
            <td>
                    ${goods.structureName}
            </td>
            <td>
                    ${goods.goodsName}
            </td>
            <td>
                    ${fns:getDictLabel(goods.goodsType, 'goods_type', '')}
            </td>
            <td>
                    ${fns:getDictLabel(goods.goodsStatus, 'goods_status', '')}
            </td>
            <td>
                    ${goods.price}
            </td>
            <td>
                    ${goods.integral}
            </td>
            <td>
                    ${goods.sales}
            </td>
            <td>
                    ${goods.evaNum}
            </td>
            <td>
                    ${goods.goodEvalRate}
            </td>
            <td>
                <fmt:formatDate value="${goods.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <shiro:hasPermission name="goods:goods:edit">
                <td>
                    <a href="${ctx}/goods/goods/putOff?id=${goods.id}">
                      <c:if test="${goods.goodsStatus == 1}">上架</c:if>                              
                      <c:if test="${goods.goodsStatus == 3}">上架</c:if>
                      <c:if test="${goods.goodsStatus == 2}"><span style="color:red;">下架</span></c:if>                    
                    </a>
                    <a href="${ctx}/goods/goods/form?id=${goods.id}">编辑</a>
                    <a href="${ctx}/goods/goods/delete?id=${goods.id}"
                       onclick="return confirmx('确认要删除该商品吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
${page}
</body>
</html>