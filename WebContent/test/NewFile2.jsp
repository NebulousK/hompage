<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<html>
 <head>
  <title> New Document </title>
  <script language="JavaScript">
	function test(this_form)
	{	//아래의 호출한 이벤트핸들러를 통해 폼의 이름을 그대로 받아서 쓰게되죠.
		this_form.submit();
	}
  </script>
 </head>

 <body>
  <form name="test_form" method="post" action="">
  <input type="text" name="text_field">
  <input type="button" onclick="test(this.form)" value="alert">
  </form>
  <form name="test_form" method="post" action="">
  <input type="text" name="text_field">
  <input type="button" onclick="test(this.form)" value="alert">
  </form>
  <form name="test_form" method="post" action="">
  <input type="text" name="text_field">
  <input type="button" onclick="test(this.form)" value="alert">
  </form>
  
  <%=request.getParameter("text_field") %>
 </body>
</html>