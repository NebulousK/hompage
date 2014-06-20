<%@page import="homepage.someDao"%>
<%@page import="Group.GroupDao"%>
<%@page import="Group.GroupDto"%>
<%@page import="Group.FriendDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<jsp:useBean id="dao2" class="homepage.someDao" />
<%!
	GroupDao dao=new GroupDao();
	boolean check=false;
%>
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script> -->
<script>
	//popup 창 띄우기(num,myid를 넘겨줌)
	function Pop_Create_Group(num,myid) {
		var popUrl = "/homepage/friend_group/Group_Create.jsp?num="+num+"&myid="+myid;	//팝업창에 출력될 페이지 URL
		var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
	} 
	
	function Pop_Friend_Add(group,myid){
		alert(group+myid);
		//transfor group and myid to Add Friend
		var popUrl = "/homepage/AddFriend.jsp?group="+group+"&myid="+myid;	//팝업창에 출력될 페이지 URL
		var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
	}
	function Pop_Friend_Delete(userid,myid,group){
		alert(userid+myid+group);
		//transfor group and myid to Delete Friend
		var popUrl = "/homepage/Delete_Friend.jsp?group="+group+"&myid="+myid+"&userid="+userid;	//팝업창에 출력될 페이지 URL
		var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
		
	}
	function Group_Delete(check){
		var check=check;
		if(check==true){	
		}
	}
	
	function fn_layer_popup(){ 
		var _x = event.clientX + document.body.scrollLeft; //마우스로 선택한곳의 x축(화면에서 좌측으로부터의 거리)를 얻는다. 
		var _y = event.clientY + document.body.scrollTop; //마우스로 선택한곳의 y축(화면에서 상단으로부터의 거리)를 얻는다. 
		var layer = document.getElementById("popup_layer"); 
		if(_x < 0) _x = 0; //마우스로 선택한 위치의 값이 -값이면 0으로 초기화. (화면은 0,0으로 시작한다.) 
		if(_y < 0) _y = 0; //마우스로 선택한 위치의 값이 -값이면 0으로 초기화. (화면은 0,0으로 시작한다.) 
		layer.style.left = _x+"px"; //레이어팝업의 좌측으로부터의 거리값을 마우스로 클릭한곳의 위치값으로 변경. 
		layer.style.top = _y+"px"; //레이어팝업의 상단으로부터의 거리값을 마우스로 클릭한곳의 위치값으로 변경. 
		layer.style.visibility="visible"; 
	} 
</script>
<%
	//회원번호와 아이디 가상으로 설정()
	int num=Integer.parseInt((String)session.getAttribute("no"));
	String myid=(String) session.getAttribute("id");
%>
<div class="aside-nav">
 <TABLE width="115" border="0" cellspacing="1" cellpadding="1" height="1">
  <TR>
    <TD align="center" valign="center" height="0">친구목록<hr/></TD>
  </TR>
  	<%
  		Vector fList=(Vector)dao.getFriends(myid);
  		for(int i=0;i<fList.size();i++){
  			FriendDto dto=(FriendDto)fList.get(i);	
  	%>
  			<TR> 
    			<TD align="center" valign="center" height="0"> 
    			<%if(dto.getUserid1().equals(myid)){%>
      			<b><P align="center"><%-- <a href="/homepage/itsme/main2.jsp?id=<%=dto.getUserid2()%>"><%=dto.getUserid2()%></a> --%>
      			<a href="#" onclick="fn_layer_popup()"><%=dto.getUserid2()%></a>
      			<%if(dao2.news(dto.getUserid2()) != 0){ 
      				int cnt = dao2.news(dto.getUserid2());
      			%>
      			<span class="count-number" style="color:red;background-color:pink"> <%=cnt %></span></P></b>
      			<%}}else{%>
      			<b><P align="center"><%-- <a href="/homepage/itsme/main2.jsp?id=<%=dto.getUserid1()%>"><%=dto.getUserid1()%></a> --%>
      			<a href="#" onclick="fn_layer_popup()"><%=dto.getUserid1()%></a>
      			<%if(dao2.news(dto.getUserid1()) != 0){ 
      				int cnt = dao2.news(dto.getUserid1());
      			%>
      			<span class="count-number" style="color:red;background-color:pink">  <%=cnt %></span></P></b>
      			<%}} %>
    			</TD>
  		  	</TR>
  	<% 
  		}
  	%>
  <TR>
   	<TD align="center"> 
  	<!-- 	<b>구룹 목록</b>  
     -->	<hr/>
    	<%
    		Vector groupList=dao.getGruop(myid);
    		for(int i=0;i<groupList.size();i++){
    			GroupDto dto=(GroupDto)groupList.get(i);
    	%>	
    		<a href="javascript:Pop_Show_Friend()"><font><%=dto.getGroup()%></font></a>&nbsp;<br/>
    		<!-- 친구 뿌려주기 -->
    		<font size="1"><a href="javascript:Pop_Friend_Add('<%=dto.getGroup()%>','<%=dto.getMyid()%>')">친구추가</a></font>&nbsp;<br/>
    		<font size="1"><a href="Delete_Group/Delete_Group.jsp?groupNo=<%=dto.getNum()%>&myid=<%=dto.getMyid()%>&group=<%=dto.getGroup()%>">구룹삭제</a></font>
    		<br/>
    	<%				
    		}
    	%>
    </TD>
  </TR>
 
</TABLE>
</div>
<div id="popup_layer" style="position:absolute;border:double;top:0px;left:0px;width:100px;height:100px;z-index:1;visibility:hidden;background-color:white;"> 
<a href="/homepage/itsme/main2.jsp"><img src="/homepage/images/messagesend.png"></a>
<a href="/homepage/itsme/main2.jsp"><img src="/homepage/images/viewmember.png"></a>
</div>



