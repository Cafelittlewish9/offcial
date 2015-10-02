package controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.service.ArticleService;
import model.vo.ArticleVO;

@WebServlet("/Article")
public class Article extends HttpServlet {
	private ArticleService articleService;

	@Override
	public void init() throws ServletException {
		this.articleService = new ArticleService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String subclassNo = req.getParameter("subclassNo");
		Collection<ArticleVO> list = null;
		if (subclassNo != null && subclassNo.trim().length() != 0) {
			list = this.articleService.searchBySubclassNo(subclassNo);
		} else {
			list = this.articleService.allArticle();
		}
		JSONObject obj = new JSONObject();
		obj.put("list", list);
//		System.out.println(obj);
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(obj.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
