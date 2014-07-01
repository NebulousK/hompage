package mvc;

import homepage.someDao;
import homepage.someDto;
import homepage.board.BoardDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class adminControl extends HttpServlet{
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
		someDao dao = new someDao();
		someDto dto = new someDto();
		
		if(action.equals("/admin.admin")){
			String result = dao.admin(req.getParameter("adminid"), req.getParameter("adminpw"));
			if(result == "b"){
				//존재하지 않는 아이디
					out.println("<script>");
					out.println("alert('관리자가 아니신것 같은데?');");
					out.println("history.back();");
					out.println("</script>");
					return;
				}
				else if(result == "a"){
				//비번 틀림
					out.println("<script>");
					out.println("alert('비밀번호가 틀렸음');");
					out.println("history.back();");
					out.println("</script>");
					return;
				}
				else{
					session.setMaxInactiveInterval(60*10);
					session.setAttribute("admin", "admin");
					nextPage = "/member.admin";
				}
		}
		
		else if(action.equals("/member.admin")){
			ArrayList<someDto> g = dao.admember();
			req.setAttribute("g", g);
			nextPage = "/admin/member.jsp";
		}
		
		else if(action.equals("/memberdel.admin")){
			dao.admindel(Integer.parseInt((String)req.getParameter("no")));
			return;
		}
		
		else if(action.equals("/board.admin")){
			BoardDao g = new BoardDao();
			ArrayList<someDto> z = g.admingetboard();
			req.setAttribute("g", z);
			nextPage = "/admin/board.jsp";
		}
		
		else if(action.equals("/boarddel.admin")){
			BoardDao g = new BoardDao();
			g.boardDelete(Integer.parseInt((String)req.getParameter("no")));
			return;
		}
		
		else if(action.equals("/re.admin")){
			int i = Integer.parseInt((String)req.getParameter("num"));
			ArrayList<someDto> list = dao.admember();
			if(list.size() > i){
				dto = (someDto) list.get(i);
				out.print("<div id='start' name='start' style='width:100%;margin-left: 30px'><form id='form1' name='form1' method='post' target='dummy' action='/homepage/memberdel.admin'><div class='style1'>"+dto.getNo()+"<input type='hidden' name='no' id='no' value='"+dto.getNo()+"'/></div><div class='style2'>"+dto.getId()+"</div><div class='style1'>"+dto.getName()+"</div><div class='style1'>"+dto.getSex()+"</div><div class='style2'>"+dto.getBirthday()+"</div><div class='style3'>"+dto.getEmail()+"</div><div class='style4'>"+dto.getAddr()+"</div><div class='style2'>"+dto.getTel()+"</div><div class='style2'><img alt='' src='/homepage/profile/"+dto.getPhoto()+"' style='max-width:100px;height:100px;'></div><div class='style1'><span style='position:relative; float:center; width:45px; height:18px; overflow:hidden; cursor:pointer; background-image:url(\"/homepage/images/b_delete1.gif\");'><input type='button' onclick='del(this.form)' style='position：absolute; margin-left:-10px; width:50px; height:18px; filter:alpha(opacity=0); opacity:0; -moz-opacity:0; cursor:pointer;'></span></div></form></div>");
				return;
			}
			return;
		}
		
		else if(action.equals("/memberse.admin")){
			String option = req.getParameter("option");
			String text = req.getParameter("text1");
			if(option.equals("id")){
				dto = dao.membergetid(text);
			}else{
				dto = dao.membergetname(text);
			}
			req.setAttribute("g2", dto);
			nextPage = "/member.admin";
		}
		
		else if(action.equals("/boardse.admin")){
			String option = req.getParameter("option");
			String text = req.getParameter("text1");
			BoardDao g = new BoardDao();
			ArrayList<someDto> g2 = g.segetboard(option, text);
			req.setAttribute("g2", g2);
			nextPage = "/board.admin";
		}
		
		else if(action.equals("/boardre.admin")){
			BoardDao g = new BoardDao();
			int i = Integer.parseInt((String)req.getParameter("num"));
			ArrayList<someDto> list = g.admingetboard();
			if(list.size() > i){
				dto = (someDto) list.get(i);
				out.print("<div id='start' name='start' style='width:100%;margin-left: 30px;height: auto;float:left;'><form name='form1' method='post' target='dummy' action='/homepage/boarddel.admin'><div class='style5'>"+dto.getId()+"<input type='hidden' name='no' id='no' value='"+dto.getNo()+"'/></div><div class='style6'>"+dto.getContent()+"</div><div class='style5'>"+dto.getDay()+"</div><div class='style5'><span style='position:relative; float:center; width:45px; height:18px; overflow:hidden; cursor:pointer; background-image:url(\"/homepage/images/b_delete1.gif\");'><input type='button' onclick='del(this.form)' style='position：absolute; margin-left:-10px; width:50px; height:18px; filter:alpha(opacity=0); opacity:0; -moz-opacity:0; cursor:pointer;'></span></div></form></div>");
				return;
			}
			return;
		}
		
		RequestDispatcher view =req.getRequestDispatcher(nextPage);//어느페이지인지 정해준다
		view.forward(req, resp);// req의 모든정보를얘가 던져주는곳  다음 페이지에서 request.getattribute("a")를 하면 마가나온다	
	}
}
