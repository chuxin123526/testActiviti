<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function()
{
	$("#addUIButton").click(function()
	{
		var url = "http://localhost:8080/testActiviti/springmvc/templateManagerAction/addUI" ;  
		$("#dataShow").load(url) ;
	}) ;
	
	
	
}) ;

function deleteTemplate(id)
{
	var url = "http://localhost:8080/testActiviti/springmvc/templateManagerAction/delete" ; 
	$.ajax({ 
		url : url ,
		data : "{'id' : "+id+"}" , 
		type : "POST" ,
		contentType: "application/json", 
		dataType : "json", 
		success : function(data){
			alert("success") ; 
			$("#dataShow").load("http://localhost:8080/testActiviti/springmvc/templateManagerAction/list") ; 
		} , 
		error : function(data){
			alert("error") ; 
		} 
	}) ; 
}

</script>
</head>
<body>
	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<td>名称</td>
				<td>修改日期</td>
				<td>大小</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${templateList}" var = "item">
			<tr>
				<td>${item.name}</td>
				<td>${item.updateDate}</td>
				<td>${item.size}</td>
				
				<td><a onclick="deleteTemplate(${item.id})" href = "#">删除</a>
			</tr>
		</c:forEach>
			
		</tbody>
	</table>
</body>
</html>