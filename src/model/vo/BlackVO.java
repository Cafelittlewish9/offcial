package model.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BlackVO {
	@XmlElement(required = true)
	private int memberId;
	@XmlElement(required = true)
	private int blackedId;
	@XmlElement(required = true)
	private MemberVO member;
	
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return memberId + ": " + blackedId + " (被黑的)";
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getBlackedId() {
		return blackedId;
	}
	public void setBlackedId(int blackedId) {
		this.blackedId = blackedId;
	}
}
