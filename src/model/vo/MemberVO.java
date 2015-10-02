package model.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Member")
@XmlAccessorType(XmlAccessType.FIELD)
public class MemberVO implements Serializable {
	@XmlElement(required = true)
	private int memberId;
	@XmlElement(required = true)
	private String memberAccount;
	@XmlElement(required = true)
	private byte[] memberPassword;
	@XmlElement(required = true)
	private String memberEmail;
	@XmlElement(required = true)
	private String memberFB;
	@XmlElement(required = true)
	private String memberGoogle;
	@XmlElement(required = true)
	private String memberTwitter;
	@XmlElement(required = true)
	private String memberName;
	@XmlElement(required = true)
	private String memberNickname;
	@XmlElement(required = true)
	private java.util.Date memberBirthday;
	@XmlElement(required = true)
	private byte[] memberPhoto;
	@XmlElement(required = true)
	private java.util.Date memberRegisterTime;
	@XmlElement(required = true)
	private String memberSelfIntroduction;
	@XmlElement(required = true)
	private String broadcastWebsite;
	@XmlElement(required = true)
	private String broadcastTitle;
//	@XmlElement(required = true)
//	private String broadcastClassName;
//	@XmlElement(required = true)
//	private java.util.Date broadcastTime;
//	@XmlElement(required = true)
//	private String broadcastDescription;
//	@XmlElement(required = true)
//	private long broadcastWatchTimes;
	@XmlElement(required = true)
	private boolean suspendMember;

	@Override
	public String toString() {
		return memberAccount;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public byte[] getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(byte[] memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberFB() {
		return memberFB;
	}

	public void setMemberFB(String memberFB) {
		this.memberFB = memberFB;
	}

	public String getMemberGoogle() {
		return memberGoogle;
	}

	public void setMemberGoogle(String memberGoogle) {
		this.memberGoogle = memberGoogle;
	}

	public String getMemberTwitter() {
		return memberTwitter;
	}

	public void setMemberTwitter(String memberTwitter) {
		this.memberTwitter = memberTwitter;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public java.util.Date getMemberBirthday() {
		return memberBirthday;
	}

	public void setMemberBirthday(java.util.Date memberBirthday) {
		this.memberBirthday = memberBirthday;
	}

	public byte[] getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(byte[] memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public java.util.Date getMemberRegisterTime() {
		return memberRegisterTime;
	}

	public void setMemberRegisterTime(java.util.Date memberRegisterTime) {
		this.memberRegisterTime = memberRegisterTime;
	}

	public String getMemberSelfIntroduction() {
		return memberSelfIntroduction;
	}

	public void setMemberSelfIntroduction(String memberSelfIntroduction) {
		this.memberSelfIntroduction = memberSelfIntroduction;
	}

	public String getBroadcastWebsite() {
		return broadcastWebsite;
	}

	public void setBroadcastWebsite(String broadcastWebsite) {
		this.broadcastWebsite = broadcastWebsite;
	}

	public String getBroadcastTitle() {
		return broadcastTitle;
	}

	public void setBroadcastTitle(String broadcastTitle) {
		this.broadcastTitle = broadcastTitle;
	}

//	public String getBroadcastClassName() {
//		return broadcastClassName;
//	}
//
//	public void setBroadcastClassName(String broadcastClassName) {
//		this.broadcastClassName = broadcastClassName;
//	}
//
//	public java.util.Date getBroadcastTime() {
//		return broadcastTime;
//	}
//
//	public void setBroadcastTime(java.util.Date broadcastTime) {
//		this.broadcastTime = broadcastTime;
//	}
//
//	public String getBroadcastDescription() {
//		return broadcastDescription;
//	}
//
//	public void setBroadcastDescription(String broadcastDescription) {
//		this.broadcastDescription = broadcastDescription;
//	}
//
//	public long getBroadcastWatchTimes() {
//		return broadcastWatchTimes;
//	}
//
//	public void setBroadcastWatchTimes(long broadcastWatchTimes) {
//		this.broadcastWatchTimes = broadcastWatchTimes;
//	}

	public boolean isSuspendMember() {
		return suspendMember;
	}

	public void setSuspendMember(boolean suspendMember) {
		this.suspendMember = suspendMember;
	}
}
