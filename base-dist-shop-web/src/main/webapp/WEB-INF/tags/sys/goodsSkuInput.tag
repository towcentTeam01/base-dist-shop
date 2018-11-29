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


    function showSpan${id}(_this) {
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
            area: ['500px', '300px'],
            offset: [${id}tops, ${id}left],
            btn: btns,
            content: "${ctx}/sys/tag/skusInput",
            success: function (layero, index) {
                if (_this) {

                    var tds = $(_this).parents('tr');
                    var minNum = $(tds).data('min');
                    var maxNum =$(tds).data('max');
                    var price = $(tds).data('price');

                    layero.find("iframe")[0].contentWindow.$("#minNum").val(minNum);
                    layero.find("iframe")[0].contentWindow.$("#price").val(price);
                    layero.find("iframe")[0].contentWindow.$("#maxNum").attr('readonly','true').val(maxNum);
''

                }else{
                    var lastObj = $('#contentTable1 tbody tr:last');
                    if(lastObj){
                        var maxNum = $(lastObj).data('max');
                        maxNum = parseInt(maxNum)+1;
                        layero.find("iframe")[0].contentWindow.$("#minNum").val(maxNum);
                    }
                }
            },
            yes: function (index, layero) {

                var minNum = layero.find("iframe")[0].contentWindow.$("#minNum").val();
                var price = layero.find("iframe")[0].contentWindow.$("#price").val();
                var maxNum = layero.find("iframe")[0].contentWindow.$("#maxNum").val();

                var minNumtip = layero.find("iframe")[0].contentWindow.$("#minNumtip");
                var pricetip = layero.find("iframe")[0].contentWindow.$("#pricetip");
                var maxNumtip = layero.find("iframe")[0].contentWindow.$("#maxNumtip");

                $(minNumtip).html('');
                $(pricetip).html('');
                $(maxNumtip).html('');

                if (maxNum == '') {
                    $(maxNumtip).html("请输入数量");
                    return;
                }

                if (parseInt(maxNum).toString() == "NaN") {
                    $(maxNumtip).html("请输入数字");
                    layero.find("iframe")[0].contentWindow.$("#maxNum").val('');
                    return;
                }
                var re = /^[1-9]+[0-9]*]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/ ;
                if (!re.test(maxNum)) {
                    $(maxNumtip).html("请输入正整数");
                    layero.find("iframe")[0].contentWindow.$("#maxNum").val('');
                    return;
                }

                if(parseInt(minNum) >= parseInt(maxNum)){
                    $(maxNumtip).html("数量必须大于起始值");
                    layero.find("iframe")[0].contentWindow.$("#maxNum").val('');
                    return;
                }

                if (price == '') {
                    $(pricetip).html("请输入价格");
                    return;
                }
                if (parseFloat(price).toString() == "NaN") {
                    $(pricetip).html("请输入有效价格");
                    layero.find("iframe")[0].contentWindow.$("#price").val('');
                    return;
                }
                var regu = /^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/;
                if (!regu.test(price)) {
                    $(pricetip).html("请输入有效价格");
                    layero.find("iframe")[0].contentWindow.$("#price").val('');
                    return;
                }

                $(_this).parents("tr").remove();

                $("#contentTable1").append("<tr data-min="+minNum+" data-max="+maxNum+" data-price="+price+" ><td>" + minNum +" ~ "+ maxNum + "</td><td>"
                    + price + "</td><td><a class='edit' >编辑</a>&nbsp;&nbsp;<a class='del' >移除</a></td></tr>");

                refreshValue${id}();

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

    function refreshValue${id}() {
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
        $("#${id}Name").val(list.join('').toString());

        $("#contentTable1 tbody tr:last").find('.del').show();
        $("#contentTable1 tbody tr:last").siblings().find('.del').hide();
    }

    $(function () {

        $("#contentTable1 tbody").delegate("tr td a.del", "click", function () {
            $(this).parents("tr").remove();
            refreshValue${id}();
        });

        $("#contentTable1 tbody").delegate("tr td a.edit", "click", function () {
            var _this = this;
            showSpan${id}(_this);
        });
    });


    $("#${id}Button, #${id}Name").click(function () {
        // 是否限制选择，如果限制，设置为disabled
        showSpan${id}();
    });


</script>