<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		CKEDITOR.replace('textarea');
		$("#submitButton").click(function(){
			var url = "springmvc/approveManagerAction/approve";
			 var data = CKEDITOR.instances.textarea.getData();
			 $("#textarea").html(data) ;
			 var fd = new FormData($("#form")[0]);
			$.ajax({
				url : url ,
				type : 'post' , 
				data : fd , 
				contentType : false , 
				processData: false,
				success : function(data){
					alert("success") ; 
					$("#dataShow").load("springmvc/approveManagerAction/waitForApprove") ; 
				} ,
				error : function(data){
					alert('error') ; 
				}
			}) ; 
		
		}) ; 
		

	});
</script>
</head>
<body>
	<form id = "form">
		<input id="hidden" type="hidden" name="taskId" value="${taskId}" />
		<textarea id="textarea" name = "textarea"></textarea>
		<div class="radio">
			<label> <input type="radio" name="optionsRadios"
				id="optionsRadios1" value="true"> 
				通过
			</label>
		</div>
		<div class="radio">
			<label> <input type="radio" name="optionsRadios"
				id="optionsRadios2" value="false"> 
				不通过
			</label>
		</div>
	</form>
	<button id="submitButton" class="btn">提交</button>
</body>
</html>