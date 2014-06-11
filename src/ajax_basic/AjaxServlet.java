package ajax_basic;
 
import homepage.DBConnectionMgr;
import homepage.someDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class AjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
     
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String command = request.getParameter("command");
        command = "idcheck";
        if(command.equals("idcheck")){
            idcheck(request,response);
        }
    }
     
    // ID 중복 검사 처리
    protected void idcheck(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    	Connection con = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	DBConnectionMgr pool = null;
    	ArrayList<String> ids = new ArrayList<String>();
    	try{
        	pool = DBConnectionMgr.getInstance();
			con = (Connection) pool.getConnection();
			
			String sql = "select id from member";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {	
				ids.add(rs.getString("id"));
			}
        	response.setContentType("text/html;charset=UTF-8");
        	response.setHeader("Cache-Control", "no-cache");
        	PrintWriter out = response.getWriter();
        	// id 중복 처리
        	String gotId = request.getParameter("id");
        	String result = null;
        	for(String id : ids){
        		if(id.equals(gotId)){
        			// 응답 메세지 1 : 이미 등록된 ID 입니다.
        			result = "<font color='red'>이미 등록된 ID 입니다.</font>";
        			break;
        		}else{
        			// 응답 메세지 2 : 사용할 수 있는 ID 입니다.
        			result = "<font color='green'>사용할 수 있는 ID 입니다.</font>";
        		}
        	}
        	out.println(result);
        }catch(Exception err){
        	System.out.println(err);
        }finally{
        	
        }
    }
}
