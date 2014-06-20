<!DOCTYPE html>
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script> 
function fn_layer_popup(){ 
	var _x = event.clientX + document.body.scrollLeft; //마우스로 선택한곳의 x축(화면에서 좌측으로부터의 거리)를 얻는다. 
	var _y = event.clientY + document.body.scrollTop; //마우스로 선택한곳의 y축(화면에서 상단으로부터의 거리)를 얻는다. 
	var layer = document.getElementById("popup_layer"); 
	if(_x < 0) _x = 0; //마우스로 선택한 위치의 값이 -값이면 0으로 초기화. (화면은 0,0으로 시작한다.) 
	if(_y < 0) _y = 0; //마우스로 선택한 위치의 값이 -값이면 0으로 초기화. (화면은 0,0으로 시작한다.) 
	layer.style.left = _x+"px"; //레이어팝업의 좌측으로부터의 거리값을 마우스로 클릭한곳의 위치값으로 변경. 
	layer.style.top = _y+"px"; //레이어팝업의 상단으로부터의 거리값을 마우스로 클릭한곳의 위치값으로 변경. 
	layer.style.visibility="visible"; 
	} 
</script> 

</head>
<body>
<div id="popup_layer" style="position:absolute; border:double;top:0px;  left:0px; width:100px; height:50px; z-index:1; visibility:hidden; background-color:white;"> 

</div>

<input type="button" value="click" onclick="fn_layer_popup()"> 
</body>
</html> 