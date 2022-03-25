<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix= "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
	<meta charset="UTF-8">
	<title>LoginPro</title>
</head>
<body>
	<br>
	<h2 align="center">loginPro page</h2>
	<h3> ${sessionScope.memId}</h3>
	
	<c:if test="${sessionScope.memId != null}">
		<script>
			alert("로그인성공")
			window.location.href="/member/main"
		</script>	 	
			<meta http-equiv="refresh" content = "3;url=http://localhost:8080/member/main">
	</c:if>
	
	<c:if test="${sessionScope.memId == null}">
	 	<script>
			alert("로그인실패, 다시시도해주세요!")
			history.go(-1);
		</script>	 
	</c:if>
	

</body>
</html>