<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>

<body>
<body class="size-1140">
	<!-- TOP NAV WITH LOGO -->
	<header>
		<nav>
			<div class="line">
				<div class="s-12 l-2">
					<img class="s-5 l-12 center" src="/homepage/images/logo.png">
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

					<form action="">
						<div align="center">
							<div style="width: 530px; text-align: left;">
								<div class="row">
									<h1>나에게 대쉬한 이성</h1>
									<p>&nbsp;</p>
									<div style="float: left;"> 
									<img src="/homepage/profile/" style="max-width: 263px; max-height: 368px; width: auto; height: auto; text-align: center" />
									</div>
									<div style="float: left;">
									<p>&nbsp;</p>
											<ol style="font-size: 13pt;">
												<li class="customLi">이름 : </li>
												<li class="customLi">나이 : </li>
												<li class="customLi">혈액형 : </li>
												<li class="customLi">키 : </li>
												<li class="customLi">몸무게 : </li>
												<li class="customLi">지역 : </li>
												<li class="customLi">성격 : </li>
												<li class="customLi">스타일 : </li>
												<li class="customLi">취미 : </li>
											</ol>
									</div>
									<div style="clear: both; height: 10px"></div>
									
									<div style="float: left;">
									<textarea name="coment" style="width: 500px; height: 150px; resize: none;" placeholder="내용 들어감" readonly="readonly"></textarea>
									</div>
									<div style="clear: both;"></div>
								</div>
							</div>
						</div>
						<p>&nbsp;</p>
						<div align="center">
							<input type="button"style="width: 250px; height: 50px; font-size: 15pt; resize: none;"
								class="btn-custom" value="좋아!!!" /> &nbsp;&nbsp; 
							<input type="button" style="width: 250px; height: 50px; font-size: 15pt; resize: none;"
								class="btn-custom" value="싫어!!!" />
						</div>
					</form>

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