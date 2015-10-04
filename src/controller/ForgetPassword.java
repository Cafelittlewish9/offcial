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



@WebServlet("/forgotPwd")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ForgetPassword() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String memberAccount = request.getParameter("memberAccount");
	    String email = request.getParameter("email");
	    request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		MemberService service = new MemberService();
		Map<String, String> errorMessage = new HashMap<>();			       
	    MemberVO bean = service.getOneMember(memberAccount);
	    if(bean!=null && bean.getMemberEmail().equals(email)){
	    	SendEmail.sendemail(bean.getMemberAccount(),new String(bean.getMemberPassword()), bean.getMemberEmail());
	    } else {
	    	errorMessage.put("wrong", "使用者帳號信箱錯誤");
	    }
	    	    
	    if(!errorMessage.isEmpty()){
			request.setAttribute("ErrorMsg", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("/GetPassword.jsp");
			rd.forward(request,response);
			
		}
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/GetPassword1.jsp");	
		rd.forward(request,response);
	    
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd =  request.getRequestDispatcher("/Login2.jsp");
		rd.forward(request, response);
		return;	
	}
}
