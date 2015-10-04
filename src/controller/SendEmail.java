package controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendEmail {
	 public static boolean sendemail(String memberId,String password1 ,String email) {
	  String host = "smtp.gmail.com";
	  int port = 587;
	  final String username = "zoo123131@gmail.com";
	  final String password = "loveban19910504";//email password

	  Properties props = new Properties();
	  props.put("mail.smtp.host", host);
	  props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	  props.put("mail.smtp.auth", "true");
	  props.put("mail.smtp.starttls.enable", "true");
	  props.put("mail.smtp.port", port);
	  Session session = Session.getInstance(props, new Authenticator() {
	   protected PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(username, password);
	   }
	  });

	  try {
		  MimeMessage  message = new MimeMessage(session); 
		  message.setFrom(new InternetAddress("zoo123131@gmail.com","ITV"));
		  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));  
		  message.setSubject("找回帳號密碼","UTF-8");   
		  Multipart mp = new MimeMultipart();
		  MimeBodyPart part1 = new MimeBodyPart();
		  StringBuilder sb = new StringBuilder();
		  sb.append("<h2>您好,這是您的帳號和密碼<h2>");
		  sb.append("<br>使用者帳號：");
		  sb.append(memberId);
		  sb.append("<br>使用者密碼：");
		  sb.append(password1);				 
		  part1.setText(sb.toString());
		  part1.setContent(sb.toString(),"text/html; charset=utf-8");
		  mp.addBodyPart(part1);
		  message.setContent(mp);
		  Transport.send(message);
	   
		  System.out.println("寄送email結束.");
		  return true;
	  	} catch (Exception e) {
	  		return false;
	  }
	  
	 }
	}