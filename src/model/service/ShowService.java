package model.service;

import java.util.Collection;
import model.dao.ShowDAO;
import model.dao.jdbc.ShowDAOjdbc;
import model.vo.ShowVO;

public class ShowService {
	private ShowDAO dao;

	public ShowService() {
		this.dao = new ShowDAOjdbc();
	}

	public Collection<ShowVO> showList(int memberId) {
		Collection<ShowVO> list = dao.selectJoinVideo(memberId);
		return list;
	}
	
	public ShowVO checkShow(int memberId,String website) {
		ShowVO list = dao.selectByIdAndWebsite(memberId,website);
		return list;
	}

	public Collection<ShowVO> addShow(int memberId, java.util.Date showTime, String website) {
		Collection<ShowVO> list = null;
		ShowVO bean = new ShowVO();
		bean.setMemberId(memberId);
		bean.setShowTime(showTime);
		bean.setWebsite(website);
		int result = dao.insert(bean);
		if (result == 1) {
			list = this.showList(memberId);
		}
		return list;
	}

	public Collection<ShowVO> addShow(ShowVO bean) {
		Collection<ShowVO> list = null;
		int result = dao.insert(bean);
		if (result == 1) {
			list = this.showList(bean.getMemberId());
		}
		return list;
	}

	public Collection<ShowVO> changeShow(java.util.Date showTime, String website, int memberId,
			java.util.Date showTimed) {
		Collection<ShowVO> list = null;
		int result = dao.update(showTime, website, memberId, showTimed);
		if (result == 1) {
			list = this.showList(memberId);
		}
		return list;
	}

	public Collection<ShowVO> changeShow(ShowVO bean, java.util.Date showTimed) {
		Collection<ShowVO> list = null;
		int result = dao.update(bean.getShowTime(), bean.getWebsite(), bean.getMemberId(), showTimed);
		if (result == 1) {
			list = this.showList(bean.getMemberId());
		}
		return list;
	}

	public Collection<ShowVO> removeShow(int memberId, String website) {
		Collection<ShowVO> list = null;
		boolean result = dao.delete(memberId, website);
		if (result) {
			list = this.showList(memberId);
		}
		return list;
	}

	public Collection<ShowVO> removeShow(ShowVO bean) {
		Collection<ShowVO> list = null;
		boolean result = dao.delete(bean.getMemberId(), bean.getWebsite());
		if (result) {
			list = this.showList(bean.getMemberId());
		}
		return list;
	}

	public static void main(String[] args) {
//		ShowService service = new ShowService();
//		for(ShowVO bean:service.showList(2)){
//			System.out.println(bean);
//			System.out.println(bean.getTitle());
//		}
	}
}
