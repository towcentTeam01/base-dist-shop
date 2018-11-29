<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>订单管理</title>
    <meta name="decorator" content="default"/>

    <style>
        .form-group.full {
            width: 100%;
            text-align: center;
        }
        .form-group {
            width: 45%;
            float: left;
        }
        .form-group .col-sm-3 {
            width: 50%;
            padding-top: 7px;
            margin-bottom: 0;
        }
        .form-group label {
            width: 50%;
        }
        .form-group.full label {
            width: 25%;
        }
        #contentTable {
            width: 80%;
            margin: 40px auto;
            text-align: center;
        }
        #contentTable tr td, #contentTable tr th {
            text-align: center;
            height: 40px;
        }
        #contentTable_1 {
            width: 80%;
            margin: 40px auto;
        }
        #contentTable_1 tr th {
            height: 40px;
            line-height: 40px;
            font-weight: bold;
            font-size: 14px;
            padding: 0 10px;
        }
        #contentTable_1 tr td, #contentTable_1 tr th {
            height: 40px;
            line-height: 40px;
            padding: 0 10px;
        }
    </style>

    <script>
        function showLogistics(id,no){
            var callbackurl = 'javascript:void(0)';
            window.sgSpan = layer.open({
                type : 2,
                title : '物流轨迹',
                area : [ '50%', '80%' ],
                content : ['https://m.kuaidi100.com/index_all.html?type='+id+'&postid='+no+'&callbackurl='+callbackurl,'yes'],
                btns:2,
                btnAlign:'c',
                btn: ['确定', '取消'],
                success: function(layero,index){
                    // console.log(layero.find("iframe")[0].contentWindow.$('.ui-btn-text'))
                    // layero.find("iframe")[0].contentWindow.$('.ui-btn-text').click(function(){
                    //     alert(11);
                    //     layero.close(index);
                    // });
                },
                yes: function(index,layero){
                },
                no: function(index,layero){}
            });
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/order/orderMain/list?orderType=1">订单列表</a></li>
    <li class="active"><a href="${ctx}/order/orderMain/detail?id=${orderMain.id}">订单查看</a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="orderMain" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <table id="contentTable" class="table  table-bordered table-condensed">
        <thead>
        <tr>
            <th style="width: 8%;">序号</th>
            <th style="width: 50px;text-align: center;">图片</th>
            <th style="width: 50%;">商品名称</th>
            <th>数量</th>
            <th>单价(元)</th>
            <th>金额(元)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderDtlList}" var="orderDtl" varStatus="od">
            <tr>
                <td>
                    ${od.index+1}
                </td>
                <td>
                    <img style="width: 50px;height: 50px; object-fit: contain; background-color: #e5e1e1;" src="${orderDtl.goodsPicUrl}"/>    
                </td>
                <td>
                    <a href="${ctx}/goods/goods/form?id=${orderDtl.goodsId}&merchant.id=0&operBy=0">
                        ${orderDtl.goodsName}
                    </a>
                </td>
                <td>
                    ${orderDtl.qty}
                </td>
                <td>
                    ${orderDtl.price}
                </td>
                <td>
                    ${orderDtl.amount}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <table id="contentTable_1" class="table  table-bordered table-condensed">
        <tr><th colspan="4">订单信息</th></tr>
        <tr>
            <td width="15%">订单编码</td>
            <td width="35%">${orderMain.orderNo}</td>
            <td width="15%">订单状态</td>
            <td width="35%">${fns:getDictLabel(orderMain.orderStatus,'order_status','')}</td>
        </tr>
        <tr>
            <td width="15%">支付状态</td>
            <td width="35%">${fns:getDictLabel(orderMain.payStatus,'pay_status','')}</td>
            <td width="15%">付款方式</td>
            <td width="35%">${fns:getDictLabel(orderMain.payWay,'pay_way','')}</td>
        </tr>
        <tr>
            <td width="15%">创建时间</td>
            <td width="35%"><fmt:formatDate value="${orderMain.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td width="15%">支付时间</td>
            <td width="35%"><fmt:formatDate value="${orderMain.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        <tr><th colspan="4">支付信息</th></tr>
        <tr>
            <td width="15%">总金额</td>
            <td width="35%">${orderMain.totalAmount}</td>
            <td width="15%">余额支付金额</td>
            <td width="35%">${orderMain.amount}</td>
        </tr>
        <tr>
            <td width="15%">线上实付金额</td>
            <td width="35%">${orderMain.payAmount}</td>
            <td width="15%">积分支付数额</td>
            <td width="35%">${orderMain.payInter}</td>
        </tr>
        <tr>
            <td width="15%">优惠金额</td>
            <td width="35%">${orderMain.couponAmount}</td>
            <td width="15%">运费</td>
            <td width="35%">${orderMain.freightFee}</td>
        </tr>
        <tr><th colspan="4">收货人信息</th></tr>
        <tr>
            <td width="15%">收货人</td>
            <td width="35%">${orderMain.consigneeName}</td>
            <td width="15%">联系方式</td>
            <td width="35%">${orderMain.mobilePhone}</td>
        </tr>
        <tr>
            <td width="15%">收货地址</td>
            <td colspan="3">${orderMain.consigneeAddr}</td>
        </tr>
        <tr style="display: ${orderMain.orderStatus == '2' ? '' : 'none'}"><th colspan="4">物流信息</th></tr>
        <tr style="display: ${orderMain.orderStatus == '2' ? '' : 'none'}">
            <td width="15%">运单号</td>
            <td width="35%"><a data-id="${orderMain.logisticsNo}" data-no="${orderMain.freightNumber}" href="javascript:showLogistics('${orderMain.logisticsNo}','${orderMain.freightNumber}')">${orderMain.freightNumber}</a></td>
            <td width="15%">物流公司</td>
            <td width="35%">${orderMain.logisticsName}</td>
        </tr>
        <tr style="display: ${orderMain.orderStatus == '2' ? '' : 'none'}">
            <td width="15%">发货时间</td>
            <td colspan="3"><fmt:formatDate value="${orderMain.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        <tr><th colspan="4">备注</th></tr>
        <tr>
            <td width="15%">备注</td>
            <td colspan="3">${not empty orderMain.remarks ? orderMain.remarks : '无'}</td>
        </tr>
    </table>

    <div class="form-group full">
        <div class="col-sm-offset-2 col-sm-3">
            <button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
        </div>
    </div>
</form:form>
</body>
</html>