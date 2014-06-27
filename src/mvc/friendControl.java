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
			String myid=(String)session.getAttribute("id");
			//내 아아디로 친구정보 가져오기(mydto)
			Vector my=(Vector)dao.getResearchFriendList(myid,"id");
			//검색해서 나온 결과(list)
			Vector list=(Vector)dao.getResearchFriendList(null, null);
			req.setAttribute("dto", list);
			nextPage = "/friendFind/main.jsp";
		}
		RequestDispatcher view =req.getRequestDispatcher(nextPage);//어느페이지인지 정해준다
		view.forward(req, resp);// req의 모든정보를얘가 던져주는곳  다음 페이지에서 request.getattribute("a")를 하면 마가나온다	
	}
}
