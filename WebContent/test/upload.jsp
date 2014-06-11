<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="basic" class="up.BasicBean" />
<% 
	basic.setPath(request, "upload");
	basic.setMax(50*1024*1024);
	basic.setEncType("UTF-8");
	basic.setMulti(request);
%>
파일 정보 : <jsp:getProperty property="upFile" name="basic"/> 




