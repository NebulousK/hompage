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
	else if(action.equals("callme")){
		String sex = (String) session.getAttribute("sex");
		int select = dao.callme(dto.getUserID(), dto.getItemID(), dto.getState(), sex);
		if(select == 1){
%>
	<script>
		alert("좋은 인연이 시작되길 바래요!!");
		location.href = "/homepage/main.jsp";
	</script>
<%
		}else{
%>
	<script>
		alert("인연이 아니였나 보군요!");
		location.href = "/homepage/someNsome/main.jsp";
	</script>		
<% 			
		}
	}
%>
