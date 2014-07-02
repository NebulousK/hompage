package Group;

import homepage.DBConnectionMgr;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.annotation.PreDestroy;
import javax.naming.InitialContext;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Member.FriendDto;
import Member.MemberDto;
public class GroupDao {
	private Connection con;
	private PreparedStatement stmt;
	private DBConnectionMgr pool = null;
	private ResultSet rs;
	public GroupDao() {
		// TODO Auto-generated constructor stub
		try {
			pool = DBConnectionMgr.getInstance();
			con = (Connection) pool.getConnection();
		} catch (Exception err) {
			System.out.println("에러");
			System.out.println(err);
		}
	}
	public void freeCon(){
		pool.freeConnection(con, stmt, rs);
	}
	
	//지역검색
	public Vector area_Print(String area, String myid){
		ResultSet rs2;
		PreparedStatement stmt2;
		Vector list=new Vector();
		String sql="";
		try{	
			sql = "select * from member where addr" + " like '%" + area + "%'";
			stmt=con.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()){
				MemberDto dto =new MemberDto();
			/*	dto.setNo(rs.getInt("no"));
				dto.setAddr(rs.getString("addr"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPhoto(rs.getString("photo"));
				dto.setSex(rs.getString("sex"));
				dto.setTel(rs.getString("tel"));*/
				String addr[] = rs.getString("addr").split(" ");
				dto.setNo(rs.getInt("no"));
				dto.setAddr(addr[1] + " " + addr[2]);
				dto.setBirthday(rs.getString("birthday"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPassword(rs.getString("password"));
				dto.setPhoto(rs.getString("photo"));
				dto.setSex(rs.getString("sex"));
				dto.setTel(rs.getString("tel"));
				if(!myid.equals(rs.getString("id"))){
				String sql2 = "select * from freind where (userid1=? and userid2=?) or (userid1=? and userid2=?)";
				stmt2 = con.prepareStatement(sql2);
				stmt2.setString(1, myid);
				stmt2.setString(2, rs.getString("id"));
				stmt2.setString(3, rs.getString("id"));
				stmt2.setString(4, myid);
				rs2 = stmt2.executeQuery();
				if(rs2.next()){
				dto.setCheck(rs2.getString("friends"));
				}else{
					dto.setCheck("not");
				}
				}
				list.add(dto);
			}
		}catch(Exception err){
			System.out.println("area_Print"+err);
		} finally {
			
			freeCon();
		}

	return list;
	}
	public Vector friend_List(String userid1){
		Vector fList=new Vector();
			String sql="";
		try{
			//select id from member where id in(select userid1 from freind where (userid1='ImJAVA' or userid2='ImJAVA') and friends='true') or id in(select userid2 from freind where (userid1='ImJAVA' or userid2='ImJAVA') and friends='true');
			sql="select id, photo from member where id in(select userid1 from freind where (userid1=? or userid2=?) and friends='true') or id in(select userid2 from freind where (userid1=? or userid2=?) and friends='true')";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,userid1);
			stmt.setString(2,userid1);
			stmt.setString(3,userid1);
			stmt.setString(4,userid1);
			rs=stmt.executeQuery();
			while(rs.next()){		
					MemberDto dto=new MemberDto();
					dto.setId(rs.getString("id"));
					dto.setPhoto(rs.getString("photo"));
					fList.add(dto);
			}
		} catch(Exception err) {
			System.out.println("createGroup:"+err);
		} finally {
			freeCon();
		}		
		return fList;
	}
	
	public void accept_Friend(String userid1,String userid2){
		
		String sql="";
		try{
			sql="update freind set friends='true' where userid1=? and userid2=? OR userid2=? and userid1=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,userid1);
			stmt.setString(2,userid2);
			stmt.setString(3,userid1);
			stmt.setString(4,userid2);
			stmt.executeUpdate();
		}catch(Exception err){
			System.out.println(sql);
			System.out.println("createGroup:"+err);
		}finally{
			freeCon();
		}		
	}
	
