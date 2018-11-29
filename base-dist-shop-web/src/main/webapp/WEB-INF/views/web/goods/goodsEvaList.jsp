<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商品评价管理</title>
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
    <li class="active"><a href="${ctx}/goods/goodsEva/">商品评价列表</a></li>
    <shiro:hasPermission name="goods:goodsEva:edit">
        <li><a href="${ctx}/goods/goodsEva/form">商品评价添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="goodsEva" action="${ctx}/goods/goodsEva/" method="post"
           class="navbar-form form-search" role="form">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>商品：</label>
            <sys:tagSearchList
                    id="goods.id"
                    value="${goodsEva.goods.id}"
                    name="goods.goodsName"
                    labelValue="${goodsEva.goods.goodsName}"
                    url="${ctx}/sys/tag/searchGoodsList"
                    placeholder="选择商品"
                    cssClass="form-control">
            </sys:tagSearchList>
        </li>
        <li><label>会员：</label>
            <sys:tagSearchList
                    id="user.id"
                    value="${goodsEva.user.id}"
                    name="user.nickName"
                    labelValue="${goodsEva.user.nickName}"
                    url="${ctx}/member/sysFrontAccount/searchMemberList"
                    placeholder="选择会员"
                    cssClass="form-control">
            </sys:tagSearchList>
        </li>
        <li><label>订单编码：</label>
            <form:input path="order.orderNo" htmlEscape="false" maxlength="100" class="form-control"/>
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
        <th>商品名称</th>
        <th>所属订单</th>
        <th>评价内容</th>
        <th>会员昵称</th>
        <th>评价星级</th>
        <th>商家回复</th>
        <th>商家回复时间</th>
        <th>是否匿名</th>
        <th>创建时间</th>
        <shiro:hasPermission name="goods:goodsEva:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="goodsEva">
        <tr>
            <td>
                <img style="width: 50px;height: 50px; object-fit: contain; background-color: #e5e1e1;" src="${goodsEva.goodsSmallPic}"/>
            </td>
            <td style="width: 30%">
                <a href="${ctx}/goods/goods/form?id=${goodsEva.goods.id}">
                    ${goodsEva.goods.goodsName}
                </a>
            </td>
            <td>
                <a href="${ctx}/order/orderMain/detail?id=${goodsEva.order.id}">${goodsEva.order.orderNo}</a>
            </td>
            <td>
                    ${goodsEva.evaContent}
            </td>
            <td>
                <a href="${ctx}/member/sysFrontAccount/form?id=${goodsEva.user.id}">${goodsEva.user.nickName}</a>
            </td>
            <td>
                <c:forEach var="i" begin="1" end="5" varStatus="od">
                    <c:choose>
                        <c:when test="${od.index le goodsEva.evaStar}">
                            <img src="${ctxStatic}/images/star-on.png">
                        </c:when>
                        <c:otherwise>
                            <img src="${ctxStatic}/images/star-off.png">
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </td>
            <td>
                    ${goodsEva.replyContent}
            </td>
            <td>
                <fmt:formatDate value="${goodsEva.replyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${fns:getDictLabel(goodsEva.isHideName, 'yes_no', '')}
            </td>
            <td>
                <fmt:formatDate value="${goodsEva.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <shiro:hasPermission name="goods:goodsEva:edit">
                <td>
                    <a href="${ctx}/goods/goodsEva/form?id=${goodsEva.id}">修改</a>
                    <a href="${ctx}/goods/goodsEva/delete?id=${goodsEva.id}"
                       onclick="return confirmx('确认要删除该商品评价吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
${page}
</body>
</html>