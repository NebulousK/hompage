<%@page import="Group.GroupDto"%>
<%@page import="Group.GroupDao"%>
<%@page import="Member.MemberDto"%>
<%@page import="Group.FriendDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Responsive Aside website template</title>

<link type="text/css" rel="stylesheet" href="/homepage/css/components.css">
<link type="text/css" rel="stylesheet" href="/homepage/css/responsee.css"> 
<link type="text/css" rel="stylesheet" href="/homepage/css/template-style.css">  

 <link type="text/css" rel="stylesheet" href="/homepage/css/luYzpvna9qk.css" />  
<link type="text/css" rel="stylesheet" href="/homepage/css/oBwXKrMsLPd.css" />  
<link type="text/css" rel="stylesheet" href="/homepage/css/k4p1J0mZLFW.css" />
 <link type="text/css" rel="stylesheet" href="/homepage/css/u4n0TOW16uV.css" />  
 <link type="text/css" rel="stylesheet" href="/homepage/css/aO5MXY7tAfJ.css" />  
<script type="text/javascript" src="/homepage/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/homepage/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/homepage/js/modernizr.js"></script>
<script type="text/javascript" src="/homepage/js/responsee.js"></script>
<jsp:useBean id="friendDao" class="Group.GroupDao"></jsp:useBean>
<jsp:useBean id="memberDto" class="Member.MemberDto"></jsp:useBean>
<jsp:setProperty property="*" name="memberDto"/>
<script>
function go_addFriend(this_form){
	this_form.submit();
}

</script>
<%
	String myid=(String) session.getAttribute("id");;
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
					<jsp:include page="/menu.jsp"/>
				</aside>
				<!-- CONTENT -->
				<section class="s-12 l-7">
						<div class="move">
						<div class="row">
							<h3>요청된 친구목록</h3>
							<div class="span10" align="center">
								<table
									class="table table-bordered table-hover table-condensed table-striped">
									<tbody>
										<%
											Vector rList=new Vector();
											GroupDao dao=new GroupDao();
											rList=dao.Request_List(myid, "false");
											System.out.println("rList.size()"+rList.size());
											for(int i=0;i<rList.size();i++){
												MemberDto mdto=new MemberDto();
												mdto=(MemberDto)rList.get(i);
												String addr[] = mdto.getAddr().split(" ");
										%>
										<form method="post" action="../AddFriend/List_Add_Friend.jsp">
											<input type="hidden" name="userid1" value="<%=myid%>"/> 
											<input type="hidden" name="userid2" value="<%=mdto.getId()%>"/> 
											 <tr>
												<td width="50" style="word-break: break-all">
												<img src="/homepage/profile/<%=mdto.getPhoto()%>" alt="그림이 없습니다." style="widht:40px;height:40px;margin-right:10px"/>
													<a href="/homepage/itsme/main2.jsp?id=<%=mdto.getName()%>"> <%=mdto.getName()%></a></td>
													<td width="50" style="word-break: break-all">
													 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<h6><%=addr[1] + " " + addr[2]%></h6></td>
												 	<td width="50" style="word-break: break-all">
												 	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="button" value="수락" onclick="go_addFriend(this.form)"/>&nbsp;&nbsp;<input type="button" value="취소"/>								
											</tr>
										</form>
										<%
									}
								
										%>
									</tbody>
								</table>

								<br />
								<hr />
							</div>
						</div>
					</div>			
					<div class="move">
						<div class="row">
							<h3>친구목록</h3>
							<div class="span10" align="center">
												
								<!-- 내 아이디 및 친구 아이디 -->				
								<table class="table table-bordered table-hover table-condensed table-striped">
									<tbody>		
								<%
									Vector list=dao.friend_List(myid);
										for(int i=0;i<list.size();i++) {
											MemberDto dto=(MemberDto)list.get(i);
								%>
										<form action="../Delete_Group/List_Delete_Friend.jsp" method="post">
										<%if(!dto.getId().equals(myid)){ 
											System.out.println("i size() - price - count:"+i);
											%>
										 <input type="hidden" name="myid" value="<%=myid%>"/>
										 <input type="hidden" name="userid2" value="<%=dto.getId()%>"/>
												<tr>
												 <td width="50" style="word-break: break-all">
												<img src="/homepage/profile/<%=dto.getPhoto()%>" alt="그림이 없습니다." style="widht:40px;height:40px;margin-right:10px"/>
											 	<a href="/homepage/itsme/main2.jsp?id=<%=dto.getId()%>"><%=dto.getId()%></a></td>
											
											 <td width="50" style="word-break: break-all" align="right">
											 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											 <input type="submit" value="친구삭제" /> <input type="button" value="쪽지" /></td>
										</tr> 
									</form>
								<%		}else if(dto.getId().equals("myid")){
									
											break;	
									
									}
								}%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</section>
				<!-- ASIDE NAV 2 -->
				<aside class="s-12 l-five">
					<jsp:include page="/Friend.jsp"/>
				</aside>
			</div>
		</div>
	</div>
	<!-- FOOTER -->
	<footer class="box">
		<jsp:include page="/footer.html"/>
	</footer>

</body>
</html>