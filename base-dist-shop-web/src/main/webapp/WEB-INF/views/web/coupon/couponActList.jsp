<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>优惠券管理</title>
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
    <li class="active"><a href="${ctx}/coupon/couponAct/">优惠券列表</a></li>
    <shiro:hasPermission name="coupon:couponAct:edit">
        <li><a href="${ctx}/coupon/couponAct/form">优惠券添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="couponAct" action="${ctx}/coupon/couponAct/" method="post"
           class="navbar-form form-search" role="form">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>活动名称：</label>
            <form:input path="actName" htmlEscape="false" maxlength="200" class="form-control"/>
        </li>
        <li><label>类型：</label>
            <form:select path="actType" class="form-control">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('act_type')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>是否开启：</label>
            <form:select path="openFlag" class="form-control">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
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
        <th>活动名称</th>
        <th>活动时间</th>
        <th>类型</th>
        <th>满减金额</th>
        <th>优惠券金额/折扣率</th>
        <th>总发行数量</th>
        <th>剩余数量</th>
        <th>是否开启</th>
        <th>更新时间</th>
        <th
                <shiro:hasPermission name="coupon:couponAct:edit">>操作</shiro:hasPermission>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="couponAct">
        <tr>
            <td><a href="${ctx}/coupon/couponAct/form?id=${couponAct.id}">
                    ${couponAct.actName}
            </a></td>
            <td>
                <fmt:formatDate value="${couponAct.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/> ~ <fmt:formatDate
                    value="${couponAct.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${fns:getDictLabel(couponAct.actType, 'act_type', '')}
            </td>
            <td>
                    ${not empty couponAct.limitAmount?couponAct.limitAmount:'~'}
            </td>
            <td>
                    ${couponAct.amount}
            </td>
            <td>
                    ${couponAct.totalQty}
            </td>
            <td>
                    ${couponAct.residQty}
            </td>
            <td>
                    ${fns:getDictLabel(couponAct.openFlag, 'yes_no', '')}
            </td>
            <td>
                <fmt:formatDate value="${couponAct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <shiro:hasPermission name="coupon:couponAct:edit">
                    <a href="${ctx}/coupon/couponAct/form?id=${couponAct.id}">修改</a>
                    <a href="${ctx}/coupon/couponAct/delete?id=${couponAct.id}"
                       onclick="return confirmx('确认要删除该优惠券吗？', this.href)">删除</a>
                </shiro:hasPermission>
                <a href="${ctx}/coupon/couponClaim?couponAct.id=${couponAct.id}">领取列表</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
${page}
</body>
</html>