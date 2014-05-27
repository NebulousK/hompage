package homepage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class someDao {
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool = null;
	
	
	public void connect() {
		try {
			pool = DBConnectionMgr.getInstance();
			con = (Connection) pool.getConnection();
		} catch (Exception err) {
			System.out.println("에러");
			System.out.println(err);
		} 
	}
	
	public void discon(){
		pool.freeConnection(con,stmt,rs);
	}
	
}
