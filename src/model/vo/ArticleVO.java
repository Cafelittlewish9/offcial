package model.vo;

import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ArticleVO {
	@XmlElement(required = true)
	private int articleId;
	@XmlElement(required = true)
	private int memberId;
	@XmlElement(required = true)
	private String subclassNo;
	@XmlElement(required = true)
	private String articleTitle;
	@XmlElement(required = true)
	private String articleContent;
	@XmlElement(required = true)
	private java.util.Date publishTime;
	@XmlElement(required = true)
	private java.util.Date modifyTime;
	@XmlElement(required = true)
	private long watchTimes;
	private MemberVO member;
	private ArticleClassVO articleClass;

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(publishTime);
		return articleId + ": " + articleTitle + " (" + date + ")";
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getSubclassNo() {
		return subclassNo;
	}

	public void setSubclassNo(String subclassNo) {
		this.subclassNo = subclassNo;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public java.util.Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public long getWatchTimes() {
		return watchTimes;
	}

	public void setWatchTimes(long watchTimes) {
		this.watchTimes = watchTimes;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}

	public ArticleClassVO getArticleClass() {
		return articleClass;
	}

	public void setArticleClass(ArticleClassVO articleClass) {
		this.articleClass = articleClass;
	}
}
