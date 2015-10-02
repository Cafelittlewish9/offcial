package model.service;


import java.util.Collection;
import model.dao.ArticleDAO;
import model.dao.jdbc.ArticleDAOjdbc;
import model.vo.ArticleVO;
import model.vo.MemberVO;

/**
 * @author iTV小組成員
 *
 */
public class ArticleService {
	private ArticleDAO dao;

	/**
	 * 初始化ArticleDAO
	 */
	public ArticleService() {
		this.dao = new ArticleDAOjdbc();
	}

	/**
	 * 查詢所有文章
	 * 
	 * @return Collection<ArticleVO>
	 */
	public Collection<ArticleVO> allArticle() {
		return dao.selectAll();
	}
	public Collection<ArticleVO> searchBySubclassNo(String subclassNo) {
		return dao.selectBySubclassNo(subclassNo);
	}

	/**
	 * 依照文章子類別代碼來查詢文章
	 * 
	 * @param subclassNo
	 *            文章的子類別代碼
	 * @return Collection<ArticleVO>
	 */
	public Collection<ArticleVO> searchByInput(String keyword) {
		if (keyword.indexOf(" ")!=-1){
			keyword.split(" ");
			
		}		
		Collection<ArticleVO> list = dao.selectByInput(keyword,keyword,keyword,keyword);
		return list;
	}


	/**
	 * 增加一篇文章
	 * 
	 * @param bean
	 *            必須包含 <b>memberId</b>、<b>subclassNoarticleTitle</b> 以及
	 *            <b>articleContent</b>
	 * @return true 新增成功; false 新增失敗
	 * @see #addArticle(int, String, String, String)
	 */
	public boolean addArticle(ArticleVO bean) {
		boolean result = false;
		bean.getMemberId();
		bean.getSubclassNo();
		bean.getArticleTitle();
		bean.getArticleContent();
		if (bean != null) {
			result = dao.insert(bean);
		}
		return result;
	}

	/**
	 * 增加一篇文章
	 * 
	 * @param memberId
	 *            會員ID
	 * @param subclassNo
	 *            文章的類別代碼
	 * @param articleTitle
	 *            文章的標題
	 * @param articleContent
	 *            文章的內容
	 * @return true 新增成功; false 新增失敗
	 * @see #addArticle(ArticleVO)
	 */
	public boolean addArticle(int memberId, String subclassNo, String articleTitle, String articleContent) {
		boolean result = false;
		ArticleVO bean = new ArticleVO();
		bean.setMemberId(memberId);
		bean.setSubclassNo(subclassNo);
		bean.setArticleTitle(articleTitle);
		bean.setArticleContent(articleContent);
		if (bean != null) {
			return dao.insert(bean);
		}
		return result;
	}

	/**
	 * 修改文章內容
	 * 
	 * @param articleContent
	 *            修改後的內容
	 * @param articleId
	 *            要修改的文章ID
	 * @return true 新增成功; false 新增失敗
	 */
	public boolean modifyArticle(String articleContent, int articleId , int memberId, String subClassNo , String articleTitle) {
		ArticleVO bean = new ArticleVO();
		
		bean.setArticleContent(articleContent);
		bean.setArticleId(articleId);
		bean.setMemberId(memberId);
		bean.setSubclassNo(subClassNo);
		bean.setArticleTitle(articleTitle);
		
		return dao.update(bean);

	}

	/**
	 * 修改文章內容
	 * 
	 * @param bean
	 *            必須包含 <b>articleContent</b> 與 <b>articleId</b>
	 * @return true 修改成功; false 修改失敗
	 * @see #modifyArticle(String, int)
	 */
	public boolean modifyArticle(ArticleVO bean) {
		boolean result = false;
		
		bean.getMemberId();
		bean.getArticleId();
		bean.getArticleContent();
		bean.getSubclassNo();
		bean.getArticleTitle();
		
		if (bean != null) {
			return dao.update(bean);
		}
		return result;
	}

	/**
	 * 刪除文章
	 * 
	 * @param articleId
	 *            要刪除的文章ID
	 * @return true 新增成功; false 新增失敗
	 * @see #addArticle(ArticleVO)
	 */
	public boolean deleteArticle(int articleId) {
		boolean result = false;
		if (dao.delete(articleId)) {
			return true;
		}
		return result;
	}

	/**
	 * 刪除文章
	 * 
	 * @param bean
	 *            必須包含 <b>articleId</b>
	 * @return true 新增成功; false 新增失敗
	 * @see #deleteArticle(int)
	 */
	public boolean deleteArticle(ArticleVO bean) {
		boolean result = false;
		if (dao.delete(bean.getArticleId())) {
			return true;
		}
		return result;
	}

	// 測試程式
	public static void main(String[] args) {
		ArticleService service = new ArticleService();
		// System.out.println(service.allArticle());
		// System.out.println(service.allSubArticle("M"));
		System.out.println(service.searchByInput("一天"));
		// System.out.println(service.searchArticle("Pikachu", "皮卡丘"));
		// System.out.println(service.addArticle());
		// System.out.println(service.deleteArticle(1));
//		System.out.println(service.modifyArticle("hey", 12));

	}
}
