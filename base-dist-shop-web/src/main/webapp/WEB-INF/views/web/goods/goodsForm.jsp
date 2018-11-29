<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商品管理</title>
    <meta name="decorator" content="default"/>

    <script type="text/javascript" charset="utf-8" src="${ctxStatic}/imgmove/move.js?t=1"></script>
    <script type="text/javascript" charset="utf-8" src="${ctxStatic}/imgmove/tuozhuai.js?t=1"></script>

    <c:set var="goodsid" value="${goods.id }"></c:set>
    <c:set var="goodsType" value="${goods.goodsType }"></c:set>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    // loading('正在提交，请稍等...');
                    // form.submit();
                    var goodsPic = document.getElementsByName("goodsPic");
                    var isok = false;
                    if (goodsPic.length > 0) {
                        isok = true;
                    } else {
                        document.getElementById('goodsPic-error').innerHTML = "这是必填字段";
                        document.getElementById('goodsPic-error').style.display = "";
                        document.getElementById('md').scrollIntoView();
                        return false;
                    }

                    var goodsDescPic = document.getElementsByName("goodsDescPic");
                    if (goodsDescPic.length > 0) {
                        isok = true;
                    } else {
                        document.getElementById('goodsDescPic-error').innerHTML = "这是必填字段";
                        document.getElementById('goodsDescPic-error').style.display = "";
                        document.getElementById('md').scrollIntoView();
                        return false;
                    }

                    var goodsSpec = document.getElementsByName("goodsSpec");
                    console.log(goodsSpec[1].value);
                    <c:if test="${goodsType == '0'}">
                    if (goodsSpec.length > 1 && goodsSpec[1].value != '') {
                        isok = true;
                    } else {
                        document.getElementById('goodsSpec-error').innerHTML = "规格是必填字段";
                        document.getElementById('goodsSpec-error').style.display = "";
                        document.getElementById('md').scrollIntoView();
                        return false;
                    }
                    </c:if>

                    var goodsSku = document.getElementsByName("goodsSku");
                    console.log(goodsSku[1].value);
                    <c:if test="${goodsType == '1'}">
                    if (goodsSku.length > 1 && goodsSku[1].value != '') {
                        isok = true;
                    } else {
                        document.getElementById('goodsSku-error').innerHTML = "量价区间是必填字段";
                        document.getElementById('goodsSku-error').style.display = "";
                        document.getElementById('md').scrollIntoView();
                        return false;
                    }
                    </c:if>


                    if (isok) {
                        loading('正在提交，请稍等...');
                        form.submit();
                    } else {
                        return false;
                    }
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
    <style>
        #contentTable th, #contentTable td {
            text-align: center;
            cursor: pointer;
        }
        #contentTable1 th, #contentTable1 td {
            text-align: center;
            cursor: pointer;
        }
    </style>
    <style type="text/css">
        .imgdiv {
            display: table-cell;
            width: 205px;
            height: 160px;
            vertical-align: middle;
            text-align: center;
        }

        .imgdiv img {
            vertical-align: middle !important;
            max-width: 150px;
            max-height: 150px;
            cursor: pointer;
            padding-left: 1px !important;
        }

        .form-group .file-input {
            width: 80%;
            display: inline-block;
        }

        .show_list {
            width: 96%;
            position: relative;
            margin: 10px 2%;
        }

        .show_list li {
            width: 150px;
            height: 150px;
            float: left;
            margin: 10px;
            -moz-border-radius: 10px;
            border-radius: 10px;
            background-color: #c6c2c2;
        }

        .show_list li:hover {
            border-color: #9a9fa4;
            box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.85);
        }

        .show_list .active {
            border: 1px dashed red;
        }

        .show_list li img {
            -moz-border-radius: 10px;
            border-radius: 10px;
            border: 1px solid #ccc;
            width:150px;
            height: 150px;
            object-fit: contain;
        }
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/goods/goods/">商品列表</a></li>
    <li class="active"><a href="${ctx}/goods/goods/form?id=${goods.id}">商品<shiro:hasPermission
            name="goods:goods:edit">${not empty goods.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="goods:goods:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="goods" action="${ctx}/goods/goods/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="form-group">
        <label class="col-sm-2 control-label">
            <span><font color="red">*</font></span>商品分类：
        </label>
        <div class="col-sm-3">
            <sys:treeselect id="goodsCategory" name="goodsCategory.id" value="${goods.goodsCategory.id}"
                            labelName="goodsCategory.categoryName" labelValue="${goods.goodsCategory.categoryName}"
                            title="商品分类" url="/goods/goodsCategory/treeData/${goods.merchantId }"
                            cssClass="form-control required"/>
            <form:hidden path="structureNo" htmlEscape="false" maxlength="100" class="form-control "/>
            <form:hidden path="structureName" htmlEscape="false" maxlength="500" class="form-control "/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">
            <span><font color="red">*</font></span>商品名称：
        </label>
        <div class="col-sm-3">
            <form:input path="goodsName" htmlEscape="false" maxlength="100" class="form-control required"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">
            <span><font color="red">*</font>商品简介：
        </label>
        <div class="col-sm-3">
            <form:textarea path="description" htmlEscape="false" maxlength="200" class="form-control input-xxlarge required"></form:textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">
            <span><font color="red">*</font></span>商品类型：
        </label>
        <div class="col-sm-3">
            <c:if test="${empty goods.id}">
            <form:select path="goodsType" class="form-control " >
                <form:options items="${fns:getDictList('goods_type')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
            </c:if>

            <c:if test="${not empty goods.id}">
                <input value="${goods.goodsType == '1' ? '批发商品' : '普通商品'}" class="form-control " readonly="readonly"/>
                <form:hidden path="goodsType"/>
            </c:if>
        </div>
    </div>
    <font color="red" id="md"></font>


    <div class="form-group gtype type_0" style="display: ${goods.goodsType == '1' ? 'none' : 'block'}">
        <label class="col-sm-2 control-label"><span><font color="red">*</font></span>添加规格：</label>
        <div class="col-sm-3" style="width:50%;">
            <sys:goodsSpecInput id="goodsSpec" name="goodsSpec" value="" labelName="goodsSpec" labelValue=""
                                title="添加规格" cssClass="form-control required"/>
        </div>
    </div>

    <div class="form-group gtype type_0" style="display: ${goods.goodsType == '1' ? 'none' : 'block'}">
        <label class="col-sm-2 control-label"><span><font color="red">*</font></span>规格列表：</label>
        <div class="col-sm-3" style="width:50%;">

            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th style="display: none">序号</th>
                    <th style="width: 40%;text-align: center;">名称</th>
                    <th style="width: 20%;text-align: center;">价格</th>
                    <th style="width: 20%;text-align: center;">库存</th>
                    <th style="width: 20%;text-align: center;">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${specList}" var="spec" varStatus="od">
                    <tr data-name="${spec.name}" data-stock="${spec.stock}" data-price="${spec.price}" data-id="${spec.id}">
                        <td style="display: none">
                                ${od.index+1}
                        </td>
                        <td style="text-align: center;">
                                ${spec.name}
                        </td>
                        <td style="text-align: center;">
                                ${spec.price}
                        </td>
                        <td style="text-align: center;">
                                ${spec.stock}
                        </td>
                        <td style="text-align: center;    cursor: pointer;">
                            <a class="edit">编辑</a>&nbsp;&nbsp;<a class="del">移除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <label id="goodsSpec-error" class="error" for="goodsSpec"></label>
        </div>
    </div>

    <div class="form-group gtype type_1" style="display: ${goods.goodsType == '1' ? 'blcok' : 'none'}">
        <label class="col-sm-2 control-label"><span><font color="red">*</font></span>添加量价区间：</label>
        <div class="col-sm-3" style="width:50%;">
            <sys:goodsSkuInput id="goodsSku" name="goodsSku" value="" labelName="goodsSku" labelValue="" title="添加量价区间"
                               cssClass="form-control required"/>
        </div>
    </div>

    <div class="form-group gtype type_1" style="display: ${goods.goodsType == '1' ? 'blcok' : 'none'}">
        <label class="col-sm-2 control-label"><span><font color="red">*</font></span>量价区间列表：</label>
        <div class="col-sm-3" style="width:50%;">

            <table id="contentTable1" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th style="width: 40%;text-align: center;">数量区间</th>
                    <th style="width: 20%;text-align: center;">价格</th>
                    <th style="width: 20%;text-align: center;">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${skuList}" var="sku" varStatus="od">
                    <tr data-min="${sku.minNum}" data-max="${sku.maxNum}" data-price="${sku.price}" data-id="${sku.id}">
                        <td style="text-align: center;">
                                ${sku.minNum}  ~  ${sku.maxNum}
                        </td>
                        <td style="text-align: center;">
                                ${sku.price}
                        </td>
                        <td style="text-align: center;    cursor: pointer;">
                            <a class="edit">编辑</a>&nbsp;&nbsp;<a style="display: ${fn:length(skuList) == (od.index + 1) ? '' : 'none'}" class="del">移除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <label id="goodsSku-error" class="error" for="goodsSku"></label>
        </div>
    </div>

    <div class="form-group gtype type_1" style="display: ${goods.goodsType == '1' ? 'blcok' : 'none'}">
        <label class="col-sm-2 control-label">
            <span><font color="red">*</font></span>商品库存：
        </label>
        <div class="col-sm-3">
            <form:input path="stock" htmlEscape="false" maxlength="50" class="form-control ${goods.goodsType == '1' ? 'required' : ''} number"/>
        </div>
    </div>

    <div class="form-group gtype type_1" style="display: ${goods.goodsType == '1' ? 'blcok' : 'none'}">
        <label class="col-sm-2 control-label">
            <span><font color="red">*</font></span>单位：
        </label>
            <div class="col-sm-3">
            <form:input path="unit" htmlEscape="false" maxlength="50" class="form-control required"/>
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-2 control-label">
            商品条形码：
        </label>
        <div class="col-sm-3">
            <form:input path="goodsBarcode" htmlEscape="false" maxlength="50" class="form-control "/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">
            商品状态：
        </label>
        <div class="col-sm-3">
            <form:select path="goodsStatus" class="form-control ">
                <form:options items="${fns:getDictList('goods_status')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">
            商品品牌：
        </label>
        <div class="col-sm-3">
            <form:input path="brand" htmlEscape="false" maxlength="100" class="form-control "/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">
            兑换积分：
        </label>
        <div class="col-sm-3">
            <form:input path="integral" htmlEscape="false" maxlength="11" class="form-control  integer"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">
            商品成本价：
        </label>
        <div class="col-sm-3">
            <form:input path="costPrice" htmlEscape="false" class="form-control  money"/>
        </div>
    </div>
    <%--<div class="form-group">--%>
        <%--<label class="col-sm-2 control-label">--%>
            <%--商品图片：--%>
        <%--</label>--%>
        <%--<div class="col-sm-3">--%>
            <%--<form:input path="mainUrls" htmlEscape="false" maxlength="1000" class="form-control "/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="form-group">--%>
        <%--<label class="col-sm-2 control-label">--%>
            <%--商品图片描述：--%>
        <%--</label>--%>
        <%--<div class="col-sm-3">--%>
            <%--<form:input path="descPic" htmlEscape="false" class="form-control "/>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="form-group">
        <label class="col-sm-2 control-label" style="margin-top: 10px;"><span><font
                color="red">*</font></span>商品主图：</label>
        <div class="col-sm-10">
            <button type="button" class="button big" onclick="initImgList('goodsPic');">查看详情图片列表</button>
            <sys:multfileInput path="goodsPicUpload" value="" type="2"  auto="true"></sys:multfileInput>
                <%--<sys:selTempPicList id="goodsPic" maxlimit="30" required="true"/>--%>
            <div id="goodsPic_span"></div>
            <label id="goodsPic-error" class="error" for="goodsPic"></label>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label" style="margin-top: 10px;"><span><font
                color="red">*</font></span>详情图片：</label>
        <div class="col-sm-10">
            <button type="button" class="button big" onclick="initImgList('goodsDescPic');">查看详情图片列表</button>
                <%--<sys:selTempPicList id="goodsDescPic" maxlimit="30" required="true"/>--%>
            <sys:multfileInput path="goodsDescPicUpload" value="" type="3"  auto="true"></sys:multfileInput>
            <div id="goodsDescPic_span"></div>
            <label id="goodsDescPic-error" class="error" for="goodsDescPic"></label>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">
            重量(kg)：
        </label>
        <div class="col-sm-3">
            <form:input path="weight" htmlEscape="false" class="form-control  number"/>
        </div>
    </div>
    <%--<div class="form-group">--%>
    <%--<label class="col-sm-2 control-label">--%>
    <%--销量：--%>
    <%--</label>--%>
    <%--<div class="col-sm-3">--%>
    <%--<form:input path="sales" htmlEscape="false" maxlength="11" class="form-control  digits"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="form-group" style="display: ${not empty goods.qrCode ? 'block' : 'none'}">
        <label class="col-sm-2 control-label">
            二维码地址：
        </label>
        <div class="col-sm-3">
            <%--<form:input path="qrCode" htmlEscape="false" maxlength="200" class="form-control "/>--%>
            <img style="width: 200px;height: 200px;" src="${goods.qrCode}" />
        </div>
    </div>
    <%--<div class="form-group">--%>
    <%--<label class="col-sm-2 control-label">--%>
    <%--评价数量：--%>
    <%--</label>--%>
    <%--<div class="col-sm-3">--%>
    <%--<form:input path="evaNum" htmlEscape="false" maxlength="11" class="form-control  digits"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="form-group">--%>
    <%--<label class="col-sm-2 control-label">--%>
    <%--好评率：--%>
    <%--</label>--%>
    <%--<div class="col-sm-3">--%>
    <%--<form:input path="goodEvalRate" htmlEscape="false" class="form-control  number"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="form-group">
        <label class="col-sm-2 control-label">
            注备：
        </label>
        <div class="col-sm-3">
            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="500"
                           class="form-control input-xxlarge "/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-3">
            <shiro:hasPermission name="goods:goods:edit">
                <button type="submit" id="btnSubmit" class="button big">保存</button>
                &nbsp;&nbsp;
            </shiro:hasPermission>
            <button type="button" id="btnCancel" class="button big" onclick="history.go(-1)">返回</button>
        </div>
    </div>
</form:form>

<script>
    $('#goodsType').change(function () {
        var val = $(this).val();
        $('.gtype').hide();
        $('.type_' + val).show();
    });

    function initGoodSpec() {
        var list = [];
        $("#contentTable tbody tr").each(function (i, obj) {
            var tds = $(this).find('td');
            if (i > 0) {
                list.push(";");
            }
            var id = $(this).data('id');
            var spec = $.trim(tds.eq(1).text());
            var price = $.trim(tds.eq(2).text());
            var stock = $.trim(tds.eq(3).text());
            var spess = spec + ":" + price + ":" + stock;
            if(id)spess = spess + ":" + id;
            list.push(spess);
        });
        $("#goodsSpecName").val(list.join('').toString());
    }

    function initGoodsSku() {
        var list = [];
        $("#contentTable1 tbody tr").each(function (i, obj) {
            if (i > 0) {
                list.push(";");
            }
            var id = $(this).data('id');
            var minNum = $(this).data('min');
            var price = $(this).data('price');
            var maxNum = $(this).data('max');
            var spess = minNum + ":" + maxNum + ":" + price;
            if(id)spess = spess + ":" + id;
            list.push(spess);
        });
        $("#goodsSkuName").val(list.join('').toString());

    }

    initGoodSpec();
    initGoodsSku();

</script>

<script type="text/javascript">

    $(function () {
        initImgSpan('goodsPic', '${picList}');
        initImgSpan('goodsDescPic', '${descPicList}');
    });

    function initImgSpan(id, list) {
        if (id && list) {
            var shtml = [];
            list = $.parseJSON(list);
            $.each(list, function (i, obj) {
                if(obj){
                    shtml.push('<input type="hidden" name="' + id + '" value="' + obj + '">');
                }
            });
            shtml = shtml.join('').toString();
            $('#' + id + '_span').html(shtml);

        }
    }


    function initImgList(id) {
        var list = [];
        var title = '商品详情图片预览';
        if (id == 'goodsPic') {
            $('input[name=goodsPic]').each(function () {
                if($(this).val()){
                    list.push($(this).val());
                }

            });
            title = '商品主图图片预览';
        } else {
            $('input[name=goodsDescPic]').each(function () {
                if($(this).val()) {
                    list.push($(this).val());
                }
            });
        }
        if (id && list) {
            showGoodsImgs(id, list, title);
        }
    }

    function showImgSpan(id, html, title) {
        layer.closeAll();
        layer.open({
            type: 1,
            title: title,
            shift:-1,
            shadeClose: false,
            shade: 0.8,
            area: ['80%', '90%'],
            btn: ['确定'],
            content: html, //iframe的url
            success: function (layero, index) {
                moveInit(id + "_list");
            },
            yes: function (index, layero) {
                changeImgSort(id);
                layer.close(index);
            },
            cancel: function (index, layero) {
                changeImgSort(id);
                layer.close(index);
            }
        });
    }

    function changeImgSort(id){
        var shtml = [],arrs = [];
        $('#'+id+'_list li img.on').each(function(){
            var img = $(this).attr('src');
            var pos = $(this).parents('li').position();
            var top = parseFloat(pos.top) ? parseFloat(pos.top) : 0;
            var left = parseFloat(pos.left) ? parseFloat(pos.left) : 0;
            arrs.push({'top':top,'left':left,'url':img})
        });

        arrs = arrs.sort(function(obj1,obj2){
            var cr = 0;
            var a = obj2['top'] - obj1['top'];
            if (a != 0)
                cr = (a > 0) ? 3 : -1;
            else {
                a = obj2['left'] - obj1['left'];
                cr = (a > 0) ? 2 : -2;
            }
            return cr;
        });

        arrs = arrs.reverse();

        $.each(arrs,function(i,obj){
            var img = obj['url'];
            if(img){
                shtml.push('<input type="hidden" name="' + id + '" value="' + img + '">');
            }
        });

        shtml = shtml.join('').toString();
        if(shtml){
            $('#' + id + '_span').html(shtml);
        }else{
            $('#' + id + '_span').html('');
        }
    }

    function goodsDescPicUploadCallBack(urls) {
        if (!urls)return;
        var urlArr = imgSortFun(urls,"goodsDescPic");
        showGoodsImgs("goodsDescPic", urlArr, "商品详情图片预览");
    }

    function goodsPicUploadCallBack(urls) {
        if (!urls)return;
        var urlArr = imgSortFun(urls,"goodsPic");
        showGoodsImgs("goodsPic", urlArr, "商品主图图片预览");
    }

    function imgSortFun(urls,name){
        if (!urls)return;
        var urlArr = urls.split(';');

        var arrs = [];
        var imgs = [];
        $('input[name='+name+']').each(function () {
            if($(this).val()){
                imgs.push($(this).val());
            }
        });
        urlArr = imgs.concat(urlArr);
        if(urlArr && urlArr.length > 0){
            $.each(urlArr,function (i,obj) {
                if(obj){
                    var sort = obj.split('/');
                    if(sort.length > 2){
                        sort = sort[sort.length - 1];
                        sort = sort.split('_');
                        sort = sort[0];
                    }else{
                        sort = 0;
                    }
                    sort = parseInt(sort) ? parseInt(sort) : 0;
                    arrs.push({'sort':sort,'url':obj});
                }
            });
            arrs = arrs.sort(function(obj1,obj2){
                return obj1['sort'] - obj2['sort'];
            });
            urlArr = [];
            $.each(arrs,function (i,obj) {
                console.info(obj);
                if(obj){
                    urlArr.push(obj['url']);
                }
            });
            return urlArr;
        }
    }

    function showGoodsImgs(id, urls, title) {

        var html = [], shtml = [];
        html.push('<div style="color:red;text-align: center;margin: 10px;">提示：拖动图片可以进行排序，点击确认保存排序</div>');
        html.push('<ul id="' + id + '_list" class="show-list show_list">');
        $.each(urls, function (i, obj) {
            if(obj){
                html.push('<li>');
                html.push('<img class="on" src="' + obj + '">');
                html.push('<img onclick="removeimg(this,\''+id+'\')" src="${ctxStatic}/images/agency_13.jpg"  style="position: absolute;right: 0;height: 24px;width: 24px;cursor: pointer;">');
                html.push('</li>');

                shtml.push('<input type="hidden" name="' + id + '" value="' + obj + '">');
            }

        })
        html.push('</ul>')
        html = html.join('').toString();
        showImgSpan(id, html, title);

        shtml = shtml.join('').toString();
        $('#' + id + '_span').html(shtml);

    }

    function removeimg(_this,id){
        $(_this).parents('li').remove();
        changeImgSort(id);
        layer.closeAll();
        initImgList(id);
    }

    function showMaxImg(img){alert(img);
        layer.photos({
            photos: [img]
            ,shift: 5 //0-6的选择，指定弹出图片动画类型，默认随机
        });
    }

</script>

</body>
</html>