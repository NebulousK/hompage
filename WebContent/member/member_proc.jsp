<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="homepage.someDao"/>
<%
 	dao.setPath(request, "profile");
	dao.setMax(5*1024*1024);
	dao.setEncType("UTF-8");
	dao.member_join(request); 
%>