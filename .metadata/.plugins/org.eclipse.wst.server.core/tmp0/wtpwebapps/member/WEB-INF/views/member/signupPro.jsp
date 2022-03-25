<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ taglib prefix= "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2> signup Pro</h2>
<h4> result : ${result}</h4>

	<c:if test= "${result==1}">
		<h2>${member.id} 님,회원가입 성공!! ${member.id} 님 환영합니다</h2>
		<button onclick="window.location ='/member/main'"> 메인으로</button>
		<!--  3초후 메인으로 이동 -->
		<meta http-equiv="refresh" content = "3;url=http://localhost:8080/member/main">
	</c:if>
	
	<c:if test= "${result!=1}">
		<h2> 회원가입 실패..다시시도해주세요</h2>
		<button onclick="window.location ='/member/main'"> 메인으로</button>
		<button onclick="window.location ='/member/signup'"> 회원가입</button>
	</c:if>
</body>
</html>