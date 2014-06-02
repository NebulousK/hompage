<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="join" %>
<jsp:useBean id="dao" class="homepage.someDao"/>
<jsp:useBean id="dto" class="homepage.someDto"/>
<%
	request.setCharacterEncoding("UTF-8");
	String action = null;
%>
<jsp:setProperty property="*" name="dto"/>
<% 
	if(!request.getParameter("action").equals(null)){
		action = request.getParameter("action");
	}	
	if(action.equals("detail")){
		String[] str = request.getParameterValues("fashion");
		String[] str2 = request.getParameterValues("fashion2");
		String fashion="" , fashion2="";
		for(int i=0; i<str.length; i++){
		 	if(i!=0){
				fashion += ",";
		 	}
			fashion += str[i];
		}
		for(int i=0; i<str2.length; i++){
			if(i!=0){
				fashion2 += ",";
		 	}
			fashion2 += str2[i];
		}
		dto.setFashion(fashion);
		dto.setFashion2(fashion2);	
		dao.member_detail(dto);
%>
	<script>
		alert("저장 완료");
		location.href = "/homepage/index.html";
	</script>	
<% 
	}
	if(action.equals("login")){
		
	}
	else{
		dao.member_join(request);
%>  
	<script>
		alert("가입 완료");
		location.href = "/homepage/member/member_join2.jsp";
	</script>
<% 
	}
%>