<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String name = "JSPStudy";
	String bloodType = request.getParameter("bloodType");
%>
<h1>Forward Tag Example2</h1>
<jsp:forward page='<%=bloodType+".jsp"%>'>
	<jsp:param value="<%=name %>" name="name"/>
</jsp:forward>
</body>
</html>