<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/homepage/css/rateit.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
<script src="/homepage/js/jquery.rateit.js" type="text/javascript"></script>
<script type="text/javascript">
	function test() {
		/* document.getElementById("a").innerHTML = $('#rateit').rateit('value'); */
		document.getElementById("value").value = $('#rateit').rateit('value');
	}
</script>
</head>
<body>	
	
	<div class="rateit bigstars" data-rateit-starwidth="32" data-rateit-starheight="32" id="rateit"></div>
    <button onclick="test()">Get value</button>
    <div id="a"></div>
    <input type="text" name="value" id="value" value="" />
</body>
</html>