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
            var reader = new FileReader(); //������ �б� ���� FileReader��ü ����
            reader.onload = function (e) { 
            //���� �о���̱⸦ ���������� ȣ��Ǵ� �̺�Ʈ �ڵ鷯
                $('#blah').attr('src', e.target.result);
                //�̹��� Tag�� SRC�Ӽ��� �о���� File������ ����
                //(�Ʒ� �ڵ忡�� �о���� dataURL����)
            }                    
            reader.readAsDataURL(input.files[0]);
            //File������ �о� dataURL������ ���ڿ��� ����
        }
    }//readURL()--

    //file ������� �̹����� ����(���� ����) �Ǿ����� ó���ϴ� �ڵ�
    $("#imgInp").change(function(){
        alert(this.value); //������ �̹��� ��� ǥ��
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
<input type="file" id="filename" name="filename" value="" style='position��absolute; margin-left:-10px; width:62px; height:18px; filter:alpha(opacity=0); opacity:0; -moz-opacity:0; cursor:pointer;' onChange="fileUpload()">
</span>
 </body>
</html>