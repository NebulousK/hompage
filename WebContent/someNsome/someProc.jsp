<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dao" class="homepage.someDao" />
<jsp:useBean id="dto" class="homepage.someDto" />
<jsp:setProperty property="*" name="dto"/>
<%
	String action = request.getParameter("action");
	if(action.equals("dash")){
		dao.dash(dto);
%>	
	<script>
		alert("대쉬 하였습니다. 좋은 결과가 있길 바래요!!");
		location.href = "/homepage/main.jsp";
	</script>
<%
	}
	else if(action.equals("dashup")){
		dao.dashup(dto);
%>
	<script>
		alert("대쉬를 수정 하였습니다. 좋은 결과가 있길 바래요!!");
		location.href = "/homepage/main.jsp";
	</script>
<%
	}
%>
