<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <jsp:useBean id="dao" class="homepage.someDao"/>
 <%
 	String email1=request.getParameter("email1");
 	String email2=request.getParameter("email2");
 	String result=dao.emailcheck(email1, email2);
 	System.out.println("이메일 체크:"+result);
 	
 	out.println(result);
 
 %>
