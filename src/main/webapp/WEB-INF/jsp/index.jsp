<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file = "common.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
padding-top:70px ; 
}
</style>

<script type="text/javascript">
$(document).ready(
		function()
		{
			$("#templateManagerButton").click(function(){
				var url = "http://localhost:8080/testActiviti/springmvc/templateManagerAction/list" ; 
				var data = {
						 
				} ; 
				$("#dataShow").load(url , data ) ; 
			}) ; 
			
			$("#processDefineManagerButton").click(function(){
				var url = "http://localhost:8080/testActiviti/springmvc/processDefineManagerAction/list" ; 
				$("#dataShow").load(url) ; 
			}) ; 
			
			$("#applyButton").click(function(){
				var url = "http://localhost:8080/testActiviti/springmvc/applyManagerAction/list" ; 
				$("#dataShow").load(url) ; 
			}) ; 
			
			$("#approveManagerButton").click(function(){
				var url = "http://localhost:8080/testActiviti/springmvc/approveManagerAction/waitForApprove" ; 
				$("#dataShow").load(url) ; 
			}) ;
			
			$("#myApplicationListButton").click(function(){
				var url = "http://localhost:8080/testActiviti/springmvc/applyManagerAction/myApplicationList" ;
				$("#dataShow").load(url) ; 
			}) ;
		}
		) ; 
		

</script>

</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
<div class = "container" >
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">Brand</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li><a class = "navbar-link" href="#" >Link</a></li>
      <li><a href="#">Link</a></li>
     
     

<form class="navbar-form navbar-left" method = "POST" action = "springmvc/userAction/login" >
  <div class="form-group">
    <input type="text" name = "userName" class="form-control" placeholder="userName">
  </div>
  <button type="submit" class="btn btn-default">登录</button>
</form>


     
    </ul>
   
    <ul class="nav navbar-nav navbar-right">
      <li><a class = "navbar-link" href="#">退出</a></li>
      <li class="dropdown">
       
      </li>
    </ul>
  </div><!-- /.navbar-collapse -->
  </div>
</nav>
<div class = "container">
<div class="list-group col-lg-3">
  <button id = "templateManagerButton" type="button" class="list-group-item">模板管理</button>
  <button id = "processDefineManagerButton" type="button" class="list-group-item">流程定义管理</button>
  <button id = "applyButton" type="button" class="list-group-item">起草申请</button>
  <button id = "approveManagerButton" type="button" class="list-group-item">待我审批</button>
  <button id = "myApplicationListButton" type="button" class="list-group-item">我的申请</button>

</div>

<div id = "dataShow" class="list-group col-lg-9">
 
</div>
</div>

</body>
</html>