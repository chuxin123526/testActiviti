<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#submitButton").click(function(){
			var url = "springmvc/processDefineManagerAction/uploadTemplate" ; 
			var fd = new FormData($("#form")[0]);
			$.ajax({
				url : url ,
				type : 'post' , 
				data : fd , 
				contentType : false , 
				processData: false,
				success : function(data){
					var info = JSON.parse(data) ; 
					alert(info.info) ;
					$("#dataShow").load("springmvc/processDefineManagerAction/list") ; 
				} ,
				error : function(data){
					alert("error") ; 
				}
			}) ; 
			
		}) ; 
	}) ; 
</script>
<body>
	<form id = "form" method = "post">
	<input type = "hidden" name = "deploymentId" value = "${deploymentId}">
		<div class = "form-group" >
		<label for = "file">请选择文件</label> 
		<input id = "file" type = "file" name = "file" />
		</div>
		<button type = "button" id = "submitButton">提交</button>
	</form>
</body>
</html>