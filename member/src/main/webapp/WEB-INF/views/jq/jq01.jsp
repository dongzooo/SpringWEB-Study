<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>jq01</title>
	<script src= "https://code.jquery.com/jquery-3.6.0.min.js"> 
	
	</script>
	
</head>
<body>
	<button onclick= "test()">btnJs</button>
	<button onclick= "test()">btnjQiery</button>

	<script type="text/javascript">
	function test(){
		alert("test!");
		
	}
	
	//jquery
	$(document).ready(function(){
		$("button").click(function(){
			alert("jq TEST!!");
		});
	});
	
	
	</script>
</body>
</html>