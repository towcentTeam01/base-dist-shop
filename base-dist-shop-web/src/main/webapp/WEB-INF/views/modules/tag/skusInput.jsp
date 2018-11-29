<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>数据选择</title>
    <meta name="decorator" content="blank"/>
    <script type="text/javascript">

        $(document).ready(function () {
        });

    </script>
</head>
<body>

<form:form id="searchForm" action="" method="post" class="form-horizontal" style="margin: 15px;">

    <div class="form-group" style="padding: 5px;">
        <label class="col-sm-2 control-label"><span class="help-inline"><font color="red">*</font></span>起始数量：</label>
        <div class="col-sm-5" style="width: 50%;display: inline-block;">
            <input id="minNum" htmlEscape="false" maxlength="20" value="1" readonly="readonly" class="form-control required"/>
        </div>
        <span id="minNumtip" style="color:red;"></span>
    </div>
    <div class="form-group" style="padding: 5px;">
        <label class="col-sm-2 control-label"><span class="help-inline"><font color="red">*</font></span>结束数量：</label>
        <div class="col-sm-5" style="width: 50%;display: inline-block;">
            <input id="maxNum" htmlEscape="false" maxlength="20" class="form-control"/>
        </div>
        <span id="maxNumtip" style="color:red;"></span>
    </div>
    <div class="form-group" style="padding: 5px;">
        <label class="col-sm-2 control-label"><span class="help-inline"><font color="red">*</font></span>区间价格：</label>
        <div class="col-sm-5" style="width: 50%;display: inline-block;">
            <input id="price" htmlEscape="false" maxlength="20" class="form-control"/>
        </div>
        <span id="pricetip" style="color:red;"></span>
    </div>

</form:form>

</body>