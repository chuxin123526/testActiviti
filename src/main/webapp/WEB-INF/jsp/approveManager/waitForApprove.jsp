<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function approve(id)
{
	var url = "springmvc/approveManagerAction/approveUI" ;
	$.ajax({
		url : url , 
		data : '{"taskId" : '+id+'}' , 
		contentType : "application/json" , 
		type : "POST" , 
		success : function(data){
			$("#dataShow").html(data) ; 
		} , 
		error : function(data){
			alert("error") ; 
		}
	}) ; 
}
</script>
</head>
<body>
	<table class = "table table-borderd">
	<tr>
	<td>申请人</td>
	<td>流程</td>
	<td>操作</td>
	</tr>
	<c:forEach items="${taskList}" var = "task">
	<tr>
	<td></td>
	<td>${task.name}</td>
	<td><a href = "#" onclick = "approve(${task.id})">审批</a></td>
	</tr>
	</c:forEach>
	
	</table>
</body>
</html>