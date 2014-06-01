<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <title> New Document </title>
  
<script type="text/javascript" src="/homepage/js/jquery-1.8.3.min.js"></script>
<script>
$(document).ready(function(){
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
            reader.onload = function (e) { 
            //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                $('#blah').attr('src', e.target.result);
                //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
                //(아래 코드에서 읽어들인 dataURL형식)
            }                    
            reader.readAsDataURL(input.files[0]);
            //File내용을 읽어 dataURL형식의 문자열로 저장
        }
    }//readURL()--

    //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
    $("#imgInp").change(function(){
        alert(this.value); //선택한 이미지 경로 표시
        readURL(this);
    });
 });
</script>
 </head>

 <body>
    <form id="form1">
       
        <img id="blah" src="#" alt="your image" />
         <input type='file' id="imgInp" /><br/>
    </form>
    

<span id="fileInputForm" style="position:relative; float:left; width:62px; height:18px; overflow:hidden; cursor:pointer; background-image:url('../images/table/add_search.png');">
<input type="file" id="filename" name="filename" value="" style='position：absolute; margin-left:-10px; width:62px; height:18px; filter:alpha(opacity=0); opacity:0; -moz-opacity:0; cursor:pointer;' onChange="fileUpload()">
</span>
 </body>
</html>