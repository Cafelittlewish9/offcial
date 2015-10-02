package model.vo;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReplyArticleVO {
	@XmlElement(required = true)
	private int replyArticleId;
	@XmlElement(required = true)
	private int memberId;
	@XmlElement(required = true)
	private int articleId;
	@XmlElement(required = true)
	private String replyContent;
	@XmlElement(required = true)
	private java.util.Date publishTime;
	@XmlElement(required = true)
	private java.util.Date modifyTime;
	@XmlElement(required = true)
	private MemberVO member;	
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(publishTime);
		return replyArticleId + "回覆的文章ID為: " + articleId + " (" + date + ")"+ "帳號：" + member.getMemberAccount() + "會員照片" + member.getMemberPhoto();
	}
	public int getReplyArticleId() {
		return replyArticleId;
	}
	public void setReplyArticleId(int replyArticleId) {
		this.replyArticleId = replyArticleId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
}
