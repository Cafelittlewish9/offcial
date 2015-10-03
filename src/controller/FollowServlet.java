package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.FollowService;
import model.service.MemberService;
import model.vo.FollowVO;
import model.vo.MemberVO;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import util.ConvertType;


@WebServlet("/FollowServlet")
public class FollowServlet extends HttpServlet {
	FollowService service;
	@Override
	public void init() throws ServletException {
		service = new FollowService();
	}
	
	public JSONArray convertToJson(Collection<FollowVO> result){
		JSONArray list = new JSONArray();
		for(FollowVO row : result){
			Map followList = new HashMap();
			followList.put("memberAccount", row.getMember().getMemberAccount());
			followList.put("memberId", row.getMemberId());
			followList.put("followId", row.getFollowId());
			list.add(followList);
		}
		return list;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		
		int memberId = member.getMemberId();
		System.out.println("Follow Test MemberID : "+memberId);
		
		String followId = request.getParameter("followId");
		System.out.println("Follow Test FollowID : "+followId);
		
		String memberAccount = request.getParameter("memberAccount");
		String memberPassword = request.getParameter("memberPassword");
		String sendMemberFollow = request.getParameter("sendMemberFollow");
		
		MemberService memberService = new MemberService();
		MemberVO checkMemberInfo = memberService.login1(memberAccount, memberPassword);
		Map<String , String> errors = new HashMap<String , String>();
		request.setAttribute("errors", errors);
		
		//判斷是會員才可
//		if(sendMemberFollow!=null && memberAccount.equals(checkMemberInfo)){
//			
//			if(sendMemberFollow.equals("insert")){
//				if(followId != null){
//					errors.put("sendMemberFollow", "您已追蹤此會員");
//				}
//			}else if(sendMemberFollow.equals("delet")){
//				if(followId == null ){
//					errors.put("sendMemberFollow", "您並無追蹤此會員");
//				}
//			}
//		}
		
		//轉換資料
		int convertFollowId = 0;
		if(followId!=null && followId.length()!=0){
			convertFollowId = ConvertType.convertToInt(followId);
			if(convertFollowId == -1000){
				errors.put("memberId", "MemberID MUST Be a Integer.");
			}
		}
		
		FollowVO followBean = new FollowVO();
		followBean.setFollowId(convertFollowId);
		followBean.setMemberId(memberId);
		
		//導向View
		if(sendMemberFollow !=null && sendMemberFollow.equals("select")){
			Collection<FollowVO> showFollowList = service.followList(memberId);
			
			response.setContentType("text/html; charset=utf-8");
			String follow = JSONValue.toJSONString(this.convertToJson(showFollowList));
			PrintWriter out = response.getWriter();
			out.println(follow);
			
			request.setAttribute("showFollowList", showFollowList);
			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response);
		}else if(sendMemberFollow !=null && sendMemberFollow.equals("insert")){
			System.out.println("sendMemberFollow insert : "+sendMemberFollow);
			boolean result = service.follow(memberId, convertFollowId);
			if(result){
				request.setAttribute("insert", 1);
			}else{
				request.setAttribute("insert", 0);
			}
			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response);
		}else if (sendMemberFollow !=null && sendMemberFollow.equals("delete")){
			boolean result = service.unfollow(convertFollowId, memberId);
			if(result){
				request.setAttribute("delete", 1);
			}else if(!result){
				request.setAttribute("delete", 0);
			}
			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response);
		}else{
			Collection<FollowVO> showFollowList = service.followList(memberId);
			response.setContentType("text/html; charset=utf-8");
			String follow = JSONValue.toJSONString(this.convertToJson(showFollowList));
			PrintWriter out = response.getWriter();
			out.println(follow);
		}
		
		
		
		
	}

}
