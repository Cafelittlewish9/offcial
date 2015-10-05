package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.BlackService;
import model.vo.BlackVO;
import model.vo.MemberVO;
@WebServlet("/Black")
public class BlackServlet extends javax.servlet.http.HttpServlet{
    private static final long serialVersionUID = 2010L;
    private BlackService service = null;

    public void init() throws ServletException{
    	service = new BlackService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        this.processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        this.processRequest(request, response);
    }   

//    public void insert(HttpServletRequest request, HttpServletResponse response) 
//        throws ServletException, IOException{
////    	接收資料
//    	String memberId =request.getParameter("memberId");
//    	String blackedId=request.getParameter("blackedId");
////      轉換型別
//        int id=Integer.parseInt(memberId);
//        int bid=Integer.parseInt(blackedId);
//        BlackVO bean = new BlackVO();
//        MemberVO member = new MemberVO();
//        member.setMemberId(id);
//        bean.setMember(member);
//        bean.setMemberId(id);
//        //還是應該用bid去求出被黑的人的memberAccount?!
////      呼叫model
////        service.insertBlackList(bean);
////      setAttribute共享資訊並轉交
//        request.setAttribute("blackList", service.searchBlackAccount(id));
//    }

    
//    public void removeAll(HttpServletRequest request, HttpServletResponse response) 
//            throws ServletException, IOException{
//        	String memberId =request.getParameter("memberId");
//            int id=Integer.parseInt(memberId);
//            service.removeAll(id);
//            request.setAttribute("blackList", service.searchBlackAccount(id));
//        }

//    public void list(HttpServletRequest request, HttpServletResponse response) 
//        throws ServletException, IOException{
//    	String memberId =request.getParameter("memberId");
//    	int id=Integer.parseInt(memberId);
//        request.setAttribute("blackList", service.searchBlackAccount(id));        
//    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String operation = request.getParameter("operation");
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		int memberId = member.getMemberId();
    	String blackedId=request.getParameter("blackedId");
        int bid=Integer.parseInt(blackedId);
        if(operation!=null && operation.equals("delete")){
			 service.removeBlackAccount(memberId, bid);
        }
       
        response.sendRedirect(request.getContextPath() + "/PersonalPage.jsp");
    }   

}