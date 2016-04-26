<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class = "table table-borderd">
	<thead>
	<tr>
	<td>申请人</td>
	<td>申请时间</td>
	<td>审批信息</td>
	</tr>
	</thead>
	<tbody>
		<c:forEach items = "${myApplicationList}" var = "application">
		<tr>
		<td>${application.user.name}</td>
		<td> </td>
		<td>${application.status}</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
</body>
</html>