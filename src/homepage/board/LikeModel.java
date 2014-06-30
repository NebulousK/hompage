package homepage.board;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LikeModel implements ICommand{

	@Override
	public Object processCommand(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

		BoardDto Bdto = new BoardDto();
		BoardDao Bdao = new BoardDao();
		Bdao.likeUpdate(Integer.parseInt(req.getParameter("no")), req.getParameter("id"));
		
		return "/board.action?command=MAINSTART";
	}
}
