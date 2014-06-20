package mail;

import homepage.someDao;
import homepage.someDto;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import javax.activation.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.io.*;
import java.util.*;
import java.security.Security;

public class Gmail {
	public static void Gmail(MultipartRequest multi) {
		String id = multi.getParameter("id");
		String email;		
		if (multi.getParameter("email2").equals(null) || multi.getParameter("email2").equals("")) {
			email = multi.getParameter("email1") + "@" + multi.getParameter("email3");
		} else {
			email = multi.getParameter("email1") + "@" + multi.getParameter("email2");
		}
		
		int key = mailKey();
		
		Properties p = new Properties();
		p.put("mail.smtp.user", "gmail_id@gmail.com"); // Google����@gmail.com����
														// ����
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");

		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");

		// Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		try {
		
			//MultipartRequest multi = new MultipartRequest(req, "a", 5 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
			
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(p, auth);
			session.setDebug(true); // ������ ����� �� ���� ��Ȳ�� �ֿܼ� ����Ѵ�.

			// session = Session.getDefaultInstance(p);
			MimeMessage msg = new MimeMessage(session);
			//키값 생성
			
		
			//System.out.println(key);
			//System.out.println(id);
			String message = "http://192.168.10.31:8080/homepage/mailConfirm.jsp?id="+id+"&key="+key+"&email="+email;
			msg.setSubject("Some N Some 인증메일");
			Address fromAddr = new InternetAddress("mcjsjs82@gmail.com"); //보내는 사람 이메일주소
			
			System.out.println(email);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(email); //
																		// �����ּ�
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(message, "text/plain;charset=KSC5601");
			//System.out.println("Message: " + msg.getContent());
			Transport.send(msg);
			//System.out.println("Gmail SMTP������ �̿��� ���Ϻ����� ����");
			
			//키값및 정보 db 저장
			someDao Sdao = new someDao();
			Sdao.insertEmailConfirm(id, key, email);
		} catch (Exception mex) { // Prints all nested (chained) exceptions as
									// well
			System.out.println("Gmail ����" + mex);
			mex.printStackTrace();
		}
		
	}
	
	public static int mailKey(){
		int key = 0;
		
		for (int j = 0; j < 10; j++) {
			// 1 ~ 10까지 랜덤 숫자 구하기
			key = (int) (Math.random() * 10000) + 1; 
			
		}	
		return key;
	}

	private static class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("djemals11", "djemalsdjemals"); 
		}
	}
	
	
}
