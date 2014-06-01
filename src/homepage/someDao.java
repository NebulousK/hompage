package homepage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

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
			System.out.println("연결");
		} catch (Exception err) {
			System.out.println("에러");
			System.out.println(err);
		} 
	}
	
	public void discon(){
		pool.freeConnection(con,stmt,rs);
	}
	
	public ArrayList<someDto> zip(String dong){
		connect();
		ArrayList<someDto> g = new ArrayList<someDto>();
		try{
			String sql = "select * from zip where juso3 like '%"+ dong +"%'"; 
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
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
		}catch(Exception err){
			System.out.println(err);
		}finally{
			discon();
		}
		return g;
	}
	
	
	public void setPath(HttpServletRequest servlet, String path){
		this.path = servlet.getServletContext().getRealPath(path);
	}

	public void setMax(int max){
		this.max = max;
	}

	public void setEncType(String encType){
		this.encType = encType;
	}
	public String getUser(){
		return multi.getParameter("user");
	}

	public String getTitle(){
		return multi.getParameter("title");
	}

	public void member_join(HttpServletRequest req) {
		connect();
		String sql = "INSERT INTO member(id, password, name, sex, birthday, addr, tel, photo, age, e-mail) VALUES(?,?,?,?,?,?,?,?,?,?)";		
		try{
			multi = new MultipartRequest(req, path, max, encType, new DefaultFileRenamePolicy());
			stmt = con.prepareStatement(sql);
			File f = multi.getFile("imgInp");
			String email;
			if(multi.getParameter("email2").equals(null)|| multi.getParameter("email2").equals("")){
				email = multi.getParameter("email1") + "@" + multi.getParameter("email3");
			}else{
				email = multi.getParameter("email1") + "@" + multi.getParameter("email2");
			}
			int age = (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(multi.getParameter("year"))) + 1;
			stmt.setString(1, multi.getParameter("id"));
			stmt.setString(2, multi.getParameter("password"));
			stmt.setString(3, multi.getParameter("name"));
			stmt.setString(4, multi.getParameter("sex"));
			stmt.setString(5, multi.getParameter("year") + "." + multi.getParameter("month") + "." + multi.getParameter("day"));
			stmt.setString(6, multi.getParameter("zip1") + "-" + multi.getParameter("zip2") + " " + multi.getParameter("juso") + " " + multi.getParameter("addr"));
			stmt.setString(7, multi.getParameter("tel") + "-" + multi.getParameter("tel2") + "-" + multi.getParameter("tel3"));
			stmt.setString(8, f.getPath());
			stmt.setInt(9, age);
			stmt.setString(10, email);
			System.out.println(stmt);
			//stmt.executeUpdate();	
		}catch(Exception err){
			System.out.println(err);
		}finally{
			discon();
		}
	}
	
	public String getUpFile(){
		String result = "";
		Enumeration enumer = multi.getFileNames();
		while(enumer.hasMoreElements()){
			String name = (String)enumer.nextElement();
			result = "오리지날 : " + multi.getOriginalFileName(name) + "<br/>";
			result += "저장된파일명 : " + multi.getFilesystemName(name) + "<br/>";
			result += "타입 : " + multi.getContentType(name) + "<br/>";
			File f = multi.getFile(name);
			result += "파일길이 : " + f.length() + "byte <br/>";
			result += "파일경로 : " + f.getPath();
		}
		return result;
	}
	
	
	
	public void idealtype(int no, String sex, int ages){
		connect();
		ArrayList<someDto> g = new ArrayList<someDto>();
		String sql="";
		int age, height = 0;
		String hobby = null, fashion = null, blood = null, style = null, weight = null;
		try{
			sql = "select * from idealtype where no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while(rs.next()){
				age = Integer.parseInt(rs.getString("age"));
				height = Integer.parseInt(rs.getString("height"));
				hobby = rs.getString("hobby");;
				blood = rs.getString("blood");
			    style =	rs.getString("style");
				weight = rs.getString("weight");
				fashion = rs.getString("fashion");
			}
			sql = "SELECT a.no ,a.age, b.height, b.hobby, b.blood,b.style, b.weight, b.fashion FROM member a, m_profile b WHERE a.sex = ? AND a.no = b.no AND a.age < ? ";	
			stmt = con.prepareStatement(sql);
			stmt.setString(1, sex);
			stmt.setInt(2, ages);
			rs = stmt.executeQuery();
			while(rs.next()){
				someDto dto = new someDto();
				dto.setNo(Integer.parseInt(rs.getString("a.no")));
				dto.setAge(Integer.parseInt(rs.getString("a.age")));
				dto.setHeight(Integer.parseInt(rs.getString("b.height")));
				dto.setHobby(rs.getString("b.hobby"));;
				dto.setBlood(rs.getString("b.blood"));
				dto.setStyle(rs.getString("b.style"));
				dto.setWeight(rs.getString("b.weight"));
				dto.setFashion(rs.getString("b.Fashion"));
				g.add(dto);
			}
			int total[][] = new int[1][g.size()];
			for (int i = 0; i < g.size(); i++) {
				someDto dto = g.get(i);
				int result = 0;
				if(height > dto.getHeight()){
					int r = height - dto.getHeight();
						if(r>10) {result += 0;}
						else if(r<=3){result += 10;}
						else if(r<=5){result += 7;}
						else if(r<=10){result += 4;}
				}
				else if(height < dto.getHeight()){
					int r = dto.getHeight() - height;
						if(r>10) {result += 0;}
						else if(r<=3){result += 10;}
						else if(r<=5){result += 7;}
						else if(r<=10){result += 4;}
				}
				if(Integer.parseInt(weight) >Integer.parseInt(dto.getWeight())){
					int r = Integer.parseInt(weight) - Integer.parseInt(dto.getWeight());
						if(r>10) {result += 0;}
						else if(r<=3){result += 10;}
						else if(r<=5){result += 7;}
						else if(r<=10){result += 4;}
				}
				else if(Integer.parseInt(weight) < Integer.parseInt(dto.getWeight())){
					int r =   Integer.parseInt(dto.getWeight()) - Integer.parseInt(weight);
						if(r>10) {result += 0;}
						else if(r<=3){result += 10;}
						else if(r<=5){result += 7;}
						else if(r<=10){result += 4;}
				}
				if(hobby.equals(dto.getHobby())){result += 10;}
				if(blood.equals(dto.getBlood())){result += 10;}	 
				else if(!blood.equals(dto.getBlood())){result += 5;}
				if(style.equals(dto.getStyle())){result += 10;}
				if(fashion.equals(dto.getFashion())){result += 10;}
				total[0][0] = dto.getNo();
				total[0][1] = result;
			}
			System.out.println(total[0][0] +  " " + total[0][1]);
		}catch(Exception err){
			System.out.println(err);
		}
	}
	
}
