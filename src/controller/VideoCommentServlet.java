package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.MemberService;
import model.service.VideoCommentsService;
import model.vo.MemberVO;
import model.vo.VideoCommentsVO;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import util.ConvertType;

@WebServlet("/VideoCommentServlet")
public class VideoCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VideoCommentsService service;
	
	public VideoCommentServlet() {
        super();
    }
	
	@Override
	public void init() throws ServletException {
		service = new VideoCommentsService();
	}
	
	public JSONArray convertToJson(Collection<VideoCommentsVO> result){
		JSONArray list = new JSONArray(); //this is Json simple's JSONArray
		
		for(VideoCommentsVO row : result){
			Map showAllVideoComments = new HashMap();
			
			showAllVideoComments.put("commentId", row.getCommentId());
			showAllVideoComments.put("memberId", row.getMemberId());
			showAllVideoComments.put("videoId", row.getVideoId());
			showAllVideoComments.put("commentContent", row.getCommentContent());
			showAllVideoComments.put("commentTime", row.getCommentTime()+"");
			showAllVideoComments.put("memberAccount", row.getMember().getMemberAccount());
			showAllVideoComments.put("memberName", row.getMember().getMemberName());
			list.add(showAllVideoComments);
		}
    	return list;
    }
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
//		String query = request.getQueryString();
//		
//		int videoId = ConvertType.convertToInt(query.substring(query.indexOf("=") + 1));
//		List<VideoCommentsVO> list = service.videoCommentsList(videoId);
//		
//		JSONObject jsonObj = new JSONObject();
//		for(VideoCommentsVO bean:list) {
//			String b64 = java.util.Base64.getEncoder().encodeToString(bean.getMember().getMemberPhoto());
//			bean.getMember().setMemberNickname(b64);
//		}
//		jsonObj.put("list", list);
//		System.out.println(jsonObj);
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(jsonObj.toString());
		
		this.doGet(request, response);
	}

	//尚未測試
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
//		HttpSession session = request.getSession();
//		MemberVO member = (MemberVO) session.getAttribute("user");
//		
//	    int memberId = member.getMemberId();
//		System.out.println("memberId : "+memberId);
//		
////		String memberName = (String) session.getAttribute("memberName");
//		String memberName = member.getMemberName();
//		System.out.println("memberName : "+memberName);
		
		
		String videoId = request.getParameter("videoId");
		System.out.println("show me : "+videoId);
//		String memberName = request.getParameter("memberName");
		String commentId = request.getParameter("commentId");
		String commentContent = request.getParameter("commentContent");
		System.out.println("commentContent : "+commentContent);
		String commentTime = request.getParameter("commentTime");
		String sendComment = request.getParameter("sendComment");
		String memberId = request.getParameter("memberId");
		System.out.println("MemberId : "+memberId);
		String memberAccount = request.getParameter("memberAccount");
		String memberPassword = request.getParameter("memberPassword");
		MemberService memberService = new MemberService();
		MemberVO checkMemberInfo = memberService.login1(memberAccount, memberPassword);
		
		
		
		Map<String , String> errors = new HashMap<String , String>();
		request.setAttribute("errors", errors);
		
		//判斷是會員才可新刪修(不要解開會屎翹翹QAQ)
//		if(sendComment!=null && memberAccount.equals(member.getMemberAccount())){
//			
//			if(sendComment.equals("insert") || sendComment.equals("update")){
//				if(commentContent == null || commentContent.length() == 0){
//					errors.put("commentContent", "請輸入留言後再" + sendComment);
//				}
//			}else if(sendComment.equals("delete")){
//				if(!memberAccount.equals(checkMemberInfo)){
//					errors.put("memberAccount", "請勿刪除其他會員留言");
//				}
//			}
//		}
		
		//轉換資料
		int convertVideoId =0;
		if(videoId!=null && videoId.length()!=0){
			convertVideoId = ConvertType.convertToInt(videoId);
			if(convertVideoId==-1000){
				errors.put("videoId", "VideoID MUST Be a Integer.");
			}
		}
		
		int convertMemberId = 0;
		if(memberId!=null && memberId.length()!=0){
			convertMemberId = ConvertType.convertToInt(memberId);
			if(convertMemberId==-1000){
				errors.put("memberId", "MemberID MUST Be a Integer.");
			}
		}
		
		Timestamp convertCommentTime = null;
		if(commentTime!=null && commentTime.length()!=0){
			SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
			try {
				convertCommentTime = (Timestamp) dsf.parse(commentTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(convertCommentTime == null){
				errors.put("commentTime", "CommentTime MUST Be a Timestamp.");
			}
		}
		
		int convertCommentId =0;
		if(commentId!=null && commentId.length()!=0){
			convertCommentId = ConvertType.convertToInt(commentId);
			if(convertCommentId==-1000){
				errors.put("commentId", "CommentID MUST Be a Integer.");
			}
		}
		
//		//呼叫Model
		VideoCommentsVO bean = new VideoCommentsVO();
		
		bean.setCommentId(convertCommentId);
		bean.setVideoId(convertVideoId);
		bean.setCommentContent(commentContent);
		bean.setCommentTime(convertCommentTime);

		
		//導向View
		
		//select, Insert, Update, Delete
		if(sendComment != null && sendComment.equals("select")){
			Collection<VideoCommentsVO> showComments = service.videoCommentsList(convertVideoId);
			
			response.setContentType("text/html; charset=utf-8");
			String comments = JSONValue.toJSONString(this.convertToJson(showComments));
			PrintWriter out = response.getWriter();
			out.println(comments);
			
			request.setAttribute("showComments", showComments);
			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response);
			
		}else if(sendComment != null && sendComment.equals("insert")){
			System.out.println("sendComment insert : "+sendComment);
			boolean result = service.insertVideoComments(convertMemberId, convertVideoId, commentContent);//commentContent不知道有沒有抓到東西:(
			if(result){
				System.out.println("insert : "+result);
				request.setAttribute("insert", true);
			}else{
				System.out.println("NONinsert : "+result);
				request.setAttribute("insert", false);
			}
			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response);
		}else if(sendComment != null && sendComment.equals("update")){
			boolean result = service.updateVideoComments(commentContent, convertCommentTime, convertCommentId);
			if(!result){
				request.setAttribute("update", false);
			}else{
				request.setAttribute("update", true);
			}
			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response);
		}else if(sendComment != null && sendComment.equals("delete")){
			boolean result = service.deleteVideoComments(convertCommentId);
			if(!result){
				request.setAttribute("delete", false);
			}else{
				request.setAttribute("delete", true);
			}
			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response);
		}else{
			Collection<VideoCommentsVO> result = service.videoCommentsList(convertVideoId);
			System.out.println("showComments : "+result);
			response.setContentType("text/html; charset=utf-8");
			String comments = JSONValue.toJSONString(this.convertToJson(result));
			PrintWriter out = response.getWriter();
			out.println(comments);
		}
		
	}
	
	

}
