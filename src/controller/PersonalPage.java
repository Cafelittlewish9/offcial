package controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import model.service.BlackService;
import model.service.FollowService;
import model.service.VideoService;
import model.vo.BlackVO;
import model.vo.FollowVO;
import model.vo.MemberVO;
import model.vo.VideoVO;

@WebServlet("/PersonalPage")
public class PersonalPage extends HttpServlet {
	private FollowService fService;
	private BlackService bService;

	@Override
	public void init() throws ServletException {
		this.fService = new FollowService();
		this.bService = new BlackService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		if (member != null) {
			int memberId = member.getMemberId();
			System.out.println(memberId);
			// Collection<VideoVO> vList = vService.searchMemberId(memberId);
			// System.out.println(memberId);
			Collection<FollowVO> fList = fService.followList(memberId);
			// request.setAttribute("vList", vList);
			// request.setAttribute("fList", fList);
			// request.setAttribute("bList",bList);

			JSONObject obj = new JSONObject();
			obj.put("follow", fList);
//			System.out.println(obj);
			response.getWriter().write(obj.toString());
		}
		// request.getRequestDispatcher("PersonalPage.jsp").forward(request,
		// response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		if (member != null) {
			int memberId = member.getMemberId();
			Collection<BlackVO> bList = bService.searchBlackAccount(memberId);
			JSONObject obj = new JSONObject();
			obj.put("black", bList);
			System.out.println(obj);
			response.getWriter().write(obj.toString());
		}
	}

}
