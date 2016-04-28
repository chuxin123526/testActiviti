<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="../common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function select(id)
{
	var url = "http://localhost:8080/testActiviti/springmvc/applyManagerAction/selectProcess" ;
	var data = {"deploymentId" : id} ; 
	//$("#dataShow").load(url , data.toJSONString()) ; 
	$.ajax({
		url : url ,
		type : "POST" , 
		data :  '{"deploymentId" : '+id+'}'  ,
		contentType : "application/json" ,
		success : function(data)
		{
			$("#dataShow").html(data) ; 
		} , 
		
		error : function(data)
		{
			alert("error") ; 
		}
	}) ; 
}
</script>
<body>
<h3></h3>
	<table class='table table-hover'>
		<c:forEach items="${deploymentList}" var="item">
		
		<tr>
			<td align = "center">
			<a href = "#" onclick = "select(${item.id})" >${item.name}</a>
			</td>
		</tr>
		
		</c:forEach>
	</table>
</body>
</html>