package homepage.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardUpdate implements ICommand{
	/*public String bUpdate(String content, int no){
		BoardDao Bdao = new BoardDao();
		BoardDto Bdto = new BoardDto();
		Bdto.setContent(content);
		Bdto.setNo(no);
		Bdao.boardUpdate(Bdto); 
		return "/newsfeed/main.jsp";
	}
*/
	@Override
	public Object processCommand(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		BoardDao Bdao = new BoardDao();
		BoardDto Bdto = new BoardDto();
		Bdto.setContent(req.getParameter("content"));
		Bdto.setNo(Integer.parseInt(req.getParameter("no")));
		Bdao.boardUpdate(3, null); 
		return "/board.action?command=MAINSTART";
	}	
	
}
