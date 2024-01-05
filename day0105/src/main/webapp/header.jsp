<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${userID==null }">
	<a href="login.do">로그인</a>
	</c:if>
	<c:if test="${userID!=null }">
	<a href="/day0105/logout.do">로그아웃</a>
	&nbsp;
	<a href="/day0105/member/listBoard.do">게시물 목록</a>
	<a href="/day0105/member/listBoard.do?writer=${userID }">내글보기</a>
	</c:if>
	
</body>
</html>