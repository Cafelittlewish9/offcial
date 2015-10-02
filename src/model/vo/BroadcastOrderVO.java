package model.vo;

public class BroadcastOrderVO {
	private String memberAccount;
	private String broadcastSite;
	private String broadcastTitle;
	private java.util.Date broadcastTime;
	private long broadcastWatchTimes;
	private MemberVO broadcastWebsite;

	@Override
	public String toString() {
		return " Account: " + memberAccount + " 標題: " + broadcastTitle + " 網址: " + broadcastSite + System.lineSeparator();
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getBroadcastSite() {
		return broadcastSite;
	}

	public void setBroadcastSite(String broadcastSite) {
		this.broadcastSite = broadcastSite;
	}

	public String getBroadcastTitle() {
		return broadcastTitle;
	}

	public void setBroadcastTitle(String broadcastTitle) {
		this.broadcastTitle = broadcastTitle;
	}

	public java.util.Date getBroadcastTime() {
		return broadcastTime;
	}

	public void setBroadcastTime(java.util.Date broadcastTime) {
		this.broadcastTime = broadcastTime;
	}

	public long getBroadcastWatchTimes() {
		return broadcastWatchTimes;
	}

	public void setBroadcastWatchTimes(long broadcastWatchTimes) {
		this.broadcastWatchTimes = broadcastWatchTimes;
	}
	
	public MemberVO getBroadcastWebsite() {
		return broadcastWebsite;
	}

	public void setBroadcastWebsite(MemberVO broadcastWebsite) {
		this.broadcastWebsite = broadcastWebsite;
	}
}
