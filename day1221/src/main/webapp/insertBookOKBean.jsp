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
		request.setCharacterEncoding("UTF-8");
	%>
	<jsp:useBean id="b" class="com.sist.vo.BookVO"></jsp:useBean>	
	<jsp:useBean id="dao" class="com.sist.dao.BookDAO"></jsp:useBean>	
	
	<jsp:setProperty property="*" name="b"/>
	<%
		dao.insertBook(b);
	%>
	OK
</body>
</html>