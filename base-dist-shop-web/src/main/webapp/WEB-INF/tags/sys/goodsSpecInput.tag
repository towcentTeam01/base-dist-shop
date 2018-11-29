<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）" %>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）" %>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）" %>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）" %>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题" %>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式" %>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式" %>
<%@ attribute name="hideBtn" type="java.lang.Boolean" required="false" description="是否显示按钮" %>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled" %>
<%@ attribute name="callBack" type="java.lang.String" required="false" description="回调方法" %>
<%@ attribute name="sysSelect" type="java.lang.Boolean" required="false" description="回调方法" %>
<input id="${id}Id" name="${name}" class="${cssClass.replace('required','')}" type="hidden" value="${value}"/>
<div class="input-group">

    <input id="${id}Name" name="${labelName}" ${allowInput?'':'readonly="readonly"'} type="hidden" value="${labelValue}"
           data-msg-required="${dataMsgRequired}"
           class="${cssClass}" style="${cssStyle}"/>

    <span class="input-group-btn">
		<button type="button" class="btn btn-default ${disabled} ${hideBtn ? 'hide' : ''}" id="${id}Button"
                style="cursor: pointer;${smallBtn?'padding:4px 2px;':''}"></a>${title}</button>ddd
	</span>
    <%-- <div id="${id}Button" class="input-group-addon ${disabled} ${hideBtn ? 'hide' : ''}" style="cursor: pointer;${smallBtn?'padding:4px 2px;':''}"><i class="icon-search"></i></div>	 --%>
</div>
<script type="text/javascript">


    function showSpan(_this) {
        if ($("#${id}Button").hasClass("disabled")) {
            return true;
        }
        var ${id}left = (pageX(document.getElementById('${id}Button')) - $(document).scrollLeft()) + 'px';
        var ${id}tops = (pageY(document.getElementById('${id}Button')) + 34 - $(document).scrollTop()) + 'px';
        var btns = ['确定'];
        if ('${allowClear}') {
            btns.push('清除');
        }
        btns.push('关闭');
        layer.open({
            title: "${title}",
            type: 2,
            area: ['450px', '300px'],
            offset: [${id}tops, ${id}left],
            btn: btns,
            content: "${ctx}/sys/tag/specsInput",
            success: function (layero, index) {
                if (_this) {
                    var tds = $(_this).parents('tr').find('td');
                    var spec = $.trim(tds.eq(1).text());
                    var price = $.trim(tds.eq(2).text());
                    var stock = $.trim(tds.eq(3).text());

                    console.info(spec);

                    layero.find("iframe")[0].contentWindow.$("#spec").val(spec);
                    layero.find("iframe")[0].contentWindow.$("#price").val(price);
                    layero.find("iframe")[0].contentWindow.$("#stock").val(stock);
                }
            },
            yes: function (index, layero) {
                var spec = layero.find("iframe")[0].contentWindow.$("#spec").val();
                var price = layero.find("iframe")[0].contentWindow.$("#price").val();
                var stock = layero.find("iframe")[0].contentWindow.$("#stock").val();
                var spectip = layero.find("iframe")[0].contentWindow.$("#spectip");
                var pricetip = layero.find("iframe")[0].contentWindow.$("#pricetip");
                var stocktip = layero.find("iframe")[0].contentWindow.$("#stocktip");
                $(spectip).html('');
                $(pricetip).html('');
                $(stocktip).html('');
                if (spec == '') {

                    $(spectip).html("请输入规格");
                    return;
                }
                if (price == '') {
                    $(pricetip).html("请输入价格");
                    return;
                }
                var regu = /^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/;
                if (parseFloat(price).toString() == "NaN") {
                    $(pricetip).html("请输入数字");
                    layero.find("iframe")[0].contentWindow.$("#price").val('');
                    return;
                }

                if (!regu.test(price)) {
                    $(stocktip).html("请输入有效金额");
                    layero.find("iframe")[0].contentWindow.$("#price").val('');
                    return;
                }


                if (stock == '') {
                    $(stocktip).html("请输入库存");
                    return;
                }
                var re = /^[1-9]+[0-9]*]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/ ;
                if (!re.test(stock)) {
                    $(stocktip).html("请输入正整数");
                    layero.find("iframe")[0].contentWindow.$("#stock").val('');
                    return;
                }

                $(_this).parents("tr").remove();

                $("#contentTable").append("<tr><td style=\"display: none\"></td><td>" + spec + "</td><td>" + price + "</td><td>" + stock + "</td><td><a class='edit' >编辑</a>&nbsp;&nbsp;<a class='del' >移除</a></td></tr>");

                refreshValue();

                layer.close(index);
            },
            btn2: function (index, layero) {
                if (layero.find('.layui-layer-btn1').text() == '清除') {
                    $("#${id}Id").val("");
                    $("#${id}Name").val("");
                }
                layer.close(index);
            }, btn3: function (index, layero) {
                layer.close(index);
            }
        });
    }

    function refreshValue() {
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
        $("#${id}Name").val(list.join('').toString());
    }

    $(function () {

        $("#contentTable tbody").delegate("tr td a.del", "click", function () {
            $(this).parents("tr").remove();
            refreshValue();
        });

        $("#contentTable tbody").delegate("tr td a.edit", "click", function () {
            var _this = this;
            showSpan(_this);
        });
    });


    $("#${id}Button, #${id}Name").click(function () {
        // 是否限制选择，如果限制，设置为disabled
        showSpan();
    });


</script>