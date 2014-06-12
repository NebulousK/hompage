<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<jsp:useBean id="dao" class="homepage.someDao" />
<jsp:useBean id="dto" class="homepage.someDao" />
<%
	int callme = dao.are(Integer.parseInt(((String)session.getAttribute("no"))));
	boolean cc = dao.came(Integer.parseInt(((String)session.getAttribute("no"))));
	int news = dao.news((String) session.getAttribute("id"));
	int friend = dao.fiee((String) session.getAttribute("id"));
	int img = callme + friend;
%>
<h3>Navigation</h3>
<div style="background-color:#e0e0e0">
	<li>
		${sessionScope.id}님 환영합니다.
		<%if(img != 0){ %>
		<img src="/homepage/images/vv.png"/>
		<%} %>
			<ul>
			<%if(img != 0){
			if(cc){%>
				<li><a href="#">Dash에 성공 하셨어요!!<span class="count-number" style="color:red;background-color:pink"><img src="/homepage/images/heart2.png"/></span></a></li>
			<%}%>
				<li><a href="/homepage/someNsome/main.jsp#">Call me : <span class="count-number" style="color:red;background-color:pink"><%=callme %></span> </a></li>
				<li><a href="/homepage/friendList/main.jsp#">친구 요청 : <span class="count-number" style="color:red;background-color:pink"><%=friend %></span></a></li>
			</ul>
			<%} %>
	</li>
</div>
&nbsp;
<div class="aside-nav">
	<ul>
		<li><a href="/homepage/itsme/main.jsp">It's me</a></li>
		<li><a href="/homepage/newsfeed/main.jsp">뉴스 피드</a></li>
		<li><a href ="/homepage/friendList/main.jsp">친구 목록</a></li>
		<li><a href ="/homepage/friendFind/main.jsp">친구 찾기</a></li>
		<li class="aside-submenu">
		<a>Some N Some<!-- <span class="count-number"> 3</span> --></a>
			<%if(dao.some_some(Integer.parseInt((String)session.getAttribute("no")))){ %>
			<ul>
				<li><a href="/homepage/someNsome/menu1/main.jsp">러브 스토리</a></li>
				<li><a href="/homepage/someNsome/menu2/photo.jsp">사진첩</a></li>
				<li><a href="/homepage/someNsome/menu3/main.jsp">달력</a></li>
			</ul>
			<%}else{ %>
			<ul>
				<li><a href="/homepage/someNsome/main2.jsp">Dash</a></li>
				<li><a href="/homepage/someNsome/main.jsp">call me</a></li>
			</ul>
			<%} %>
		</li>
	</ul>
</div>