	//��û�� ģ�����
	public Vector Request_List(String userid2, String friends){
		Vector rList= new Vector();
		String sql="";
		try{
			sql="select * from member where id in (select userid1 from freind where userid2=? and friends=?)";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, userid2);
			stmt.setString(2, friends);
			rs=stmt.executeQuery();
		while(rs.next()){
				MemberDto dto=new MemberDto();
				String addr[] = rs.getString("addr").split(" ");
				dto.setAddr(addr[1] + " " + addr[2]);
				dto.setBirthday(rs.getString("birthday"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setNo(rs.getInt("no"));
				dto.setPassword(rs.getString("password"));
				dto.setPhoto(rs.getString("photo"));
				dto.setSex(rs.getString("sex"));
				dto.setTel(rs.getString("tel"));
				rList.add(dto);
			}
		}catch(Exception err){
			System.out.println("list_request:"+err);
		}finally{
			freeCon();
		}
		return rList;
	}

	//ģ����û
	public void Friend_request(String userid1,String userid2, String friends){
		String sql="";
		try{	
			sql="insert into freind(userid1,userid2,friends)values(?,?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, userid1);
			stmt.setString(2, userid2);
			stmt.setString(3, friends);
			stmt.executeUpdate();
		}catch(Exception err){
			System.out.println("list_request:"+err);
		}finally{
			freeCon();
		}
	}

	public void createGroup(GroupDto dto) {
			String sql="";
		try{
			sql="insert into `group`(myid,`group`) values(?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,dto.getMyid());
			stmt.setString(2,dto.getGroup());
			stmt.executeUpdate();
		} catch(Exception err) {
			System.out.println(sql);
			System.out.println("createGroup:"+err);
		} finally {
			freeCon();
		}		
	}

	public Vector getGruop(String myid){
		Vector GroupList=new Vector();
		String sql="";
			try{
				sql="select * from `group` where myid=?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, myid);
				rs=stmt.executeQuery();
				while(rs.next()){
					GroupDto dto= new GroupDto();
					dto.setNum(rs.getInt("no"));
					dto.setMyid(rs.getString("myid"));
					dto.setGroup(rs.getString("group"));
					GroupList.add(dto);
				}
			}catch(Exception err){
				System.out.println("getGruop:"+err);
			}finally{
				freeCon();
				
			}
		
		return GroupList;
	}

	public Vector getTotalFriend(String myid){
		Vector CompleteGroupList =new Vector();
		String sql="";
		try {
			sql="select * from freind where myid=? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, myid);
			rs=stmt.executeQuery();
			while(rs.next()){
				FriendDto dto= new FriendDto();
				dto.setUserid1(rs.getString("userid1"));
				dto.setUserid2(rs.getString("userid2"));
				dto.setFriends(rs.getString("friends"));
				CompleteGroupList.add(dto);
			}	
		} catch(Exception err) {	
			System.out.println("getGroup:"+err);
		} finally {
			freeCon();	
		}
		return CompleteGroupList;	
	}
	
	public Vector request_Friend_list(String userid1,String userid2){
		Vector friend_list=new Vector();
		String sql="";
		try{
			sql="insert into freind(userid1,userid2,friends) values(?,?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, userid1);
			stmt.setString(2, userid2);
			stmt.setString(3, "false");
			stmt.executeUpdate();
		}catch(Exception err){
			System.out.println("request_Friend_list():"+err);
		}finally{
			freeCon();
		}
	return friend_list;
	}
	
	public String check_Friend( String myid, String userid2 ){
		String sql="";
		String check="";
		try{
			sql="select * from freind where userid1=? and userid2=? and friends='false'";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, myid);
			stmt.setString(2, userid2);
			rs=stmt.executeQuery();
				if(!rs.next()){
					sql="select * from freind where userid1=? and userid2=? and friends='false'";
					stmt=con.prepareStatement(sql);
					stmt.setString(1, userid2);
					stmt.setString(2, myid);
					rs=stmt.executeQuery();
					if(!rs.next()){
						sql="select * from freind where (userid1=? and userid2=?) or(userid1=? and userid2=?)and friends='true'";
						stmt=con.prepareStatement(sql);
						stmt.setString(1, userid2);
						stmt.setString(2, myid);
						stmt.setString(3,myid);
						stmt.setString(4,userid2);
						rs=stmt.executeQuery();
						if(rs.next()){
							check="three";
						}
					} else {
						check="two";
					}
				} else {
					check="one";	
				}
		}catch(Exception err){
			System.out.println("check_Friend:"+err);
		}finally{
			freeCon();
		}
		return check;
	}
		
	
	
