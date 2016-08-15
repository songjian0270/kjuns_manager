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
	<script type="text/javascript" charset="utf-8" src="${basePath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}/ueditor/lang/zh-cn/zh-cn.js"> </script>
	<script type="text/javascript" src="${basePath}/My97DatePicker/WdatePicker.js"></script>
	
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
		$("#tag-iframe .tiptop a").click(function(){
			$("#tag-iframe").fadeOut(200);
		});
	
		UE.getEditor("editor");
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
		UE.Editor.prototype.getActionUrl = function(action){
			if(action == 'uploadimage'){
				return 'http://localhost:8080/kjuns_manager/ueditor/uploadImg.json';
			}else{
				return this._bkGetActionUrl.call(this, action);
			}
		}
		
		var thumbnailKey = null;
		// 中文字符判断
		function getStrLength(str) { 
		    var len = str.length; 
		    var reLen = 0; 
		    for (var i = 0; i < len; i++) {        
		        if (str.charCodeAt(i) < 27 || str.charCodeAt(i) > 126) { 
		            // 全角    
		            reLen += 2; 
		        } else { 
		            reLen++; 
		        } 
		    } 
		    return reLen;    
		}
		
		$(function() {
			
			var maxCount = 500;  // 最高字数，这个值可以自己配置
			$("#summary").on('keyup', function() {
			    var len = getStrLength(this.value);
			    var residue = maxCount-len;
			    if(residue < 0){
			    	$(this).val(this.value.substring(0, maxCount));
			    	residue = 0;
			    }
			    $("#count").html("已输入"+ len +"字符，剩余:" + residue +"字符");
			})
			
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
								$("#thumbnail").val(res.key);
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
			
			$('.mindMapreg-photo-demo').click(function(){
				$('#mindMapUpload').click();
			})
			
			var mindMapUpload = Qiniu.uploader({
			    runtimes: 'html5,flash,html4',    //上传模式,依次退化
			    browse_button: 'mindMapUpload',       //上传选择的点选按钮，**必需**
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
			                    var progress = new FileProgress(file, 'mmfsUploadProgress');
			                    progress.setStatus("等待...");
			                });
			            },
			            'BeforeUpload': function(up, file) {
							// 每个文件上传前,处理相关的事情
			                var progress = new FileProgress(file, 'mmfsUploadProgress');
			                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
			                if (up.runtime === 'html5' && chunk_size) {
			                    progress.setChunkProgess(chunk_size);
			                }
			            },
			            'UploadProgress': function(up, file) {
							// 每个文件上传时,处理相关的事情
			                var progress = new FileProgress(file, 'mmfsUploadProgress');
			                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
			                progress.setProgress(file.percent + "%", up.total.bytesPerSec, chunk_size);
			            },
			            'UploadComplete': function() {
							//队列文件处理完毕后,处理相关的事情
			                //$('#success').show();
			            },
				        'FileUploaded': function(up, file, info) {
				        	$('#mmfsUploadProgress').empty();
				            var progress = new FileProgress(file, 'mmfsUploadProgress');
				            progress.setComplete(up, info);
				                
				            var res = $.parseJSON(info);
				            thumbnailKey = res.key;   
							$("#mindMap").val(res.key);
				            $("#file1").attr("src","http://7xwu0j.com1.z0.glb.clouddn.com/"+thumbnailKey);                      
			        	},		            
		       			 'Error': function(up, err, errTip) {
		       				alert(errTip);
							//上传出错时,处理相关的事情
			              /*   var progress = new FileProgress(err.file, 'bgfsUploadProgress');
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
			<li>编辑内容</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>编辑内容</span>
		</div>
		<form action="content/addContent" method="post">
			<input type="hidden" value="${content.id}" name="id" />
			<input type="hidden" value="${content.sectionId}" name="sectionId" />
			<table class="formtable">
				<tr>
					<td class="w100">标题</td>
					<td><input name="title" id="title" type="text" class="dfinput w200" value="${content.title}" /></td>
				</tr>
				<tr>
					<td class="w100">类别</td>
					<td>
						<select name="type">
							<c:forEach items="${typeList}" var="type">
								<option value="${type.id }" 	<c:if test="${content.type==type.id}">selected=selected</c:if>>${type.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="w100">来源</td>
					<td><input name="source" id="source" type="text" class="dfinput w200" value="${content.source}" /></td>
				</tr>
				<tr>
					<td class="w100">标签</td>
					<td>
						<input name="tags" id="tags" type="hidden" class="dfinput w200" value="${content.tags}" readonly="readonly"/>
						<input name="tagsName" id="tagsName" type="text" class="dfinput w200" value="${content.tagsName}" readonly="readonly"/>
						<div onclick="myTags()">添加</div>
					</td>
				</tr>
				<tr>
					<td class="w100">发布人</td>
					<td>
						<input name="issuers" id="issuers" type="hidden" class="dfinput w200" value="${content.issuers}" readonly="readonly"/>
						<input name="issuersName" id="issuersName" type="text" class="dfinput w200" value="${content.issuersName}" readonly="readonly"/>
						<div onclick="myIssuers()">添加</div>
					</td>
				</tr>
				<tr>
					<td class="w100">推荐阅读</td>
					<td>
						<input name="relatedArticless" id="relatedArticless" type="hidden" class="dfinput w200" value="${content.relatedArticless}" readonly="readonly"/>
						<input name="relatedArticlessName" id="relatedArticlessName" type="text" class="dfinput w200" value="${content.relatedArticlessName}" readonly="readonly"/>
						<div onclick="myRelatedArticles()">添加</div>
					</td>
				</tr>
		<%-- 		<tr>
					<td class="w100">来源超链</td>
					<td><input name="sourceUrl" id="sourceUrl" type="text" class="dfinput w200" value="${news.sourceUrl}" /></td>
				</tr> --%>
				<tr>
					<td class="w100">摘要</td>
					<td>
						<textarea rows="50" cols="50" name="summary" id="summary" class="textinput">${content.summary}</textarea>
						<span id="count" style="color: gray;display: inline;font-size: 10px; margin-left: 20px"></span>
					</td>	
				</tr>
				<tr>
					<td class="w100">缩略图</td>
					<td>
						<div class="col-md-offset-3 col-md-6">
							<div class="clearfix"></div>
							<div class="reg-video-desc">
								<c:if test="${content.thumbnail != null}">
									<input type="hidden" name="thumbnail" id="thumbnail" value="${content.thumbnail}"/>
									<input type="hidden" id="imggg" value="1"/>
									<img src="${content.thumbnail}" id="file0" class="reg-photo-demo reg-upimg" width="152" height="152" style="float:left"/>  
								</c:if>
								<c:if test="${ content.thumbnail == null}">
									<input type="hidden" name="thumbnail" id="thumbnail" value=""/>
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
					<td class="w100">导图</td>
					<td>
						<div class="col-md-offset-3 col-md-6">
							<div class="clearfix"></div>
							<div class="reg-video-desc">
								<c:if test="${content.mindMap != null}">
									<input type="hidden" name="mindMap" id="mindMap" value="${content.mindMap}"/>
									<input type="hidden" id="bgimggg" value="1"/>
									<img src="${content.mindMap}" id="file1" class="bgreg-photo-demo reg-upimg" width="152" height="152" style="float:left;"/>  
								</c:if>
								<c:if test="${ content.mindMap == null}">
									<input type="hidden" name="mindMap" id="mindMap" value=""/>
									<input type="hidden" id="bgimggg" value="2"/>
									<img src="${basePath}/images/photo_file_btn.png" id="file1" class="bgreg-photo-demo reg-upimg" width="152" height="152" style="float:left"/>    
								</c:if>
								<input type="file" accept="image/*" multiple="true" img-href="#file1" id="mindMapUpload" class="reg-upfile"/> 
								<div id="mmfsUploadProgress" style="float:left;margin-left:80px;margin-top:70px"></div>
							</div>
							<div style="display: none" id="success">
								<div class="alert-success">队列文件已处理完毕</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="w100">时间</td>
					<td><input name="createDate"  id="createDate" type="text" 
							onclick="WdatePicker()" class="dfinput w200"
							value="${content.createDate}" /><span style="color: red;display: inline;">(默认当前时间)</span></td>
				</tr>
				<tr>
					<td>
						内容
					</td>
					<td><textarea id="editor" name="content" style="width:760px;height:1024px">${content.content}</textarea></td>
				</tr>
				<tr>
					<td class="w100">是否置顶</td>
					<td>
						<select name="isTop">
							<option value="0" <c:if test="${content.isTop==0}">selected=selected</c:if> >否</option>
							<option value="1" <c:if test="${content.isTop==1}">selected=selected</c:if>>是</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="w100">是否热帖</td>
					<td>
						<select name="isHot">
							<option value="0" <c:if test="${content.isHot==0}">selected=selected</c:if> >否</option>
							<option value="1" <c:if test="${content.isHot==1}">selected=selected</c:if>>是</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="w100">是否深度</td>
					<td>
						<select name="isDepth">
							<option value="0" <c:if test="${content.isDepth==0}">selected=selected</c:if> >否</option>
							<option value="1" <c:if test="${content.isDepth==1}">selected=selected</c:if>>是</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="w100">是否吐槽</td>
					<td>
						<select name="isTease">
							<option value="0" <c:if test="${content.isTease==0}">selected=selected</c:if> >否</option>
							<option value="1" <c:if test="${content.isTease==1}">selected=selected</c:if>>是</option>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input name="" type="submit" class="btn" value="确认保存" /></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">  
		function myTags() {  
			$("#tag-iframe").show();
	    }  
		
		function myRelatedArticles() {  
			$("#relatedArticles-iframe").show();
	    } 
		
		function myIssuers() {  
			$("#issuer-iframe").show();
	    } 
		
		function closeTags(val, name){
			//var val = $(window.frames["myTagFrame"].document).find("#tags").val();
			$("#tag-iframe").hide();
			$('#tags').val(val)
			$('#tagsName').val(name)
		}
		
		function closeIssuers(val, name){
			//var val = $(window.frames["myTagFrame"].document).find("#tags").val();
			$("#issuer-iframe").hide();
			$('#issuers').val(val)
			$('#issuersName').val(name)
		}
		
		function closeCancel(){
			$("#tag-iframe").hide();
			$("#relatedArticles-iframe").hide();
			$("#issuer-iframe").hide();
		}
		
		function closeRelatedArticless(val, name){
			//var val = $(window.frames["myRelatedArticlesFrame"].document).find("#relatedArticless").val();
			$("#relatedArticles-iframe").hide();
			$('#relatedArticless').val(val)
			$('#relatedArticlessName').val(name)
		}
	</script>
    
    <!--弹出内容层-->
	<div class="tip-ctent" style="width: 550px" id="tag-iframe">
    	<div class="tiptop"><span id="windowSapn">标签</span></div>
      	<div class="tipinfo">
		 	<iframe id="myTagFrame" frameborder="0" src="content/tag/list?pageNumber=1"  style="width: 100%; height: 550px;"></iframe>  
        </div>
    </div>
    
    <!--弹出内容层-->
	<div class="tip-ctent" style="width: 700px" id="relatedArticles-iframe">
    	<div class="tiptop"><span id="windowSapn">推荐阅读</span></div>
      	<div class="tipinfo">
		 	<iframe id="myRelatedArticlesFrame" frameborder="0" src="content/relatedArticles/list?pageNumber=1"  style="width: 100%; height: 550px;"></iframe>  
        </div>
    </div>
    
    <!--弹出内容层-->
	<div class="tip-ctent" style="width: 550px" id="issuer-iframe">
    	<div class="tiptop"><span id="windowSapn">推荐阅读</span></div>
      	<div class="tipinfo">
		 	<iframe id="myIssuerFrame" frameborder="0" src="content/issuer/list?pageNumber=1"  style="width: 100%; height: 550px;"></iframe>  
        </div>
    </div>
    
</body>

</html>
