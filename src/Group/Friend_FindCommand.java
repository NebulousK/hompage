package Group;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Friend_FindCommand implements ICommand{

	@Override
	public Object proecssCommand(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		GroupDao dao=new GroupDao();
		
		Vector list=null;
		String myid=req.getParameter("myid");
		String keyField=req.getParameter("keyField");
		String research=req.getParameter("research");
		
		if( myid==null || myid.equals("null")|| myid.isEmpty() ){
				
			list=dao.getResearchFriendList(keyField, research, null);
		}else{
			
			list=dao.getResearchFriendList(myid, "id", null);
		}
		//Session 
		
		req.setAttribute("research", research);
		req.setAttribute("keyField", keyField);
		req.setAttribute("area", list);
		return "/friendFind/main.jsp";
	}

}
