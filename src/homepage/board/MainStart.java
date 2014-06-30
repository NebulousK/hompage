package homepage.board;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainStart implements ICommand {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		BoardDao Bdao = new BoardDao();
		Vector list = new Vector();
		list = Bdao.getBoardList(id);
		int totalrecord = list.size();
		System.out.println(list.size());
		req.setAttribute("boardlist", list);
		return "/newsfeed/main.jsp";
	}

}
