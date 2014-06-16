<%@page import="homepage.someDto"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="homepage.someDao"/>
<jsp:useBean id="dto" class="homepage.someDto"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<link href="/homepage/css/bootstrap2.css" rel="stylesheet" type="text/css"/> 
<link href="/homepage/css/1.css" rel="stylesheet" type="text/css"/>
<script>
	function like(this_form,set) {
		if(set == 1){
			this_form.state.value = 1;
		}else{
			this_form.state.value = 2;
		}
		this_form.submit();
	}
</script>
</head>
<% 
	Vector g = dao.callme(Integer.parseInt((String)session.getAttribute("no")));
%>
<body>
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
					<%
						if(g.size() > 0){
							for(int i=0; i<g.size();i++){
								dto = (homepage.someDto) g.get(i);
					%>
					<form action="someProc.jsp" method="post">
						<div align="center">
							<div style="width: 530px; text-align: left;">
								<div class="row">
									<input type="hidden" name="action" id="action" value="callme" />
									<input type="hidden" name="userID" id="userID" value="<%=dto.getUserID()%>"/>
									<input type="hidden" name="itemID" id="itemID" value="<%=dto.getItemID()%>"/>
									<input type="hidden" name="state" id="state" value=""/>
									<h1>나에게 대쉬한 이성</h1>
									<p>&nbsp;</p>
									<div style="float: left;"> 
									<img src="/homepage/profile/<%=dto.getPhoto() %>" style="max-width: 263px; max-height: 368px; width: auto; height: auto; text-align: center" />
									</div>
									<div style="float: left;width: 287px;height: 340px">
									
											<ol style="font-size: 13pt;">
												<li class="customLi">이름 : <%=dto.getName() %></li>
												<li class="customLi">나이 : <%=dto.getAge() %></li>
												<li class="customLi">혈액형 : <%=dto.getBlood() %></li>
												<li class="customLi">키 : <%=dto.getHeight() %></li>
												<li class="customLi">몸무게 : <%=dto.getWeight() %></li>
												<li class="customLi">지역 : <%=dto.getAddr() %></li>
												<li class="customLi">성격 : <%=dto.getStyle() %></li>
												<li class="customLi">스타일 : <%=dto.getFashion() %></li>
												<li class="customLi">취미 : <%=dto.getHobby() %></li>
											</ol>
									</div>
									<div style="clear: both; height: 10px"></div>
									
									<div style="float: left;">
									<textarea name="coment" style="width: 500px; height: 150px; resize: none;" readonly="readonly"><%=dto.getComent() %></textarea>
									</div>
									<div style="clear: both;"></div>
								</div>
							</div>
						</div>
						<p>&nbsp;</p>
						<div align="center">
							<input type="button"style="width: 250px; height: 50px; font-size: 15pt; resize: none;" class="btn-custom" value="좋아!!!" onclick="like(this.form,1)"/> &nbsp;&nbsp; 
							<input type="button" style="width: 250px; height: 50px; font-size: 15pt; resize: none;" class="btn-custom" value="싫어!!!" onclick="like(this.form,2)" />
						</div>
					</form> 
					<%		}
						}else{		
					%>
						<div align="center">
						<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
							<font size="5">온라인 상에서도 인기가 없네 하아....</font><br/><br/> 
							<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
						</div>
					<%} %>
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