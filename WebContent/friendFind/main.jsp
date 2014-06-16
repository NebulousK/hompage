<%@page import="Group.GroupDto"%>
<%@page import="Member.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Responsive Aside website template</title>

<link type="text/css" rel="stylesheet"
	href="/homepage/css/components.css">
<link type="text/css" rel="stylesheet"
	href="/homepage/css/responsee.css">
<link type="text/css" rel="stylesheet"
	href="/homepage/css/template-style.css">
<link type="text/css" rel="stylesheet"
	href="/homepage/css/luYzpvna9qk.css" />
<link type="text/css" rel="stylesheet"
	href="/homepage/css/oBwXKrMsLPd.css" />
<link type="text/css" rel="stylesheet"
	href="/homepage/css/k4p1J0mZLFW.css" />
<link type="text/css" rel="stylesheet"
	href="/homepage/css/u4n0TOW16uV.css" />
<link type="text/css" rel="stylesheet"
	href="/homepage/css/aO5MXY7tAfJ.css" />
<script type="text/javascript" src="/homepage/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/homepage/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/homepage/js/modernizr.js"></script>
<script type="text/javascript" src="/homepage/js/responsee.js"></script>
<%!
 	//int count=0;
	String area="";
	MemberDto mydto=null;
%>
<script>
	function check(){
		document.research.submit();
	}
	//구역친구 설정
	function areaCheck(){
		document.research.submit();
	}
	
	function friendRequest(this_form){
		//alert("friendRequest불렀따..");
		this_form.submit();
	}
	
	<%
	response.setCharacterEncoding("utf-8");   
	request.setCharacterEncoding("utf-8");
	%>
	
	
</script>
<link href="bootstrap.css" rel="stylesheet" />
<jsp:useBean id="dao" class="Group.GroupDao"></jsp:useBean>
<%
	String myid=(String) session.getAttribute("id");
		String research="";
		String keyField="";
		research=request.getParameter("research");
		keyField=request.getParameter("keyField");
		//내 아아디 정보 가져오기
		Vector my=(Vector)dao.getResearchFriendList(myid,"id");
		MemberDto mydto=null;
		for(int i=0;i<my.size();i++){
			 mydto=(MemberDto)my.get(i);
		}
		//검색해서 나온 결과
		Vector list=(Vector)dao.getResearchFriendList(keyField, research);
%>

