<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix= "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">

<meta charset="UTF-8">
<title>Main</title>

</head>
<body>
	<br/>
	<h1 align="center">메인 페이지</h1>
	
	<c:if test="${sessionScope.memId ==null}">
	<table>
		<tr>
			<td>
				로그인을 원하시면 버튼을 누르세요.<br>
				<button onclick="window.location='/member/login'">로그인</button>
			</td>
		</tr>
		
		<tr>
			<td>
				<a href = "/member/signup">회원가입</a>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<br>
	</c:if>
	
	<c:if test="${sessionScope.memId !=null}">
	<table>
		<tr>
			<td>
				<button onclick="window.location='/member/mypage'">마이페이지</button>
			</td>
		</tr>
		<tr>
			<td>
				<button onclick="window.location='/member/logout'">로그아웃</button>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<br>
	</c:if>
	
	
	<div align="center">
		<img src="/resources/imgs/beach.jpg" width="600px">
	</div>

</body>
</html>