package homepage.board;

import homepage.someDao;
import homepage.someDto;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mail.Gmail;

public class BoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String command = req.getParameter("command");
		String nextpage = "";
		
		//아이디찾기 메일 발송
		if(command.equals("SENDEMAIL")){
			someDao Sdao = new someDao();
			//get방식 일때 한글 인코딩
			String name = new String (req.getParameter("name").getBytes("8859_1"), "UTF-8");
			String email = req.getParameter("email");
			someDto dto = Sdao.getId(name, email);
			String id = dto.getId();
			
			int flag = 0;
			PrintWriter out = resp.getWriter();
			if(id != null){
				Gmail gmail = new Gmail();
				gmail.Gmail1(req, id);
				flag = 1;
			}
			else{
				flag = 0;
			}
			out.print(flag);				
			return;
		}
		
		//비번찾기 메일 발송
		else if(command.equals("SENDEMAIL1")){
			someDao Sdao = new someDao();
			//get방식 일때 한글 인코딩
			String id = new String (req.getParameter("id").getBytes("8859_1"), "UTF-8");
			String email = req.getParameter("email");
			someDto dto = Sdao.getPw(id, email);
			String name = dto.getName();
				
			int flag = 0;
			PrintWriter out = resp.getWriter();
			if(name != null){
				Gmail gmail = new Gmail();
				gmail.Gmail1(req, id);
				flag = 1;
			}
			else{
				flag = 0;
			}
			out.print(flag);				
			return;
		}
		
		
		CommandFactory factory = CommandFactory.getInstance();
		ICommand icmd = factory.createCommand(command);
		nextpage = (String) icmd.processCommand(req, resp);
		
		RequestDispatcher view = req.getRequestDispatcher(nextpage);
		
		System.out.println(nextpage);
		view.forward(req, resp);
	}
	
}
