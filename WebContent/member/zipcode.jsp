<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>회원 가입창</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:useBean id="dao" class="homepage.someDao"/>
<jsp:useBean id="dto" class="homepage.someDto"/> 
<% 
	request.setCharacterEncoding("UTF-8");
	String zip_kind = null;
	if(request.getParameter("zip_kind") != null || request.getParameter("zip_kind") != ""){
	zip_kind = request.getParameter("zip_kind"); 
	}	
%>
<script language="javascript">
	function SearchZip() {
		if (!form.dong.value) {
			alert("검색하실 동명을 입력해 주십시오.");
			form.dong.focus();
			return;
		}
		form.submit();
	}
	function SendZip(zip_kind) {
		if (form1.jusor.value == "") {
			alert("나머지 주소를 입력하여 주십시오.");
			form1.jusor.focus();
			return;
		}
		var str, zip1, zip2, juso;
		str = form1.post_no.value;
		str = str.split("^^");
		zip1 = str[0];
		zip2 = str[1];
		juso = str[2] + " " + form1.jusor.value;

		if (zip_kind == 1) {
			opener.form1.o_zip1.value = zip1;
			opener.form1.o_zip2.value = zip2;
			opener.form1.o_juso.value = juso;
		} else if (zip_kind == 2) {
			opener.form1.r_zip1.value = zip1;
			opener.form1.r_zip2.value = zip2;
			opener.form1.r_juso.value = juso;
		} else {
			opener.form1.zip1.value = zip1;
			opener.form1.zip2.value = zip2;
			opener.form1.juso.value = juso;
		}
		self.close();
	}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<script language="javascript">
		window.resizeTo(500, 340);
	</script>
	<table width="500" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><p>
								<img src="/homepage/images/zipcode_title.gif" border="0">
							</p></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<form name="form" method="post" action="zipcode.jsp">
					<table width="385" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<input type="hidden" name="zip_kind" value="<%=zip_kind%>">
						<tr>
							<td width="14">&nbsp;</td>
							<td width="355" align="center">
								<table width="86%" border="0" cellspacing="5" cellpadding="0">
									<tr height="20">
										<td colspan="2" valign="middle" width="305">
										<spanstyle="font-size: 9pt;">
										<font color="#666666"><b>검색할동명을 입력해 주세요�</b></font></span></td>
									</tr>
									<tr>
										<td width="161"><input name="dong" type="text" size="25" maxlength="20" value="" style="border: 1 solid #E5E5E5; font-size: 9pt"></td>
										<td width="134">
										<a href="javascript:SearchZip()">
										<img src="/homepage/images/b_search.gif" border="0"></a></td>
									</tr>
								</table>
							</td>
							<td width="16">&nbsp;</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>

	<table width="500" border="0" cellpadding="0" cellspacing="0">
		<form name="form1">
			<tr>
				<td align="center"><select style="BACKGROUND-COLOR: #E3E7EB" border="0" name="post_no" style="width:440;font-size:9pt">
			 	<%
					String dong = null; 
					if(request.getParameter("dong") != null){
						dong = request.getParameter("dong");
					}
					if(dong != null){
					 	ArrayList<homepage.someDto> g = new ArrayList<homepage.someDto>(); 
					 	g = dao.zip(dong);
					 	for(int i=0; i<g.size(); i++){
							dto = g.get(i);
				%>
					<option 
					value="<%=dto.getZip() %>^^<%=dto.getZip2() %>^^<%=dto.getJuso1()%> <%=dto.getJuso2()%> <%=dto.getJuso3()%> <%=dto.getJuso4()%>"><%=dto.getZip() %>-
							<%=dto.getZip2() %>
							<%=dto.getJuso1()%>
							<%=dto.getJuso2()%>
							<%=dto.getJuso3()%>
							<%=dto.getJuso4()%>
							</option>	
				<% 
					 	}
					}
				%> 
				</select></td>
			</tr>
			<tr>
				<td align="center"><input type="text" name="jusor" size="50"maxlength="50" STYLE="width: 440; border: 1 solid #E5E5E5"><br>
					<b><font color="#666666"><span style="font-size: 9pt;">나머지 주소를 입력해 주세요</span></font></b></td>
			</tr>
			<!-- 회원가입인 경우 : SendZip(0), 주문지인 경우 : SendZip(1), 배송지인 경우 : SendZip(2) -->
			<tr height="55">
				<td align="center"><a href="javascript:SendZip(<%=zip_kind%>)"><img src="/homepage/images/b_ok1.gif" border="0"></a></td>
			</tr>
		</form>
	</table>
</body>
</html>