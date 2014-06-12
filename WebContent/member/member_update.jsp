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
<link href="/homepage/css/bootstrap2.css" rel="stylesheet" />
<link href="/homepage/css/1.css" rel="stylesheet" />
<script src="/homepage/js/jquery-2.1.1.min.js"></script>
<script src="/homepage/js/bootstrap.js"></script> 
<script src="/homepage/js/memberjoin.js"></script>
<script src="/homepage/js/member_join.js"></script>
<jsp:useBean id="dao" class="homepage.someDao"/>
<jsp:useBean id="dto" class="homepage.someDto"/>
<%
	dto = dao.memberget(Integer.parseInt((String)session.getAttribute("no")));
	String email[] = dto.getEmail().split("@");
	int first = dto.getAddr().indexOf(" ");
	int last = dto.getAddr().lastIndexOf("  ");
	String zip1,zip2,juso,addr = "";
	String tzip[] = dto.getAddr().substring(0, dto.getAddr().indexOf(" ")).split("-");
	zip1 = tzip[0];
	zip2 = tzip[1];
	String taddr = dto.getAddr().substring(dto.getAddr().indexOf(" ")+1);
	String tjuso[] = taddr.split(" ");
	juso = tjuso[0] + " " + tjuso[1] + " " + tjuso[2];
	for(int i=3; i<tjuso.length; i++){
		addr += tjuso[i];
	}
%>
<script>
	function name() {
		var check = "<%= dto.getSex()%>";
		if(check == "man"){
			document.form1.sex[0].checked = true;
		}else{
			document.form1.sex[1].checked = true;
		}
	}
