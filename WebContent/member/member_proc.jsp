<%@page import="java.io.File"%>
<%@page import="homepage.someDao"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="mail.Gmail"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="join" %>
<jsp:useBean id="dao" class="homepage.someDao"/>
<jsp:useBean id="dto" class="homepage.someDto"/>
<%
	request.setCharacterEncoding("UTF-8");
	String action = null;
%>
<jsp:setProperty property="*" name="dto"/>
<% 
	if(!request.getParameter("action").equals(null)){
		action = request.getParameter("action");
		System.out.print(action);
	}	
	if(action.equals("detail")){
		String[] str = request.getParameterValues("fashion");
		String[] str2 = request.getParameterValues("fashion2");
		String fashion="" , fashion2="";
		for(int i=0; i<str.length; i++){
		 	if(i!=0){
				fashion += ",";
		 	}
			fashion += str[i];
		}
		for(int i=0; i<str2.length; i++){
			if(i!=0){
				fashion2 += ",";
		 	}
			fashion2 += str2[i];
		}
		dto.setFashion(fashion);
		dto.setFashion2(fashion2);	
		dao.member_detail(dto);
%>
	<script>
		alert("저장 완료");
		location.href = "/homepage/index.html";
	</script>	
<% 
	}
	if(action.equals("login")){
		String id = request.getParameter("id");
		String state = dao.selectMemberState(id);
		String result =  dao.login(dto.getId(), dto.getPassword());
		if(result == "b"){
%>
	<script>
		alert("존재 하지 않는 아이디 입니다. 아이디를 확인하세요");
		location.href = "/homepage/index.html";
	</script>	
<% 
		}
		else if(result == "a"){
%>
	<script>
		alert("비밀 번호가 틀렸습니다. 비밀번호를 확인 하세요");
		location.href = "/homepage/index.html";
	</script>
<% 			
		}
		else if(!state.equals("true")){
%>
	<script>
			location.href = "/homepage/emailfalse.jsp";
	</script>	
<% 			
		}
		else{
			String a[] = result.split(",");
			session.setAttribute("id", a[0]);
			session.setAttribute("no", a[1]);
			session.setAttribute("sex", a[2]);
			session.setAttribute("photo", a[3]);
			session.setAttribute("name", a[4]);
%>
	<script>
		location.href = "/homepage/main.jsp";
	</script>	
<% 
		}
	}
	else if(action.equals("update")){
		dao.memberupdate(request, Integer.parseInt((String) session.getAttribute("no")));
%>
	<script>
		alert("수정 완료");
		location.href = "/homepage/itsme/main.jsp";
	</script>	
<%
	}
	//여기
	else if(action.equals("update2")){
		String[] str = request.getParameterValues("fashion");
		String[] str2 = request.getParameterValues("fashion2");
		String fashion="" , fashion2="";
		for(int i=0; i<str.length; i++){
		 	if(i!=0){
				fashion += ",";
		 	}
			fashion += str[i];
		}
		for(int i=0; i<str2.length; i++){
			if(i!=0){
				fashion2 += ",";
		 	}
			fashion2 += str2[i];
		}
		dto.setFashion(fashion);
		dto.setFashion2(fashion2);
		dao.memberupdate2(dto, Integer.parseInt((String) session.getAttribute("no")));
%>
	<script>
		alert("수정 완료");
		location.href = "/homepage/itsme/main.jsp";
	</script>	
<%
	}
	else if(action.equals("logout")){
		session.invalidate();
%>
	<script>
		alert("안녕히 가세요");
		 top.location.href = '/homepage/index.html'; 
	</script>	
<%
	}
	else if(action.equals("getout")){
		boolean result = dao.getout(Integer.parseInt((String)session.getAttribute("no")), (String)session.getAttribute("id"), dto.getPassword());
		if(result){
%>
	<script>
		location.href = "/homepage/member/getoutcomplete.jsp";
	</script>
<% 
	}else{
%>
		<script>
			alert('비밀번호가 올바르지 않다.');
			histroy.back();
		</script>
<% 		
		}
	}
	else if(action.equals("join")){
		/* dao.member_join(request);
		 *///System.out.println(request.getParameter("id"));
		Gmail gmail = new Gmail();
		MultipartRequest multi = dao.member_join(request);
	 	gmail.Gmail(multi); 
%>  
	<script>
		alert("가입 완료");
		location.href = "/homepage/member/member_join2.jsp";
	</script>
<% 
	}
	else{
%>
	<script>
		alert("시스템이 오류 났다!!");
		location.href = "/homepage/index.html";
	</script>
<%
	}
%>