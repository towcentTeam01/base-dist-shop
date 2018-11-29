<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/fileinput.jsp"%>
<%@ attribute name="path" type="java.lang.String" required="true" description="控件名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="文件Url"%>
<%@ attribute name="type" type="java.lang.String" required="true" description="图片类型"%>
<%@ attribute name="auto" type="java.lang.Boolean" required="false" description="默认自动上传"%>
<%@ attribute name="browerIE" type="java.lang.Boolean" required="false" description="是否是ie"%>
<%@ attribute name="thumbSize" type="java.lang.String" required="false" description="缩略图尺寸(300X200)"%>
<%@ attribute name="url" type="java.lang.String" required="false" description="上传url"%>
<%@ attribute name="classS" type="java.lang.String" required="false" description="样式"%>
<style>
.file-input{width: 800px;}
</style>

<c:if test="${browerIE }">
	<input type="file" name="fileupload" id="upload${path }" />
</c:if>
<c:if test="${empty browerIE || !browerIE}">
	<input id="upload${path }" name="fileupload" type="file" multiple>
</c:if>
<script type="text/javascript">
window.jsonData = {merchantId : '0',thumbSize:'${thumbSize}',uploadType:'${type}'}
var imgCount = 0;
var currNum = 0;
var upUrl = '${url}';
if(!upUrl){
	upUrl = '/image/upload/imageUpload';
}
<c:if test="${browerIE}">
$('#upload${path}').uploadify({
		'swf' : '${ctxStatic}/swf/uploadify.swf', //上传所用的flash 必须
		'fileObjName' : 'fileupload', //file的name,
		'method' : 'post',
		'auto' : ${auto} == null ? true : ${auto},
		'width' : '107',
		'height' : '36',
		'preventCaching' : true,
		'removeCompleted' : true,
		'buttonImage' : '${ctxStatic}/images/button_Groupupload.png',
		'cancelImage' : '${ctxStatic}/images/upload_cancel.png', //删除的图片*/
		'multi' : true, //设置为true时可以上传多个文件
		'fileDesc' : '*.jpg;*.jpeg;*.png', //用来设置选择文件对话框中的提示文本
		'fileExt' : '*.jpg;*.jpeg;*.png', //设置可以选择的文件的类型，格式如：'*.doc;*.pdf;*.rar'
		'formData' : jsonData,
		'uploader' : '${ctx}'+upUrl,
		onDialogClose : function(queueData) {
			imgCount = queueData.filesQueued;
		},
		onUploadStart : function(file) {
			$('#adminPic').uploadify(
				'settings',
				'formData',
				{});
		},
		onUploadSuccess : function(file,data,response) {
			currNum++;
			var url = $('#tempPhoto').val();
			url += data.response.imageUrl + ',';
		 	$('#tempPhoto').val(url);
			if (currNum == imgCount) {
				currNum = 0;
				 if(typeof '${path}CallBack' == 'function'){
					 ${path}CallBack(url);
				 }
			}
		}
	});
</c:if>
<c:if test="${empty browerIE || !browerIE}">
var img = [];
if ('${value}') {
	var tempPic = '${value}';
	var list = tempPic.split(",");
	for(var i=0;i<list.length;i++){
		if(list[i] == null || list[i] == '')continue;
		img.push("<img src='"+list[i]+"' class='file-preview-image' style='width:auto;height:160px;'>");
	}
}

$('#upload${path }').fileinput({
	uploadUrl : '${ctx}/sys/images/uploadImage',
	allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
	uploadasync:false,
	uploadExtraData : jsonData,
	showClose : false,
	initialPreviewShowDelete : true,
	/* showUpload : true, */
	dropZoneEnabled : false,
	initialPreviewShowDelete:true,
	initialPreview : img,
	initialPreviewConfig:[],
	showCaption: false,
	showPreview:true,
	fileActionSettings : {
		showUpload : false,
		showRemove : true,
		showDrag : false
	},
	language : 'zh'
}).on("filebatchselected", function(event, files) {
	imgCount = files.length;
	<c:if test="${auto || empty auto}">
	 	$(this).fileinput("upload");
	</c:if>
})
.on("fileuploaded", function(event, data) {
if(data.response){
	 var url = $('#${path}').val();
	 if(url){
		 url +=  ','+ data.response.imageUrl;
	 }else{
		 url += data.response.imageUrl;
	 }
	 $('#${path}').val(url);
	 currNum++;
	 if(currNum == imgCount){
		 currNum = 0;
		 if(typeof ${path}CallBack == 'function'){
			 $('#upload${path }').fileinput('clear').fileinput('enable');
			 $('#upload${path }').fileinput('refresh');
			 ${path}CallBack(url);
		 }
	 }
}
});

$('#upload${path }').on('filecleared', function(event) {
	$('#${path}').val('');
});

</c:if>
</script>
<input type="hidden" id="${path}" name="${path}" value="${value }" class="${classS}"/>
