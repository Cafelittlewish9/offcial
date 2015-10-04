package util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.vo.User;

public class EmailUtils {
	//由這信箱發送信件
	private static final String FROM = "zoo123131@gmail.com";
	
	// 發送重設密碼鏈接的郵件 
	public static void sendResetPasswordEmail(User user){
		Session session = getSession(); 
		MimeMessage message = new MimeMessage(session);
		try{
			message.setSubject("找回您的帳戶與密碼");
			message.setSentDate(new Date());
			message.setRecipient(RecipientType.TO, new InternetAddress(user.getMemberEmail()));
			message.setContent("要使用新的密碼, 請使用以下連結啟用密碼:<br/><a href='" + GenerateLinkUtils.generateResetPwdLink(user) +"'>點擊重新設置密碼</a>","text/html;charset=utf-8");  
	            // 發送郵件  
	            Transport.send(message);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
		}
	}
	
	

	public static Session getSession() {
		 Properties props = new Properties();  
		 props.setProperty("mail.transport.protocol", "smtp");  
		 props.setProperty("mail.smtp.host", "smtp.163.com");  
	     props.setProperty("mail.smtp.port", "25");  
	     props.setProperty("mail.smtp.auth", "true");
	     Session session = Session.getInstance(props,new Authenticator() {
	    	 @Override  
	            protected PasswordAuthentication getPasswordAuthentication(){
	    		 String password = null;
	    		 InputStream is = EmailUtils.class.getResourceAsStream("password.dat");
	    		 byte[]b = new byte[1024];
	    		 try{
	    			 int len = is.read(b);
	    			 password = new String(b,0,len);
	    		 }catch(IOException e){
	    			 e.printStackTrace();
	    		 }
	    		 return new PasswordAuthentication(FROM, password);
	    	 }
	     
	     });
		return session;
	}
}
