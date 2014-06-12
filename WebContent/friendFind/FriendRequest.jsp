<%@page import="Group.GroupDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	String myid=request.getParameter("myid");
	String id=request.getParameter("id");
	String bool="false";
	GroupDao dao=new GroupDao();
	dao.Friend_request(myid,id,bool);
	response.sendRedirect("main.jsp");
%>
</body>
</html>