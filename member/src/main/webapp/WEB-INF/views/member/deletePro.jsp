<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%@ taglib prefix= "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>deletePro</title>
	<link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br>
	<c:if test="${result ==1 }">
		<h3>탈퇴되셨습니다!..안녕!</h3>
		<button onclick="window.location='/member/main'">메인</button>
	</c:if>
	<c:if test="${result !=1 }">
		<script>
		 alert("잘못입력 다시입력바람")
		 history.go(-1);
		</script>
	</c:if>

</body>
</html>