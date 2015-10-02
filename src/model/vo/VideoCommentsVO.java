package model.vo;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VideoCommentsVO {
	@XmlElement(required = true)
	private int commentId;
	@XmlElement(required = true)
	private int memberId;
	@XmlElement(required = true)
	private int videoId;
	@XmlElement(required = true)
	private String commentContent;
	@XmlElement(required = true)
	private java.util.Date commentTime;
	@XmlElement(required = true)
	private MemberVO member;
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(commentTime);
		return commentId + "討論的影片ID為: " + videoId + " (" + date + ")" +commentContent + member.getMemberName();
	}
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public java.util.Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(java.util.Date commentTime) {
		this.commentTime = commentTime;
	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}

}
