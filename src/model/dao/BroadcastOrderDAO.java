package model.dao;

import java.util.List;

import model.vo.BroadcastOrderVO;

public interface BroadcastOrderDAO {

	public List<BroadcastOrderVO> selectAll();

	public List<BroadcastOrderVO> selectByMemberAccountOrBroadcastTitle(String memberAccount, String broadcastTitle);
	
	public BroadcastOrderVO selectByMemberAccount(String memberAccount);

	public int insert(String memberAccount, String broadcastWebsite, String broadcastTitle, java.util.Date broadcastTime);

	public int update(String broadcastTitle, String memberAccount);

	public boolean delete(String memberAccount);

}