<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="#996600">
   <table width="500" align="center" cellpadding="0" cellspacing="0">
      <tr>
         <td align="center" valign="middle" bgcolor="#ffffcc">
            <table border="1" cellpadding="2" cellspacing="0" align="center">
               <form name="regForm" method="post" action="memberProc.jsp">
                  <tr align="center" bgcolor="#996600">
                     <td>
                        <font color="#ffffff"><b>회원가입</b></font>
                     </td>
                  </tr>
                  <tr>
                     <td width="100">아이디</td>
                     <td width="200">
                        <input type="text" name="id" size="15">
                     </td>
                     <td width="200">아이디를 적어주세요</td>
                  </tr>
                  
                  <tr>
                     <td width="100">패스워드</td>
                     <td width="200">
                        <input type="password" name="pwd" size="15">
                     </td>
                     <td width="200">패스워드를 적어주세요</td>
                  </tr>
                  <tr>
                     <td width="100">패스워드확인</td>
                     <td width="200">
                        <input type="password" name="pwd2" size="15">
                     </td>
                     <td width="200">패스워드를 적어주세요</td>
                  </tr>
                  <tr>
                     <td width="100">이름</td>
                     <td width="200">
                        <input type="text" name="name" size="15">
                     </td>
                     <td width="200">이름를 적어주세요</td>
                  </tr>
                  <tr>
                     <td width="100">생년월일</td>
                     <td width="200">
                        <input type="text" name="birth" size="15">
                     </td>
                     <td width="200">생년월일를 적어주세요</td>
                  </tr>
                  <tr>
                     <td width="100">이메일</td>
                     <td width="200">
                        <input type="email" name="email" size="15">
                     </td>
                     <td width="200">이메일를 적어주세요</td>
                  </tr>
                  <tr>
                     <td colspan="3" align="center">
                        <input type="button" value="회원가입" onclick="inputCheck()">
                        <input type="reset" value="다시쓰기">                        
                     </td>
                  </tr>
               </form>
            </table>
         </td>
      </tr>>
   </table>
</body>
</html>