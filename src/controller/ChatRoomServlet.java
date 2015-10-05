package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ChatRoomServlet")

public class ChatRoomServlet extends javax.servlet.http.HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static LinkedList<Message> messages = new LinkedList<Message>();

	public ChatRoomServlet() {		
	}
	// 新增、存新留言
	private List<Message> addMessage(String text) {
		if (text != null && text.trim().length() > 0) {
			messages.addFirst(new Message(text));
			while (messages.size() > 20) {
				messages.removeLast();
			}
		}
		return messages;
	}
	// 抓新留言
	private List<Message> getMessages() {
		return messages;
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		HttpSession session = request.getSession();
		List<Message> list = null;
	
		if ("send".equals(request.getParameter("task"))) {
			list = addMessage(request.getParameter("msg"));
		} else if ("query".equals(request.getParameter("task"))) {
			list = getMessages();
		}
		PrintWriter out = response.getWriter();		// 回復拿到字串丟給列印字串
		response.setContentType("text/xml");		// 使客戶端瀏覽器，區分不同種類的數據
		response.setHeader("Cache-Control", "no-cache");	// no-cache, 雖然會 cache，但還是會每次都問有沒有新內容，就是三個判斷點的第一個
		response.setCharacterEncoding("UTF-8");

		out.println("<response>");
		for (int i = 0; i < list.size(); i++) {		// list.size() 傳回 ArrayList 元素個數 (與容量無關)
			String msg = list.get(i).getText();
			out.println("<message>" + msg + "</message>");
		}
		out.println("</response>");
		out.close();
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