</head>
<body class="size-1140">
	<!-- TOP NAV WITH LOGO -->
	<header>
		<nav style="background-color: #212121;">
			<div class="line" >
				<div class="s-12 l-2" >
					<img class="s-5 l-12 center" src="/homepage/images/IMG_004.png" style="width: 100px; height: 100px;margin-top: 7px;margin-bottom: 7px"/>
				</div>
				
			</div>
		</nav>
	</header>
	<!-- ASIDE NAV AND CONTENT -->
	<div class="line"> 
		<div class="box  margin-bottom">
			<div class="margin">
				<!-- ASIDE NAV 2 -->
				<aside class="s-12 l-five">
					<jsp:include page="/menu.jsp" />
				</aside>
				<!-- CONTENT -->
				<section class="s-12 l-7">
					<div class="span12">
						<form action="main.jsp" method="post" name="research">
						<div class="span12">
								<div class="navbar-form" align="center"
									style="margin-left: 0px; margin-right: 20px">
									<!-- 모양이 둥근 것  -->
									<input type="text" name="keyField" value="<%-- <%=searchVal%> --%>" style="width: 90%;" class="input-medium search-query" /> &nbsp;&nbsp;
									<!-- 전송버튼 모양 적용한것-->
									&nbsp;&nbsp;<input type="button" name="smt" value="검색" class="btn" onclick="check()"/>
								</div>
						</div>
						<div><!-- 공백 -->  </div>
							
							<div align="right">
								
								<input type="radio" name="research" value="id">id&nbsp;&nbsp;&nbsp;
								<input type="radio" name="research" value="name" checked="checked">name
							</div>
						</form>
						
							<form action="main.jsp" method="post" name="area">
								<input type="hidden" name="acheck" value="true"/>
								<div class="navbar-form pull-left">
								<!--테두리로 묶어주는것  -->
								<fieldset>
									<input type="text" name="area" placeholder="부산,대구,인천,대전,광주,울산" />
									<!-- 버튼 -->
									<button type="submit" class="btn">광역시로검색</button>
								</fieldset>
							</div>
						</form>
							<% 
								String acheck="";
								acheck = request.getParameter("acheck");
								String area="";
								area = request.getParameter("area");
								%>
								<table
									class="table table-bordered table-hover table-condensed table-striped">
									<caption>전체 친구</caption>
									<thead>
										<tr>
											<th>사진</th>
											<th>이름</th>
											<th>사는 곳</th>
											<th>action</th>
										</tr>
									</thead>
									<tbody>
									<%
									//요청 누눌때마다 올라가는 count 수
										if("true".equals(acheck)){
											Vector aList=dao.area_Print(area);						
									int count=0;
									for(int i=0;i<aList.size();i++){
										MemberDto dto=(MemberDto)aList.get(i);
										String addr[] = dto.getAddr().split(" ");
									%>
										<tr>
											<form name="FriendRequest" method="post" action="../friendFind/FriendRequest.jsp">
											<input type="hidden" name="no" value="<%=dto.getNo() %>"/>
											<input type="hidden" name="id" value="<%=dto.getId()%>"/>
											<input type="hidden" name="name" value="<%= dto.getName() %>"/>
											<input type="hidden" name="sex" value="<%=dto.getSex()%>"/>
											<input type="hidden" name="birthday " value="<%=dto.getBirthday()%>"/>
											<input type="hidden" name="addr" value="<%=addr[1] + " " + addr[2] %>"/>
											<input type="hidden" name="tel" value="<%=dto.getTel()%>"/>
											<input type="hidden" name="photo" value="<%=dto.getPhoto() %>"/>
											<input type="hidden" name="check" value="true"/>
											<input type="hidden" name="myid" value="<%=myid%>"/>
											<td><a href="#"><img src="/homepage/profile/<%=dto.getPhoto() %>" alt="그림이 없습니다."/ style="width:40px;height:40px"></a></td>
											<td><%=dto.getName()%></td>
											<td><font size="1"><%=addr[1] + " " + addr[2]%></font></td>
											<%
											String check=dao.check_Friend(myid, dto.getId());
											if(check.equals("1")){%>				
											<td><span>친구요청 중입니다.</span>
											<%	
											} else if(check.equals("2")){
											%>
											<td><span>친구요청응답에 응답하세요.</span>
											
											<%
											} else if(check.equals("3")){
												%>
											<td><span>이미 친구 입니다.</span>
											<%
											} else if(myid.equals(dto.getId())) {
											
											%>	<td><span>본인입니다.</span>
											<%
											} else {
											%>
											<td><input type="button" value="친구요청" onclick="friendRequest(this.form)"/>
											<%} %>
											&nbsp;&nbsp;&nbsp;<input type="button" value="쪽지" /></td>
										</tr>
										</form>
									<%
											}
										} else {
										
											for(int i=0;i<list.size();i++){
												MemberDto dto=(MemberDto)list.get(i);
												String addr[] = dto.getAddr().split(" ");
									%>
											<tr>
											<form name="FriendRequest" method="post" action="../friendFind/FriendRequest.jsp">
											<input type="hidden" name="no" value="<%=dto.getNo() %>"/>
											<input type="hidden" name="id" value="<%=dto.getId()%>"/>
											<input type="hidden" name="password" value="<%=dto.getPassword()%>"/>
											<input type="hidden" name="name" value="<%= dto.getName() %>"/>
											<input type="hidden" name="sex" value="<%=dto.getSex()%>"/>
											<input type="hidden" name="birthday " value="<%=dto.getBirthday()%>"/>
											<input type="hidden" name="addr" value="<%=addr[1] + " " + addr[2]%>"/>
											<input type="hidden" name="tel" value="<%=dto.getTel()%>"/>
											<input type="hidden" name="photo" value="<%=dto.getPhoto() %>"/>
											<input type="hidden" name="check" value="true"/>
											<input type="hidden" name="myid" value="<%=myid%>"/>
											<td><a href="#"><img src="/homepage/profile/<%=dto.getPhoto() %>" alt="그림이 없습니다."/ style="width:40px;height:40px"></a></td>
											<td><%=dto.getName()%></td>
											<td><font size="1"><%=addr[1] + " " + addr[2]%></font></td>
											<%
											String check=dao.check_Friend(myid, dto.getId());
											if(check.equals("1")){%>
											<td><span>친구요청 중입니다.</span>
											<%	
											} else if(check.equals("2")){
												
											%>
											
											<td><span>친구요청응답에 응답하세요.</span>
											
											<%
											} else if(check.equals("3")){
												%>
											<td><span>이미 친구 입니다.</span>
											
											
											<%
											} else if(myid.equals(dto.getId())) {
											
											%>	<td><span>본인입니다.</span>
											<%
											} else {
											%>
											<td>
											<input type="button" value="친구요청" onclick="friendRequest(this.form)"/>
											<%} %>
											&nbsp;&nbsp;&nbsp;<input type="button" value="쪽지" /></td>
										</tr>
										<% }
											
										}
									
									%>
									 </form>					
									</tbody>
								</table>
							</div>
				</section>
				<!-- ASIDE NAV 2 -->
				<aside class="s-12 l-five">
					<jsp:include page="/Friend.jsp" />
				</aside>
			</div>
		</div>
	</div>
	<!-- FOOTER -->
	<footer class="box">
		<jsp:include page="/footer.html" />
	</footer>

</body>
</html>