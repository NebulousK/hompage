<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="Member.MemberDto" %>
<jsp:useBean id="dao" class="homepage.someDao"/>
<%
	
	String id=request.getParameter("id");
	String password=request.getParameter("password");
	String result=dao.login(id, password);
	out.println(result);
	
%>