<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src= "https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
	<h3>hello2 jq</h3>
	<h3 id ="id1">idjq!</h3>
	<h3 id ="cls1">class jq!</h3>
	
	<ul>
		<li>11</li>
		<li>22</li>
		<li>33</li>
		<li>44</li>
	
	</ul>
	
	<script >
	$(document).ready(function(){
		$("h3").css("color","red");
		$("#id1").css("color","blue");
		$(".cls1").css("color","green");
		
		$("li:nth-child(2n)").css("color","magenta");
		$("li").attr("class","test");
		$("li").addClass("abc");
		$("li").removeClass("test");
		
		$(".btn").click(function(){
			$("img").attr("src", "/resources/imgs/bebe.png")
		})
	});
	
	</script>
</body>
</html>