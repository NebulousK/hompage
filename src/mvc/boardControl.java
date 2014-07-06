package mvc;


import homepage.someDao;
import homepage.someDto;
import homepage.board.BoardDao;
import homepage.board.BoardDelete;
import homepage.board.BoardDto;
import homepage.board.BoardUpdate;
import homepage.board.LikeModel;
import homepage.board.ReplyDelete;
import homepage.board.ReplyPost;
import homepage.board.UnlikeModel;
import homepage.board.UpdatePassword;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class boardControl extends HttpServlet{
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
		PrintWriter out=resp.getWriter(); //
		BoardDao dao = new BoardDao();
		someDao dao2 = new someDao();
		someDto dto = new someDto();
		BoardDto dto2 = new BoardDto();
		
		if(action.equals("/main.board")){
			String id = (String) session.getAttribute("id");
			Vector list = new Vector();
			list = dao.getBoardList(id);
			req.setAttribute("boardlist", list);
			nextPage = "/newsfeed/main.jsp";
		}
		
		else if(action.equals("/re.board")){
			String id = (String) session.getAttribute("id");
			String id2 = "\""+ id +"\"";
			Vector list = new Vector();
			int i = Integer.parseInt((String)req.getParameter("num"));
			list = dao.getBoardList(id);
			int flag = 0;
			if(list.size() > i){
				dto = (someDto) list.get(i);
				out.print("<div id='start' name='start'><div id='maindiv"+ i +"'><div class='_4-u2 mbm _5jmm _5pat _5v3q _5sq8 _5x16' id='u_ps_0_0_m'><div class='userContentWrapper _5pcr _3ccb'><div class='_4r_y'><div class='_6a uiPopover _5pbi _5puc _5v56' id='u_ps_0_0_n'></div></div><div class='clearfix _5x46'><div class='_3dp _29k'><div><div class='_6a'><div class='_6a _6b'><h5 class='_5pbw'><div class='fwn fcg'><table style='border: 0px;width: 554px'><tr><td style='border: 0px;width: 55px; padding: 0px;margin: 0px;'><a class='_5pb8 _5v9u _29h _303' href='/homepage/how.board?id="+dto.getId()+"'><img class='_s0 _5xib _5sq7 _rw img' src='/homepage/profile/"+dto.getPhoto()+"' alt='' align='middle' /></a></td><td style='border: 0px;text-align: left;'><span class='fwb fcg'><a href='/homepage/how.board?id="+dto.getId()+"' style='font-size: 14px;'>"+dto.getId()+"</a></span><br/><div class='_5pcp' style='margin-top: 5px'><span>"+dto.getDay()+"</span></div></td><td style='border: 0px;text-align: right;'>");
				if(id.equals(dto.getId())){
					out.print("<div class='dropdown'><a class='dropdown-toggle' id='dLabel' role='button' data-toggle='dropdown' data-target='#' href='/page.html'><b class='caret'></b></a><ul class='dropdown-menu' role='menu' aria-labelledby='dLabel'><li style='text-align: center;font-size: 12px;line-height: 25px;'><span id='updateclick"+ i +"' onclick='addup("+dto.getNo()+")'>수&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;정</span></li><mcjsjs><input type='hidden' id='divNoCheck"+i+"' value='"+i+"'/></mcjsjs><input type='hidden' id='boardId' value='"+ dto.getId() +"' /><input type='hidden' id='connectId' value='"+ id +">' /><li style='text-align: center;font-size: 12px;line-height: 25px'><span onclick='Bdelete("+ dto.getNo() +","+ i +")'>삭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제</span></li></ul></div>");
				}
				out.print("</td></tr></table></div></h5><div class='_5pcp'><span>"+dto.getDay()+"</span></div></div></div></div></div></div><div id='sujung"+dto.getNo()+"' name='sujung"+dto.getNo()+"'></div><div class='_5pbx userContent'><p>"+dto.getContent()+"</p></div><div><div></div></div><div class='clearfix'><div class='_5pcp _5vsi lfloat _ohe'><span id='likeunlike"+i+"'><span id='inlikeunlike"+i+"'>");
				Vector vlike = dao.board_likeSelect(dto.getNo());
				for(int j=0; j<vlike.size();j++){
					String likeid =(String) vlike.get(j);
					if(id.equals(likeid)){
						flag = 1;
						break;
					}
					else{
						flag = 0;
					}
				}
				if(flag == 1){
					out.print("<a href='javascript:unlike(" +dto.getNo()+ " , "+ id2 +" , "+i+")' class='default_message' name='unlike' id='unlike"+i+"'>좋아요 취소</a>");
				}else{
					out.print("<a href='javascript:like("+dto.getNo()+",  "+ id2 +" , "+i+")' class='default_message' name='like' id='like"+i+"'>좋아요</a>");
				}
				out.print("</span></span>• <mcjsjs><span class='default_message' name='reply' id='replyshow"+i+"' style='cursor:pointer;'onclick='getreplylist("+dto.getNo()+", "+id2+", "+i+")'>댓글</span></mcjsjs><mcjsjs><input type='hidden' id='divNoCheck"+i+"' value='"+i+"'/></mcjsjs>• <a class='uiBlingBox feedbackBling'><i class='img sp_p5WkkL41GeK sx_408c76'></i><span class='text' id='likecnt"+i+"'>"+dto.getLike()+"</span></a></div></div></div><div id='replydiv"+i+"' style='background-color: #E6FFFF;'></div></div></div></div>");
				return;
			}
			return;
		}
		
		else if(action.equals("/update.board")){
			dao.boardUpdate(Integer.parseInt((String)req.getParameter("num")), (String)req.getParameter("content"));
			if(req.getParameter("action") != null){
				nextPage = "/itsme.board";
			}else{
				nextPage = "/main.board";
			}
		}
		
		else if(action.equals("/itsme.board")){
			String id = (String) session.getAttribute("id");
			Vector list = new Vector();
			list = dao.getBoardList2(id);
			req.setAttribute("boardlist", list);
			nextPage = "/itsme/main.jsp";
		}
		
		else if(action.equals("/how.board")){
			String id = req.getParameter("id");
			dto = dao2.member(id);
			Vector list = new Vector();
			list = dao.getBoardList2(id);
			req.setAttribute("boardlist", list);
			req.setAttribute("dto", dto);
			nextPage = "/itsme/main2.jsp";
		}
		
		else if(action.equals("/post.board")){
			String content = req.getParameter("content");
			String id = req.getParameter("id");
			//System.out.println(content);
			dto2.setId(id);
			dto2.setContent(content);
			dao.insertBoard(dto2); 
			nextPage = "/main.board"; 
		}
		else if(action.equals("/LIKE.board")){
			LikeModel lm= new LikeModel();
			nextPage = (String) lm.processCommand(req, resp);
		}
		else if(action.equals("/UNLIKE.board")){
			UnlikeModel um =  new UnlikeModel();
			nextPage = (String) um.processCommand(req, resp);
		}
		else if(action.equals("/DELETE.board")){
			BoardDelete bd = new BoardDelete();
			nextPage = (String) bd.processCommand(req, resp);
		}
		/*else if(action.equals("/UPDATE.board")){
			BoardUpdate bu = new BoardUpdate();
			nextPage = (String) bu.processCommand(req, resp);
		}*/
		else if(action.equals("/REPLYPOST.board")){
			ReplyPost rp = new ReplyPost();
			nextPage = (String) rp.processCommand(req, resp);
		}
		else if(action.equals("/REPLYDELETE.board")){
			ReplyDelete rd = new ReplyDelete();
			nextPage = (String) rd.processCommand(req, resp);
		}
		/*else if(action.equals("/MAINSTART.board")){
			MainStart ms = new MainStart();
			nextPage = (String) ms.processCommand(req, resp);
		}*/
		/*else if(action.equals("/SENDEMAIL.board")){
			FindId fi = new FindId();
			nextPage = (String) fi.processCommand(req, resp);
		}
		else if(action.equals("/UPDATEPW.board")){
	         UpdatePassword up = new UpdatePassword();
	         nextPage = (String) up.processCommand(req, resp);
	    }*/
		
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, resp);
	}
}
