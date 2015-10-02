package model.vo;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportArticleVO {
	@XmlElement(required = true)
	private int orderId;
	@XmlElement(required = true)
	private int reportedArticleId;
	@XmlElement(required = true)
	private java.util.Date reportTime;
	@XmlElement(required = true)
	private String reportReason;
	@XmlElement(required = true)
	private ArticleVO article;
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(reportTime);
		return orderId + "被檢舉的文章ID為: " + reportedArticleId + " (" + date + ")";
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getReportedArticleId() {
		return reportedArticleId;
	}
	public void setReportedArticleId(int reportedArticleId) {
		this.reportedArticleId = reportedArticleId;
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
	public ArticleVO getArticle() {
		return article;
	}
	public void setArticle(ArticleVO article) {
		this.article = article;
	}
}
