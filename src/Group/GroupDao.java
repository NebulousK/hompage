package Group;

import homepage.DBConnectionMgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.sql.DataSource;
import javax.annotation.PreDestroy;
import javax.naming.InitialContext;

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
	
	
	public Vector area_Print(String area){
		Vector list=new Vector();
		String sql="";
		try{	
			sql = "select * from member where addr" + 
					" like '%" + area + "%'";
			stmt=con.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()){
				MemberDto dto =new MemberDto();
				dto.setNo(rs.getInt("no"));
				dto.setAddr(rs.getString("addr"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPassword(rs.getString("password"));
				dto.setPhoto(rs.getString("photo"));
				dto.setSex(rs.getString("sex"));
				dto.setTel(rs.getString("tel"));
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
			sql="select id from member where id in(select userid1 from freind where (userid1=? or userid2=?) and friends='true') or id in(select userid2 from freind where (userid1=? or userid2=?) and friends='true')";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,userid1);
			stmt.setString(2,userid1);
			stmt.setString(3,userid1);
			stmt.setString(4,userid1);
			System.out.println("[friend_List stmt]: "+stmt);
			rs=stmt.executeQuery();
			while(rs.next()){		
					MemberDto dto=new MemberDto();
					dto.setId(rs.getString("id"));
					fList.add(dto);
			}
			System.out.println("[friend_List]�ϼ�");
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
			sql="update freind set friends='true' where userid1=? and userid2=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,userid1);
			stmt.setString(2,userid2);
			stmt.executeUpdate();
			System.out.println("stmtstmtstmtstmtstmtstmtstmtstmtstmtstmtstmtstmtstmt"+stmt);
			System.out.println("[accept_Friend]�ϼ�");
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
			sql="select * from member where id in(select userid1 from freind where userid2=? and friends=?)";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, userid2);
			stmt.setString(2, friends);
			rs=stmt.executeQuery();
			System.out.println(stmt);
			//System.out.println("rs.next()rs.next()rs.next()rs.next()rs.next()rs.next()rs.next()rs.next()"+rs.next());
			while(rs.next()){
				MemberDto dto=new MemberDto();
				dto.setAddr(rs.getString("addr"));
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
	
	
	//�����
	public void createGroup(GroupDto dto) {
			String sql="";
		try{
			sql="insert into `group`(myid,`group`) values(?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,dto.getMyid());
			stmt.setString(2,dto.getGroup());
			stmt.executeUpdate();
			System.out.println("[����ϼ�]");
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
				/*System.out.println("getGroup myid:"+myid);*/
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
			//System.out.println("getGroup myid:"+myid+"getGroup mygroup:"+mygroup);
			sql="select * from freind where myid=? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, myid);
			//stmt.setString(2, mygroup);
			rs=stmt.executeQuery();
			while(rs.next()){
				FriendDto dto= new FriendDto();
				dto.setUserid1(rs.getString("userid1"));
				dto.setUserid2(rs.getString("userid2"));
				dto.setFriends(rs.getString("friends"));
				CompleteGroupList.add(dto);
			}	
			System.out.println("[getTotalFriend �ϼ�]");
		} catch(Exception err) {	
			System.out.println("getGroup:"+err);
		} finally {
			
			freeCon();
			
		}
		
		return CompleteGroupList;	
	}
	
	//��û�� ģ�����
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
			System.out.println("[request_Friend_list] �ϼ�");
		}catch(Exception err){
			System.out.println("request_Friend_list():"+err);
			
		}finally{
			
			freeCon();
			
		}
	return friend_list;
	
	}
	
	public String check_Friend(String myid,String userid2){
		String check="";
		String sql="";
		try{
			sql="select * from freind where userid1=? and userid2=? and friends='false'";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, myid);
			stmt.setString(2, userid2);
			rs=stmt.executeQuery();//System.out.println("getFriends no After:"+no)
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
							check="3";
							//ģ��
						}
					} else {
						check="2";//ģ����û ����
					}
				} else {
					check="1";	
				}
			//	return check;
		}catch(Exception err){
			System.out.println("check_Friend:"+err);


		}finally{


			freeCon();
		}
		return check;
	}
		public Vector getFriends(String myid){
			/*System.out.println(myid);*/
			Vector friendList =new Vector();
			String sql;
			try{
				sql="select * from freind where (userid1=? or userid2=? ) and friends=?";
				stmt=con.prepareStatement(sql);
				stmt.setString(1, myid);
				stmt.setString(2, myid);
				stmt.setString(3, "true");
				rs=stmt.executeQuery();
				System.out.println(stmt);
				while(rs.next()){
					FriendDto dto=new FriendDto();
					dto.setFriends(rs.getString("friends"));
					dto.setUserid1(rs.getString("userid1"));
					dto.setUserid2(rs.getString("userid2"));
					friendList.add(dto);
				}
				System.out.println("get friends success!");
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
			System.out.println("[delete_friend_Gruoup �ϼ�]");
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
	
	//��ó�� ģ�� ���
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
			System.out.println("[requestfriend �ϼ�]");
		}catch(Exception err){
			System.out.println("requestfriend"+err);
		}finally{
			freeCon();
		}
	return mem;
	}
	
	
	//���쿡�� ģ�� ����
	public void friend_Delete(GroupDto dto) {
		String sql="";
		System.out.println("delete Friend entered ok??");
		try{
			sql="delete from mygroup where myid=? and userid=? and `group`=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, dto.getMyid());
			stmt.setString(2, dto.getUserid());
			stmt.setString(3, dto.getGroup());
			stmt.executeUpdate();
		
			System.out.println("[ģ�� ���� �Ϸ�]");
		}catch(Exception err){
			System.out.println("Friend_Delete:"+err);
			
		}finally{
			freeCon();
		}
	}
	
	//Group friend Delete(�������)
	public void delete_FriendGroup(int no){
				String sql="";
		try{
			sql="delete from mygroup where no=? ";		
				stmt=con.prepareStatement(sql);
				stmt.setInt(1, no);
				stmt.executeUpdate();
				System.out.println("[delete_FriendGroup �ϼ�]");
		} catch(Exception err) {
			System.out.println("delete_FriendGroup:"+err);	
		} finally {
			freeCon();
		}
	}
	
	//Group friend Delete(�������)
	public void delete_Group(int no){
		String sql="";
		try{
			sql="delete from `group` where no=? ";		
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
			System.out.println("[delete_FriendGroup �ϼ�]");
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
			System.out.println("[[ cuserid1]]    :"+userid1);
			System.out.println("[[ cuserid2]]    :"+userid2);
			System.out.println("[[ friends ]]    :"+friends);
			/*			
			"update tblboard set name=?, email=?, " +
			"subject=?, content=? where num=?";
			
			 userid1,userid2,friends  
			
			 *update ABCDE set column1='xyz' where no='3'
			 */
			
			//update freind set friends='true'where userid1='ImJAVA' and friends='false' and userid2='one'
			sql="update freind set friends=? where userid1=? and userid2=? and friends=?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, friends);
			stmt.setString(2, userid1); 
			stmt.setString(3, userid2);
			stmt.setString(4, False);
			System.out.println(sql);
			
			stmt.executeUpdate();
			
			/*//����? ������Ʈ�� �ȵǰ� insert�� �Ǽ� ����óġ
			sql="delete from freind where userid1=? and userid2=? and friends=?";
			stmt.setString(1, userid1);
			stmt.setString(2, userid2); 
			stmt.setString(3, False);
			stmt.executeUpdate();*/
			
			System.out.println("[addListFriend] �ϼ�");
			
		}catch(Exception err){
			System.out.println("addListFriend"+err);
		}finally{
			
			freeCon();
		}
		
		
	}
	
	
	//userid1 userid2 friends  
	//���쿡 ģ�� �ֱ�
	public void addGroupFriend(GroupDto dto) {
			String sql="";
			//System.out.println("���� ����?");
		try{
			sql="insert into mygroup(myid,userid,`group`) values(?,?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, dto.getMyid());
			stmt.setString(2, dto.getUserid());
			stmt.setString(3, dto.getGroup());
			stmt.executeUpdate();
			System.out.println(stmt);
		System.out.println("[addGroupFriend �ռ�]");
		}catch(Exception err){
			System.out.println("addGroupFriend()"+err);
		}finally{
			freeCon();
			
		}
	}
	
	/*//list ģ������ friend no �� �޾ƿ���
	public int list_deleteNo(String userid1,String userid2){
		int no=0;
		String sql="select no from freind where "
				+ "id in(select userid1 from freind where userid1 (userid1=? or userid2=?) and friends='true') or no in(select userid2 from freind where (userid1=? or userid2=?) and friends='true')";
		try{
			con=ds.getConnection();
			sql="select no from Friend where userid1=? and userid2=?";
					
			
		}catch(Exception err){
			
			
			System.out.println("list_deleteNo:"+err);
		}finally{
			
			freeCon();
		}
		
	}*/
	
	
	
	//ģ�� list���� �����ϱ�
	public void list_Friend_Delete(String myid, String userid){
		String sql="";
			try {
				//userid2  friends  userid1  
					sql="delete from freind where (userid1=? and userid2=?) or (userid1=? and userid2=?)and (friends='true')";
					stmt=con.prepareStatement(sql);
					stmt.setString(1, myid);
					stmt.setString(2, userid);
					stmt.setString(3, userid);
					stmt.setString(4, myid);
					stmt.executeUpdate();
					System.out.println("[list_Friend_Delete �ϼ�]");
					System.out.println("[list_Friend_Delete stmt]"+stmt);
			} catch(Exception err) {
		
				System.out.println("list_Friend_Delete:"+err);
		
			} finally {
		
				freeCon();
			}
		
	}

	//ģ�� �˻��ϱ�
	public Vector getResearchFriendList( String keyField, String research){
			Vector list=new Vector();
			String sql="";
			//String alse=area;
			//System.out.println("1System.out.println(keyField):"+keyField);
			//System.out.println("1System.out.println(research):"+research);
			try{
				//System.out.println("1System.out.println(area):"+area);
				if(keyField==null||keyField.isEmpty()||keyField.equals("null")){
					//System.out.println("KeyField null");
					sql="select * from member";
				} else {
					//System.out.println("2System.out.println(area):"+area);
					sql = "select * from member where " +  research + 
							" like'" +keyField + "%'";
				}
				System.out.println("[getResearchFriendList]�ϼ�");	
				stmt=con.prepareStatement(sql);
				rs=stmt.executeQuery();
			
				while(rs.next()){
					MemberDto dto =new MemberDto();
					dto.setNo(rs.getInt("no"));
					dto.setAddr(rs.getString("addr"));
					dto.setBirthday(rs.getString("birthday"));
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setPassword(rs.getString("password"));
					dto.setPhoto(rs.getString("photo"));
					dto.setSex(rs.getString("sex"));
					dto.setTel(rs.getString("tel"));
					list.add(dto);
				}
				System.out.println("[getFriendList�ϼ�]");
			}catch(Exception err){
				System.out.println("getFriendList:"+err);
			}finally{
				freeCon();
			}
			return list;
	}
}
