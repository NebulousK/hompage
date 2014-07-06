package homepage.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnlikeModel implements ICommand{
/*	public String unlike(int no, String id){
		BoardDao Bdao = new BoardDao();
		Bdao.likeCancel(no, id);
		return "/newsfeed/main.jsp";
	}
*/
	@Override
	public Object processCommand(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		BoardDao Bdao = new BoardDao();
		Bdao.likeCancel(Integer.parseInt(req.getParameter("no")), req.getParameter("id"));
		return "/main.board?action=main.board";
	}	
}
