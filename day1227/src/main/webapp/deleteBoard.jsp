<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시물 삭제</h2>
<form action="deleteBoard" method="post">
	<input type="hidden" name="no" value="<%=request.getParameter("no")%>">
	삭제를 위해 비밀번호를 입력하세요 : <input type="text" name="pwd">
	<input type="submit" value="삭제">
</form>
</body>
</html>