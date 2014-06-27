package Group;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class friend_requestCommand implements ICommand
{

	@Override
	public Object proecssCommand(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, IOException {
		// TODO Auto-generated method stub
		res.setCharacterEncoding("utf-8");   
		req.setCharacterEncoding("utf-8");
		String myid=req.getParameter("myid");
		String id=req.getParameter("id");
		int no=Integer.parseInt(req.getParameter("no"));
		String bool="false";
		GroupDao dao=new GroupDao();
		dao.Friend_request(myid,id,bool);
		//res.sendRedirect("/friendFind/main.jsp");
		
		return "/friendFind/main.jsp";
	}

}
