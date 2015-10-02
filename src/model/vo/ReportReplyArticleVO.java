package model.vo;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportReplyArticleVO {
	@XmlElement(required = true)
	private int orderId;
	@XmlElement(required = true)
	private int reportedReplyArticleId;
	@XmlElement(required = true)
	private java.util.Date reportTime;
	@XmlElement(required = true)
	private String reportReason;
	@XmlElement(required = true)
	private ReplyArticleVO replyArticle;
	private MemberVO member;

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(reportTime);
		return orderId + "被檢舉的回覆文章ID為: " + reportedReplyArticleId + " (" + date + ")" + replyArticle.getMemberId() + replyArticle.getMember().getMemberAccount();
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getReportedReplyArticleId() {
		return reportedReplyArticleId;
	}
	public void setReportedReplyArticleId(int reportedReplyArticleId) {
		this.reportedReplyArticleId = reportedReplyArticleId;
	}
	public java.util.Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(java.util.Date reportTime) {
		this.reportTime = reportTime;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public ReplyArticleVO getReplyArticle() {
		return replyArticle;
	}
	public void setReplyArticle(ReplyArticleVO replyArticle) {
		this.replyArticle = replyArticle;
	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
}
