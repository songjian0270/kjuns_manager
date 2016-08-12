<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>无标题文档</title>
	<base href="${basePath}/" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${basePath}/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
	
	<script type="text/javascript" src="${basePath}/upload/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${basePath}/upload/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${basePath}/upload/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="${basePath}/upload/js/zh_CN.js"></script>
	<script type="text/javascript" src="${basePath}/upload/js/ui.js"></script>
	<script type="text/javascript" src="${basePath}/upload/js/qiniu.js"></script>
	<script type="text/javascript" src="${basePath}/upload/js/highlight.js"></script>
	<style type="text/css">
		.reg-upfile {
			cursor:pointer;
			opacity:0;
			margin-left:-152px;
			float:left;
			width:152px;
			height:152px;
		}
	</style>
	<script type="text/javascript">
		function onSave() {
			var userName = $("#title").val();
			if (userName == "") {
				alert("请输入标Banner名称");
				return false;
			}
			return true;
		}
	</script>
	
	<script type="text/javascript">

		var thumbnailKey = null;
		
		$(function() {
			
			$('.reg-photo-demo').click(function(){
				$('#thumbnailUpload').click();
			})
			
			var thumbnailUpload = Qiniu.uploader({
			    runtimes: 'html5,flash,html4',    //上传模式,依次退化
			    browse_button: 'thumbnailUpload',       //上传选择的点选按钮，**必需**
			    uptoken_url : 'system/requestUploadToken.json',
			    //uptoken : '-zkjEWAKT2wxEbw4_TjG6QBzPHlTU1kzLF959riy:2u4f4J84LgbnNGASzegNUOdwHDc=:eyJzY29wZSI6ImlwLTAxLXRlc3QiLCJkZWFkbGluZSI6MTQzNzAzMzU2Mn0=',
			    unique_names: true,// 默认 false，key为文件名。若开启该选项，SDK会为每个文件自动生成key（文件名）
			   // save_key: false,// 默认 false。若在服务端生成uptoken的上传策略中指定了 `sava_key`，则开启，SDK在前端将不对key进行任何处理
				drop_element: 'container',
				max_file_size: '100mb',
				flash_swf_url: 'http://jssdk.demo.qiniu.io/js/plupload/Moxie.s7wf', //引入flash,相对路径
				dragdrop: false,					//开启可拖曳上传
				chunk_size: '4mb',              //分块上传时，每片的体积
				multi_selection:false,
				domain: 'http://7xwu0j.com1.z0.glb.clouddn.com/',//bucket 域名，下载资源时用到，**必需**
				filters: {
				  mime_types : [ //只允许上传图片
				      { title : "Video files", extensions : "jpg,png" }
				  ],
				  max_file_size : '50000kb', //最大只能上传50000kb的文件
				  prevent_duplicates : true //不允许选取重复文件
				},
			    auto_start: true,                 //选择文件后自动上传，若关闭需要自己绑定事件触发上传
			    init: {
		        		'FilesAdded': function(up, files) {
			                plupload.each(files, function(file) {
								 // 文件添加进队列后,处理相关的事情
			                    var progress = new FileProgress(file, 'fsUploadProgress');
			                    progress.setStatus("等待...");
			                });
			            },
			            'BeforeUpload': function(up, file) {
							// 每个文件上传前,处理相关的事情
			                var progress = new FileProgress(file, 'fsUploadProgress');
			                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
			                if (up.runtime === 'html5' && chunk_size) {
			                    progress.setChunkProgess(chunk_size);
			                }
			            },
			            'UploadProgress': function(up, file) {
							// 每个文件上传时,处理相关的事情
			                var progress = new FileProgress(file, 'fsUploadProgress');
			                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
			                progress.setProgress(file.percent + "%", up.total.bytesPerSec, chunk_size);
			            },
			            'UploadComplete': function() {
							//队列文件处理完毕后,处理相关的事情
			                //$('#success').show();
			            },
				        'FileUploaded': function(up, file, info) {
				                var progress = new FileProgress(file, 'fsUploadProgress');
				                progress.setComplete(up, info);
				                
				                var res = $.parseJSON(info);
				                thumbnailKey = res.key;   
								$("#background").val(res.key);
				                $("#file0").attr("src","http://7xwu0j.com1.z0.glb.clouddn.com/"+thumbnailKey);                      
			        	},		            
		       			 'Error': function(up, err, errTip) {
		       				alert(errTip);
							//上传出错时,处理相关的事情
			                /* var progress = new FileProgress(err.file, 'fsUploadProgress');
			                progress.setError();
			                progress.setStatus(errTip); */
						}
					}
				});
			
			});
	</script>
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>编辑banner</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>编辑banner</span>
		</div>
		<form action="banner/addBanner" method="post">
			<input type="hidden" value="${banner.id}" name="id" />
			<table class="formtable">
				<tr>
					<td class="w100">名称</td>
					<td><input name="title" id="title" type="text" class="dfinput w200" value="${banner.title}" maxlength="100" /></td>
				</tr>
				<tr>
					<td class="w100">链接地址</td>
					<td><input name="url" id="url" type="text" class="dfinput w200" value="${banner.url}" maxlength="100" /></td>
				</tr>
				<tr>
					<td class="w100">内容</td>
					<td><input name="content" id="content" type="text" class="dfinput w200" value="${banner.content}" maxlength="1000" /></td>
				</tr>
				<tr>
					<td class="w100">序号</td>
					<td><input name="sortIndex" id="sortIndex" type="text" class="dfinput w200" value="${banner.sortIndex}" maxlength="2" /></td>
				</tr>
				<tr>
					<td class="w100">背景图片</td>
					<td>
						<div class="col-md-offset-3 col-md-6">
							<div class="clearfix"></div>
							<div class="reg-video-desc">
								<c:if test="${banner.background != null}">
									<input type="hidden" name="background" id="background" value="${banner.background}"/>
									<input type="hidden" id="imggg" value="1"/>
									<img src="${banner.background}" id="file0" class="reg-photo-demo reg-upimg" width="152" height="152" style="float:left"/>  
								</c:if>
								<c:if test="${ banner.background == null}">
									<input type="hidden" name="background" id="background" value=""/>
									<input type="hidden" id="imggg" value="2"/>
									<img src="${basePath}/images/photo_file_btn.png" id="file0" class="reg-photo-demo reg-upimg" width="152" height="152" style="float:left"/>  
								</c:if>
								<input type="file" accept="image/*" multiple="true" img-href="#file0" id="thumbnailUpload" class="reg-upfile"/> 
								<div id="fsUploadProgress" style="float:left;margin-left:80px;margin-top:70px"></div>
							</div>
							<div style="display: none" id="success">
								<div class="alert-success">队列文件已处理完毕</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input name="" type="submit" class="btn" value="确认保存" onclick="return onSave()" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>

</html>
