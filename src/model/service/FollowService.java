package model.service;

import java.util.Collection;

import model.dao.FollowDAO;
import model.dao.jdbc.FollowDAOjdbc;
import model.vo.FollowVO;
import model.vo.ShowVO;

public class FollowService {
	private FollowDAO dao;

	public FollowService() {
		this.dao = new FollowDAOjdbc();
	}

	public boolean follow(int memberId, int followId) {
		FollowVO bean = new FollowVO();
		bean.setMemberId(memberId);
		bean.setFollowId(followId);
		int result = dao.insert(bean);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean unfollow(int followId, int memberId) {
		return dao.delete(followId, memberId);
	}
	
	public Collection<FollowVO> followList(int memberId) {
		return dao.selectByMemberId(memberId);
	}
	
	public FollowVO checkFollow(int memberId,int followId) {
		FollowVO list = dao.selectByMemberIdAndFollowId(memberId, followId);
		return list;
	}
}
