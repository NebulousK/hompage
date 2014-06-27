package Group;

import java.io.IOException;
import java.util.Vector;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Friend_FindAreaCommand implements ICommand{

	@Override
	public Object proecssCommand(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		GroupDao dao=new GroupDao();

		String area=req.getParameter("area");
		String acheck=req.getParameter("acheck");	
		Vector alist=dao.area_Print(area);
		//Session 
		 req.setAttribute("acheck", acheck);
		 req.setAttribute("area", alist);
		return "/friendFind/main.jsp";
	}

}
