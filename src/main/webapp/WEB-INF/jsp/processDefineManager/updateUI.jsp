<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#button").click(function(){
		$("#form").submit() ; 
	}) ; 
}) ; 

</script>
</head>
<body>
<form id = "form" enctype="multipart/form-data" method = "POST" 
action = "springmvc/templateManagerAction/update" role = "form">
<input type = "hidden" name = "id" value = "${id}" />
<div class = "form-group">
<label for = "file">请选择文件</label>
<input id = "file" name = "file" type = "file" >

</div>
<button id = "button" class = "btn btn-primary">提交</button>
</form>
</body>
</html>