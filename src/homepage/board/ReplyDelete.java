package homepage.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplyDelete implements ICommand{
/*	public String rDelete(int no){
		BoardDao Bdao = new BoardDao();
		Bdao.replyDelete(no);
		return "/newsfeed/main.jsp";
		
	}
*/
	@Override
	public Object processCommand(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		BoardDao Bdao = new BoardDao();
		System.out.println(Integer.parseInt(req.getParameter("no")));
		Bdao.replyDelete(Integer.parseInt(req.getParameter("no")));
		return "/main.board?action=main.board";
	}
}
