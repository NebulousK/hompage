package mvc;

import homepage.someDao;
import homepage.someDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import mail.Gmail;

public class memberControl extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		String path = req.getContextPath();
		String action = url.substring(path.length());
		String nextPage = "";
		resp.setContentType("text/html;charset=utf-8"); // 어떤 타입으로 출력할것인지 명시하였다.
		PrintWriter out=resp.getWriter(); //
		someDao dao = new someDao();
		someDto dto = new someDto();
		if(action.equals("/zip.me")){
			String dong = req.getParameter("dong");
			ArrayList<homepage.someDto> g = new ArrayList<homepage.someDto>(); 
			g = dao.zip(dong);
			req.setAttribute("g", g);
			nextPage = "/member/zipcode.jsp";
		}
		else if(action.equals("/join.me")){
			Gmail gmail = new Gmail();
			MultipartRequest multi = dao.member_join(req);
		 	gmail.Gmail(multi);
		 	nextPage = "/member/member_join2.jsp";
		}
		else if(action.equals("/join2.me")){
			
		}
		RequestDispatcher view =req.getRequestDispatcher(nextPage);//어느페이지인지 정해준다
		view.forward(req, resp);// req의 모든정보를얘가 던져주는곳  다음 페이지에서 request.getattribute("a")를 하면 마가나온다	
	}
	
	
}
