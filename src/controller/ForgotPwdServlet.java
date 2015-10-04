package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDao;
import model.dao.jdbc.UserDAOjdbc;
import model.vo.User;
import util.EmailUtils;



/** 
 * 發送重設密碼申請的鏈接 
 */  
public class ForgotPwdServlet extends HttpServlet{
	private static final long serialVersionUID =1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userNameOrEmail = req.getParameter("useraccount");
		UserDao usesrDao = UserDAOjdbc.getInstance();
		User user = usesrDao.findUserByNameOrEmail(userNameOrEmail);
		if(user ==null){
			req.setAttribute("errorMsg",userNameOrEmail+",不存在");
			req.getRequestDispatcher("/GetPassword").forward(req, resp);
			return;
		}
		
		EmailUtils.sendResetPasswordEmail(user);
		
		req.setAttribute("sendMailMsg","您的申請已提交成功,請查看您的"+user.getMemberEmail()+"郵箱");
		
		req.getRequestDispatcher("HomePageVersion3.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	

}
