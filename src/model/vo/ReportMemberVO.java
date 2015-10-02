package model.vo;

//import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportMemberVO {
	@XmlElement(required = true)
	private int orderId;
	@XmlElement(required = true)
	private int reportedMemberId;
	@XmlElement(required = true)
	private java.util.Date reportTime;
	@XmlElement(required = true)
	private String reportReason;
	@XmlElement(required = true)
	private MemberVO member;
	
	@Override
	public String toString() {
//		VO只要simpleDateFromat就死，註解之後進資料庫格式也正確，所以先註解。
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date = sdf.format(reportTime);
		return orderId + "被檢舉的會員ID為: " + reportedMemberId + " (" + reportTime + ")";
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getReportedMemberId() {
		return reportedMemberId;
	}
	public void setReportedMemberId(int reportedMemberId) {
		this.reportedMemberId = reportedMemberId;
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
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
}
