package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.FollowService;
import model.service.LoginService;
import model.service.MemberService;
import model.vo.FollowVO;
import model.vo.MemberVO;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms;
	private LoginService ls;
//	private FollowService fService;

	public void init() throws ServletException {
		ms = new MemberService();
		ls = new LoginService();
//		this.fService = new FollowService();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<String> errors = new ArrayList<String>();
		request.setAttribute("ErrorMsgKey", errors);
		String username = request.getParameter("memberAccount");
		String password = request.getParameter("memberPassword");
		String operation = request.getParameter("operation");
		if (username == null ||username.trim().length() == 0) {
			errors.add("帳號欄必須輸入");
		}
		if (password == null ||password.trim().length() == 0) {
			errors.add("密碼欄必須輸入");
		}

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("Login2.jsp").forward(request, response);
			return;
		}

		MemberVO bean = ms.login1(username, password);
		if (operation != null && operation.trim().length()!=0 && operation.equals("登入") && bean != null) {
			HttpSession session = request.getSession();
			String ip = request.getRemoteAddr();
			ls.login(username, ip);
//			Collection<FollowVO> fList = fService.followList(bean.getMemberId());
			session.setAttribute("user", bean);
//			session.setAttribute("fList", fList);
			response.sendRedirect(request.getContextPath() + "/HomePageVersion3.jsp");
			return;
		} else {
			errors.add("帳號密碼錯誤，請重新輸入");
			request.getRequestDispatcher("Login2.jsp").forward(request, response);
			return;
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

}
