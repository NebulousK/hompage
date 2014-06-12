<%@page import="Group.GroupDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%-- <jsp:useBean id="dto"class="Group.FriendDto"></jsp:useBean>
	 --%>
<%



	//친구하기
	/* String userid1=request.getParameter("userid1");
	String userid2=request.getParameter("userid2");
	String friends="true";
	//dto.setFriends(friends);
	//dto.setUserid1(userid1);
	//d/to.setUserid2(userid2);
	//System.out.println("List_Add_Friend.jsp userid1"+dto.getUserid1());
	//System.out.println("List_Add_Friend.jsp userid2"+dto.getUserid2());
	//System.out.println("List_Add_Friend.jsp friends"+dto.getFriends());
	
	GroupDao dao=new GroupDao();
	//dao.list_Friend_Delete(userid1, userid1, friends);
	System.out.println("List_Add_Friend.jsp 오나요?");
	dao.listAddFriend(userid1, userid2, friends);
	*/	
	
	String userid1=request.getParameter("userid1");
	String userid2=request.getParameter("userid2");
	GroupDao dao=new GroupDao();
	dao.accept_Friend(userid2, userid1);
	
	
	response.sendRedirect("../friendList/main.jsp");
	
%>
<B>List_Add_Friend.jsp</B>