package homepage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
