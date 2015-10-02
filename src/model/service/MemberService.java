package model.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import model.dao.MemberDAO;
import model.dao.jdbc.MemberDAOjdbc;
import model.vo.MemberVO;
import util.ServicePasswordChange;

//註冊後轉update頁面要由controller處理
//查詢、修改個人資料、搜尋會員
public class MemberService {
	private MemberDAO dao;

	public MemberService() {
		this.dao = new MemberDAOjdbc();
	}

	// registry1採帳號密碼信箱註冊；registry2是採信箱密碼註冊
	public String registry1(String username, String password, String usermail,String broadcastWebsite) {
		String result = null;
		MemberVO mvo = new MemberVO();
		
		try {
			
			if (username.length() == 0) {
				result = "Please keyin a username.";
			} else {
				if (dao.getId(username) != -1) {
					result = "Please change another username.";
				} else {
					mvo.setMemberAccount(username);
					mvo.setMemberPassword(password.getBytes());
					mvo.setMemberEmail(usermail);
					mvo.setMemberName("img/default.png");
					mvo.setBroadcastWebsite(broadcastWebsite);
					// mvo.setMemberRegisterTime(new java.util.Date());
					dao.insert(mvo);
					result = "success";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// registry1採帳號密碼信箱註冊；registry2是採信箱密碼註冊
	public String registry2(String usermail, String password){
		String result = null;
		MemberVO mvo = new MemberVO();
		try {
			
			if (usermail.length() == 0) {
				result = "Please keyin a your mail.";
			} else {
				if (dao.getId(usermail.substring(0, usermail.indexOf("@"))) != 0) {
					result = "Please change another mail address to registry.";
				} else {
					mvo.setMemberAccount(usermail.substring(0, usermail.indexOf("@")));
					mvo.setMemberPassword(password.getBytes());
					mvo.setMemberEmail(usermail);
					mvo.setMemberName("img/default.png");
					// mvo.setMemberRegisterTime(new java.util.Date());
					dao.insert2(mvo);
					result = "success";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// login1是採用帳號登入；login2是採信箱登入
	public MemberVO login1(String username, String password) {
		MemberVO mvo = null;		
		if (password != null && password!="" && password.length() != 0) {
			mvo = dao.findByMemberAccount(username);
			System.out.println(mvo);
			if(mvo!=null){
				byte[] memberPwd = mvo.getMemberPassword();
				byte[] temp = password.getBytes();
				if (Arrays.equals(memberPwd, temp)) {
					mvo = dao.findByPK(mvo.getMemberId());
					return mvo;
				}else{
					return null;					
				}
			}return mvo;
		}return mvo;		
	}

	// login1是採用帳號登入；login2是採信箱登入
	public MemberVO login2(String usermail, String password) {
		int memberId = dao.getId(usermail.substring(0, usermail.indexOf("@")));
		MemberVO mvo = dao.findByPK(memberId);
		if (mvo != null) {
			if (password != null && password.length() != 0) {
				byte[] memberPwd = mvo.getMemberPassword();
				byte[] temp = password.getBytes();
				if (Arrays.equals(memberPwd, temp)) {
					mvo = dao.findByPK(memberId);
					return mvo;
				}else{
					return null;
				}
			}
		}
		return mvo;
	}

	// 會員修改密碼
	public String changePassword(String username, String oldPassword, String newPassword) {
		MemberVO mvo = this.login1(username, oldPassword);// 透過帳密拿到該會員的資料
		String result = "Error, please contact the administrator!";
		if (mvo != null) {
			byte[] temp = newPassword.getBytes();
			mvo.setMemberPassword(temp);
			if (dao.update(mvo) == 1) {
				result = "You've successfully changed your password!";
			}
		}
		return result;
	}
	
	//↓↓↓↓↓↓↓↓雖然很討厭他，但我還是測完了，但請再幫我測一次感恩。
	public String insertMD5Password(String memberAccount, String usermail , String newPassword){
		MemberVO mvo = this.login1(memberAccount, usermail);// 透過帳密拿到該會員的資料
		String result = "你打錯了!!!!";
		if (mvo != null || dao.getId(usermail.substring(0, usermail.indexOf("@"))) != 0) {
			String replace = Math.random() * 100 + "";// 亂數產生一組數字去轉成md5回傳給使用者
			newPassword = ServicePasswordChange.getMD5Endocing(replace);
			byte[] temp = newPassword.getBytes();
			mvo.setMemberPassword(temp);
			if (dao.update(mvo) == 1) {
				result = "這是輸進資料庫的MD5 : "+newPassword;
			}
		}
		return result;
	} 
	//↑↑↑↑↑↑↑雖然很討厭他，但我還是測完了，但請再幫我測一次感恩。

	// 會員查詢個資
	public MemberVO checkInfo(String username, String password) {
		MemberVO mvo = this.login1(username, password);
		return mvo;
	}
	// ↑是否是指連查詢個資都要輸入一次帳密？
	
	
	public MemberVO showMemberInfo(String memberAccount , String password){
		MemberVO result = this.login1(memberAccount, password);
		if(result!=null){
			MemberVO showMemberInfo = dao.findByMemberAccount(memberAccount);
			return showMemberInfo;
		}
		return result;
	}
	
	

	// 更改、測試完成
	public int update(MemberVO mvo) {
		int result = -1;
		if (mvo != null) {
			mvo.setMemberPassword(mvo.getMemberPassword());
			mvo.setMemberEmail(mvo.getMemberEmail());
			mvo.setMemberFB(mvo.getMemberFB());
			mvo.setMemberGoogle(mvo.getMemberGoogle());
			mvo.setMemberTwitter(mvo.getMemberTwitter());
			mvo.setMemberName(mvo.getMemberName());
			mvo.setMemberNickname(mvo.getMemberNickname());
			mvo.setMemberBirthday(mvo.getMemberBirthday());
			mvo.setMemberPhoto(mvo.getMemberPhoto());
			mvo.setMemberRegisterTime(mvo.getMemberRegisterTime());
			mvo.setMemberSelfIntroduction(mvo.getMemberSelfIntroduction());
			mvo.setBroadcastWebsite(mvo.getBroadcastWebsite());
			mvo.setBroadcastTitle(mvo.getBroadcastTitle());
//			mvo.setBroadcastClassName(mvo.getBroadcastClassName());
//			mvo.setBroadcastTime(mvo.getBroadcastTime());
//			mvo.setBroadcastDescription(mvo.getBroadcastDescription());
//			mvo.setBroadcastWatchTimes(mvo.getBroadcastWatchTimes());
			result = dao.update(mvo);
		}
		return result;
	}
	
	// 測完了，沒問題可以動呦吼吼。
	public int suspendMember(int memberId, boolean suspendRight){
		int result = -1;
		if(memberId!=0){
			result =  dao.switchSuspend(memberId, suspendRight);
		}
		return result;
	}
	
	public String getMemberNickname(String memberAccount){
		String result = null;
		if(memberAccount!=null){
			result = dao.getMemberNickname(memberAccount);
		}
		return result;
	}

	public int getPhoto(byte[] photo,int memberId,String memberName){
		int result=dao.photoOut(photo, memberId,memberName);
		return result;
	}
	
	
	public MemberVO getOneMember(String memberAccount){
		return dao.findByMemberAccount(memberAccount);		
	}

	
	public static void main(String[] args) throws SQLException {
		MemberService service = new MemberService();
		// MemberVO mvo = service.login1("niceguy", "E");
		// System.out.println("");
//		String result = service.registry2("madclown@gmail.com", "E");
//		System.out.println(result);

		service.insertMD5Password("AA", "AA@gmail.com", "123");
		
		// String result=service.registry1("niceguy", "E","madclown@gmail.com");
		// System.out.println("registry result="+result);
		// MemberVO mvo = service.login1("niceguy", "E");
		// System.out.println("VO info="+mvo);

//		 MemberVO mvo = service.login2("Pikachu@gmail.com", "A");
//		 System.out.println("VO info="+mvo);

		// String result = service.changePassword("niceguy",
		// "madclown@gmail.com", "E");
		// System.out.println("result="+result);
		
		//Member switchSuspend
//		MemberService memberService = new MemberService();
//		System.out.println(memberService.suspendMember(7, false));

	}
}
