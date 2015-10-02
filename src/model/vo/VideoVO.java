package model.vo;

import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VideoVO {
	@XmlElement(required = true)
	private int videoId;
	@XmlElement(required = true)
	private int memberId;
	@XmlElement(required = true)
	private String videoWebsite;
	@XmlElement(required = true)
	private String videoClassName;
	@XmlElement(required = true)
	private String videoTitle;
	@XmlElement(required = true)
	private String videoName;
	@XmlElement(required = true)
	private String videoPath;
	@XmlElement(required = true)
	private java.util.Date videoUploadTime;
	@XmlElement(required = true)
	private long videoWatchTimes;
	@XmlElement(required = true)
	private String videoDescription;
	@XmlElement(required = true)
	private java.util.Date videoDescriptionModifyTime;
	@XmlElement(required = true)
	private MemberVO member;

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(videoUploadTime);
		return videoId + ": " + videoName + " (" + date + ")";
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getVideoWebsite() {
		return videoWebsite;
	}

	public void setVideoWebsite(String videoWebsite) {
		this.videoWebsite = videoWebsite;
	}

	public String getVideoClassName() {
		return videoClassName;
	}

	public void setVideoClassName(String videoClassName) {
		this.videoClassName = videoClassName;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public java.util.Date getVideoUploadTime() {
		return videoUploadTime;
	}

	public void setVideoUploadTime(java.util.Date videoUploadTime) {
		this.videoUploadTime = videoUploadTime;
	}

	public long getVideoWatchTimes() {
		return videoWatchTimes;
	}

	public void setVideoWatchTimes(long videoWatchTimes) {
		this.videoWatchTimes = videoWatchTimes;
	}

	public String getVideoDescription() {
		return videoDescription;
	}

	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}

	public java.util.Date getVideoDescriptionModifyTime() {
		return videoDescriptionModifyTime;
	}

	public void setVideoDescriptionModifyTime(java.util.Date videoDescriptionModifyTime) {
		this.videoDescriptionModifyTime = videoDescriptionModifyTime;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}

}
