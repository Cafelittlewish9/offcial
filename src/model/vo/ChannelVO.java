package model.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ChannelVO {
	@XmlElement(required = true)
	private int memberId;
	@XmlElement(required = true)
	private byte channelNo;
	@XmlElement(required = true)
	private String broadcastWebsite;
	@XmlElement(required = true)
	private MemberVO member;

	@Override
	public String toString() {
		return "頻道 " + channelNo + ": " + broadcastWebsite;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public byte getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(byte channelNo) {
		this.channelNo = channelNo;
	}

	public String getBroadcastWebsite() {
		return broadcastWebsite;
	}

	public void setBroadcastWebsite(String broadcastWebsite) {
		this.broadcastWebsite = broadcastWebsite;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}
}
