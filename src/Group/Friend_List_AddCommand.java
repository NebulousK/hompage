package Group;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Friend_List_AddCommand implements ICommand{

	@Override
	public Object proecssCommand(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		GroupDao dao=new GroupDao();
		
		String userid1=req.getParameter("userid1");
		String userid2=req.getParameter("userid2");
		dao.accept_Friend(userid1, userid2);
		
		return "/friendList/main.jsp";
	}

}
