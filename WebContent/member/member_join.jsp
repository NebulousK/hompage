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

<script>
function email_write()
{var form = document.form1;var email = form.email2.value;
 if (email == "etc")
 {document.all("email2").style.display = "none";document.all("email3").style.display = "";form.email3.focus();}
 else
 {document.all("email2").style.display = "";}return;}

function FindZip(zip_kind) 
{window.open("zipcode.jsp?zip_kind="+zip_kind, "", "scrollbars=no,width=500,height=250");}

   $(document).ready(function(){
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
                reader.onload = function (e) { 
                //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                    $('#blah').attr('src', e.target.result);
                    //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
                    //(아래 코드에서 읽어들인 dataURL형식)
                }                    
                reader.readAsDataURL(input.files[0]);
                //File내용을 읽어 dataURL형식의 문자열로 저장
            }
        }//readURL()--

        //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
        $("#imgInp").change(function(){
          /*   alert(this.value); //선택한 이미지 경로 표시 */
            readURL(this);
        });
     });
</script>

</script>
</head>
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
				<section class="s-12 l-7">
					<div class="line">
						<div class="container" style="margin-left: 80px">
							<h1>회원가입</h1>
							<br /> <br />
							<form id="form1" name="form1" action="member_proc.jsp" method="post" enctype="multipart/form-data">
							<div class="row">
								<div class="span12">
									<div class="span6" align="center">
										<div class="customDiv2" align="center">
											<img id="blah" src="/homepage/images/noData.gif" style="max-width: 450px; max-height: 460px;" /> 
											<br/><br/> 
											<span id="fileInputForm" style="position:relative; float:left; width:100px; height:30px; overflow:hidden; cursor:pointer; background-image:url('../images/cc.png');">
											<input type="file" id="imgInp" name="imgInp" style='position：absolute; margin-left:-10px; width:62px; height:18px; filter:alpha(opacity=0); opacity:0; -moz-opacity:0; cursor:pointer;'>
											</span>
											</div>
									</div>

									<div class="span5" style="margin-right: 0;">
										<table align="center">
											<tr>
												<input type="hidden" name="action" id="action" value="join"/>
												<td style="padding-right: 70px">아이디</td>
												<td><input type="text" style="width: 150px" name="id" id="id" /></td>
											</tr>
											<tr>
												<td>이름</td>
												<td><input type="text" style="width: 150px" name="name" id="name" /></td>
											</tr>
											<tr>
												<td>비밀번호</td>
												<td><input type="password" style="width: 150px" name="password" id="password" /></td>
											</tr>
											<tr>
												<td>비밀번호 확인</td>
												<td><input type="password" style="width: 150px" name="password2" id="password2" /></td>
											</tr>
											<tr>
												<td>e-mail</td>
												<td id="email"><input type=text size=10 name="email1" style="width: 100px" > @
												<select name="email2" onChange="javascript:email_write()" style="width:130px;">
														<option value="" selected>:: 선택 ::</option>
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
												<input type=text size=20 name="email3" value="" style="width:130px;display:none;">
												</td>
											</tr>
											<tr>
												<td>전화번호</td>
												<td><input type="text" size="6" style="width: 45px" name="tel">
													- <input type="text" size="6" style="width: 45px" name="tel2">
													- <input type="text" size="6" style="width: 45px" name="tel3"></td>
											</tr>
											<tr>
												<td>주소</td>
												<td>
												  <input type=text size=3 name="zip1" style="width: 50px" readonly> - 
                              					  <input type=text size=3 name="zip2" style="width: 50px" readonly>
                              					  <a href="javascript:FindZip(0)"><img align="absmiddle" src="/homepage/images/wupeun_bunho.jpg" border="0"></a><br/>
                              					  <input type=text size=50 name="juso" style="width: 250px" maxlength="200" readonly>
												</td>
											</tr>
											<tr>
												<td>상세주소</td>
												<td><input type="text" style="width: 250px" name="addr" id="addr" /></td>
											</tr>
											<tr>
												<td>생년월일</td>
												<td><input type="text" size="6" style="width: 45px" name="year">년<input
													type="text" size="4" style="width: 45px" name="month">월<input
													type="text" size="4" style="width: 45px" name="day">일
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
								<div class="span12" align="center">
									<div class="span-cus">
										<input type="button" name="exam2" value="세부정보 입력" onclick="javascript:location.href='member_join2.jsp';" class="btn-custom" style="width: 150px; height: 35px" /> <br />
										<br />
										<p align="left">*세부정보 입력시에만 이성추천을 해드립니다.</p>
										<P align="left">*추후 수정 및 추가 가능 합니다.</P>
									</div>
									<div class="span-cus">
										<input type="submit" name="exam2" value="가입 완료" class="btn-custom" style="width: 150px; height: 35px" />
									</div>
									<div class="span-cus">
										<input type="button" name="exam2" value="취소" class="btn-custom" style="width: 150px; height: 35px" />
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