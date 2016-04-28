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
	$(document).ready(function(){
		$("#addUIButton").click(function(){
			var url = "http://localhost:8080/testActiviti/springmvc/processDefineManagerAction/addUI" ;
			$("#dataShow").load(url) ; 
		
		}) ; 
	}) ; 
	
	function processDefinitiondelete(id)
	{
<<<<<<< HEAD
		if(!confirm("delete?"))
			{
				return ;	
			}
=======
>>>>>>> 40bfaac1f307f5aeac0efd1c1f7141d1420f2638
		var url = "http://localhost:8080/testActiviti/springmvc/processDefineManagerAction/delete" ; 
		$.ajax({
			url : url , 
			type : "POST" , 
			data : '{id : '+id+'}' , 
			contentType : "application/json" ,
			dataType : "json" , 
			success : function(data)
			{
				alert("success") ; 
				$("#dataShow").load("http://localhost:8080/testActiviti/springmvc/processDefineManagerAction/list") ; 
			} , 
			error : function(data)
			{
				alert("failure") ; 
				$("#dataShow").load("http://localhost:8080/testActiviti/springmvc/processDefineManagerAction/list") ; 
			}
			
		}) ; 
	}
	
	function uploadTemplate(id)
	{
		$("#dataShow").load("http://localhost:8080/testActiviti/springmvc/processDefineManagerAction/uploadTemplateUI?deploymentId="+id ) ; 
	}
	
	function downloadTemplate(id)
	{
		$("#dataShow").load("http://localhost:8080/testActiviti/springmvc/processDefineManagerAction/downloadTemplate?deploymentId="+id ) ; 
	}

</script>
</head>
<body>
<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<td>名称</td>
				<td>版本</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${processDefinitionList}" var="item">
		<tr>
		<td>${item.name}</td>
		<td>${item.version}</td>
		<td> <a href = "#" onclick = "processDefinitiondelete(${item.deploymentId})">删除</a>
			<a href = "springmvc/processDefineManagerAction/downloadTemplate?deploymentId=${item.deploymentId}" >下载模板</a>
			<a href = "#" onclick = "uploadTemplate(${item.deploymentId})">上传模板</a>
<<<<<<< HEAD
			<a href = "#" onclick = "updateTemplate(${item.deploymentId})">更新模板</a>
=======
			<a href = "#" onclick = "updateTemplate(${item.deploymentId})">修改模板</a>
>>>>>>> 40bfaac1f307f5aeac0efd1c1f7141d1420f2638
			<a target = "_blank" href = "springmvc/processDefineManagerAction/seeProcessDiagram?deploymentId=${item.deploymentId}">查看流程图</a>
		</td>
		</tr>	
		</c:forEach>
			
		</tbody>
	</table>
	
	<button id = "addUIButton" class = "btn btn-primary active">添加</button>
</body>
</html>