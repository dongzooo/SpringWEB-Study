<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uplaod Form</title>
</head>
<body>
	 <h2>Upload Form</h2>
	 <form action="/upload/uploadPro" method ="post" enctype="multipart/form-data">
	 	MSG :  <input type = "text" name = "msg" />  <br/> <br/>
	 	File : <input type = "file" name = "img" />  <br/> <br/>
	 	 <input type = "submit" value = "전송" />
	 </form>
</body>
</html>