<div id='start' name='start' style='width:100%;margin-left: 30px;height: auto;float:left;'>
<form name='form1' method='post' target='dummy' action='/homepage/boarddel.admin'>
<div class='style5'>"+dto.getId()+"<input type='hidden' name='no' id='no' value='"+dto.getNo()+"'/></div>
<div class='style6'>"+dto.getContent()+"</div>
<div class='style5'>"+dto.getDay()+"</div>
<div class='style5'><span style='position:relative; float:center; width:45px; height:18px; overflow:hidden; cursor:pointer; background-image:url(\"/homepage/images/b_delete1.gif\");'>
<input type='button' onclick='del(this.form)' style='position：absolute; margin-left:-10px; width:50px; height:18px; filter:alpha(opacity=0); opacity:0; -moz-opacity:0; cursor:pointer;'></span></div>
</form>
</div>