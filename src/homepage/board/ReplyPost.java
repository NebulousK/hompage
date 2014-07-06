package homepage.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplyPost implements ICommand{
/*	public String rPost(String content, int no, String id){
		ReplyDto Rdto = new ReplyDto();
		BoardDao Bdao = new BoardDao();
		Rdto.setContent(content);
		Rdto.setNo(no);
		Rdto.setId(id);
		Bdao.insertreply(Rdto);
		return "/newsfeed/main.jsp";
	}
*/
	@Override
	public Object processCommand(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		ReplyDto Rdto = new ReplyDto();
		BoardDao Bdao = new BoardDao();
		Rdto.setContent(req.getParameter("content"));
		Rdto.setNo(Integer.parseInt(req.getParameter("no")));
		Rdto.setId(req.getParameter("id"));
		Bdao.insertreply(Rdto);
		return "/main.board?action=main.board";
	}
}
