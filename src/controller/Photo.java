package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

@WebServlet(name = "Photo", urlPatterns = { "/upload" })
public class Photo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MemberService ms;

	public void init() throws ServletException {
		ms = new MemberService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		Part filePart = request.getPart("photo1");
		String updateMemberInfo = request.getParameter("operation");
		if (updateMemberInfo != null && updateMemberInfo.equals("update")&&filePart!=null ){
			String header = filePart.getHeader("Content-Disposition");
			String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
			InputStream in = filePart.getInputStream();

			byte[] buffer = new byte[in.available()];
			int length = -1;
			String temp=null;
			while ((length = in.read(buffer)) != -1) {
				temp = ConvertType.convertToBase64(buffer, "png");
//				member.setMemberName(temp);
			}

			ms.getPhoto(buffer,member.getMemberId(),temp);
			
			in.close();
			
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath())+"/PersonalPage.jsp");
		}else{
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath())+"/HomePageVersion3.jsp");
		}
		
	}
}