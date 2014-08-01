package mvc;

import homepage.someDao;
import homepage.someDto;
import homepage.board.UpdatePassword;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import mail.Gmail;

public class memberControl extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		String path = req.getContextPath();
		String action = url.substring(path.length());
		String nextPage = "";
		resp.setContentType("text/html;charset=utf-8"); // 어떤 타입으로 출력할것인지 명시하였다.
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		PrintWriter out=resp.getWriter(); //
		someDao dao = new someDao();
		someDto dto = new someDto();
		
		if(action.equals("/zip.me")){
			String dong = req.getParameter("dong");
			ArrayList<homepage.someDto> g = new ArrayList<homepage.someDto>(); 
			g = dao.zip(dong);
			req.setAttribute("g", g);
			nextPage = "/member/zipcode.jsp";
		}
		
		else if(action.equals("/join.me")){
			Gmail gmail = new Gmail();
			MultipartRequest multi = dao.member_join(req);
		 	gmail.Gmail(multi);
		 	nextPage = "/member/member_join2.jsp";
		}
		
		else if(action.equals("/join2.me")){
			String[] str = req.getParameterValues("fashion");
			String[] str2 = req.getParameterValues("fashion2");
			String fashion="" , fashion2="";
			for(int i=0; i<str.length; i++){
			 	if(i!=0){
					fashion += ",";
			 	}
				fashion += str[i];
			}
			for(int i=0; i<str2.length; i++){
				if(i!=0){
					fashion2 += ",";
			 	}
				fashion2 += str2[i];
			}
			dto.setHeight(Integer.parseInt((String)req.getParameter("height")));
			dto.setHeight2(Integer.parseInt((String)req.getParameter("height2")));
			dto.setHobby(req.getParameter("hobby"));
			dto.setHobby2(req.getParameter("hobby2"));
			dto.setBlood(req.getParameter("blood"));
			dto.setBlood2(req.getParameter("blood2"));
			dto.setStyle(req.getParameter("style"));
			dto.setStyle2(req.getParameter("style2"));
			dto.setWeight(Integer.parseInt((String)req.getParameter("weight")));
			dto.setWeight2(Integer.parseInt((String)req.getParameter("weight2")));
			dto.setAge(Integer.parseInt((String)req.getParameter("age")));
			dto.setFashion(fashion);
			dto.setFashion2(fashion2);
			dao.member_detail(dto);
			nextPage = "/member/member_complete.jsp";
		}
		
		else if(action.equals("/login.me")){
			String id = req.getParameter("id");
			String password = req.getParameter("password");
			String state = dao.selectMemberState(id);
			String result =  dao.login(id, password);
			if(result == "b"){
			//존재하지 않는 아이디
				out.println("<script>");
				out.println("alert('존재 하지 않는 아이디 입니다. 아이디를 확인하세요');");
				out.println("history.back();");
				out.println("</script>");
				return;
			}
			else if(result == "a"){
			//비번 틀림
				out.println("<script>");
				out.println("alert('비밀 번호가 틀렸습니다. 비밀번호를 확인 하세요');");
				out.println("history.back();");
				out.println("</script>");
				return;
			}
			else if(!state.equals("true")){	
				nextPage = "/emailfalse.jsp";
			}
			else{
				String a[] = result.split(",");
				session.setAttribute("id", a[0]);
				session.setAttribute("no", a[1]);
				session.setAttribute("sex", a[2]);
				session.setAttribute("photo", a[3]);
				session.setAttribute("name", a[4]);
				int b = dao.some_some2(Integer.parseInt(a[1]));
				if(b != 0){
					session.setAttribute("some", b);
				}
				nextPage = "/main.board";
			}
		}
		
		else if(action.equals("/mupdate.me")){
			dao.memberupdate(req, Integer.parseInt((String) session.getAttribute("no")));
			out.println("<script>");
			out.println("alert('수정이 완료 되었습니다.');");
			out.println("location.href='/homepage/itsme/main.jsp'");
			out.println("</script>");
			return;
		}
		
		
		else if(action.equals("/member_update.me")){
			dto = dao.memberget(Integer.parseInt((String)session.getAttribute("no")));
			String email[] = dto.getEmail().split("@");
			dto.setEmail(email[0]);
			dto.setEmail2(email[1]);
			int first = dto.getAddr().indexOf(" ");
			int last = dto.getAddr().lastIndexOf("  ");
			String zip1,zip2,juso,addr = "";
			String tzip[] = dto.getAddr().substring(0, dto.getAddr().indexOf(" ")).split("-");
			zip1 = tzip[0];
			zip2 = tzip[1];
			dto.setZip(Integer.parseInt(zip1));
			dto.setZip2(Integer.parseInt(zip2));
			String taddr = dto.getAddr().substring(dto.getAddr().indexOf(" ")+1);
			String tjuso[] = taddr.split(" ");
			juso = tjuso[0] + " " + tjuso[1] + " " + tjuso[2];
			dto.setJuso1(juso);
			for(int i=3; i<tjuso.length; i++){
				addr += tjuso[i];
			}
			dto.setAddr(addr);
			String tel[] = dto.getTel().split("-");
			dto.setTel(tel[0]);
			dto.setTel2(tel[1]);
			dto.setTel3(tel[2]);
			String birth[] = dto.getBirthday().split("-"); 
			dto.setBirth(birth[0]);
			dto.setBirth2(birth[1]);
			dto.setBirth3(birth[2]);
			req.setAttribute("dto", dto);
			nextPage = "/member/member_update.jsp";
		}
		
		else if(action.equals("/member_update2.me")){
			dto = dao.memberget2(Integer.parseInt((String)session.getAttribute("no")));	
			String fashion[] = dto.getFashion().split(",");
			String fashion2[] = dto.getFashion2().split(",");
			req.setAttribute("dto", dto);
			req.setAttribute("fashion", fashion);
			req.setAttribute("fashion2", fashion2);
			nextPage = "/member/member_update2.jsp";
		}
		
		else if(action.equals("/mupdate2.me")){
			String[] str = req.getParameterValues("fashion");
			String[] str2 = req.getParameterValues("fashion2");
			String fashion="" , fashion2="";
			for(int i=0; i<str.length; i++){
				if(i!=0){
					fashion += ",";
				}
				fashion += str[i];
			}
			for(int i=0; i<str2.length; i++){
				if(i!=0){
					fashion2 += ",";
				}
				fashion2 += str2[i];
			}
			dto.setHeight(Integer.parseInt((String) req.getParameter("height")));
			dto.setHeight2(Integer.parseInt((String) req.getParameter("height2")));
			dto.setHobby(req.getParameter("hobby"));
			dto.setHobby2(req.getParameter("hobby2"));
			dto.setBlood(req.getParameter("blood"));
			dto.setBlood2(req.getParameter("blood2"));
			dto.setStyle(req.getParameter("style"));
			dto.setStyle2(req.getParameter("style2"));
			dto.setWeight(Integer.parseInt((String) req.getParameter("weight")));
			dto.setWeight2(Integer.parseInt((String) req.getParameter("weight2")));
			dto.setFashion(fashion);
			dto.setFashion2(fashion2);
			dto.setAge(Integer.parseInt((String) req.getParameter("age")));
			dao.memberupdate2(dto, Integer.parseInt((String) session.getAttribute("no")));
			out.println("<script>");
			out.println("alert('수정이 완료 되었습니다.');");
			out.println("location.href='/homepage/itsme/main.jsp'");
			out.println("</script>");
			return;
		}
		else if(action.equals("/getout.me")){
			boolean result = dao.getout(Integer.parseInt((String)session.getAttribute("no")), (String)session.getAttribute("id"), req.getParameter("password"));
			if(result){
				nextPage = "/member/getoutcomplete.jsp";	
			}else{
				out.println("<script>");
				out.println("alert('비밀번호가 올바르지 않다.');");
				out.println("location.href='/homepage/member/member_getout.jsp'");
				out.println("</script>");
				return;
			}
		}
		
		else if(action.equals("/logout.me")){
			session.invalidate();
			out.println("<script>");
			out.println("alert('안녕히 가세요');");
			out.println("location.href='/homepage/index.html'");
			out.println("</script>");
			return;
		}
		
		else if(action.equals("/memseage.me")){
			dto = dao.member(req.getParameter("id"));
			req.setAttribute("id2", req.getParameter("id"));
			req.setAttribute("g", dto);
			nextPage = "/message/viewmessage.jsp";
		}
		
		else if(action.equals("/mmemseage.me")){
			String id = req.getParameter("id");
			String id2 = req.getParameter("id2");
			String result = dao.mgetmessage(id, id2);
			out.println(result);
			return;
		}
		
		else if(action.equals("/memseagesend.me")){
			dto.setDear(req.getParameter("dear"));
			dto.setContent(req.getParameter("content"));
			dto.setPhoto((String) session.getAttribute("photo"));
			dao.insertmessage((String)session.getAttribute("id"),dto);
		}///homepage/mesagedel.me
		 
		else if(action.equals("/mmemseagesend.me")){
			dto.setDear(req.getParameter("dear"));
			dto.setContent(req.getParameter("content"));
			dto.setPhoto(req.getParameter("photo"));
			dao.insertmessage(req.getParameter("id"),dto);
		}
		
		else if(action.equals("/mmemseagesend.me")){
			dto.setDear(req.getParameter("dear"));
			dto.setContent(req.getParameter("content"));
			dto.setPhoto(req.getParameter("photo"));
			dao.insertmessage(req.getParameter("id"),dto);
		}///homepage/mesagedel.me
		
		
		else if(action.equals("/mesagedel.me")){
			dao.delmessage(Integer.parseInt((String)req.getParameter("number")));
		}
		
		else if(action.equals("/mesageget.me")){
			ArrayList g = dao.messagelist((String) session.getAttribute("id"));
			req.setAttribute("g", g);
			nextPage = "/message/message.jsp";
		}
		
		else if(action.equals("/imageup.me")){
			String photo = dao.profileup(req, Integer.parseInt((String)session.getAttribute("no")));
			session.setAttribute("photo", photo);
			nextPage = "/member/close.html";
		}
		
		else if(action.equals("/cel.me")){
			ArrayList g = dao.celendar((int)session.getAttribute("some"));
			req.setAttribute("g", g);
			nextPage = "/someNsome/menu3/main.jsp";
		}
		
		else if(action.equals("/mcel.me")){
			ArrayList g = dao.celendar(Integer.parseInt(req.getParameter("some")));
			req.setAttribute("g", g);
			nextPage = "/someNsome/menu3/mmain.jsp";
		}
		
		else if(action.equals("/celin.me")){
			dao.celendarin((int)session.getAttribute("some"), (String)req.getParameter("id"), (String)req.getParameter("title"), (String)req.getParameter("start"), (String)req.getParameter("end"));
			return;
		}
		
		else if(action.equals("/celdel.me")){
			dao.celendardel(Integer.parseInt((String)req.getParameter("no")));
			return;
		}
		//아이디찾기 메일 발송
				if(action.equals("/SENDEMAIL.me")){
					someDao Sdao = new someDao();
					//get방식 일때 한글 인코딩
					String name = req.getParameter("name");
					String email = req.getParameter("email");
					someDto Sdto = Sdao.getId(name, email);
					String id = Sdto.getId();
					System.out.println(id);
					
					int flag = 0;
					PrintWriter Pout = resp.getWriter();
					if(id != null){
						Gmail gmail = new Gmail();
						gmail.Gmail1(req, id);
						flag = 1;
					}
					else{
						flag = 0;
					}
					out.print(flag);				
					return;
				}
				
				//비번찾기 메일 발송
				else if(action.equals("/SENDEMAIL1.me")){
					someDao Sdao = new someDao();
					//get방식 일때 한글 인코딩
					String id = req.getParameter("id");
					String email = req.getParameter("email");
					someDto Sdto = Sdao.getPw(id, email);
					String name = Sdto.getName();
						
					int flag = 0;
					PrintWriter Pout = resp.getWriter();
					if(name != null){
						Gmail gmail = new Gmail();
						gmail.Gmail1(req, id);
						flag = 1;
					}
					else{
						flag = 0;
					}
					Pout.print(flag);				
					return;
				}
				//비번 바꾸기
				else if(action.equals("/UPDATEPW.me")){
			         UpdatePassword up = new UpdatePassword();
			         nextPage = (String) up.processCommand(req, resp);
			    }
		
		
		RequestDispatcher view =req.getRequestDispatcher(nextPage);//어느페이지인지 정해준다
		view.forward(req, resp);// req의 모든정보를얘가 던져주는곳  다음 페이지에서 request.getattribute("a")를 하면 마가나온다	
	}	
}
