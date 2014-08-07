package homepage.board;

import homepage.DBConnectionMgr;
import homepage.someDao;
import homepage.someDto;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardDao {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DataSource ds;
	private DBConnectionMgr pool = null;
	private MultipartRequest multi;
	private String path;
	private int max;
	private String encType;
	
	public BoardDao(){
		try {
			pool = DBConnectionMgr.getInstance();
			con = (Connection) pool.getConnection();
			/* System.out.println("연결");*/
		} catch (Exception err) {
			System.out.println("에러");
			System.out.println(err);
		}
	}

	public void freeCon(){
		pool.freeConnection(con, stmt, rs);
	}
	
	//글쓰기
	public void insertBoard(BoardDto Bdto){
		try {
			String sql = "insert into board (id, content, day) values(?, ?, now())";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Bdto.getId());
			stmt.setString(2, Bdto.getContent());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertBoard() : " + e);
		}
		finally{
			freeCon();
		}
	}
	
	public ArrayList<String> insertPush(String id){
		ArrayList<String> v = new ArrayList<String>();
		String sql2;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		try {
			String sql = "select DISTINCT a.id from board a  INNER JOIN member b ON a.id = b.id where a.id IN (select userid2 from freind where (userid1=? or userid2=?) and  friends = 'true') or a.id IN (select userid1 from freind where (userid1=? or userid2=?) and  friends = 'true') or a.id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, id);
			stmt.setString(3, id);
			stmt.setString(4, id);
			stmt.setString(5, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				if(!rs.getString("a.id").equals(id)){
					sql2 = "select regID from push where ID = ?";
					stmt2 = con.prepareStatement(sql2);
					stmt2.setString(1, rs.getString("a.id"));
					rs2 = stmt2.executeQuery();
					if(rs2.next()){
						v.add(rs2.getString("regID"));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("push : " + e);
		} 
		finally{
			freeCon();
			try {
				if(stmt2 != null){stmt2.close();}
				if(rs2 !=null){rs2.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return v;
	}
	
	//글목록 불러오기
	public Vector<someDto> getBoardList(String id){
		Vector<someDto> v = new Vector<someDto>();
		try {
			String sql ="select a.no, a.id, a.content, a.day, a.hit, a.like, b.photo from board a  INNER JOIN member b ON a.id = b.id where a.id IN (select userid2 from freind where (userid1=? or userid2=?) and  friends = 'true') or a.id IN (select userid1 from freind where (userid1=? or userid2=?) and  friends = 'true') or a.id = ? order by no desc";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, id);
			stmt.setString(3, id);
			stmt.setString(4, id);
			stmt.setString(5, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				someDto Bdto = new someDto();
				Bdto.setNo(rs.getInt(1));
				Bdto.setId(rs.getString(2));
				Bdto.setContent(rs.getString(3));
				Bdto.setDay(rs.getString(4));
				Bdto.setHit(rs.getInt(5));
				Bdto.setLike(rs.getInt(6));
				Bdto.setPhoto(rs.getString("b.photo"));
				v.add(Bdto);
			}
		} catch (SQLException e) {
			System.out.println("getBoardList() : " + e);
		}
		finally{
			freeCon();
		}
		return v;
	}
	
	//글목록 불러오기
		public Vector<someDto> getBoardList2(String id){
			Vector<someDto> v = new Vector<someDto>();
			try {
				String sql = "SELECT a.no, a.id, a.content, a.day, a.hit, a.like, b.photo FROM board a INNER JOIN member b ON a.id = b.id where a.id = ? order by a.no desc;";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				rs = stmt.executeQuery();
			/*	System.out.println(stmt);*/
				while(rs.next()){
					someDto Bdto = new someDto();
					Bdto.setNo(rs.getInt(1));
					Bdto.setId(rs.getString(2));
					Bdto.setContent(rs.getString(3));
					Bdto.setDay(rs.getString(4));
					Bdto.setHit(rs.getInt(5));
					Bdto.setLike(rs.getInt(6));
					Bdto.setPhoto(rs.getString("b.photo"));
					v.add(Bdto);
				}
			} catch (SQLException e) {
				System.out.println("getBoardList() : " + e);
			}
			finally{
				freeCon();
			}
			return v;
		}
		
		public String mboardlist(String id, int num) {
			String sql;
			ArrayList<someDto> g = new ArrayList<someDto>();
			ArrayList likeId = new ArrayList();
			String returnStr="";
			String boardList = "'board_list':''";
			String err2 = "\"err\":\"\"";
			JSONArray result = new JSONArray();
			try {
				sql ="select a.no, a.id, a.content, a.day, a.hit, a.like, b.photo from board a  INNER JOIN member b ON a.id = b.id where a.id IN (select userid2 from freind where (userid1=? or userid2=?) and  friends = 'true') or a.id IN (select userid1 from freind where (userid1=? or userid2=?) and  friends = 'true') or a.id = ? order by no desc";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.setString(2, id);
				stmt.setString(3, id);
				stmt.setString(4, id);
				stmt.setString(5, id);
				rs = stmt.executeQuery();
				while(rs.next()){
					someDto dto = new someDto();
					dto.setId(rs.getString("id"));
					dto.setNo(rs.getInt("no"));
					dto.setContent(rs.getString("content"));
					dto.setDay(rs.getString("day"));
					dto.setPhoto(rs.getString("photo"));
					dto.setLike(rs.getInt("like"));
					g.add(dto);
				}
				if(num+5 < g.size()){
					for(int j=num; j < num + 5; j++){
						someDto rs = g.get(j);
						Vector vlike = board_likeSelect(rs.getNo());
						int flag = 0;//1이면 좋아요 누른 상태 0이면 좋아요 안누른 상태
						for(int i = 0; i <vlike.size(); i++){							
							if(id.equals(vlike.get(i))){
								flag = 1;
								break;
							}
							else{
								flag = 0;
							}
						}
						JSONObject obj = new JSONObject();
						obj.put("pic","http://54.178.192.82/homepage/profile/" + rs.getPhoto());
						obj.put("connectId", id);
					    obj.put("name",rs.getId());
					    obj.put("date",rs.getDay());
					    obj.put("no",rs.getNo());
					    obj.put("like",rs.getLike());
					    obj.put("flag",flag);
					    obj.put("content", rs.getContent().replace("\"", "$%^"));
					    result.add(obj);
					}
				}else{
					for(int j=num; j < g.size(); j++){
						someDto rs = g.get(j);
						
						Vector vlike = board_likeSelect(rs.getNo());
						int flag = 0;//1이면 좋아요 누른 상태 0이면 좋아요 안누른 상태
						for(int i = 0; i <vlike.size(); i++){							
							if(id.equals(vlike.get(i))){
								flag = 1;
							}
						}
						JSONObject obj = new JSONObject();
						obj.put("pic","http://54.178.192.82/homepage/profile/" + rs.getPhoto());
						obj.put("connectId", id);
					    obj.put("name",rs.getId());
					    obj.put("date",rs.getDay());
					    obj.put("no",rs.getNo());
					    obj.put("like",rs.getLike());
					    obj.put("flag",flag);
					    obj.put("content", rs.getContent().replace("\"", "$%^"));
					    result.add(obj);
					}
				}
				boardList = "\"someboard_list\":" + result.toString() ;
			} catch (Exception err) {
				System.out.println(err);
			} finally {
				freeCon();
			}
			return returnStr = "{\"data\":{" + boardList + ","+ err2 + "}}";
		}
	
	//like 증가
	public void likeUpdate(int no, String id){
		try {
		//System.out.println(no);
			String sql = "update board set `like` = `like`+1 where no=?";
		
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
			
			board_likeInsert(no, id);
		} catch (SQLException e) {
			System.out.println("likeUpdate() : " + e);
		}
		finally{
			freeCon();
		}
	}
	
	//board_like insert
	public void board_likeInsert(int no, String id){
		try {
			String sql = "insert into board_like (board_no, id) values(?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setString(2, id);
			stmt.executeUpdate();
					
		} catch (SQLException e) {
			System.out.println("board_likeInsert(): " + e);
		}
		finally{
			freeCon();
		}
	}
	
	//like 취소
	public void likeCancel(int no, String id){
		try {
			//System.out.println(no);
			String sql = "update board set `like` = `like`-1 where no=?";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
			
			board_likeCancel(no, id);
		} catch (SQLException e) {
			System.out.println("likeUpdate() : " + e);
		}
		finally{
			freeCon();
		}
	}

	//board_like cancel
	public void board_likeCancel(int no, String id){
		try {
			String sql = "delete from board_like where board_no=? and id=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setString(2, id);
			stmt.executeUpdate();
					
		} catch (SQLException e) {
			System.out.println("board_likeInsert(): " + e);
		}
		finally{
			freeCon();
		}
	}

	//like 여부 확인 벡터 리턴
	public Vector board_likeSelect(int no){
		Vector vlike = new Vector();
		try {
			String sql = "select * from board_like where board_no=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while(rs.next()){
				vlike.add(rs.getString("id"));
			}	
		} catch (SQLException e) {
			System.out.println("board_likeSelect() : " + e);
		}
		finally{
			freeCon();
		}
		return vlike;
	}
	
	//글 삭제
	public void boardDelete(int no){
		try {
			String sql = "delete from board where no=?";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
			
			board_likeDelete(no);
			
			replyDelete1(no);
			
		} catch (SQLException e) {
			System.out.println("boardDelete() : " + e);
		}
	}
	
	//board_like 삭제
	public void board_likeDelete(int no){
		try {
			String sql = "delete from board_like where board_no=?";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("boardDelete() : " + e);
		}
	}
	//글 수정
	public void boardUpdate(int no, String content){
		try {
			String sql = "update board set content = ?, day = now() where no=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, content);
			stmt.setInt(2, no);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("boardUpdate() : " + e);
		}
		finally{
			freeCon();
		}
	}
	//댓글 저장
	public void insertreply(ReplyDto Rdto){
		try {
			String sql = "insert into borad_ripple (parent, id, content, `regist_day`) values(?, ?, ?,now())";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Rdto.getNo());
			stmt.setString(2, Rdto.getId());
			stmt.setString(3, Rdto.getContent());
			stmt.executeUpdate();
			/*System.out.println(stmt);*/
			//System.out.println(Bdto.getContent());
		} catch (SQLException e) {
			System.out.println("insertBoard() : " + e);
		}
		finally{
			freeCon();
		}
		
	}
	//댓글 벡터 불러오기
	public Vector getReplyList(int no){
		Vector v = new Vector();
		//System.out.println(no);
		
		try {
/*			String sql = "select * from board where id=? order by no desc";*/
			String sql = "select a.no, a.parent, a.id, a.content, a.regist_day , b.photo FROM `borad_ripple` a INNER JOIN member b ON a.id = b.id  where a.parent = ? order by a.no desc";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				ReplyDto Rdto = new ReplyDto();
				Rdto.setNo(rs.getInt(1));
				Rdto.setParent(rs.getInt(2));
				Rdto.setId(rs.getString(3));
				Rdto.setContent(rs.getString(4));
				Rdto.setRegist_day(rs.getString(5));
				Rdto.setPhoto(rs.getString(6));
				v.add(Rdto);
			}
			
		} catch (SQLException e) {
			System.out.println("getBoardList() : " + e);
		}
		finally{
			freeCon();
		}
		return v;
	}
	//댓글 삭제
	public void replyDelete(int Rno){
		try {
			String sql = "delete from borad_ripple where no=?";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, Rno);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("boardDelete() : " + e);
		}
	}
	//글 삭제시 댓글 삭제
	public void replyDelete1(int no){
		try {
			String sql = "delete from borad_ripple where parent=?";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("boardDelete() : " + e);
		}
	}
	
	
	
	  public void setPath(HttpServletRequest servlet, String path) {
	      this.path = servlet.getServletContext().getRealPath(path);
	   }

	   public void setMax(int max) {
	      this.max = max;
	   }

	   public void setEncType(String encType) {
	      this.encType = encType;
	   }

	   public String getUser() {
	      return multi.getParameter("user");
	   }

	   public String getTitle() {
	      return multi.getParameter("title");
	   }
	
	   
	   
	   
	//업로드 사진
	public String[] up(HttpServletRequest req) {
	      String file[] = new String[5];
	      setPath(req, "upload");
	      setMax(5 * 1024 * 1024);
	      setEncType("UTF-8");
	      String sql="";
	      try {
	    	 sql = "select no from board order by no desc";
			 stmt = con.prepareStatement(sql);
			 rs = stmt.executeQuery();
			 /*System.out.println(stmt);*/
			 int number;
			 if(rs.next()){
					number = rs.getInt("no");
				}
				else{
					number = 0;
				}
	    	 sql = "INSERT INTO `board_image`(no, filename, desination, filesize, filetype, fileurl) VALUES(?,?,?,?,?,?)";
	    	 multi = new MultipartRequest(req, path, max, encType, new DefaultFileRenamePolicy());
	         stmt = con.prepareStatement(sql);
	         File f = multi.getFile("upload_file");
	         stmt.setInt(1, number + 1);
	         stmt.setString(2, multi.getFilesystemName("upload_file"));
	         stmt.setString(3, f.getPath());
	         stmt.setFloat(4, f.length());
	         stmt.setString(5, multi.getContentType("upload_file"));
	         stmt.setString(6, "/homepage/upload/" + multi.getFilesystemName("upload_file"));
	         /*System.out.println(stmt);*/
	         stmt.executeUpdate();
	         file[0] = multi.getFilesystemName("upload_file");
	         file[1] = Double.toString(f.length());
	         file[2] = multi.getContentType("upload_file");
	     	 file[3] = multi.getParameter("width");
			 file[4] = multi.getParameter("height");
	      } catch (Exception err) {
	         System.out.println(err + "adad");
	      } finally {
	         freeCon();
	      }
	      return file;
	   }
	
	public void someplusdel(String name){
		String sql;
		try {
			sql = "select * from `board_image` where filename = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			rs.next();
			String path = rs.getString("desination");
			File f = new File(path);
			f.delete();
			sql = "DELETE FROM `board_image` WHERE filename = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			freeCon();
		}
	}
	
	//id 찾기
	public someDto getId(String name, String email) {
		String sql = "select * from member where name= ? and `e-mail`= ?";
		someDto dto = new someDto();
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, email);
			rs = stmt.executeQuery();
			System.out.println(stmt);
			
			while(rs.next()){
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setEmail(rs.getString("e-mail"));
			}
			//System.out.println(name);
			//System.out.println(email);
			System.out.println(dto.getId());
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			freeCon();
		}
		return dto;
	}
	
	public someDto getboard(int no){
		String sql = "select * from board where no = ?";
		someDto dto = new someDto();
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			rs.next();
			dto.setNo(rs.getInt("no"));
			dto.setContent(rs.getString("content"));
			dto.setId(rs.getString("id"));
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			freeCon();
		}
		return dto;
	}
	
	public ArrayList<someDto> admingetboard(){
		String sql = "select * from board";
		ArrayList<someDto> g = new ArrayList<someDto>();
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				someDto dto = new someDto();
				dto.setNo(rs.getInt("no"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setDay(rs.getString("day"));
				g.add(dto);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			freeCon();
		}
		return g;
	}
	
	public ArrayList<someDto> segetboard(String search, String value){
		String sql = "select * from board where "+ search +" Like '%"+value+"%'";
		ArrayList<someDto> g = new ArrayList<someDto>();
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				someDto dto = new someDto();
				dto.setNo(rs.getInt("no"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setDay(rs.getString("day"));
				g.add(dto);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			freeCon();
		}
		return g;
	}
	
	//어플 글쓰기
		public void mobileInsertBoard(BoardDto Bdto){
			try {
				//board 테이블에 값 넣기	<p style="text-align: center;"><img src="/homepage/upload/으리.jpg" style="max-width:450px;clear:none;float:none;"/></p>
				String content0 = Bdto.getContent().replace("\n", "<br/>");
				if(Bdto.getFilename() != null){
					content0 = content0 + "<p style=\"text-align: center;\"><img src=\""+Bdto.getFileurl()+"\" style=\"max-width:100%;clear:none;float:none;\"/></p>";
				}
				String sql = "insert into board (id, content, day) values(?, ?, now())";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, Bdto.getId());
				stmt.setString(2, content0);
				stmt.executeUpdate();
				
				//방금 넣은 게시글 번호 가져오기
				sql = "select no from board order by no desc";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				/*System.out.println(stmt);*/
				int number = 0;
				if(rs.next()){
					number = rs.getInt("no");
				}
				
				//board_image 테이블에 값넣기
				sql = "INSERT INTO `board_image`(no, filename, desination, filesize, filetype, fileurl) VALUES(?,?,?,?,?,?)";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, number);
				stmt.setString(2, Bdto.getFilename());
				stmt.setString(3, Bdto.getDesination());
				stmt.setString(4, Bdto.getFilesize());
				stmt.setString(5, Bdto.getFiletype());
				stmt.setString(6, Bdto.getFileurl());
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				freeCon();
			}
		}
}
