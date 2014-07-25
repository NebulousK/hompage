<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="Member.MemberDto" %>
<jsp:useBean id="dao" class="homepage.someDao"/>
<%
	String id=request.getParameter("id");
	String result=dao.idcheck(id);
	System.out.println("result: "+result);
	out.println(result);

%>