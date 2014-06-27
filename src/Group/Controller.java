
package Group;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");   
		request.setCharacterEncoding("utf-8");
		System.out.println("test..");
		String nextPage=null;
		String cmd=request.getParameter("command");
		ICommand iCmd=null;
		//CommandFactory factory=CommandFactory.getInstance();
		//ICommand iCmd=factory.createCommand(cmd);
		
		if(cmd.equals("Friend_FindArea")){	
			iCmd=new Friend_FindAreaCommand();
		}
		else if(cmd.equals("Friend_Find")){
			iCmd=new Friend_FindCommand();	
		}
		else if(cmd.equals("Friend_List_add")){
			iCmd=new Friend_List_AddCommand();
		}
		else if(cmd.equals("Friend_List_delete")){	
			iCmd=new Friend_List_DeleteCommand();	
		}
		else if(cmd.equals("Friend_Request")){	
			iCmd=new friend_requestCommand();
		}
		
		nextPage=(String)iCmd.proecssCommand(request, response);
		RequestDispatcher view = request.getRequestDispatcher(nextPage);
		view.forward(request, response);
	}
}
