package model.vo;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShowVO {
	@XmlElement(required=true)
	private int memberId;
	@XmlElement(required=true)
	private java.util.Date showTime;
	@XmlElement(required=true)
	private String website;
	@XmlElement(required=true)
	private VideoVO video;
	@XmlElement(required=true)
	private MemberVO member;

	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(showTime);
		return "節目網址: " + website + " (" + date + ")";
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public java.util.Date getShowTime() {
		return showTime;
	}
	public void setShowTime(java.util.Date showTime) {
		this.showTime = showTime;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
