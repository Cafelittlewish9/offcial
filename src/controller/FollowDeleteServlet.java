package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.BlackService;
import model.service.FollowService;
import model.vo.BlackVO;
import model.vo.MemberVO;
@WebServlet("/FollowDeleteServlet")
public class FollowDeleteServlet extends javax.servlet.http.HttpServlet{
    private static final long serialVersionUID = 2010L;
    private FollowService service = null;

    public void init() throws ServletException{
    	service = new FollowService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        this.processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        this.processRequest(request, response);
    }
    public void processRequest(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String operation = request.getParameter("operation");
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		int memberId = member.getMemberId();
    	String followId=request.getParameter("followId");
        int fid=Integer.parseInt(followId);
        if(operation!=null && operation.equals("delete")){
			 service.unfollow(fid, memberId);
        }
       
        response.sendRedirect(request.getContextPath() + "/PersonalPage.jsp");
    }   

}