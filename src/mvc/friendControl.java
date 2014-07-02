package mvc;

import homepage.someDao;
import homepage.someDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Group.GroupDao;
import Group.GroupDto;
import Member.MemberDto;

public class friendControl extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		String path = req.getContextPath();
		String action = url.substring(path.length());
		String nextPage = "";
		resp.setContentType("text/html;charset=utf-8"); // 어떤 타입으로 출력할것인지 명시하였다.
		PrintWriter out=resp.getWriter(); //
		GroupDao dao = new GroupDao();
		GroupDto dto = new GroupDto();
		
		if(action.equals("/find.friend")){
			nextPage = "/friendFind/main.jsp";
		}
		
		else if(action.equals("/findadd.friend")){
			String myid=req.getParameter("myid");
			String id=req.getParameter("id");
			int no=Integer.parseInt(req.getParameter("no"));
			String bool="false";
			dao.Friend_request(myid,id,bool);
			nextPage = "/find.friend";
		}
		else if(action.equals("/findf.friend")){
			Vector list=null;
			String myid=(String)session.getAttribute("id");
			String keyField=req.getParameter("keyField");
			String research=req.getParameter("research");
			list=dao.getResearchFriendList(keyField, research, myid, Integer.parseInt((String)session.getAttribute("no")), null);
			req.setAttribute("keyField", keyField);
			req.setAttribute("research", research);
			req.setAttribute("dto", list);
			nextPage = "/friendFind/main.jsp";
		}
		
		else if(action.equals("/findff.friend")){
			Vector list=null;
			String myid=(String)session.getAttribute("id");
			String keyField=(String) req.getParameter("keyField2");
			String research=(String) req.getParameter("research2");
			list=dao.getResearchFriendList(keyField, research, myid, Integer.parseInt((String)session.getAttribute("no")), req.getParameter("area"));
			req.setAttribute("keyField", keyField);
			req.setAttribute("research", research);
			req.setAttribute("area", req.getParameter("area"));
			req.setAttribute("dto", list);
			nextPage = "/friendFind/main.jsp";
		}
		
		else if(action.equals("/finda.friend")){
			String area=req.getParameter("area");
			String acheck=req.getParameter("acheck");	
			String myid=(String)session.getAttribute("id");
			Vector alist=dao.area_Print(area, myid);
			req.setAttribute("acheck", acheck);
			req.setAttribute("dto", alist);
			nextPage = "/friendFind/main.jsp";
		}
		
		else if(action.equals("/List.friend")){
			String myid=(String) session.getAttribute("id");
			Vector rList=dao.Request_List(myid, "false");
			Vector list=dao.friend_List(myid);
			req.setAttribute("dto", rList);
			req.setAttribute("g", list);
			nextPage = "/friendList/main.jsp";
		}
		
		else if(action.equals("/ListAdd.friend")){
			String userid1=req.getParameter("userid1");
			String userid2=req.getParameter("userid2");
			dao.accept_Friend(userid1, userid2);	
			nextPage = "/List.friend";
		}
		
		else if(action.equals("/Listdel.friend")){
			String myid=req.getParameter("myid");
			String userid2=req.getParameter("userid2");
			dao.list_Friend_Delete(myid,userid2);
			nextPage = "/List.friend";
		}
		
		RequestDispatcher view =req.getRequestDispatcher(nextPage);//어느페이지인지 정해준다
		view.forward(req, resp);// req의 모든정보를얘가 던져주는곳  다음 페이지에서 request.getattribute("a")를 하면 마가나온다	
	}
}
