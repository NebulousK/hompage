package homepage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.regexp.RE;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class someDao {
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool = null;
	private MultipartRequest multi;
	private String path;
	private int max;
	private String encType;

	public void connect() {
		try {
			pool = DBConnectionMgr.getInstance();
			con = (Connection) pool.getConnection();
		} catch (Exception err) {
			System.out.println("에러");
			System.out.println(err);
		}
	}

	public void discon() {
		pool.freeConnection(con, stmt, rs);
	}
	
	public someDto member(String id){
		connect();
		someDto g = new someDto();
		try {
			String sql = "select * from member where id =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			rs.next();
			g.setId(rs.getString("id"));
			g.setName(rs.getString("name"));
			g.setBirthday(rs.getString("birthday"));
			g.setAge(rs.getInt("age"));
			g.setEmail(rs.getString("e-mail"));
			g.setPhoto(rs.getString("photo"));
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return g;
	}

	public ArrayList<someDto> zip(String dong) {
		connect();
		ArrayList<someDto> g = new ArrayList<someDto>();
		try {
			String sql = "select * from zip where juso3 like '%" + dong + "%'";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				someDto dto = new someDto();
				dto.setNo(Integer.parseInt(rs.getString("no")));
				dto.setZip(Integer.parseInt(rs.getString("zip")));
				dto.setZip2(Integer.parseInt(rs.getString("zip2")));
				dto.setJuso1(rs.getString("juso1"));
				dto.setJuso2(rs.getString("juso2"));
				dto.setJuso3(rs.getString("juso3"));
				dto.setJuso4(rs.getString("juso4"));
				g.add(dto);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return g;
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

	public void member_join(HttpServletRequest req) {
		connect();
		setPath(req, "profile");
		setMax(5 * 1024 * 1024);
		setEncType("UTF-8");
		String sql = "INSERT INTO member(id, password, name, sex, birthday, addr, tel, photo, age, `e-mail`) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			multi = new MultipartRequest(req, path, max, encType, new DefaultFileRenamePolicy());
			stmt = con.prepareStatement(sql);
			File f = multi.getFile("imgInp");
			String email;
			String password = sha1(multi.getParameter("password"));
			if (multi.getParameter("email2").equals(null) || multi.getParameter("email2").equals("")) {
				email = multi.getParameter("email1") + "@" + multi.getParameter("email3");
			} else {
				email = multi.getParameter("email1") + "@" + multi.getParameter("email2");
			}
			int age = (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(multi.getParameter("year"))) + 1;
			stmt.setString(1, multi.getParameter("id"));
			stmt.setString(2, password);
			stmt.setString(3, multi.getParameter("name"));
			stmt.setString(4, multi.getParameter("sex"));
			stmt.setString(5, multi.getParameter("year") + "." + multi.getParameter("month") + "." + multi.getParameter("day"));
			stmt.setString(6, multi.getParameter("zip1") + "-" + multi.getParameter("zip2") + " " + multi.getParameter("juso") + " " + multi.getParameter("addr"));
			stmt.setString(7, multi.getParameter("tel") + "-" + multi.getParameter("tel2") + "-" + multi.getParameter("tel3"));
			stmt.setString(8, multi.getFilesystemName("imgInp"));
			stmt.setInt(9, age);
			stmt.setString(10, email);
			// System.out.println(stmt);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public void member_detail(someDto g) {
		connect();
		String sql = "INSERT INTO m_profile(no, height, hobby, blood, style, weight, fashion) VALUES(LAST_INSERT_ID(), ?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getHeight());
			stmt.setString(2, g.getHobby());
			stmt.setString(3, g.getBlood());
			stmt.setString(4, g.getStyle());
			stmt.setInt(5, g.getWeight());
			stmt.setString(6, g.getFashion());
			/* System.out.println(stmt); */
			stmt.executeUpdate();
			stmt = null;
			sql = "INSERT INTO idealtype(no, height, hobby, blood, style, weight, fashion, age) VALUES(LAST_INSERT_ID(), ?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getHeight2());
			stmt.setString(2, g.getHobby2());
			stmt.setString(3, g.getBlood2());
			stmt.setString(4, g.getStyle2());
			stmt.setInt(5, g.getWeight2());
			stmt.setString(6, g.getFashion2());
			stmt.setInt(7, g.getAge());
			/* System.out.println(stmt); */
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public String login(String id, String password) {
		connect();
		String sql = "select * from member where id= ?";
		String set = "";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				set = "b";
			} else {
				if (!sha1(password).equals(rs.getString("password"))) {
					set = "a";
				} else {
					set += id + ",";
					set += rs.getInt("no") + ",";
					set += rs.getString("sex") + ",";
					set += rs.getString("photo") + ",";
					set += rs.getString("name");
				}
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return set;
	}

	public someDto idealtype(int no, String sex) {
		connect();
		ArrayList<someDto> g = new ArrayList<someDto>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		String sql = "";
		someDto re = new someDto();
		int age = 0, height = 0;
		String hobby = null, fashions = null, blood = null, style = null, weight = null, fashion = null, fashion2 = null, fashion3 = null;
		try {
			sql = "select * from `join` where userid = ? and date = CURDATE()";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			/*System.out.println(stmt);*/
			if (rs.next()) {
				sql = "SELECT a.no ,a.name, a.age, b.blood, b.height, b.weight, a.addr , b.style, b.fashion, b.hobby , a.photo  FROM member a, m_profile b WHERE a.no = ? AND a.no = b.no";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, Integer.parseInt(rs.getString("itemid")));
				rs = stmt.executeQuery();
				rs.next();
				re.setNo(rs.getInt("a.no"));
				re.setName(rs.getString("a.name"));
				re.setAge(Integer.parseInt(rs.getString("a.age")));
				re.setBlood(rs.getString("b.blood"));
				re.setHeight(Integer.parseInt(rs.getString("b.height")));
				re.setWeight(Integer.parseInt(rs.getString("b.weight")));
				String c[] = rs.getString("a.addr").split(" ");
				re.setAddr(c[1]);
				re.setStyle(rs.getString("b.style"));
				re.setFashion(rs.getString("b.fashion"));
				re.setHobby(rs.getString("b.hobby"));
				re.setPhoto(rs.getString("a.photo"));
			}else {
				sql = "select * from idealtype where no = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, no);
				rs = stmt.executeQuery();
				/*System.out.println(stmt);*/
				rs.next();
				age = Integer.parseInt(rs.getString("age"));
				height = Integer.parseInt(rs.getString("height"));
				hobby = rs.getString("hobby");
				blood = rs.getString("blood");
				style = rs.getString("style");
				weight = rs.getString("weight");
				fashions = rs.getString("fashion");
				String arr[] = fashions.split(",");
				fashion = arr[0];
				if (!arr[1].equals(null)) {fashion2 = arr[1];}
				if (!arr[2].equals(null)) {fashion3 = arr[2];}
				sql = "SELECT a.no ,a.age, b.height, b.hobby, b.blood,b.style, b.weight, b.fashion FROM member a, m_profile b WHERE a.sex = ? AND a.no = b.no AND a.age <= ?  AND a.no not in (select itemid from `join` where userid = ?) and a.no not in (select `man_ID` from `some_some` where state = ?) and a.no not in (select `woman_ID` from `some_some` where state = ?)";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, sex);
				stmt.setInt(2, rs.getInt("age"));
				stmt.setInt(3, no);
				stmt.setInt(4, 1);
				stmt.setInt(5, 1);
				rs = stmt.executeQuery();
				/*System.out.println(stmt);*/
				while (rs.next()) {
					someDto dto = new someDto();
					dto.setNo(Integer.parseInt(rs.getString("a.no")));
					dto.setAge(Integer.parseInt(rs.getString("a.age")));
					dto.setHeight(Integer.parseInt(rs.getString("b.height")));
					dto.setHobby(rs.getString("b.hobby"));
					dto.setBlood(rs.getString("b.blood"));
					dto.setStyle(rs.getString("b.style"));
					dto.setWeight(rs.getInt("b.weight"));
					fashions = rs.getString("b.Fashion");
					String arr1[] = fashions.split(",");
					dto.setFashion(arr1[0]);
					if (!arr1[1].equals(null)) {dto.setFashion2(arr1[1]);}
					if (!arr1[2].equals(null)) {dto.setFashion3(arr1[2]);}
					g.add(dto);
				}
				for (int i = 0; i < g.size(); i++) {
					someDto dto = g.get(i);
					int result = 0;
					if (height >= dto.getHeight()) {
						int r = height - dto.getHeight();
						if (r > 10) {result += 0;} 
						else if (r <= 3) {result += 10;} 
						else if (r <= 5) {result += 7;} 
						else if (r <= 10) {result += 4;}
					} else if (height <= dto.getHeight()) {
						int r = dto.getHeight() - height;
						if (r > 10) {result += 0;} 
						else if (r <= 3) {result += 10;} 
						else if (r <= 5) {result += 7;} 
						else if (r <= 10) {result += 4;}
					}
					if (Integer.parseInt(weight) >= dto.getWeight()) {
						int r = Integer.parseInt(weight) - dto.getWeight();
						if (r > 10) {result += 0;} 
						else if (r <= 3) {result += 10;} 
						else if (r <= 5) {result += 7;} 
						else if (r <= 10) {result += 4;}
					} else if (Integer.parseInt(weight) <= dto.getWeight()) {
						int r = dto.getWeight() - Integer.parseInt(weight);
						if (r > 10) {result += 0;} 
						else if (r <= 3) {result += 10;} 
						else if (r <= 5) {result += 7;} 
						else if (r <= 10) {result += 4;}
					}
					if (hobby.equals(dto.getHobby())) {result += 10;}
					if (blood.equals(dto.getBlood())) {result += 10;} 
					else if (!blood.equals(dto.getBlood())) {result += 5;}
					if (style.equals(dto.getStyle())) {result += 10;}
					if (fashion.equals(dto.getFashion())) {result += 10;}
					if (fashion.equals(dto.getFashion2())) {result += 10;}
					if (fashion.equals(dto.getFashion3())) {result += 10;}
					if (fashion2.equals(dto.getFashion())) {result += 10;}
					if (fashion2.equals(dto.getFashion2())) {result += 10;}
					if (fashion2.equals(dto.getFashion3())) {result += 10;}
					if (fashion3.equals(dto.getFashion())) {result += 10;}
					if (fashion3.equals(dto.getFashion2())) {result += 10;}
					if (fashion3.equals(dto.getFashion3())) {result += 10;}
					map.put(dto.getNo(), result);
				}
				sql = "select * from rating ORDER BY userID ASC";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\test.csv"));
				while (rs.next()) {
					bw.write(rs.getInt("userID") + "," + rs.getInt("itemID") + "," + rs.getInt("value") + "\n");
				}
				bw.close();
				DataModel model = new FileDataModel(new File("c:\\test.csv"));
				UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
				// 피어슨 상관 관계??
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
				// NearestNUserNeighborhood(int n, double minSimilarity,
				// UserSimilarity userSimilarity, DataModel dataModel)
				Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
				List<RecommendedItem> recommendations = recommender.recommend(no, 3);
				// recommend(long userID, int howMany)
				for (RecommendedItem recommendation : recommendations) {
						map2.put((int) recommendation.getItemID(), (int) (recommendation.getValue() * 10));
				}
				Set<Entry<Integer, Integer>> set2 = map2.entrySet();
				Iterator<Entry<Integer, Integer>> it2 = set2.iterator();
				while (it2.hasNext()) {
					Map.Entry<Integer, Integer> e = (Map.Entry<Integer, Integer>) it2.next();
					if (map.get(e.getKey()) != null) {
						map.put(e.getKey(), map.get(e.getKey()) + e.getValue());
					}
				}
				ValueComparator<Integer, Integer> comparator = new ValueComparator<Integer, Integer>(map);
				TreeMap<Integer, Integer> reverse = new TreeMap<Integer, Integer>(new ReverseComparator<Integer>(comparator));
				reverse.putAll(map);
				 /*System.out.println(reverse); 
				 System.out.println(reverse.firstKey()); 
				 System.out.println(reverse.get(reverse.firstKey())); */
				sql = "SELECT a.name, a.age, b.blood, b.height, b.weight, a.addr , b.style, b.fashion, b.hobby , a.photo  FROM member a, m_profile b WHERE a.no = ? AND a.no = b.no";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, reverse.firstKey());
				rs = stmt.executeQuery();
				rs.next();
				re.setNo(reverse.firstKey());
				re.setName(rs.getString("a.name"));
				re.setAge(Integer.parseInt(rs.getString("a.age")));
				re.setBlood(rs.getString("b.blood"));
				re.setHeight(Integer.parseInt(rs.getString("b.height")));
				re.setWeight(Integer.parseInt(rs.getString("b.weight")));
				String c[] = rs.getString("a.addr").split(" ");
				re.setAddr(c[1]);
				re.setStyle(rs.getString("b.style"));
				re.setFashion(rs.getString("b.fashion"));
				re.setHobby(rs.getString("b.hobby"));
				re.setPhoto(rs.getString("a.photo"));
				sql = "insert into `join`(userid, itemid, date ) values(?,?,now())";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, no);
				stmt.setInt(2, reverse.firstKey());
				stmt.executeUpdate();
				sql = "insert into rating(userid, itemid, value) values(?,?,0)";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, no);
				stmt.setInt(2, reverse.firstKey());
				stmt.executeUpdate();
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return re;
	}
	
	public void dash(someDto g){
		connect();
		String sql ="";		
		try{
			sql = "update rating set userID=?, itemID=?, value=? where userid=? and itemid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getUserID());
			stmt.setInt(2, g.getItemID());
			stmt.setFloat(3, g.getValue());
			stmt.setInt(4, g.getUserID());
			stmt.setInt(5, g.getItemID());
			stmt.executeUpdate();
			sql = "insert into dash(userID, itemID, coment, date) values(?,?,?,now())";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getUserID());
			stmt.setInt(2, g.getItemID());
			stmt.setString(3, g.getComent());
			stmt.executeUpdate();
		}catch(Exception err){
			System.out.println(err);
		}finally{
			discon();
		}
	}
	
	public String dashch(int userID, int itemID){
		connect();
		String sql ="";
		String result ="";
		try{
			sql = "select a.userID, a.itemID, a.coment, b.value from dash a , rating b where a.userID = ? and a.itemID = ? and a.userID=b.userID and a.itemID=b.itemID";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setInt(2, itemID);
			rs = stmt.executeQuery();
			if(rs.next()){
				result = rs.getString("a.coment") + "," + rs.getString("b.value");
			}else{
				result = "no,no";
			}
		}catch(Exception err){
			System.out.println(err);
		}finally{
			discon();
		}
		return result;
	}
	
	public void dashup(someDto g){
		connect();
		String sql ="";
		try{
			sql = "update rating set userID=?, itemID=?, value=? where userid=? and itemid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getUserID());
			stmt.setInt(2, g.getItemID());
			stmt.setFloat(3, g.getValue());
			stmt.setInt(4, g.getUserID());
			stmt.setInt(5, g.getItemID());
			stmt.executeUpdate();
			/*System.out.println(stmt);*/
			sql = "update dash set userID=?, itemID=?, coment=? where userid=? and itemid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getUserID());
			stmt.setInt(2, g.getItemID());
			stmt.setString(3, g.getComent());
			stmt.setInt(4, g.getUserID());
			stmt.setInt(5, g.getItemID());
			stmt.executeUpdate();
			/*System.out.println(stmt);*/
		}catch(Exception err){
			System.out.println(err);
		}finally{
			discon();
		}
	}
	
	public Vector<someDto> callme(int no){
		connect();
		String sql ="";
		Vector<someDto> v = new Vector<someDto>();
		try{
			sql = "select a.name, a.age, a.photo,b.blood, b.height, b.weight, a.addr, b.style, b.fashion, b.hobby, c.coment , c.userID, c.itemID , c.state from member a , m_profile b , dash c where a.no = b.no and a.no = c.userID and c.state = 0 and c.itemID = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while(rs.next()){
				someDto g = new someDto();
				g.setName(rs.getString("a.name"));
				g.setAge(rs.getInt("a.age"));
				g.setPhoto(rs.getString("a.photo"));
				g.setBlood(rs.getString("b.blood"));
				g.setHeight(rs.getInt("b.height"));
				g.setWeight(rs.getInt("b.weight"));
				String c[] = rs.getString("a.addr").split(" ");
				g.setAddr(c[1]);
				g.setStyle(rs.getString("b.style"));
				g.setFashion(rs.getString("b.fashion"));
				g.setHobby(rs.getString("b.hobby"));
				g.setComent(rs.getString("c.coment"));
				g.setUserID(rs.getInt("c.userID"));
				g.setItemID(rs.getInt("c.itemID"));
				g.setState(rs.getInt("c.state"));
				v.add(g);
			}
		}catch(Exception err){
			System.out.println(err);
		}finally{
			discon();
		}
		return v;
	}
	
	public int callme(int userID, int itemID, int state, String sex){
		connect();
		String sql ="";
		try{
			sql = "update dash set state=? where userid=? and itemid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, state);
			stmt.setInt(2, userID);
			stmt.setInt(3, itemID);
			stmt.executeUpdate();
			if(state == 1){
				sql = "insert into `some_some`(man_ID, woman_ID, state, `start_Day`) values(?,?,?,now())";
			/*	System.out.println("들어옴");*/
				if(sex.equals("man")){
					stmt = con.prepareStatement(sql);
					stmt.setInt(1, itemID);
					stmt.setInt(2, userID);
					stmt.setInt(3, state); 
					stmt.executeUpdate();
				}else{
					stmt = con.prepareStatement(sql);
					stmt.setInt(1, userID);
					stmt.setInt(2, itemID);
					stmt.setInt(3, state);
					stmt.executeUpdate();
				}
				return 1;
			}
		}catch(Exception err){
			System.out.println(err);
		}finally{
			discon();
		}
		return 2;
	}
	
	public boolean some_some(int no){
		connect();
		String sql ="";
		try{
			sql = "select * from `some_some` where man_ID = ? or woman_ID = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setInt(2, no);
			rs = stmt.executeQuery();
			rs.next();
			if(rs.getInt("state") == 1){
				return true;
			}
		}catch(Exception err){
			System.out.println(err);
		}finally{
			discon();
		}
		return false;
	}
	
	public String[] up(HttpServletRequest req) {
		connect();
		String file[] = new String[5];
		setPath(req, "upload");
		setMax(5 * 1024 * 1024);
		setEncType("UTF-8");
		String sql ="";
		try {
			sql = "select no from `some_board` order by no desc";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			int number;
			if(rs.next()){
				number = rs.getInt("no");
			}
			else{
				number = 0;
			}
			sql = "INSERT INTO `someboard_plus`(no, filename, desination, filesize, filetype, fileurl) VALUES(?,?,?,?,?,?)";
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
			System.out.println(err);
		} finally {
			discon();
		}
		return file;
	}
	

	public void some_board(someDto g) {
		connect();
		String sql = "INSERT INTO some_board(id, `id_no`, content, day) VALUES(?,?,?,now())";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, g.getId());
			stmt.setInt(2, g.getNo());
			stmt.setString(3, g.getContent());
			// System.out.println(stmt);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}
	
	public ArrayList<someDto> some_board_list(int no) {
		connect();
		ArrayList<someDto> g = new ArrayList<someDto>();
		String sql;
		try {
			sql = "select man_ID, woman_ID from some_some where man_ID = ? or woman_ID = ? and state = 1";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setInt(2, no);
			rs = stmt.executeQuery();
			rs.next();
			int id1 = rs.getInt("man_ID");
			int id2 = rs.getInt("woman_ID");
			sql = "select * from some_board where `id_no` = ? or `id_no` = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			rs = stmt.executeQuery();
			while (rs.next()) {
				someDto dto = new someDto();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getString("day"));
				dto.setHit(rs.getInt("hit"));
				dto.setLike(rs.getInt("like"));
				g.add(dto);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return g;
	}
	
	public void someplusdel(String name){
		connect();
		String sql;
		try {
			sql = "select * from `someboard_plus` where filename = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			rs.next();
			String path = rs.getString("desination");
			File f = new File(path);
			f.delete();
			sql = "DELETE FROM `someboard_plus` WHERE filename = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}
	
	public ArrayList<someDto> photo(int no){
		connect();
		ArrayList<someDto> g = new ArrayList<someDto>();
		String sql;
		try {
			sql = "select man_ID, woman_ID from some_some where man_ID = ? or woman_ID = ? and state = 1";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setInt(2, no);
			rs = stmt.executeQuery();
			rs.next();
			int id1 = rs.getInt("man_ID");
			int id2 = rs.getInt("woman_ID");
			/*System.out.println(stmt);*/
			sql = "select * from `someboard_plus` where no in (select no from some_board where `id_no` = ? or `id_no` = ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			rs = stmt.executeQuery();
		/*	System.out.println(stmt);*/
			while (rs.next()) {
				someDto dto = new someDto();
				dto.setNo(rs.getInt("no"));
				dto.setFilename(rs.getString("filename"));
				dto.setFiletype(rs.getString("filetype"));
				g.add(dto);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return g;
	}
	
	public String sha1(String s) {
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++)
	            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
	
	public int are(int id){
		connect();
		String sql;
		int cnt = 0;
		try {
			sql = "select * from dash where itemID = ? and state = 0";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				cnt++;
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return cnt;	
	}
	
	public boolean came(int id){
		connect();
		String sql;
		try {
			sql = "select * from dash where userID = ? and state = 1 and date = CURDATE()";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return false;	
	}
	
	public int news (String id){
		connect();
		int cnt = 0;
		try{
		String sql ="select * from board where id = ? and day = CURDATE() order by no desc";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, id);
		rs = stmt.executeQuery();
		while(rs.next()){
			cnt++;
		}
	} catch (SQLException e) {
		System.out.println("getBoardList() : " + e);
	}
	finally{
		discon();
	}
	return cnt;
}
	
	public int fiee(String id){
		connect();
		int cnt = 0;
		try{
		String sql ="select * from freind where userid2 = ? and friends = 'false'";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, id);
		rs = stmt.executeQuery();
		while(rs.next()){
			cnt++;
		}
	} catch (SQLException e) {
		System.out.println("getBoardList() : " + e);
	}
	finally{
		discon();
	}
	return cnt;
	}
	
	public someDto memberget(int no){
		someDto g = new someDto();
		connect();
		try{
		String sql ="select * from member where no = ? ";
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, no);
		rs = stmt.executeQuery();
		rs.next();
		g.setId(rs.getString("id"));
		g.setName(rs.getString("name"));
		g.setEmail(rs.getString("e-mail"));
		g.setTel(rs.getString("tel"));
		g.setAddr(rs.getString("addr"));
		g.setBirthday(rs.getString("birthday"));
		g.setSex(rs.getString("sex"));
		g.setPhoto(rs.getString("photo"));
	} catch (SQLException e) {
		System.out.println("getBoardList() : " + e);
	}
	finally{
		discon();
	}
		return g;
	}
	
	public void memberupdate(HttpServletRequest req, int no){
		connect();	
		try {
			req.setCharacterEncoding("UTF-8");
			String sql = "update  member set password=?, name=?, sex=?, birthday=?, addr=?, tel=?, photo=?, age=?, `e-mail`=? where no = ?";
			setPath(req, "profile");
			setMax(5 * 1024 * 1024);
			setEncType("UTF-8");
			multi = new MultipartRequest(req, path, max, encType, new DefaultFileRenamePolicy());
			stmt = con.prepareStatement(sql);
			File f = multi.getFile("imgInp");
			String email;
			String password = sha1(multi.getParameter("password"));
			if (multi.getParameter("email2").equals(null) || multi.getParameter("email2").equals("")) {
				email = multi.getParameter("email1") + "@" + multi.getParameter("email3");
			} else {
				email = multi.getParameter("email1") + "@" + multi.getParameter("email2");
			}
			int age = (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(multi.getParameter("year"))) + 1;
			stmt.setString(1, password);
			stmt.setString(2, multi.getParameter("name"));
			stmt.setString(3, multi.getParameter("sex"));
			stmt.setString(4, multi.getParameter("year") + "." + multi.getParameter("month") + "." + multi.getParameter("day"));
			stmt.setString(5, multi.getParameter("zip1") + "-" + multi.getParameter("zip2") + " " + multi.getParameter("juso") + " " + multi.getParameter("addr"));
			stmt.setString(6, multi.getParameter("tel") + "-" + multi.getParameter("tel2") + "-" + multi.getParameter("tel3"));
			stmt.setString(7, multi.getFilesystemName("imgInp"));
			stmt.setInt(8, age);
			stmt.setString(9, email);
			stmt.setInt(10, no);
			System.out.println(stmt);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}
	
	public someDto memberget2(int no){
		someDto g = new someDto();
		connect();
		try{
		String sql ="select * from `m_profile` where no = ? ";
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, no);
		rs = stmt.executeQuery();
		rs.next();
		g.setHeight(rs.getInt("height"));
		g.setHobby(rs.getString("hobby"));
		g.setBlood(rs.getString("blood"));
		g.setStyle(rs.getString("style"));
		g.setWeight(rs.getInt("weight"));
		g.setFashion(rs.getString("fashion"));
		
		sql ="select * from idealtype where no = ? ";
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, no);
		rs = stmt.executeQuery();
		rs.next();
		g.setHeight2(rs.getInt("height"));
		g.setHobby2(rs.getString("hobby"));
		g.setBlood2(rs.getString("blood"));
		g.setStyle2(rs.getString("style"));
		g.setWeight2(rs.getInt("weight"));
		g.setFashion2(rs.getString("fashion"));
		
	} catch (SQLException e) {
		System.out.println("getBoardList() : " + e);
	}
	finally{
		discon();
	}
		return g;
	}
	
	private static class ValueComparator<K extends Comparable<K>, V extends Comparable<V>>
			implements Comparator<K> {
		private Map<K, V> map;

		ValueComparator(Map<K, V> map) {
			this.map = map;
		}

		public int compare(K o1, K o2) {
			int p = map.get(o1).compareTo(map.get(o2));
			if (p != 0) {
				return p;
			}
			return o1.compareTo(o2);
		}
	}

	private static class ReverseComparator<T> implements Comparator<T> {
		private Comparator<T> comparator;

		ReverseComparator(Comparator<T> comparator) {
			this.comparator = comparator;
		}

		public int compare(T o1, T o2) {
			return -1 * comparator.compare(o1, o2);
		}
	}
}
