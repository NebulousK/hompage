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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

public class someControl extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		PrintWriter out=resp.getWriter(); //
		someDao dao = new someDao();
		someDto dto = new someDto();
		
		if(action.equals("/dash.some")){
			String sex;
			if(session.getAttribute("sex").equals("man")){
				sex = "woman";
			}else{
				sex = "man";
			}
			dto = dao.idealtype(Integer.parseInt((String)session.getAttribute("no")), sex);
		 	String check[] = dao.dashch(Integer.parseInt((String)session.getAttribute("no")), dto.getNo()).split(",");
		 	req.setAttribute("dto", dto);
		 	req.setAttribute("check", check);
		 	nextPage = "/someNsome/main2.jsp";
		}
		
		else if(action.equals("/mdash.some")){
			String sex;
			if(req.getParameter("sex").equals("man")){
				sex = "woman";
			}else{
				sex = "man";
			}
			String result = dao.midealtype(Integer.parseInt((String)req.getParameter("no")), sex);
			out.println(result.replace('\'','\"').trim());
			return;
		}
		
		else if(action.equals("/dashup.some")){
			String cmd = req.getParameter("action");
			dto.setUserID(Integer.parseInt((String) req.getParameter("userID")));
			dto.setItemID(Integer.parseInt((String) req.getParameter("itemID")));
			dto.setValue(Float.parseFloat((String) req.getParameter("value")));
			dto.setComent(req.getParameter("coment"));
			if(cmd.equals("dash")){
				dao.dash(dto);	
				out.println("<script>");
				out.println("alert('대쉬 하였습니다. 좋은 결과가 있길 바래요!!');");
				out.println("location.href = '/homepage/dash.some';");
				out.println("</script>");
				return;
			}	
			else if(action.equals("dashup")){
				dao.dashup(dto);
				out.println("<script>");
				out.println("alert('대쉬를 수정 하였습니다. 좋은 결과가 있길 바래요!!');");
				out.println("location.href = '/homepage/dash.some';");
				out.println("</script>");
				return;
			}
		}
		
		else if(action.equals("/mdashup.some")){
			dto.setUserID(Integer.parseInt((String) req.getParameter("userID")));
			dto.setItemID(Integer.parseInt((String) req.getParameter("itemID")));
			dto.setValue(Float.parseFloat((String) req.getParameter("value")));
			dto.setComent(req.getParameter("coment"));
			dao.mdash(dto);
			return;
		}
		
		else if(action.equals("/call.some")){
			Vector<someDto> g = dao.callme(Integer.parseInt((String)session.getAttribute("no")));
			req.setAttribute("g", g);
			nextPage = "/someNsome/main.jsp";
		}
		
		else if(action.equals("/mcall.some")){
			String g = dao.mcallme(Integer.parseInt((String)req.getParameter("no")));
			out.println(g.replace('\'','\"').trim());
			return;
		}
		
		else if(action.equals("/mcall_detail.some")){
			String g = dao.mcallme_detail(Integer.parseInt((String)req.getParameter("no")),req.getParameter("name"));
			out.println(g.replace('\'','\"').trim());
			return;
		}
		
		else if(action.equals("/callup.some")){
			String sex = (String) session.getAttribute("sex");
			int select = dao.callme(Integer.parseInt((String)req.getParameter("userID")), Integer.parseInt((String)req.getParameter("itemID")), Integer.parseInt((String)req.getParameter("state")), sex);
			if(select == 1){
				out.println("<script>");
				out.println("alert('좋은 인연이 시작되길 바래요!!');");
				out.println("location.href = '/homepage/main.jsp';");
				out.println("</script>");
				return;
			}else{
				out.println("<script>");
				out.println("alert('인연이 아니였나 보군요!');");
				out.println("location.href = '/homepage/someNsome/main.jsp';");
				out.println("</script>");
				return;
			}
		}
		
		else if(action.equals("/mcallup.some")){
			String sex = req.getParameter("sex");
			dao.callme(Integer.parseInt((String)req.getParameter("userID")), Integer.parseInt((String)req.getParameter("itemID")), Integer.parseInt((String)req.getParameter("state")), sex);
			return;
		}
		else if(action.equals("/luvstory.some")){
			ArrayList list= new ArrayList();
			list = dao.some_board_list(Integer.parseInt((String)session.getAttribute("no")));
			req.setAttribute("list", list);
			nextPage = "/someNsome/menu1/main.jsp";
		}
		

		else if(action.equals("/mlubstory.some")){
			int no = Integer.parseInt(req.getParameter("no"));
			int num = Integer.parseInt(req.getParameter("num"));
			String tempList = dao.msome_board_list(no, num);
			out.println(tempList);
			return;
		}
		
		else if(action.equals("/luvstoryinsert.some")){
			dto.setId(req.getParameter("id"));
			dto.setNo(Integer.parseInt((String) req.getParameter("no")));
			dto.setContent(req.getParameter("content"));
			dao.some_board(dto);
			nextPage = "/luvstory.some";
		}
			
		else if(action.equals("/renew.some")){
			ArrayList list= new ArrayList();
			list = dao.some_board_list(Integer.parseInt((String)session.getAttribute("no")));
			int i = Integer.parseInt((String)req.getParameter("num"));
			if(list.size() > i){
				dto = (someDto) list.get(i);
				out.print("<div id='start' name='start'><div class='_4-u2 mbm _5jmm _5pat _5v3q _5sq8 _5x16' id='u_ps_0_0_m'><div class='userContentWrapper _5pcr _3ccb'><div class='_4r_y'><div class='_6a uiPopover _5pbi _5puc _5v56' id='u_ps_0_0_n'></div></div><div class='clearfix _5x46'><div class='_3dp _29k'><div><div class='_6a'><div class='_6a _6b'><h5 class='_5pbw'><div class='fwn fcg'><table style='border: 0px;width: 554px'><tr><td style='border: 0px;width: 55px; padding: 0px;margin: 0px;'><a class='_5pb8 _5v9u _29h _303' href='/homepage/how.board?id="+ dto.getId() + "'><img class='_s0 _5xib _5sq7 _rw img' src='/homepage/profile/" + dto.getPhoto() + "' alt='' align='middle' /></a></td><td style='border: 0px;text-align: left;'><span class='fwb fcg'><a href='/homepage/how.board?id="+ dto.getId() + "' style='font-size: 14px;'>"+ dto.getId() + "</a></span><br/><div class='_5pcp' style='margin-top: 5px'><span>" + dto.getDay() + "</span></div></td><td style='border: 0px;text-align: right;'>");
				if(session.getAttribute("id").equals(dto.getId())){
				out.print("<div class='dropdown'><a class='dropdown-toggle' id='dLabel' role='button' data-toggle='dropdown' data-target='#' href='/page.html'><b class='caret'></b></a><ul class='dropdown-menu' role='menu' aria-labelledby='dLabel'><li style='text-align: center;font-size: 12px;line-height: 25px;'><span id='updateclick"+dto.getNo()+"' onclick='addup("+dto.getNo()+")'>수&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;정</span></li><mcjsjs><input type='hidden' id='divNoCheck"+dto.getNo()+"' value='"+dto.getNo()+"'/></mcjsjs><input type='hidden' id='boardId' value='"+dto.getId()+"' /><input type='hidden' id='connectId' value='"+session.getAttribute("id")+"' /><li style='text-align: center;font-size: 12px;line-height: 25px'><span onclick='Bdelete("+dto.getNo()+")'>삭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제</span></li></ul></div></td></tr></table></div></h5><div class='_5pcp'><span>"+dto.getDay()+"</span></div></div></div></div></div></div><div id='sujung"+dto.getNo()+"' name='sujung"+dto.getNo()+"'></div><div class='_5pbx userContent'><p>"+dto.getContent()+"</p></div><div><div></div></div><form action='proc.jsp' method='post' name='boardaction'><div class='clearfix'></div></form></div></div></div>");
				}
				return;
			}
			return;
		}
		
		else if(action.equals("/updatesomeboard.some")){
			dao.upsomeboard(Integer.parseInt((String)req.getParameter("num")), (String)req.getParameter("content"));
			nextPage = "/luvstory.some";
		}
		
		
		else if(action.equals("/delsomeboard.some")){
			dao.delsomeboard(Integer.parseInt((String)req.getParameter("no")));
			nextPage = "/luvstory.some";
		}
		
		else if(action.equals("/luvphoto.some")){
			ArrayList<someDto> g = dao.photo(Integer.parseInt((String)session.getAttribute("no")));
			req.setAttribute("g", g);
			nextPage = "/someNsome/menu2/photo.jsp";
		}
		
		
		else if(action.equals("/mluvphoto.some")){
			int no = Integer.parseInt(req.getParameter("no"));
			JSONArray tempList = dao.mphoto(no);
			//JSONObject = {} JSONArray=[]
		    out.println("{\"files\":" + tempList + "}");
			return;
		}
		
	
		RequestDispatcher view =req.getRequestDispatcher(nextPage);//어느페이지인지 정해준다
		view.forward(req, resp);// req의 모든정보를얘가 던져주는곳  다음 페이지에서 request.getattribute("a")를 하면 마가나온다	
	}
}



















