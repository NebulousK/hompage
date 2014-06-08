<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	if(action.equals("insert")){
		dao.some_board(dto);
%>
	<script>
		alert("작성완료");
		location.href = "/homepage/someNsome/menu1/main.jsp";
	</script>
<%
	}
%>