package model.vo;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportVideoVO {
	@XmlElement(required = true)
	private int orderId;
	@XmlElement(required = true)
	private int reportedVideoId;
	@XmlElement(required = true)
	private java.util.Date reportTime;
	@XmlElement(required = true)
	private String reportReason;
	@XmlElement(required = true)
	private VideoVO video;
	private MemberVO member;
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(reportTime);
		return orderId + "被檢舉的影片ID為: " + reportedVideoId + " (" + date + ")" + video.getMemberId() +video.getMember().getMemberAccount();
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getReportedVideoId() {
		return reportedVideoId;
	}
	public void setReportedVideoId(int reportedVideoId) {
		this.reportedVideoId = reportedVideoId;
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
	public VideoVO getVideo() {
		return video;
	}
	public void setVideo(VideoVO video) {
		this.video = video;
	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
	
}
