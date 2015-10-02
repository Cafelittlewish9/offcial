package controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.service.MemberService;
import model.vo.MemberVO;
import util.ConvertType;

//@WebServlet("/memberInfoServlet")
@WebServlet(name = "MemberServlet", urlPatterns = { "/memberInfoServlet" })
public class MemberServlet extends HttpServlet {
	MemberService service;

	@Override
	public void init() throws ServletException {
		service = new MemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		String memberPassword=null;
		String memberNewPwd=null;
		String memberCheckPwd=null;
		String memberEmail=null;
		String memberNickname=null;
		String memberBirthday=null;
		String updateMemberInfo=null;
		Part filePart=null;
		
		Collection<Part> parts= request.getParts();
		for(Part item:parts){
			String name=item.getName();
			String value=request.getParameter(name);
			if(item.getContentType()==null){
				if (name.equals("memberOldPwd")){
					memberPassword=value;
					System.out.println(memberPassword);
				}else if(name.equals("memberNewPwd")){
					memberNewPwd=value;
				}else if(name.equals("memberCheckPwd")){
					memberCheckPwd=value;
				}else if(name.equals("memberEmail")){
					memberEmail=value;
				}else if(name.equals("nick")){
					memberNickname=value;
				}else if(name.equals("memberBirthday")){
					memberBirthday=value;
				}else if(name.equals("operation")){
					updateMemberInfo=value;
				}				
			}else{
				filePart = request.getPart("photo1");
			}			
		}		

		List<String> errors = new ArrayList<String>();
		request.setAttribute("WrongMsg", errors);
		//判斷是否為會員本人才可修改會員資料←因為會員的其他資料從session來可判斷為確實已登入且為會員
		//驗證用戶真的有輸入資料
//		System.out.println(memberPassword+"what");
		if(memberPassword!=null && memberPassword!="" && !memberPassword.getBytes().equals(member.getMemberPassword())){
			if (memberNewPwd != "" && memberCheckPwd != "") {
				if (!memberNewPwd.equals(memberCheckPwd)) {
					errors.add("請再確認一次新密碼");
				}
			}			
		}else{
			errors.add("舊密碼不相符，請重新輸入");
		}

		//資料轉換
		Date convertMemberBirthday = null;
		if(memberBirthday!=null && memberBirthday.length()!=0){
			SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				convertMemberBirthday = (Date) dsf.parse(memberBirthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}	
		
		if (updateMemberInfo != null && updateMemberInfo.equals("update")){
			if (filePart != null) {
				String header = filePart.getHeader("Content-Disposition");
				String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
				InputStream in = filePart.getInputStream();

				byte[] buffer = new byte[in.available()];
				int length = -1;
				while ((length = in.read(buffer)) != -1) {
					String temp = ConvertType.convertToBase64(buffer, "png");
					member.setMemberName(temp);
				}
				service.getPhoto(buffer, member.getMemberId(), member.getMemberName());
				in.close();
			}else {
				member.setMemberPhoto(member.getMemberPhoto());
				member.setMemberName(member.getMemberName());
			}
			if(memberNewPwd!=null && memberNewPwd.trim().length()!=0 && memberCheckPwd.trim().length() != 0 && memberCheckPwd!=null){
				member.setMemberPassword(memberNewPwd.getBytes());
				System.out.println(memberNewPwd+"ho"+memberCheckPwd+"ye");
			}else{
				byte[] pwd=member.getMemberPassword();
				System.out.println(pwd+"yo");
			}
			if (memberEmail != "" && memberEmail != null) {
				member.setMemberEmail(memberEmail);				
			}else{
				String email=member.getMemberEmail();
				member.setMemberEmail(email);
			}
			if (memberNickname != "" && memberNickname!=null ) {
				member.setMemberNickname(memberNickname);
			}else{
				String nickname=member.getMemberNickname();
				member.setMemberNickname(nickname);
			}
			if (memberBirthday != "" && memberBirthday!=null) {
				member.setMemberBirthday(convertMemberBirthday);
			}else{
				java.util.Date birth=member.getMemberBirthday();
				member.setMemberBirthday(birth);
			}
			String broadcastWebsite=member.getBroadcastWebsite();
			service.update(member);
		}

		if (errors.isEmpty()) {
			response.sendRedirect(request.getContextPath()+"/PersonalPage.jsp");
			return;
		}
		else{
			response.sendRedirect(request.getContextPath()+"/HomePageVersion3.jsp");
			return;
		}
	}



}
