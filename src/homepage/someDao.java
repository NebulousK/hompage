package homepage;

import homepage.board.BoardDto;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
			System.out.println("에러 : " + err);
		}
	}

	public void discon() {
		pool.freeConnection(con, stmt, rs);
	}

	public someDto member(String id) {
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

	public MultipartRequest member_join(HttpServletRequest req) {
		connect();
		setPath(req, "profile");
		setMax(5 * 1024 * 1024);
		setEncType("UTF-8");
		String sql = "INSERT INTO member(id, password, name, sex, birthday, addr, tel, photo, age, `e-mail`,confirmState) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			multi = new MultipartRequest(req, path, max, encType,
					new DefaultFileRenamePolicy());
			stmt = con.prepareStatement(sql);
			File f = multi.getFile("imgInp");
			String email;
			String password = sha1(multi.getParameter("password"));
			if (multi.getParameter("email2").equals(null)
					|| multi.getParameter("email2").equals("")) {
				email = multi.getParameter("email1") + "@"
						+ multi.getParameter("email3");
			} else {
				email = multi.getParameter("email1") + "@"
						+ multi.getParameter("email2");
			}
			int age = (Calendar.getInstance().get(Calendar.YEAR) - Integer
					.parseInt(multi.getParameter("year"))) + 1;
			stmt.setString(1, multi.getParameter("id"));
			stmt.setString(2, password);
			stmt.setString(3, multi.getParameter("name"));
			stmt.setString(4, multi.getParameter("sex"));
			stmt.setString(
					5,
					multi.getParameter("year") + "."
							+ multi.getParameter("month") + "."
							+ multi.getParameter("day"));
			stmt.setString(
					6,
					multi.getParameter("zip1") + "-"
							+ multi.getParameter("zip2") + " "
							+ multi.getParameter("juso") + " "
							+ multi.getParameter("addr"));
			stmt.setString(
					7,
					multi.getParameter("tel") + "-"
							+ multi.getParameter("tel2") + "-"
							+ multi.getParameter("tel3"));
			stmt.setString(8, multi.getFilesystemName("imgInp"));
			stmt.setInt(9, age);
			stmt.setString(10, email);
			stmt.setString(11, multi.getParameter("confirmState"));
			// System.out.println(stmt);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return multi;
	}

	public void member_detail(someDto g) {
		connect();
		String sql;
		try {
			sql = "select no from member order by no desc";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
			int no = rs.getInt("no");
			sql = "INSERT INTO m_profile(no, height, hobby, blood, style, weight, fashion) VALUES(?, ?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setInt(2, g.getHeight());
			stmt.setString(3, g.getHobby());
			stmt.setString(4, g.getBlood());
			stmt.setString(5, g.getStyle());
			stmt.setInt(6, g.getWeight());
			stmt.setString(7, g.getFashion());
			/* System.out.println(stmt); */
			stmt.executeUpdate();
			stmt = null;
			sql = "INSERT INTO idealtype(no, height, hobby, blood, style, weight, fashion, age) VALUES(?, ?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setInt(2, g.getHeight2());
			stmt.setString(3, g.getHobby2());
			stmt.setString(4, g.getBlood2());
			stmt.setString(5, g.getStyle2());
			stmt.setInt(6, g.getWeight2());
			stmt.setString(7, g.getFashion2());
			stmt.setInt(8, g.getAge());
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
					set += rs.getString("name") + ",";
					int b = some_some2(rs.getInt("no"));
					if (b != 0) {
						set += b;
					} else {
						set += 0;
					}
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
			} else {
				sql = "select * from idealtype where no = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, no);
				rs = stmt.executeQuery();
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
				if (!arr[1].equals(null)) {
					fashion2 = arr[1];
				}
				if (!arr[2].equals(null)) {
					fashion3 = arr[2];
				}
				sql = "SELECT a.no ,a.age, b.height, b.hobby, b.blood,b.style, b.weight, b.fashion FROM member a, m_profile b WHERE a.sex = ? AND a.no = b.no AND a.age <= ?  AND a.no not in (select itemid from `join` where userid = ?) and a.no not in (select `man_ID` from `some_some` where state = ?) and a.no not in (select `woman_ID` from `some_some` where state = ?)";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, sex);
				stmt.setInt(2, rs.getInt("age"));
				stmt.setInt(3, no);
				stmt.setInt(4, 1);
				stmt.setInt(5, 1);
				rs = stmt.executeQuery();
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
					if (!arr1[1].equals(null)) {
						dto.setFashion2(arr1[1]);
					}
					if (!arr1[2].equals(null)) {
						dto.setFashion3(arr1[2]);
					}
					g.add(dto);
				}
				for (int i = 0; i < g.size(); i++) {
					someDto dto = g.get(i);
					int result = 0;
					if (height >= dto.getHeight()) {
						int r = height - dto.getHeight();
						if (r > 10) {
							result += 0;
						} else if (r <= 3) {
							result += 10;
						} else if (r <= 5) {
							result += 7;
						} else if (r <= 10) {
							result += 4;
						}
					} else if (height <= dto.getHeight()) {
						int r = dto.getHeight() - height;
						if (r > 10) {
							result += 0;
						} else if (r <= 3) {
							result += 10;
						} else if (r <= 5) {
							result += 7;
						} else if (r <= 10) {
							result += 4;
						}
					}
					if (Integer.parseInt(weight) >= dto.getWeight()) {
						int r = Integer.parseInt(weight) - dto.getWeight();
						if (r > 10) {
							result += 0;
						} else if (r <= 3) {
							result += 10;
						} else if (r <= 5) {
							result += 7;
						} else if (r <= 10) {
							result += 4;
						}
					} else if (Integer.parseInt(weight) <= dto.getWeight()) {
						int r = dto.getWeight() - Integer.parseInt(weight);
						if (r > 10) {
							result += 0;
						} else if (r <= 3) {
							result += 10;
						} else if (r <= 5) {
							result += 7;
						} else if (r <= 10) {
							result += 4;
						}
					}
					if (hobby.equals(dto.getHobby())) {
						result += 10;
					}
					if (blood.equals(dto.getBlood())) {
						result += 10;
					} else if (!blood.equals(dto.getBlood())) {
						result += 5;
					}
					if (style.equals(dto.getStyle())) {
						result += 10;
					}
					if (fashion.equals(dto.getFashion())) {
						result += 10;
					}
					if (fashion.equals(dto.getFashion2())) {
						result += 10;
					}
					if (fashion.equals(dto.getFashion3())) {
						result += 10;
					}
					if (fashion2.equals(dto.getFashion())) {
						result += 10;
					}
					if (fashion2.equals(dto.getFashion2())) {
						result += 10;
					}
					if (fashion2.equals(dto.getFashion3())) {
						result += 10;
					}
					if (fashion3.equals(dto.getFashion())) {
						result += 10;
					}
					if (fashion3.equals(dto.getFashion2())) {
						result += 10;
					}
					if (fashion3.equals(dto.getFashion3())) {
						result += 10;
					}
					map.put(dto.getNo(), result);
				}
				sql = "select * from rating ORDER BY userID ASC";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				BufferedWriter bw = new BufferedWriter(new FileWriter("/home/ubuntu/test/test.csv"));
				while (rs.next()) {
					bw.write(rs.getInt("userID") + "," + rs.getInt("itemID")
							+ "," + rs.getInt("value") + "\n");
				}
				bw.close();
				DataModel model = new FileDataModel(new File("/home/ubuntu/test/test.csv"));
				UserSimilarity similarity = new PearsonCorrelationSimilarity(
						model);
				// 피어슨 상관 관계??
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(2,
						similarity, model);
				// NearestNUserNeighborhood(int n, double minSimilarity,
				// UserSimilarity userSimilarity, DataModel dataModel)
				Recommender recommender = new GenericUserBasedRecommender(
						model, neighborhood, similarity);
				List<RecommendedItem> recommendations = recommender.recommend(
						no, 3);
				// recommend(long userID, int howMany)
				for (RecommendedItem recommendation : recommendations) {
					map2.put((int) recommendation.getItemID(),
							(int) (recommendation.getValue() * 10));
				}
				Set<Entry<Integer, Integer>> set2 = map2.entrySet();
				Iterator<Entry<Integer, Integer>> it2 = set2.iterator();
				while (it2.hasNext()) {
					Map.Entry<Integer, Integer> e = (Map.Entry<Integer, Integer>) it2
							.next();
					if (map.get(e.getKey()) != null) {
						map.put(e.getKey(), map.get(e.getKey()) + e.getValue());
					}
				}
				ValueComparator<Integer, Integer> comparator = new ValueComparator<Integer, Integer>(
						map);
				TreeMap<Integer, Integer> reverse = new TreeMap<Integer, Integer>(
						new ReverseComparator<Integer>(comparator));
				reverse.putAll(map);
				/*
				 * System.out.println(reverse);
				 * System.out.println(reverse.firstKey());
				 * System.out.println(reverse.get(reverse.firstKey()));
				 */
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

	public String midealtype(int no, String sex) {
		connect();
		ArrayList<someDto> g = new ArrayList<someDto>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		String sql = "";
		someDto re = new someDto();
		int age = 0, height = 0;
		String hobby = null, fashions = null, blood = null, style = null, weight = null, fashion = null, fashion2 = null, fashion3 = null;
		String returnStr = "";
		String err = "'err':''";
		String psnDetail = "'psn_detail':''";
		StringBuffer tempData = new StringBuffer();
		try {
			sql = "select * from `join` where userid = ? and date = CURDATE()";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
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
				PreparedStatement stmt2;
				ResultSet rs2;
				String sql2 = "select a.userID, a.itemID, a.coment, b.value from dash a , rating b where a.userID = ? and a.itemID = ? and a.userID=b.userID and a.itemID=b.itemID";
				stmt2 = con.prepareStatement(sql2);
				stmt2.setInt(1, no);
				stmt2.setInt(2, rs.getInt("a.no"));
				rs2 = stmt2.executeQuery();
				if (rs2.next()) {
					tempData.append("{")
							.append("'no':'")
							.append(rs.getString("no"))
							.append("',")
							.append("'name':'")
							.append(rs.getString("name"))
							.append("',")
							.append("'age':'")
							.append(rs.getString("age"))
							.append("',")
							.append("'blood':'")
							.append(rs.getString("blood"))
							.append("',")
							.append("'height':'")
							.append(rs.getString("height"))
							.append("',")
							.append("'weight':'")
							.append(rs.getString("weight"))
							.append("',")
							.append("'addr':'")
							.append(c[1])
							.append("',")
							.append("'style':'")
							.append(rs.getString("style"))
							.append("',")
							.append("'fashion':'")
							.append(rs.getString("fashion"))
							.append("',")
							.append("'coment':'")
							.append(rs2.getString("coment").replace("\n",
									"<br>")).append("',").append("'value':'")
							.append(rs2.getString("value")).append("',")
							.append("'hobby':'").append(rs.getString("hobby"))
							.append("',").append("'pic':'")
							.append(rs.getString("photo")).append("'")
							.append("}");
				} else {
					tempData.append("{").append("'no':'")
							.append(rs.getString("no")).append("',")
							.append("'name':'").append(rs.getString("name"))
							.append("',").append("'age':'")
							.append(rs.getString("age")).append("',")
							.append("'blood':'").append(rs.getString("blood"))
							.append("',").append("'height':'")
							.append(rs.getString("height")).append("',")
							.append("'weight':'")
							.append(rs.getString("weight")).append("',")
							.append("'addr':'").append(c[1]).append("',")
							.append("'style':'").append(rs.getString("style"))
							.append("',").append("'fashion':'")
							.append(rs.getString("fashion")).append("',")
							.append("'hobby':'").append(rs.getString("hobby"))
							.append("',").append("'pic':'")
							.append(rs.getString("photo")).append("'")
							.append("}");
				}
				psnDetail = "'psn_detail':" + tempData.toString() + "";
				returnStr = "{'data':{" + psnDetail + "," + err + "}}";
			} else {
				sql = "select * from idealtype where no = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, no);
				rs = stmt.executeQuery();
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
				if (!arr[1].equals(null)) {
					fashion2 = arr[1];
				}
				if (!arr[2].equals(null)) {
					fashion3 = arr[2];
				}
				sql = "SELECT a.no ,a.age, b.height, b.hobby, b.blood,b.style, b.weight, b.fashion FROM member a, m_profile b WHERE a.sex = ? AND a.no = b.no AND a.age <= ?  AND a.no not in (select itemid from `join` where userid = ?) and a.no not in (select `man_ID` from `some_some` where state = ?) and a.no not in (select `woman_ID` from `some_some` where state = ?)";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, sex);
				stmt.setInt(2, rs.getInt("age"));
				stmt.setInt(3, no);
				stmt.setInt(4, 1);
				stmt.setInt(5, 1);
				rs = stmt.executeQuery();
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
					if (!arr1[1].equals(null)) {
						dto.setFashion2(arr1[1]);
					}
					if (!arr1[2].equals(null)) {
						dto.setFashion3(arr1[2]);
					}
					g.add(dto);
				}
				for (int i = 0; i < g.size(); i++) {
					someDto dto = g.get(i);
					int result = 0;
					if (height >= dto.getHeight()) {
						int r = height - dto.getHeight();
						if (r > 10) {
							result += 0;
						} else if (r <= 3) {
							result += 10;
						} else if (r <= 5) {
							result += 7;
						} else if (r <= 10) {
							result += 4;
						}
					} else if (height <= dto.getHeight()) {
						int r = dto.getHeight() - height;
						if (r > 10) {
							result += 0;
						} else if (r <= 3) {
							result += 10;
						} else if (r <= 5) {
							result += 7;
						} else if (r <= 10) {
							result += 4;
						}
					}
					if (Integer.parseInt(weight) >= dto.getWeight()) {
						int r = Integer.parseInt(weight) - dto.getWeight();
						if (r > 10) {
							result += 0;
						} else if (r <= 3) {
							result += 10;
						} else if (r <= 5) {
							result += 7;
						} else if (r <= 10) {
							result += 4;
						}
					} else if (Integer.parseInt(weight) <= dto.getWeight()) {
						int r = dto.getWeight() - Integer.parseInt(weight);
						if (r > 10) {
							result += 0;
						} else if (r <= 3) {
							result += 10;
						} else if (r <= 5) {
							result += 7;
						} else if (r <= 10) {
							result += 4;
						}
					}
					if (hobby.equals(dto.getHobby())) {
						result += 10;
					}
					if (blood.equals(dto.getBlood())) {
						result += 10;
					} else if (!blood.equals(dto.getBlood())) {
						result += 5;
					}
					if (style.equals(dto.getStyle())) {
						result += 10;
					}
					if (fashion.equals(dto.getFashion())) {
						result += 10;
					}
					if (fashion.equals(dto.getFashion2())) {
						result += 10;
					}
					if (fashion.equals(dto.getFashion3())) {
						result += 10;
					}
					if (fashion2.equals(dto.getFashion())) {
						result += 10;
					}
					if (fashion2.equals(dto.getFashion2())) {
						result += 10;
					}
					if (fashion2.equals(dto.getFashion3())) {
						result += 10;
					}
					if (fashion3.equals(dto.getFashion())) {
						result += 10;
					}
					if (fashion3.equals(dto.getFashion2())) {
						result += 10;
					}
					if (fashion3.equals(dto.getFashion3())) {
						result += 10;
					}
					map.put(dto.getNo(), result);
				}
				sql = "select * from rating ORDER BY userID ASC";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"/home/ubuntu/test/test.csv"));
				while (rs.next()) {
					bw.write(rs.getInt("userID") + "," + rs.getInt("itemID")
							+ "," + rs.getInt("value") + "\n");
				}
				bw.close();
				DataModel model = new FileDataModel(new File("/home/ubuntu/test/test.csv"));
				UserSimilarity similarity = new PearsonCorrelationSimilarity(
						model);
				// 피어슨 상관 관계??
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(2,
						similarity, model);
				// NearestNUserNeighborhood(int n, double minSimilarity,
				// UserSimilarity userSimilarity, DataModel dataModel)
				Recommender recommender = new GenericUserBasedRecommender(
						model, neighborhood, similarity);
				List<RecommendedItem> recommendations = recommender.recommend(
						no, 3);
				// recommend(long userID, int howMany)
				for (RecommendedItem recommendation : recommendations) {
					map2.put((int) recommendation.getItemID(),
							(int) (recommendation.getValue() * 10));
				}
				Set<Entry<Integer, Integer>> set2 = map2.entrySet();
				Iterator<Entry<Integer, Integer>> it2 = set2.iterator();
				while (it2.hasNext()) {
					Map.Entry<Integer, Integer> e = (Map.Entry<Integer, Integer>) it2
							.next();
					if (map.get(e.getKey()) != null) {
						map.put(e.getKey(), map.get(e.getKey()) + e.getValue());
					}
				}
				ValueComparator<Integer, Integer> comparator = new ValueComparator<Integer, Integer>(
						map);
				TreeMap<Integer, Integer> reverse = new TreeMap<Integer, Integer>(
						new ReverseComparator<Integer>(comparator));
				reverse.putAll(map);
				/*
				 * System.out.println(reverse);
				 * System.out.println(reverse.firstKey());
				 * System.out.println(reverse.get(reverse.firstKey()));
				 */
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
				tempData.append("{").append("'name':'")
						.append(rs.getString("name")).append("',")
						.append("'age':'").append(rs.getString("age"))
						.append("',").append("'blood':'")
						.append(rs.getString("blood")).append("',")
						.append("'height':'").append(rs.getString("height"))
						.append("',").append("'weight':'")
						.append(rs.getString("weight")).append("',")
						.append("'addr':'").append(c[1]).append("',")
						.append("'style':'").append(rs.getString("style"))
						.append("',").append("'fashion':'")
						.append(rs.getString("fashion")).append("',")
						.append("'hobby':'").append(rs.getString("hobby"))
						.append("',").append("'pic':'")
						.append(rs.getString("photo")).append("'").append("}");
				psnDetail = "'psn_detail':" + tempData.toString() + "";
				returnStr = "{'data':{" + psnDetail + "," + err + "}}";
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
		} catch (Exception err2) {
			System.out.println("aaa" + err2);
		} finally {
			discon();
		}
		return returnStr;
	}

	public void dash(someDto g) {
		connect();
		String sql = "";
		try {
			sql = "update rating set userID=?, itemID=?, value=? where userid=? and itemid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getUserID());
			stmt.setInt(2, g.getItemID());
			stmt.setFloat(3, g.getValue());
			stmt.setInt(4, g.getUserID());
			stmt.setInt(5, g.getItemID());
			stmt.executeUpdate();
			// System.out.println(stmt);
			sql = "insert into dash(userID, itemID, coment, date) values(?,?,?,now())";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getUserID());
			stmt.setInt(2, g.getItemID());
			stmt.setString(3, g.getComent());
			stmt.executeUpdate();
			// System.out.println(stmt);
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public void mdash(someDto g) {
		connect();
		String sql = "";
		PreparedStatement stmt2;
		try {
			sql = "update rating set userID=?, itemID=?, value=? where userid=? and itemid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getUserID());
			stmt.setInt(2, g.getItemID());
			stmt.setFloat(3, g.getValue());
			stmt.setInt(4, g.getUserID());
			stmt.setInt(5, g.getItemID());
			stmt.executeUpdate();
			sql = "select * from dash where userid=? and itemid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getUserID());
			stmt.setInt(2, g.getItemID());
			rs = stmt.executeQuery();
			if (rs.next()) {
				sql = "update dash set coment=? where userid=? and itemid=?";
				stmt2 = con.prepareStatement(sql);
				stmt2.setString(1, g.getComent());
				stmt2.setInt(2, g.getUserID());
				stmt2.setInt(3, g.getItemID());
				stmt2.executeUpdate();
			} else {
				sql = "insert into dash(userID, itemID, coment, date) values(?,?,?,now())";
				stmt2 = con.prepareStatement(sql);
				stmt2.setInt(1, g.getUserID());
				stmt2.setInt(2, g.getItemID());
				stmt2.setString(3, g.getComent());
				stmt2.executeUpdate();
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public String dashch(int userID, int itemID) {
		connect();
		String sql = "";
		String result = "";
		try {
			sql = "select a.userID, a.itemID, a.coment, b.value from dash a , rating b where a.userID = ? and a.itemID = ? and a.userID=b.userID and a.itemID=b.itemID";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setInt(2, itemID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("a.coment") + ","
						+ rs.getString("b.value");
			} else {
				result = "no,no";
			}
		} catch (Exception err) {
			System.out.println("dashch:" + err);
		} finally {
			discon();
		}
		return result;
	}

	public void dashup(someDto g) {
		connect();
		String sql = "";
		try {
			sql = "update rating set userID=?, itemID=?, value=? where userid=? and itemid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getUserID());
			stmt.setInt(2, g.getItemID());
			stmt.setFloat(3, g.getValue());
			stmt.setInt(4, g.getUserID());
			stmt.setInt(5, g.getItemID());
			stmt.executeUpdate();
			/* System.out.println(stmt); */
			sql = "update dash set userID=?, itemID=?, coment=? where userid=? and itemid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getUserID());
			stmt.setInt(2, g.getItemID());
			stmt.setString(3, g.getComent());
			stmt.setInt(4, g.getUserID());
			stmt.setInt(5, g.getItemID());
			stmt.executeUpdate();
			/* System.out.println(stmt); */
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public Vector<someDto> callme(int no) {
		connect();
		String sql = "";
		Vector<someDto> v = new Vector<someDto>();
		try {
			sql = "select a.name, a.age, a.photo,b.blood, b.height, b.weight, a.addr, b.style, b.fashion, b.hobby, c.coment , c.userID, c.itemID , c.state from member a , m_profile b , dash c where a.no = b.no and a.no = c.userID and c.state = 0 and c.itemID = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while (rs.next()) {
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
		} catch (Exception err) {
			System.out.println("callme" + err);
		} finally {
			discon();
		}
		return v;
	}

	public String mcallme(int no) {
		connect();
		String sql = "";
		String returnStr = "";
		String err = "'err':''";
		String callmeList = "'callme_list':''";
		StringBuffer tempList = new StringBuffer();
		try {
			sql = "select a.name, a.age, a.photo,b.blood, b.height, b.weight, a.addr, b.style, b.fashion, b.hobby, c.coment , c.userID, c.itemID , c.state from member a , m_profile b , dash c where a.no = b.no and a.no = c.userID and c.state = 0 and c.itemID = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				if (i != 0) {
					tempList.append(",");
				}
				String c[] = rs.getString("a.addr").split(" ");
				tempList.append("{'photo':'").append(rs.getString("a.photo"))
						.append("','name':'").append(rs.getString("a.name"))
						.append("','addr':'").append(c[1]).append("'}");
				i++;
			}
			callmeList = "'callme_list':[" + tempList.toString() + "]";
			returnStr = "{'data':{" + callmeList + "," + err + "}}";
		} catch (Exception err2) {
			System.out.println("callme" + err2);
		} finally {
			discon();
		}
		return returnStr;
	}

	public String mcallme_detail(int no, String name) {
		connect();
		String sql = "";
		String returnStr = "";
		String err = "'err':''";
		String callmeList = "'callme_list':''";
		StringBuffer tempList = new StringBuffer();
		try {
			sql = "select a.no, a.name, a.age, a.photo,b.blood, b.height, b.weight, a.addr, b.style, b.fashion, b.hobby, c.coment , c.userID, c.itemID , c.state from member a , m_profile b , dash c where a.no = b.no and a.no = c.userID and c.state = 0 and c.itemID = ? and a.name=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setString(2, name);
			rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				if (i != 0) {
					tempList.append(",");
				}
				String c[] = rs.getString("a.addr").split(" ");
				tempList.append("{").append("'no':'")
						.append(rs.getString("no")).append("',")
						.append("'name':'").append(rs.getString("name"))
						.append("',").append("'age':'")
						.append(rs.getString("age")).append("',")
						.append("'blood':'").append(rs.getString("blood"))
						.append("',").append("'height':'")
						.append(rs.getString("height")).append("',")
						.append("'weight':'").append(rs.getString("weight"))
						.append("',").append("'addr':'").append(c[1])
						.append("',").append("'style':'")
						.append(rs.getString("style")).append("',")
						.append("'fashion':'").append(rs.getString("fashion"))
						.append("',").append("'hobby':'")
						.append(rs.getString("hobby")).append("',")
						.append("'comment':'").append(rs.getString("coment"))
						.append("',").append("'pic':'")
						.append(rs.getString("photo")).append("'").append("}");
				i++;
			}
			callmeList = "'callme':" + tempList.toString() + "";
			returnStr = "{'data':{" + callmeList + "," + err + "}}";
		} catch (Exception err2) {
			System.out.println("callme" + err2);
		} finally {
			discon();
		}
		return returnStr;
	}

	public int callme(int userID, int itemID, int state, String sex) {
		connect();
		String sql = "";
		try {
			sql = "update dash set state=? where userid=? and itemid=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, state);
			stmt.setInt(2, userID);
			stmt.setInt(3, itemID);
			stmt.executeUpdate();
			// System.out.println(stmt);
			if (state == 1) {
				sql = "insert into `some_some`(man_ID, woman_ID, state, `start_Day`) values(?,?,?,now())";
				// System.out.println("들어옴");
				if (sex.equals("man")) {
					stmt = con.prepareStatement(sql);
					stmt.setInt(1, itemID);
					stmt.setInt(2, userID);
					stmt.setInt(3, state);
					stmt.executeUpdate();
				} else {
					stmt = con.prepareStatement(sql);
					stmt.setInt(1, userID);
					stmt.setInt(2, itemID);
					stmt.setInt(3, state);
					stmt.executeUpdate();
				}
				return 1;
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return 2;
	}

	public boolean some_some(int no) {
		connect();
		String sql = "";
		try {
			sql = "select * from `some_some` where man_ID = ? or woman_ID = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setInt(2, no);
			rs = stmt.executeQuery();
			if(rs.next()){
				if (rs.getInt("state") == 1) {
					return true;
				}
			}
		} catch (Exception err) {
			System.out.println("some_some" + err);
		} finally {
			discon();
		}
		return false;
	}

	public int some_some2(int no) {
		connect();
		String sql = "";
		int a = 0;
		try {
			sql = "select * from `some_some` where man_ID = ? or woman_ID = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setInt(2, no);
			rs = stmt.executeQuery();
			rs.next();
			if (rs.getInt("state") == 1) {
				a = rs.getInt("no");
				return a;
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return a;
	}

	public String[] up(HttpServletRequest req) {
		connect();
		String file[] = new String[5];
		setPath(req, "upload");
		setMax(5 * 1024 * 1024);
		setEncType("UTF-8");
		String sql = "";
		try {
			sql = "select no from `some_board` order by no desc";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			int number;
			if (rs.next()) {
				number = rs.getInt("no");
			} else {
				number = 0;
			}
			sql = "INSERT INTO `someboard_plus`(no, filename, desination, filesize, filetype, fileurl) VALUES(?,?,?,?,?,?)";
			multi = new MultipartRequest(req, path, max, encType,
					new DefaultFileRenamePolicy());
			stmt = con.prepareStatement(sql);
			File f = multi.getFile("upload_file");
			stmt.setInt(1, number + 1);
			stmt.setString(2, multi.getFilesystemName("upload_file"));
			stmt.setString(3, f.getPath());
			stmt.setFloat(4, f.length());
			stmt.setString(5, multi.getContentType("upload_file"));
			stmt.setString(
					6,
					"/homepage/upload/"
							+ multi.getFilesystemName("upload_file"));
			/* System.out.println(stmt); */
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
			sql = "select a.id, a.no, a.content, a.day, a.hit, a.like, b.photo from some_board a, member b where b.no = a.id_no and (a.id_no = ? or a.id_no = ?) order by a.no desc";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			rs = stmt.executeQuery();
			while (rs.next()) {
				someDto dto = new someDto();
				dto.setId(rs.getString("id"));
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setDay(rs.getString("day"));
				dto.setHit(rs.getInt("hit"));
				dto.setLike(rs.getInt("like"));
				dto.setPhoto(rs.getString("photo"));
				g.add(dto);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return g;
	}

	public String msome_board_list(int no, int num) {
		connect();
		String sql;
		ArrayList<someDto> g = new ArrayList<someDto>();
		String returnStr = "";
		String boardList = "'board_list':''";
		String err2 = "\"err\":\"\"";
		JSONArray result = new JSONArray();
		try {
			sql = "select man_ID, woman_ID from some_some where man_ID = ? or woman_ID = ? and state = 1";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setInt(2, no);
			rs = stmt.executeQuery();
			rs.next();
			int id1 = rs.getInt("man_ID");
			int id2 = rs.getInt("woman_ID");
			sql = "select a.id, a.no, a.content, a.day, a.hit, a.like, b.photo from some_board a, member b where b.no = a.id_no and (a.id_no = ? or a.id_no = ?) order by a.no desc";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			rs = stmt.executeQuery();
			while (rs.next()) {
				someDto dto = new someDto();
				dto.setId(rs.getString("id"));
				dto.setNo(rs.getInt("no"));
				dto.setContent(rs.getString("content"));
				dto.setDay(rs.getString("day"));
				dto.setPhoto(rs.getString("photo"));
				g.add(dto);
			}
			if (num + 5 < g.size()) {
				for (int j = num; j < num + 5; j++) {
					someDto rs = g.get(j);
					JSONObject obj = new JSONObject();
					obj.put("pic", "http://54.178.192.82/homepage/profile/"
							+ rs.getPhoto());
					obj.put("name", rs.getId());
					obj.put("date", rs.getDay());
					obj.put("content", rs.getContent().replace("\"", "\'"));
					result.add(obj);
				}
			} else {
				for (int j = num; j < g.size(); j++) {
					someDto rs = g.get(j);
					JSONObject obj = new JSONObject();
					obj.put("pic", "http://54.178.192.82/homepage/profile/"
							+ rs.getPhoto());
					obj.put("name", rs.getId());
					obj.put("date", rs.getDay());
					obj.put("content", rs.getContent().replace("\"", "\'"));
					result.add(obj);
				}
			}
			boardList = "\"someboard_list\":" + result.toString();
		} catch (Exception err) {
			System.out.println(err);
			err2 = err.toString();
		} finally {
			discon();
		}
		return returnStr = "{\"data\":{" + boardList + "," + err2 + "}}";
	}

	public void upsomeboard(int no, String content) {
		connect();
		String sql;
		try {
			sql = "UPDATE `some_board` SET content=? WHERE no=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, content);
			stmt.setInt(2, no);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public void delsomeboard(int no) {
		connect();
		String sql;
		try {
			sql = "DELETE FROM `some_board` WHERE no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();

			sql = "select * from `someboard_plus` where no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String path = rs.getString("desination");
				File f = new File(path);
				f.delete();
			}
			sql = "DELETE FROM `someboard_plus` WHERE no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}

	}

	public void someplusdel(String name) {
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

	public someDto getsomeboard(int no) {
		connect();
		String sql;
		someDto dto = new someDto();
		try {
			sql = "select no, content from `some_board` where no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			rs.next();
			dto.setNo(rs.getInt("no"));
			dto.setContent(rs.getString("content"));
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return dto;
	}

	public ArrayList<someDto> photo(int no) {
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
			/* System.out.println(stmt); */
			sql = "select * from `someboard_plus` where no in (select no from some_board where `id_no` = ? or `id_no` = ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			rs = stmt.executeQuery();
			/* System.out.println(stmt); */
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

	public JSONArray mphoto(int no) {
		connect();
		String sql;
		JSONArray result = new JSONArray();
		try {
			sql = "select man_ID, woman_ID from some_some where man_ID = ? or woman_ID = ? and state = 1";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setInt(2, no);
			rs = stmt.executeQuery();
			rs.next();
			int id1 = rs.getInt("man_ID");
			int id2 = rs.getInt("woman_ID");
			/* System.out.println(stmt); */
			sql = "select * from `someboard_plus` where no in (select no from some_board where `id_no` = ? or `id_no` = ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			rs = stmt.executeQuery();
			/* System.out.println(stmt); */
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("name", rs.getString("fileurl"));
				obj.put("size", rs.getString("filesize"));
				obj.put("date", rs.getString("filetype"));
				obj.put("path", rs.getString("fileurl"));
				obj.put("thumb", rs.getString("fileurl"));
				result.add(obj);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return result;
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

	public int are(int id) {
		connect();
		String sql;
		int cnt = 0;
		try {
			sql = "select * from dash where itemID = ? and state = 0";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				cnt++;
			}
		} catch (Exception err) {
			System.out.println("ard :" + err);
		} finally {
			discon();
		}
		return cnt;
	}

	public boolean came(int id) {
		connect();
		String sql;
		try {
			sql = "select * from dash where userID = ? and state = 1 and date = CURDATE()";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception err) {
			System.out.println("cam" + err);
		} finally {
			discon();
		}
		return false;
	}

	public int news(String id) {
		connect();
		int cnt = 0;
		try {
			String sql = "select * from board where id = ? and day = CURDATE() order by no desc";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				cnt++;
			}
		} catch (SQLException e) {
			System.out.println("news" + e);
		} finally {
			discon();
		}
		return cnt;
	}

	public int fiee(String id) {
		connect();
		int cnt = 0;
		try {
			String sql = "select * from freind where userid2 = ? and friends = 'false'";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				cnt++;
			}
		} catch (SQLException e) {
			System.out.println("fiee " + e);
		} finally {
			discon();
		}
		return cnt;
	}

	public someDto memberget(int no) {
		someDto g = new someDto();
		connect();
		try {
			String sql = "select * from member where no = ? ";
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
		} finally {
			discon();
		}
		return g;
	}

	public someDto membergetid(String id) {
		someDto g = new someDto();
		connect();
		try {
			String sql = "select * from member where id = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			rs.next();
			g.setNo(rs.getInt("no"));
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
		} finally {
			discon();
		}
		return g;
	}

	public someDto membergetname(String name) {
		someDto g = new someDto();
		connect();
		try {
			String sql = "select * from member where name = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			rs.next();
			g.setNo(rs.getInt("no"));
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
		} finally {
			discon();
		}
		return g;
	}

	public void memberupdate(HttpServletRequest req, int no) {
		connect();
		try {
			req.setCharacterEncoding("UTF-8");
			String sql = "update  member set password=?, name=?, sex=?, birthday=?, addr=?, tel=?, age=?, `e-mail`=? where no = ?";
			stmt = con.prepareStatement(sql);
			String email;
			String password = sha1(req.getParameter("password"));
			if (req.getParameter("email2").equals(null)
					|| req.getParameter("email2").equals("")) {
				email = req.getParameter("email1") + "@"
						+ req.getParameter("email3");
			} else {
				email = req.getParameter("email1") + "@"
						+ req.getParameter("email2");
			}
			int age = (Calendar.getInstance().get(Calendar.YEAR) - Integer
					.parseInt(req.getParameter("year"))) + 1;
			stmt.setString(1, password);
			stmt.setString(2, req.getParameter("name"));
			stmt.setString(3, req.getParameter("sex"));
			stmt.setString(4,
					req.getParameter("year") + "." + req.getParameter("month")
							+ "." + req.getParameter("day"));
			stmt.setString(
					5,
					req.getParameter("zip1") + "-" + req.getParameter("zip2")
							+ " " + req.getParameter("juso") + " "
							+ req.getParameter("addr"));
			stmt.setString(6,
					req.getParameter("tel") + "-" + req.getParameter("tel2")
							+ "-" + req.getParameter("tel3"));
			stmt.setInt(7, age);
			stmt.setString(8, email);
			stmt.setInt(9, no);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public someDto memberget2(int no) {
		someDto g = new someDto();
		connect();
		try {
			String sql = "select * from `m_profile` where no = ? ";
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

			sql = "select * from idealtype where no = ? ";
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
			g.setAge(rs.getInt("age"));
		} catch (SQLException e) {
			System.out.println("getBoardList() : " + e);
		} finally {
			discon();
		}
		return g;
	}

	public void memberupdate2(someDto g, int no) {
		connect();
		try {
			String sql = "update  `m_profile` set height=?, hobby=?, blood=?, style=?, weight=?, fashion=? where no=? ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getHeight());
			stmt.setString(2, g.getHobby());
			stmt.setString(3, g.getBlood());
			stmt.setString(4, g.getStyle());
			stmt.setInt(5, g.getWeight());
			stmt.setString(6, g.getFashion());
			stmt.setInt(7, no);
			stmt.executeUpdate();

			sql = "update  `idealtype` set height=?, hobby=?, blood=?, style=?, weight=?, fashion=?, age=? where no=? ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getHeight2());
			stmt.setString(2, g.getHobby2());
			stmt.setString(3, g.getBlood2());
			stmt.setString(4, g.getStyle2());
			stmt.setInt(5, g.getWeight2());
			stmt.setString(6, g.getFashion2());
			stmt.setInt(7, g.getAge());
			stmt.setInt(8, no);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public boolean getout(int no, String id, String passwd) {
		connect();
		try {
			String sql = "select * from member where no=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			System.out.println(stmt);
			rs.next();
			passwd = sha1(passwd);
			String passwd2 = rs.getString("password");

			if (passwd.equals(passwd2)) {
				sql = "DELETE FROM `board_image` WHERE no in (select no from board where id = ?)  ";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.executeUpdate();
				System.out.println(stmt);

				sql = "DELETE FROM board where id = ? ";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.executeUpdate();
				System.out.println(stmt);

				sql = "DELETE FROM `borad_ripple` WHERE id = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.executeUpdate();
				System.out.println(stmt);

				sql = "DELETE FROM freind where userid1 = ? or userid2 =?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.setString(2, id);
				stmt.executeUpdate();
				System.out.println(stmt);

				sql = "DELETE FROM freind where userid1 = ? or userid2 =?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.setString(2, id);
				stmt.executeUpdate();
				System.out.println(stmt);

				sql = "DELETE FROM idealtype where no = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, no);
				stmt.executeUpdate();
				System.out.println(stmt);

				sql = "DELETE FROM freind where userid1 = ? or userid2 =?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.setString(2, id);
				stmt.executeUpdate();
				System.out.println(stmt);

				sql = "DELETE FROM `join` where userID = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.executeUpdate();
				System.out.println(stmt);

				sql = "DELETE FROM `m_profile` where no = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, no);
				stmt.executeUpdate();
				System.out.println(stmt);

				sql = "DELETE FROM member where no = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, no);
				stmt.executeUpdate();
				System.out.println(stmt);

				return true;
			} else {
				return false;
			}

		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return false;
	}

	// 이메일 인증테이블 값넣기
	public void insertEmailConfirm(String id, int key, String email) {
		connect();
		String sql = "INSERT INTO `emailconfirm`(`id`, `email`, `key`) VALUES (?, ? ,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, email);
			stmt.setInt(3, key);
			stmt.executeUpdate();

		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	// 이메일 확인
	public someDto selectEmailconfirm(String id) {
		someDto Sdto = new someDto();
		connect();
		String sql = "SELECT `no`, `id`, `email`, `key` FROM `emailconfirm` where id=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			System.out.println(stmt);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Sdto.setNo(rs.getInt(1));
				Sdto.setId(rs.getString(2));
				Sdto.setEmail(rs.getString(3));
				Sdto.setKey(rs.getString(4));
			}

		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return Sdto;
	}

	// 이메일 인증후 회원 상태 변경
	public void updateMemberState(String id) {
		connect();
		String sql = "";
		try {
			sql = "update member set confirmstate='true' where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();

		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	// 회원의 이메일인증상태 조회
	public String selectMemberState(String id) {
		connect();
		String sql = "";
		String state = "";
		try {
			sql = "select confirmstate from member where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				state = rs.getString("confirmstate");
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return state;
	}

	public ArrayList<someDto> messagelist(String id) {
		connect();
		String sql = "";
		ArrayList<someDto> list = new ArrayList<someDto>();
		try {
			sql = "SELECT * FROM (select * from message order by no desc) as message where dear=? group by dear";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				someDto g = new someDto();
				g.setNo(rs.getInt("no"));
				g.setSender(rs.getString("sender"));
				g.setDear(rs.getString("dear"));
				g.setContent(rs.getString("content"));
				g.setDay(rs.getString("day"));
				list.add(g);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return list;
	}

	public ArrayList<someDto> sendmessage(String id) {
		connect();
		String sql = "";
		ArrayList<someDto> list = new ArrayList<someDto>();
		try {
			sql = "select * from message where sender=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				someDto g = new someDto();
				g.setNo(rs.getInt("no"));
				g.setSender(rs.getString("sender"));
				g.setDear(rs.getString("dear"));
				g.setContent(rs.getString("content"));
				g.setDay(rs.getString("day"));
				list.add(g);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return list;
	}

	public ArrayList<someDto> getmessage(String id, String id2) {
		connect();
		String sql = "";
		ArrayList<someDto> list = new ArrayList<someDto>();
		try {
			sql = "select no, sender, dear, content, day, photo from message where (dear=? and sender=?) or (dear=? and sender=?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, id2);
			stmt.setString(3, id2);
			stmt.setString(4, id);
			// System.out.println(stmt);
			rs = stmt.executeQuery();
			// System.out.println(stmt);
			while (rs.next()) {
				someDto g = new someDto();
				g.setNo(rs.getInt("no"));
				g.setSender(rs.getString("sender"));
				g.setDear(rs.getString("dear"));
				g.setContent(rs.getString("content"));
				g.setDay(rs.getString("day"));
				g.setPhoto(rs.getString("photo"));
				list.add(g);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return list;
	}

	public String mgetmessage(String id, String id2) {
		connect();
		String sql = "";
		JSONArray result = new JSONArray();
		String returnStr = "";
		String messageList = "'board_list':''";
		String err2 = "\"err\":\"\"";
		try {
			sql = "select no, sender, dear, content, day, photo from message where (dear=? and sender=?) or (dear=? and sender=?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, id2);
			stmt.setString(3, id2);
			stmt.setString(4, id);
			// System.out.println(stmt);
			rs = stmt.executeQuery();
			// System.out.println(stmt);
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("no", rs.getString("no"));
				obj.put("sender", rs.getString("sender"));
				obj.put("dear", rs.getString("dear"));
				obj.put("content", rs.getString("content"));
				obj.put("day", rs.getString("day"));
				obj.put("photo", rs.getString("photo"));
				result.add(obj);
			}
			messageList = "\"message_list\":" + result.toString();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return returnStr = "{\"data\":{" + messageList + "," + err2 + "}}";
	}

	public int getmessage2(String id) {
		connect();
		int count = 0;
		String sql = "";
		ArrayList<someDto> list = new ArrayList<someDto>();
		try {
			sql = "select no, sender, dear, content, day, photo from message where dear=? and day=CURDATE()";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				someDto g = new someDto();
				g.setNo(rs.getInt("no"));
				list.add(g);
			}
			count = list.size();
		} catch (Exception err) {
			System.out.println("getmessage2" + err);
		} finally {
			discon();
		}
		return count;
	}

	public void insertmessage(String id, someDto g) {
		connect();
		String sql = "";
		try {
			sql = "insert into message(sender, dear, content, day, state, photo) values(?,?,?,now(),?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, g.getDear());
			stmt.setString(3, g.getContent());
			stmt.setInt(4, 0);
			stmt.setString(5, g.getPhoto());
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public void delmessage(int id) {
		connect();
		String sql = "";
		try {
			sql = "DELETE FROM `message` WHERE no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public String profileup(HttpServletRequest req, int no) {
		connect();
		String photo = null;
		setPath(req, "profile");
		setMax(5 * 1024 * 1024);
		setEncType("UTF-8");
		String sql = "UPDATE `member` SET `photo`=? WHERE no = ?";
		try {
			multi = new MultipartRequest(req, path, max, encType,
					new DefaultFileRenamePolicy());
			stmt = con.prepareStatement(sql);
			stmt.setString(1, multi.getFilesystemName("upload_file"));
			stmt.setInt(2, no);
			photo = multi.getFilesystemName("upload_file");
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return photo;
	}

	public void celendarin(int no, String id, String title, String Start,
			String end) {
		connect();
		String sql = "INSERT INTO `calendar`(`couple`, `id`, `title`, `start`, `end`) VALUES (?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setString(2, id);
			stmt.setString(3, title);
			stmt.setString(4, Start);
			stmt.setString(5, end);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public void celendardel(int no) {
		connect();
		String sql = "DELETE FROM `calendar` WHERE no = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public ArrayList<someDto> celendar(int no) {
		connect();
		String sql = "select * from calendar where `couple` = ?";
		ArrayList<someDto> dto = new ArrayList<someDto>();
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while (rs.next()) {
				someDto g = new someDto();
				g.setNo(rs.getInt("no"));
				g.setId(rs.getString("id"));
				g.setTitle(rs.getString("title"));
				g.setStart(rs.getString("start"));
				g.setEnd(rs.getString("end"));
				dto.add(g);
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return dto;
	}

	// id 찾기
	public someDto getId(String name, String email) {
		connect();
		String sql = "select * from member where name= ? and `e-mail`= ?";
		someDto dto = new someDto();
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, email);
			rs = stmt.executeQuery();
			// System.out.println(stmt);

			while (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setEmail(rs.getString("e-mail"));
			}
			// System.out.println(dto.getId());
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return dto;
	}

	// 비번 찾기
	public someDto getPw(String id, String email) {
		connect();
		String sql = "select * from member where id= ? and `e-mail`= ?";
		someDto dto = new someDto();
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, email);
			rs = stmt.executeQuery();
			// System.out.println(stmt);

			while (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("e-mail"));
			}
		} catch (Exception err) {
			// System.out.println(err);
		} finally {
			discon();
		}
		return dto;
	}

	// 비번바꾸기
	public void updatePw(String id, String pw, String email) {
		connect();
		String sql = "update member set password = ? where id= ? and `e-mail`= ?";
		try {

			stmt = con.prepareStatement(sql);
			stmt.setString(1, sha1(pw));
			stmt.setString(2, id);
			stmt.setString(3, email);
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updatePw : " + e);
		} finally {
			discon();
		}

	}

	public String admin(String id, String pw) {
		connect();
		String set = null;
		String sql = "select * from admin where id=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				set = "b";
			} else {
				if (!sha1(pw).equals(rs.getString("passwd"))) {
					set = "a";
				} else {
					set = "c";
				}
			}
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return set;
	}

	public ArrayList<someDto> admember() {
		connect();
		ArrayList<someDto> dto = new ArrayList<someDto>();
		try {
			String sql = "select * from member";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				someDto g = new someDto();
				g.setNo(rs.getInt("no"));
				g.setId(rs.getString("id"));
				g.setName(rs.getString("name"));
				g.setEmail(rs.getString("e-mail"));
				g.setTel(rs.getString("tel"));
				g.setAddr(rs.getString("addr"));
				g.setBirthday(rs.getString("birthday"));
				g.setSex(rs.getString("sex"));
				g.setPhoto(rs.getString("photo"));
				dto.add(g);
			}
		} catch (SQLException e) {
			System.out.println("getBoardList() : " + e);
		} finally {
			discon();
		}
		return dto;
	}

	public boolean admindel(int no) {
		connect();
		String id = "";
		try {
			String sql = "select * from member where no=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			rs.next();
			id = rs.getString("id");

			sql = "DELETE FROM `board_image` WHERE no in (select no from board where id = ?)  ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();

			sql = "DELETE FROM board where id = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();

			sql = "DELETE FROM `borad_ripple` WHERE id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();

			sql = "DELETE FROM freind where userid1 = ? or userid2 =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, id);
			stmt.executeUpdate();

			sql = "DELETE FROM freind where userid1 = ? or userid2 =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, id);
			stmt.executeUpdate();

			sql = "DELETE FROM idealtype where no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();

			sql = "DELETE FROM freind where userid1 = ? or userid2 =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, id);
			stmt.executeUpdate();

			sql = "DELETE FROM `join` where userID = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();

			sql = "DELETE FROM `m_profile` where no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();

			sql = "DELETE FROM member where no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();

		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
		return false;
	}

	// 모바일회원가입 이메일 중복검사
	public String emailcheck(String email1, String email2) {

		String email = email1 + "@" + email2;
		String result = "";
		ArrayList<String> ids = new ArrayList<String>();
		try {
			connect();

			String sql = "select `e-mail` from member";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ids.add(rs.getString("e-mail"));
			}

			// id 중복 처리
			String gotId = email;

			for (String id : ids) {
				if (id.equals(gotId)) {
					// 응답 메세지 1 : 이미 등록된 email 입니다.
					result = "1";
					break;
				} else {
					// 응답 메세지 2 : 사용할 수 있는 email 입니다.
					result = "2";
				}
			}

		} catch (Exception err) {
			System.out.println(err);
		} finally {
			pool.freeConnection(con, stmt, rs);
		}

		return result;
	}

	// 모바일회원가입 아이디 중복검사
	public String idcheck(String inputId) {

		ArrayList<String> ids = new ArrayList<String>();
		String result = null;
		try {
			connect();

			String sql = "select id from member";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ids.add(rs.getString("id"));
			}

			// id 중복 처리
			String gotId = inputId;
			for (String id : ids) {
				if (id.equals(gotId)) {
					// 응답 메세지 1 : 이미 등록된 ID 입니다.
					result = "1";
					break;
				} else {
					// 응답 메세지 2 : 사용할 수 있는 ID 입니다.
					result = "2";
				}
			}
			// out.println(result);
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			pool.freeConnection(con, stmt, rs);
		}
		return result;
	}

	// 모바일용 회원가입 테스트
	public void member_joinTest(String id, String password,
			String check_password, String name, String sex, String birthday,
			String num1, String num2, String addr, String tel, String tel2,
			String tel3, String fileName, int age, String email1, String email2) {
		connect();
		String confirmstate = "false";
		setMax(5 * 1024 * 1024);
		setEncType("UTF-8");
		String sql = "INSERT INTO member(id, password, name, sex, birthday, addr, tel, photo, age, `e-mail`, confirmstate) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			System.out.println("member_joinTest" + id + password
					+ check_password + name + sex + birthday + num1 + num2
					+ addr + tel + tel2 + tel3 + fileName + age + email1
					+ email2);
			stmt = con.prepareStatement(sql);
			// File f = multi.getFile("imgInp");
			String password1 = sha1(password);
			stmt.setString(1, id);
			stmt.setString(2, password1);
			stmt.setString(3, name);
			stmt.setString(4, sex);
			stmt.setString(5, birthday);
			stmt.setString(6, num1 + "-" + num2 + " " + addr);
			stmt.setString(7, tel + "-" + tel2 + "-" + tel3);
			stmt.setString(8, fileName);
			stmt.setInt(9, age);
			stmt.setString(10, email1 + "@" + email2);
			stmt.setString(11, confirmstate);
			// System.out.println(stmt);
			stmt.executeUpdate();
		} catch (Exception err) {
			System.out.println(err);
		} finally {
			discon();
		}
	}

	public void mobileInsertBoard(someDto Bdto) {
		connect();
		try {
			String content0 = Bdto.getContent().replace("\n", "<br/>");
			if (Bdto.getFilename() != null) {
				content0 = content0
						+ "<p style=\"text-align: center;\"><img src=\""
						+ Bdto.getFileurl()
						+ "\" style=\"max-width:100%;clear:none;float:none;\"/></p>";
			}
			String sql = "insert into some_board (id, id_no, content, day) values(?, ?, ?, now())";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Bdto.getId());
			stmt.setString(2, Integer.toString(Bdto.getNo()));
			stmt.setString(3, content0);
			stmt.executeUpdate();

			// 방금 넣은 게시글 번호 가져오기
			sql = "select no from some_board order by no desc";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			/* System.out.println(stmt); */
			int number = 0;
			if (rs.next()) {
				number = rs.getInt("no");
			}

			// board_image 테이블에 값넣기
			sql = "INSERT INTO `someboard_plus`(no, filename, desination, filesize, filetype, fileurl) VALUES(?,?,?,?,?,?)";
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
		} finally {
			discon();
		}
	}

	public ArrayList<String> insertPush(String no) {
		ArrayList<String> v = new ArrayList<String>();
		String sql, sql2;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		try {
			sql = "select man_ID, woman_ID from some_some where man_ID = ? or woman_ID = ? and state = 1";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, no);
			stmt.setString(2, no);
			rs = stmt.executeQuery();
			System.out.println(stmt);
			rs.next();
			String id1 = rs.getString("man_ID");
			String id2 = rs.getString("woman_ID");
			if (no.equals(id1)) {
				sql = "select id from member where no =?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id2);
				rs = stmt.executeQuery();
				rs.next();
				sql2 = "select regID from push where ID = ?";
				stmt2 = con.prepareStatement(sql2);
				stmt2.setString(1, rs.getString("id"));
				rs2 = stmt2.executeQuery();
				if (rs2.next()) {
					v.add(rs2.getString("regID"));
				}
			} else {
				sql = "select id from member where no =?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id1);
				rs = stmt.executeQuery();
				rs.next();
				sql2 = "select regID from push where ID = ?";
				stmt2 = con.prepareStatement(sql2);
				stmt2.setString(1, rs.getString("id"));
				rs2 = stmt2.executeQuery();
				if (rs2.next()) {
					v.add(rs2.getString("regID"));
				}
			}
		} catch (SQLException e) {
			System.out.println("pust : " + e);
		} finally {
			discon();
			try {
				if(stmt2 != null){stmt2.close();}
				if(rs2 !=null){rs2.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return v;
	}

	public ArrayList<String> messageinsertPush(String dear) {
		ArrayList<String> v = new ArrayList<String>();
		String sql;
		try {
			sql = "select regID from push where ID = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dear);
			rs = stmt.executeQuery();
			if (rs.next()) {
				v.add(rs.getString("regID"));
			}
		} catch (SQLException e) {
			System.out.println("pust : " + e);
		} finally {
			discon();
		}
		return v;
	}

	public void pushreg(String id, String regID) {
		connect();
		String sql = "";
		String sql2 = "";
		PreparedStatement stmt2;
		try {
			sql = "select * from push where ID = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				sql2 = "UPDATE `push` SET `regID`= ?  WHERE ID = ?";
				stmt2 = con.prepareStatement(sql2);
				stmt2.setString(1, regID);
				stmt2.setString(2, id);
				stmt2.executeUpdate();
			} else {
				sql2 = "INSERT INTO `push`(`ID`, `regID`) VALUES (?,?)";
				stmt2 = con.prepareStatement(sql2);
				stmt2.setString(1, id);
				stmt2.setString(2, regID);
				stmt2.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			discon();
		}
	}

	public String passwordcheck(String password, String check_password) {

		ArrayList<String> ids = new ArrayList<String>();
		String result = null;
		System.out.println("password: " + password + ", " + "check_password"
				+ check_password);
		if (password.length() < 6 || check_password.length() > 12) {
			result = "3";// 비밀번호를 6~12자까지 입력해주세요.
		} else {
			if (!password.equals(check_password)) {
				result = "4";// 비밀번호가 일치하지 않습니다.
			} else if (password.equals(check_password)
					|| password == check_password) {
				result = "5";// 성공
			}
		}
		return result;
	}

	// 모바일 비번바꾸기
	public String updatePwM(String id, String pw, String email) {
		connect();
		String result = "";
		System.out.println("id: " + id + ", " + "pw: " + pw + ", " + "email: "
				+ email);
		String sql = "update member set password = ? where id= ? and `e-mail`= ?";
		try {

			stmt = con.prepareStatement(sql);
			stmt.setString(1, sha1(pw));
			stmt.setString(2, id);
			stmt.setString(3, email);
			stmt.executeUpdate();
			result = "1";
		} catch (SQLException e) {
			System.out.println("updatePw : " + e);
			result = "2";
		} finally {
			discon();

		}
		return result;
	}

	// 모바일 아이디 키값비교
	public String keyCompare(String key) {
		connect();
		String result = "";
		String result_id = "";
		try {
			String sql = "SELECT `id` from `emailconfirm` where `key`=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, key);
			rs = stmt.executeQuery();
			while (rs.next()) {
				result_id = rs.getString("id");
			}

		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		System.out.println("keyCompare 결과:" + result_id);
		return result_id;
	}

	//이메일 인증후 키값 삭제
	public void deleteKey(String id){
		connect();
		try {
			String sql="delete from emailconfirm where id=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		
		
	}
	//모바일 프로필 사진 업데이트 
	public String PhotoUpdate(String id, String photo){
		connect();
		String result="";
		try {
			String sql="update member set photo=? where id= ?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, photo);
			stmt.setString(2, id);
			stmt.executeUpdate();
			result="1";
		
		} catch (Exception e) {
			System.out.println("PhotoUpdate: "+ e);
			result="2";
		} finally{
			discon();
		}
		return result;
	}

	//모바일 프로필 사진 받아오기
	public someDto PhotoGet(String id){
		someDto g= new someDto();
		connect();
		String result="";
		System.out.println("id: "+id);
		try {
			String sql="select * from member where id=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				g.setNo(rs.getInt("no"));
				g.setId(rs.getString("id"));
				g.setName(rs.getString("name"));
				g.setEmail(rs.getString("e-mail"));
				g.setTel(rs.getString("tel"));
				g.setAddr(rs.getString("addr"));
				g.setBirthday(rs.getString("birthday"));
				g.setSex(rs.getString("sex"));
				g.setPhoto(rs.getString("photo"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
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
