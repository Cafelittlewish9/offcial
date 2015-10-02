package model.service;

import java.util.Collection;

import model.dao.LoginDAO;
import model.dao.jdbc.LoginDAOjdbc;
import model.vo.LoginVO;

public class LoginService {
	private LoginDAO dao;

	public LoginService(){
		this.dao=new LoginDAOjdbc();
	}
	
	public boolean login(String memberAccount, String ip) {
		LoginVO bean = new LoginVO();
		bean.setMemberAccount(memberAccount);
		bean.setIp(ip);
		return dao.insert(bean);
	}
	public Collection<LoginVO> loginLog(String memberAccount) {
		return dao.selectAll(memberAccount);
	}
	
	public java.util.Date lastLogin(String memberAccount) {
		java.util.Date result = null;
		if (memberAccount != null && memberAccount.trim().length() != 0) {
			LoginVO bean = dao.select(memberAccount);
			if (bean != null) {
				result = bean.getLoginTime();
			}
		}
		return result;
	}
}
