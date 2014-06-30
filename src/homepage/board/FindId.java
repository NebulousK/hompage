package homepage.board;

import homepage.someDto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mail.Gmail;

public class FindId extends HttpServlet implements ICommand {

	@Override
	public Object processCommand(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		BoardDao Bdao = new BoardDao();
		//get방식 일때 한글 인코딩
		String name = new String (req.getParameter("name").getBytes("8859_1"), "UTF-8");
		//String name = req.getParameter("name");
		String email = req.getParameter("email");
		someDto dto = Bdao.getId(name, email);
		String id = dto.getId();
		System.out.println(dto);
		//System.out.println(email);		
		System.out.println(dto.getId() != null);		
	
		if(id != null){
			Gmail gmail = new Gmail();
			//gmail.Gmail1(req, id);
			return "/homepage/findid.html";
		}
		else{
			return "/homepage/nomember.html";
		}
	
	}

}
