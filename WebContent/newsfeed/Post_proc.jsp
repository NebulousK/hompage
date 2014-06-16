<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<jsp:useBean id="Bdto" class="homepage.board.BoardDto"></jsp:useBean>
<jsp:useBean id="Bdao" class="homepage.board.BoardDao"></jsp:useBean>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String content = request.getParameter("content");
	String id = request.getParameter("id");
	String action = null;
	if(request.getParameter("content") !=null){
		action = request.getParameter("action");
	}
	//System.out.println(content);

	Bdto.setId(id);
	Bdto.setContent(content);
	Bdao.insertBoard(Bdto); 
	if(action.equals("me")){
			
	}
	response.sendRedirect("main.jsp");
%>
</body>
</html>