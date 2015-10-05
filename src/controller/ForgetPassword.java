package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.MemberService;
import model.vo.MemberVO;
import util.ServicePasswordChange;



@WebServlet("/forgotPwd")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;
	@Override
	public void init() throws ServletException {
		this.service = new MemberService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    String memberAccount = request.getParameter("memberAccount");
	    String email = request.getParameter("memberEmail");
	    System.out.println(memberAccount);
	    System.out.println(email);
		response.setCharacterEncoding("UTF-8");
		this.service = new MemberService();
		Map<String, String> errorMessage = new HashMap<>();			       
	    MemberVO bean = service.getOneMember(memberAccount);
	    
	    
	    String replace = Math.random() * 100 + "";
	    String newPassword = ServicePasswordChange.getMD5Endocing(replace);
	    byte[] temp = newPassword.getBytes();
	    
	    boolean result = false;
	    if(bean!=null){
	    	result = SendEmail.sendemail(memberAccount,"xxxx", email);
	    } else {
	    	errorMessage.put("wrong", "使用者帳號信箱錯誤");
	    }
	    if(result) {
	    	response.getWriter().write("{\"result\":\"true\"}");
	    } else {
	    	response.getWriter().write("{\"result\":\"false\"}");
	    }
//	    if(!errorMessage.isEmpty()){
//			request.setAttribute("ErrorMsg", errorMessage);
//			RequestDispatcher rd = request.getRequestDispatcher("GetPassword.jsp");
//			rd.forward(request,response);			
//		}else{
//			RequestDispatcher rd = request.getRequestDispatcher("GetPassword1.jsp");	
//			rd.forward(request,response);
//		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
