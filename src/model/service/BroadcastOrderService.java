package model.service;

import java.util.Collection;

import model.dao.BroadcastOrderDAO;
import model.dao.jdbc.BroadcastOrderDAOjdbc;
import model.vo.BroadcastOrderVO;

public class BroadcastOrderService {
	private BroadcastOrderDAO dao;

	public BroadcastOrderService() {
		this.dao = new BroadcastOrderDAOjdbc();
	}

	public Collection<BroadcastOrderVO> broadcastOrder() {
		return dao.selectAll();
	}

	public Collection<BroadcastOrderVO> searchBroadcast(String keyword) {
		Collection<BroadcastOrderVO> list = null;
		if (keyword != null && keyword.trim().length() != 0) {
			list = dao.selectByMemberAccountOrBroadcastTitle(keyword, keyword);
		}
		return list;
	}

	public BroadcastOrderVO searchAccount(String Account) {
		BroadcastOrderVO list = null;
		if (Account != null && Account.trim().length() != 0) {
			list = dao.selectByMemberAccount(Account);
		}
		return list;
	}
	
	public BroadcastOrderVO createBroadcast(BroadcastOrderVO bean) {
		BroadcastOrderVO result = null;
		int temp = dao.insert(bean.getMemberAccount(), bean.getBroadcastSite(), bean.getBroadcastTitle(),
				bean.getBroadcastTime());
		if (temp == 1) {
			result = dao.selectByMemberAccount(bean.getMemberAccount());
		}
		return result;
	}

	public boolean changeTitle(BroadcastOrderVO bean) {
		int temp = dao.update(bean.getBroadcastTitle(), bean.getMemberAccount());
		if (temp == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean removeBroadcast(BroadcastOrderVO bean) {
		return dao.delete(bean.getMemberAccount());
	}
}
