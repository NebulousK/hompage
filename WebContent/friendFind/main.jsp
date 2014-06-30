<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

	function goPOPUP2(id) { 
	 	 window.open('','target_name2','scrollbars=yes,toolbar=yes,resizable=yes,width=500,height=800');
	 	 formex2.action = "/homepage/memseage.me"; // 팝업화면을 호출할 페이지 또는 파일명 
	  	 formex2.id.value = id;                      // form에서 넘겨주는 seq값 
	 	 formex2.target = "target_name2";             // window.open 에서 선언한 target name 
	 	 formex2.submit(); 
	} 
	
	
</script>
<link href="bootstrap.css" rel="stylesheet" />
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
					<%--  ${requestScope.testList}--%>	
					<!-- 넘겨주는 변수 값 : id,name 그리고 textField 값 검색공간 -->
					<form action="/homepage/findf.friend" method="post" name="research">
						<input type="hidden" name="myid" value="myid"/>
						<div class="span12">
							<div class="navbar-form" align="center"
								style="margin-left: 0px; margin-right: 20px">
								<!-- 모양이 둥근 것  -->
								<input type="text" name="keyField" value="" style="width: 90%;" class="input-medium search-query" /> &nbsp;&nbsp;
								<!-- 전송버튼 모양 적용한것-->
								&nbsp;&nbsp;<input type="button" name="smt" value="검색" class="btn" onclick="check()"/>
							</div>
						</div>
						<div><!-- 공백 --></div>
							
						<div align="right">
							<input type="radio" name="research" value="id">id&nbsp;&nbsp;&nbsp;
							<input type="radio" name="research" value="name" checked="checked">name
						</div>
					</form>
						
					<!--지역 검색 공간  넘겨주는 변수값: area acheck-->				
					<form action="/homepage/finda.friend" method="post" name="area">
						<input type="hidden" name="command" value="Friend_FindArea"/>
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
			
				<!-- 정보 뿌려주는 곳(사진,이름,사는 곳 ,action)  -->
				<table class="table table-bordered table-hover table-condensed table-striped">
				<caption>전체 친구</caption>				
				<thead>
				<tr>
					<th>사진</th>
					<th>이름</th>
					<th>사는 곳</th>
					<th>친구 요청</th>
					<th>쪽지</th>
				</tr>
				</thead>
					<tbody>          
					<c:forEach var="dto" items="${requestScope.dto}">     
					<tr>
					<form name="FriendRequest" method="post" action="/homepage/findadd.friend">
					<input type="hidden" name="no" value="${dto.no}"/>
					<input type="hidden" name="id" value="${dto.id}"/>
					<input type="hidden" name="name" value="${dto.name}"/>
					<input type="hidden" name="sex" value="${dto.sex}"/>
					<input type="hidden" name="birthday " value="${dto.birthday}"/>
					<input type="hidden" name="addr" value="${dto.addr}"/>
					<input type="hidden" name="tel" value="${dto.tel}"/>
					<input type="hidden" name="photo" value="${dto.photo}"/>
					<input type="hidden" name="check" value="true"/>
					<input type="hidden" name="myid" value="${sessionScope.id}"/>
					<td><a href="/homepage/how.board?id=${dto.id}"><img src="/homepage/profile/${dto.photo}" style="width:40px;height:40px"></a></td>				
					<td>${dto.name}</td>
					<td><font size="1">${dto.addr}</font></td>
					<c:if test='${dto.check == "true"}'>
					<td><span>이미 친구 입니다.</span></td>
					<td><input type="button" value="쪽지 보내기" onclick="goPOPUP2('${dto.id}')"/></td>
					</c:if>
					<c:if test='${dto.check == "false"}'>
					<td><span>친구요청 중입니다.</span></td>
					<td><input type="button" value="쪽지 보내기" onclick="goPOPUP2('${dto.id}')"/></td>
					</c:if>
					<c:if test='${dto.check == "not"}'>
					<td><input type="button" value="친구요청" onclick="friendRequest(this.form)"/></td>
					<td><input type="button" value="쪽지 보내기" onclick="goPOPUP2('${dto.id}')"/></td>
					</c:if>
					</form>	 
				</c:forEach>	
				</tr>
				</tbody>
			</table>
		</div>
	</section>
	
	<!-- ASIDE NAV 2 -->
	<aside class="s-12 l-five">
	<jsp:include page="../Friend.jsp" />
	</aside>
	</div>
	</div>
	</div>
<!-- FOOTER -->
<footer class="box">
<jsp:include page="../footer.html" />
</footer>
 <form name="formex2" id="formex2" method="post">
    <input type="hidden" name="id" id="id">
    </form>
<div id="popup_layer" style="position:absolute;border:double;top:0px;left:0px;width:100px;height:50px;z-index:1;visibility:hidden;background-color:white;"> 
</div>
</body>
</html>