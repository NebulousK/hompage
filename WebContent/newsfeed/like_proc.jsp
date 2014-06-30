<%@page import="homepage.board.ReplyDto"%>
<%@page import="homepage.board.BoardDto"%>
<%@page import="homepage.board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String command = request.getParameter("command");
	int no = Integer.parseInt(request.getParameter("no"));//글번호
	String id = request.getParameter("id");//접속아이디
	String content = request.getParameter("content");//수정 글 내용
	BoardDao Bdao = new BoardDao();
	
	//System.out.println(command.equals("LIKE"));
	
	
	if(command.equals("LIKE")){
		Bdao.likeUpdate(no, id);
		response.sendRedirect("main.jsp");
	}
	else if(command.equals("UNLIKE")){
		Bdao.likeCancel(no, id);
		response.sendRedirect("main.jsp");
	}
	else if(command.equals("DELETE")){
		Bdao.boardDelete(no);
		response.sendRedirect("main.jsp");
	}
	else if(command.equals("UPDATE")){
	 	BoardDto Bdto = new BoardDto();
		Bdto.setContent(content);
		Bdto.setNo(no);
		Bdao.boardUpdate(1,null); 
		response.sendRedirect("main.jsp");
	}
	else if(command.equals("REPLYPOST")){
		ReplyDto Rdto = new ReplyDto();
		Rdto.setContent(request.getParameter("content"));
		Rdto.setNo(no);
		Rdto.setId(id);
		Bdao.insertreply(Rdto);
		response.sendRedirect("main.jsp");
	}
	else if(command.equals("REPLYDELETE")){
		int Rno = Integer.parseInt(request.getParameter("no"));
		Bdao.replyDelete(Rno);
		response.sendRedirect("main.jsp");
	}
	

%>

</body>
</html>