	public Vector getFriends(String myid){
		Vector friendList =new Vector();
		String sql;
			try{
				sql="select * from freind where (userid1=? or userid2=? ) and friends=?";
				stmt=con.prepareStatement(sql);
				stmt.setString(1, myid);
				stmt.setString(2, myid);
				stmt.setString(3, "true");
				rs=stmt.executeQuery();
				while(rs.next()){
					FriendDto dto=new FriendDto();
					dto.setFriends(rs.getString("friends"));
					dto.setUserid1(rs.getString("userid1"));
					dto.setUserid2(rs.getString("userid2"));
					friendList.add(dto);
				}
			}catch(Exception err){
				System.out.println("getFriends"+err);
			}finally{
				freeCon();
			}
			return friendList;
		}

	public void delete_Friend_Gruoup(String myid,String group){
		String sql="";
		try{
			sql="delete from mygroup where myid=? and `group`=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, myid);
			stmt.setString(2, group);
			stmt.executeUpdate();
		}catch(Exception err){
			System.out.println("delete_friend_Gruoup:"+err);		
		}finally{
			freeCon();
		}
	}
	public Vector getCompleteFriend(String myid , String mygroup){
		Vector CompleteGroupList =new Vector();
		String sql="";
		try{
			sql="select * from mygroup where myid=? and `group` =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, myid);
			stmt.setString(2, mygroup);
			rs=stmt.executeQuery();
			while(rs.next()){
				GroupDto dto= new GroupDto();
				dto.setNum(rs.getInt("no"));
				dto.setMyid(rs.getString("myid"));
				dto.setGroup(rs.getString("group"));
				dto.setUserid(rs.getString("userid"));
				CompleteGroupList.add(dto);
			}	
		}catch(Exception err){
			System.out.println("getGroup:"+err);
		}finally{
			freeCon();	
		}	
		return CompleteGroupList;	
	}
	public MemberDto requestfriend(String name){
		MemberDto mem=new MemberDto();
		String sql="";
		try{
			sql = "select * from member where name=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if(rs.next()){
				mem.setAddr(rs.getString("addr"));
				mem.setBirthday(rs.getString("birthday"));
				mem.setId(rs.getString("id"));
				mem.setName(rs.getString("name"));
				mem.setNo(rs.getInt("no"));
				mem.setPassword(rs.getString("password"));
				mem.setPhoto(rs.getString("photo"));
				mem.setSex(rs.getString("sex"));
				mem.setTel(rs.getString("tel"));
			}
		}catch(Exception err){
			System.out.println("requestfriend"+err);
		}finally{
			freeCon();
		}
	return mem;
	}
	public void friend_Delete(GroupDto dto) {
		String sql="";
		try{
			sql="delete from mygroup where myid=? and userid=? and `group`=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, dto.getMyid());
			stmt.setString(2, dto.getUserid());
			stmt.setString(3, dto.getGroup());
			stmt.executeUpdate();
		}catch(Exception err){
			System.out.println("Friend_Delete:"+err);
		}finally{
			freeCon();
		}
	}
	public void delete_FriendGroup(int no){
				String sql="";
		try{
			sql="delete from mygroup where no=? ";		
				stmt=con.prepareStatement(sql);
				stmt.setInt(1, no);
				stmt.executeUpdate();
		} catch(Exception err) {
			System.out.println("delete_FriendGroup:"+err);	
		} finally {
			freeCon();
		}
	}
	
	public void delete_Group(int no){
		String sql="";
		try{
			sql="delete from `group` where no=? ";		
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} catch(Exception err) {
			System.out.println("delete_FriendGroup:"+err);	
		} finally {
			freeCon();
		}
	}
	public void listAddFriend( String userid1 , String userid2, String friends ){
		String sql="";	
		try{
			String False="";
			sql="update freind set friends=? where userid1=? and userid2=? and friends=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, friends);
			stmt.setString(2, userid1); 
			stmt.setString(3, userid2);
			stmt.setString(4, False);
			stmt.executeUpdate();
		}catch(Exception err){
			System.out.println("addListFriend"+err);
		}finally{
			freeCon();
		}
	}
	public void addGroupFriend(GroupDto dto) {
			String sql="";
		try{
			sql="insert into mygroup(myid,userid,`group`) values(?,?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, dto.getMyid());
			stmt.setString(2, dto.getUserid());
			stmt.setString(3, dto.getGroup());
			stmt.executeUpdate();
		}catch(Exception err){
			System.out.println("addGroupFriend()"+err);
		}finally{
			freeCon();
			
		}
	}
	public void list_Friend_Delete(String myid, String userid){
		String sql="";
			try {
					sql="delete from freind where (userid1=? and userid2=?) or (userid1=? and userid2=?)and (friends='true')";
					stmt=con.prepareStatement(sql);
					stmt.setString(1, myid);
					stmt.setString(2, userid);
					stmt.setString(3, userid);
					stmt.setString(4, myid);
					stmt.executeUpdate();
			} catch(Exception err) {
				System.out.println("list_Friend_Delete:"+err);
			} finally {
				freeCon();
			}	
	}
	public Vector getResearchFriendList(String keyField, String research, String myid, int no, String area){
			Vector list=new Vector();
			String sql="";
			ResultSet rs2;
			PreparedStatement stmt2;
			try{
				if(area == null){
					sql = "select * from member where " +  research +  " like'%" +keyField + "%'";
				}else{
					sql = "select * from member where " +  research +  " like'%" +keyField + "%' and addr like '%"+ area +"%'";
				}
				stmt=con.prepareStatement(sql);
				rs=stmt.executeQuery();
				while(rs.next()){
					if(!rs.getString("no").equals(no)){
					MemberDto dto =new MemberDto();
					String addr[] = rs.getString("addr").split(" ");
					dto.setNo(rs.getInt("no"));
					dto.setAddr(addr[1] + " " + addr[2]);
					dto.setBirthday(rs.getString("birthday"));
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setPassword(rs.getString("password"));
					dto.setPhoto(rs.getString("photo"));
					dto.setSex(rs.getString("sex"));
					dto.setTel(rs.getString("tel"));
					if(!myid.equals(rs.getString("id"))){
					String sql2 = "select * from freind where (userid1=? and userid2=?) or (userid1=? and userid2=?)";
					stmt2 = con.prepareStatement(sql2);
					stmt2.setString(1, myid);
					stmt2.setString(2, rs.getString("id"));
					stmt2.setString(3, rs.getString("id"));
					stmt2.setString(4, myid);
					rs2 = stmt2.executeQuery();
					if(rs2.next()){
					dto.setCheck(rs2.getString("friends"));
					}else{
						dto.setCheck("not");
					}
					}
					list.add(dto);
					}
				}
			}catch(Exception err){
				System.out.println("getFriendList:"+err);
			}finally{
				freeCon();
			}
			return list;
	}
}
