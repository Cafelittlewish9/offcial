package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import model.service.MemberService;
import model.vo.MemberVO;
import util.ConvertType;

@WebServlet("/registry")
public class Registry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms;

	@Override
	public void init() throws ServletException {
		ms = new MemberService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		List<String> errors = new ArrayList<String>();
		request.setAttribute("Errors", errors);// 要與前端共享的錯誤訊息
		// 接收資料
		String username = request.getParameter("account");
		String userRegExp = "^[a-zA-Z0-9]{6,20}$";// 帳號正規式驗證
		String password = request.getParameter("password");
		String pwdRegExp = "^[a-zA-Z0-9]{8,20}$";// 密碼正規式驗證
		String usermail = request.getParameter("mail");
		String broadcastWebsite = request.getParameter("broadcastWebsite");
		String nickname = request.getParameter("nickname");
		String birthday = request.getParameter("birthday");
		String operation = request.getParameter("operation");
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		// 檢查資料
		if (username == null || username.trim().length() == 0) {
			errors.add("請輸入帳號");
		} else if (!username.trim().matches(userRegExp)) {
			errors.add("帳號限英文字母、數字，長度必須在6-20之間");
		}
		if (password == null || password.trim().length() == 0) {
			errors.add("密碼不能空白");
		} else if (!password.trim().matches(pwdRegExp)) {
			errors.add("密碼限英文字母、數字，長度必須在8-20之間");
		}
		if (usermail == null || usermail.trim().length() == 0) {
			errors.add("信箱請勿空白");
		}

		if (errors != null && !errors.isEmpty()) {// 以上任一錯誤發生時，留在註冊畫面
			request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			return;
		}
		// 呼叫service服務，將資料送去DB型轉已寫在model裡
		MemberVO result = ms.getOneMember(username);
		if (operation != null && operation.equals("註冊") && result != null) {
			errors.add("帳號已存在，請再輸入其他帳號");
			RequestDispatcher rd = request.getRequestDispatcher("SignUp.jsp");
			rd.forward(request, response);
			return;
		} else {
			ms.registry1(username, password, usermail,broadcastWebsite);
			
			MemberVO bean = new MemberVO();
			bean.setMemberAccount(username);
			bean.setMemberPassword(password.getBytes());
			bean.setMemberEmail(usermail);
			bean.setBroadcastWebsite(broadcastWebsite);
			bean.setMemberRegisterTime(new java.util.Date());
			if (nickname != null) {
				bean.setMemberNickname(nickname);
			} else {
				bean.setMemberNickname("");
			}
			if (birthday != "") {
				bean.setMemberBirthday(ConvertType.convertToUtilDate(birthday));
			} else {
				bean.setMemberBirthday(null);
			}			
			bean.setMemberName("img/default.png");
			ms.update(bean);
			session.setAttribute("user", bean);
			response.sendRedirect(path + "/PersonalPage.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}