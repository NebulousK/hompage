package homepage.board;

import homepage.someDao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePassword extends HttpServlet implements ICommand {

	@Override
	public Object processCommand(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		someDao Sdao = new someDao();
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String email = req.getParameter("email");
		
		Sdao.updatePw(id, pw, email);
		return "index.html";
	}

}
