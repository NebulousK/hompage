package homepage.board;

import homepage.DBConnectionMgr;
import homepage.someDao;
import homepage.someDto;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

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
				String sql = "SELECT a.no, a.id, a.content, a.day, a.hit, a.like, b.photo FROM board a INNER JOIN member b ON a.id = b.id where a.id = ?;";
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
				//System.out.println(rs.getString(3));
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
}
