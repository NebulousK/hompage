<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="homepage.someDao"/>
<jsp:useBean id="dto" class="homepage.someDto"/>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:setProperty property="*" name="dto"/>
<%
	if(request.getParameter("action") != null){
		String action = request.getParameter("action");
	if(action.equals("m-insert")){
	    dao.insertmessage((String)session.getAttribute("id"),dto);
		}
	}
%>