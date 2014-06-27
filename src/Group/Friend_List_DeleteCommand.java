package Group;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Friend_List_DeleteCommand implements ICommand{

	@Override
	public Object proecssCommand(HttpServletRequest req, HttpServletResponse res)
			throws SecurityException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		GroupDao dao=new GroupDao();
		
		String myid=req.getParameter("myid");
		String userid2=req.getParameter("userid2");
	/*	<input type="hidden" name="myid" value="<%=myid%>"/>
	 	<input type="hidden" name="userid2" value="<%=dto.getId()%>"/>
	*/	
		
		dao.list_Friend_Delete(myid,userid2);
		
		return "/friendList/main.jsp";
	}

}
