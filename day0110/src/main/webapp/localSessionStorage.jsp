<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#btnReadSession").click(function(){
		$("#output_session").html(sessionStorage.getItem("data"));
	})
	
	$("#btnSaveSession").click(function(){
		sessionStorage.setItem("data",$("#input_session").val());
	})
	
	$("#btnReadLocal").on("click",function(){
		$("#output_local").html(localStorage.getItem("data"));
	})
	
	$("#btnSaveLocal").on("click",function(){
		localStorage.setItem("data",$("#input_local").val());
	})
})
</script>
</head>
<body>

sessionStorage 출력 : <span id="output_session"></span>
<button id="btnReadSession">sessionStorage 읽어오기</button>
<br>
sessionStorage 입력 : <input type="text" id="input_session">
<button id="btnSaveSession">sessionStorage 저장</button>
<hr>

localStorage 출력 : <span id="output_local"></span>
<button id="btnReadLocal">localStorage 읽어오기</button>
<br>
localStorage 입력 : <input type="text" id="input_local">
<button id="btnSaveLocal">localStorage 저장</button>
<hr>
</body>
</html>