package mvc;

import homepage.someDao;
import homepage.someDto;

import java.io.IOException;
import java.io.PrintWriter;

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
			
		}
	}
}