</script>
</head>
<body class="size-1140" onload="name()">
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
            <section class="s-12 l-7">
               <div class="line">
                  <div class="container" style="margin-left: 80px">
                     <h1>회원 정보 수정</h1>
                     <br /> <br />
                     <form id="form1" name="form1" action="member_proc.jsp?action=update" method="post" enctype="multipart/form-data">
                     <input type="hidden" style="width: 150px" maxlength="12" name="id" id="id" value="<%=dto.getId()%>"/>
                     <div class="row">
                        <div class="span12">
                           <div class="span6" align="center">
                              <div class="customDiv2" align="center">
                                 <img id="blah" src="/homepage/profile/<%=dto.getPhoto() %>" style="max-width: 450px; max-height: 460px;" /> 
                                 <br/><br/> 
                                 <span id="fileInputForm" style="position:relative; float:left; width:100px; height:30px; overflow:hidden; cursor:pointer; background-image:url('../images/cc.png');">
                                 <input type="file" id="imgInp" name="imgInp" style='position：absolute; margin-left:-10px; width:62px; height:18px; filter:alpha(opacity=0); opacity:0; -moz-opacity:0; cursor:pointer;'>
                                 <br/><span id="imgs"></span>
                                 </span>
                                 </div>
                           </div>

                           <div class="span5" style="margin-right: 0;">
                              <table align="center">
                                 <tr>
                                    <td>이름</td>
                                    <td><input type="text" style="width: 150px" name="name" id="name" value="<%=dto.getName()%>"/><br/><span id="nas"></span></td>
                                 </tr>
                                 <tr>
                                    <td>비밀번호</td>
                                    <td><input type="password" style="width: 150px" name="password" id="password" /><br/><span id="pss"></span></td>
                                 </tr>
                                 <tr>
                                    <td>비밀번호 확인</td>
                                    <td><input type="password" style="width: 150px" name="password2" id="password2" /><br/><span id="pss2"></span></td>
                                 </tr>
                                 <tr>
                                    <td>e-mail</td>
                                    <td id="email"><input type=text size=10 id="email1" name="email1" style="width: 100px" onblur="emailcheck()" value="<%=email[0] %>"> @
                                    <select name="email2" id="email2" onChange="javascript:email_write()" style="width:130px;" onblur="emailcheck()">
                                          <option value="a">:: 선택 ::</option>
                                          <option value="chol.com">chol.com</option>
                                          <option value="dreamwiz.com">dreamwiz.com</option>
                                          <option value="empal.com">empal.com</option>
                                          <option value="hanmir.com">hanmir.com</option>
                                          <option value="hanafos.com">hanafos.com</option>
                                          <option value="hotmail.com">hotmail.com</option>
                                          <option value="lycos.co.kr">lycos.co.kr</option>
                                          <option value="nate.com">nate.com</option>
                                          <option value="naver.com">naver.com</option>
                                          <option value="daum.net">daum.net</option>
                                          <option value="hanmail.net">hanmail.net</option>
                                          <option value="gmail.com">gmail.com</option>
                                          <option value="paran.com">paran.com</option>
                                          <option value="yahoo.co.kr">yahoo.co.kr</option>
                                          <option value="etc">직접입력
                                    </select>
                                    <script language="javascript">document.form1.email2.value=<%=email[1]%>;</script>
                                    <input type=text size=20 name="email3" id="email3" value="" style="width:130px;display:none;" onblur="emailcheck()"><br/>
                                    <span id="ems"></span>
                                    </td>
                                 </tr>
                                 <tr>
                                 <%
                                 	String tel[] = dto.getTel().split("-");
                                 %>
                                    <td>전화번호</td>
                                    <td><input type="text" size="4" maxlength="4" style="width: 45px" name="tel" value="<%=tel[0]%>">
                                       - <input type="text" size="4" maxlength="4" style="width: 45px" name="tel2" value="<%=tel[1]%>">
                                       - <input type="text" size="4" maxlength="4" style="width: 45px" name="tel3" value="<%=tel[2]%>"><br/><span id="tels"></span></td>
                                 </tr>
                                 <tr>
                                    <td>주소</td>
                                    <td>
                                      <input type=text size=3 name="zip1" style="width: 50px" value="<%=zip1 %>" readonly> - 
                                               <input type=text size=3 name="zip2" style="width: 50px" value="<%=zip2 %>" readonly>
                                               <a href="javascript:FindZip(0)"><img align="absmiddle" src="/homepage/images/wupeun_bunho.jpg" border="0"></a><br/>
                                               <input type=text size=50 name="juso" style="width: 250px" maxlength="200" value="<%=juso %>" readonly>
                                    </td>
                                 </tr>
                                 <tr>
                                    <td>상세주소</td>
                                    <td><input type="text" style="width: 250px" name="addr" id="addr" value="<%=addr %>" readonly="readonly"/><br/><span id="ads"></span></td>
                                 </tr>
                                 <tr>
                                 <% String birth[] = dto.getBirthday().split("-"); %>
                                    <td>생년월일</td>
                                    <td><input type="text" size="5" maxlength="4" style="width: 45px" name="year" value="<%=birth[0]%>">년
                                    <input type="text" size="4" maxlength="2" style="width: 45px" name="month" value="<%=birth[1]%>">월
                                    <input type="text" size="4" maxlength="2" style="width: 45px" name="day" value="<%=birth[2]%>">일<br/><span id="bus"></span>
                                 </tr>
                                 <tr>
                                    <td>성별</td>
                                    <td>
                                          <input type="radio" name="sex" style="width: 45px" value="man">남성
                                          <input type="radio" name="sex" style="width: 45px" value="woman">여성
                                    </td>
                                 </tr>
                              </table>
                           </div>
                        </div>
                     </div>
                  </div>
                  <br /> <br />
                  <div class="container">
                     <div class="row">
                        <div class="span12" align="center" >
                           <!-- <div class="span-cus">
                              <input type="button" name="exam2" value="세부정보 입력" onclick="javascript:location.href='member_join2.jsp';" class="btn-custom" style="width: 150px; height: 35px" /> <br />
                              <br />
                              <p align="left">*세부정보 입력시에만 이성추천을 해드립니다.</p>
                              <P align="left">*추후 수정 및 추가 가능 합니다.</P>
                           </div> -->
                           <div class="span-cus3"></div>
                           
                           <div class="span-cus">                           
                              <input type="button" name="exam2" value="수정" class="btn-custom" style="width: 150px; height: 35px" onclick="javascript:Id()" />
                           <!-- <a href="javascript:Id()">가입 완료</a> -->                              
                           </div>                           
                           <div class="span-cus">
                              <input type="button" name="exam2" value="취소" class="btn-custom" style="width: 150px; height: 35px" onclick="history.back()" />
                           </div>
                        </div>
                     </div>
                  </div>
                  </form>
               </div>
            </section>
            <!-- ASIDE NAV 2 -->
         </div>
      </div>
   </div>
   <!-- FOOTER -->
   <footer class="box">
      <jsp:include page="/footer.html" />
   </footer>

</body>
</html>