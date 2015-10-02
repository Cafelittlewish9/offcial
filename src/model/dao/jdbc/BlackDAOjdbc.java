package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.dao.BlackDAO;
import model.dao.MemberDAO;
import model.vo.BlackVO;
import model.vo.MemberVO;
import util.GC;

/**
 * @author iTV小組成員
 *
 */
public class BlackDAOjdbc implements BlackDAO {
//	private static final String URL = GC.URL;
//	private static final String USERNAME = GC.USERNAME;
//	private static final String PASSWORD = GC.PASSWORD;
	private DataSource ds;
	public BlackDAOjdbc(){
		try {
			Context ctx = new InitialContext();
			this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String MARK_BLACK = "INSERT INTO black VALUES (?,?)";
	/**
	 * 設定黑名單
	 * @param memberId 設定黑名單之會員編號
	 * @param blackedId 被設定為黑名單之會員編號
	 * @return true 設定成功；false 設定失敗
	 */	
	@Override
	public boolean markBlack(int memberId, int blackedId) {
		boolean markResult = false;
		int updateCount = 0;
		try (Connection conn=ds.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(MARK_BLACK);) {
			if (memberId != blackedId) {
				pstmt.setInt(1, memberId);
				pstmt.setInt(2, blackedId);
				updateCount = pstmt.executeUpdate();
				if (updateCount == 1) {
					markResult = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return markResult;
	}

	//SELECT更改過了
	private static final String GET_LIST="SELECT b.memberId, b.blackedId,m.memberAccount FROM black b JOIN member m"
	+" ON b.blackedid = m.memberid WHERE b.memberId=?";
	
	/**
	 * 查詢某會員編號所設定的全部黑名單
	 * @param memberId 設定黑名單之會員編號
	 * @return List<BlackVO>
	 */	
	@Override
	public List<BlackVO> getList(int memberId) {		
		BlackVO blackMem = null;
		List<BlackVO> blacks = new ArrayList<BlackVO>();
		try (Connection conn=ds.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(GET_LIST);) {
			pstmt.setInt(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO bean=new MemberVO();
				blackMem = new BlackVO();
				blackMem.setBlackedId(rs.getInt("blackedId"));
				blackMem.setMemberId(rs.getInt("memberId"));
				bean.setMemberAccount(rs.getString("memberAccount"));
				blackMem.setMember(bean);
				blacks.add(blackMem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blacks;
	}

	private static final String REMOVE_BLACK = "DELETE FROM black WHERE memberId=? AND blackedId=?";
	/**
	 * 解除某會員編號所設定的單筆黑名單
	 * @param memberId 設定黑名單之會員編號
	 * @param blackedId 被設定為黑名單之會員編號
	 * @return List<BlackVO>
	 */	
	@Override
	public boolean removeBlack(int memberId, int blackedId) {
		boolean result = false;
		try (Connection conn=ds.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(REMOVE_BLACK);) {
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, blackedId);
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * （突然覺得世間充滿大愛）解除某會員編號所設定的全部黑名單
	 * @param memberId 設定黑名單之會員編號
	 * @return List<BlackVO>
	 */
	private static final String REMOVE_ALL = "DELETE FROM black WHERE memberId=?";
	@Override
	public boolean removeAll(int memberId) {
		boolean removeResult = false;
		try (Connection conn=ds.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(REMOVE_ALL);) {
			pstmt.setInt(1, memberId);
			int updateCount = pstmt.executeUpdate();
			if (updateCount >= 1) {
				removeResult = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return removeResult;
	}

	
	
	// 測試程式
	public static void main(String[] args) throws SQLException {
		BlackDAO blackDao = new BlackDAOjdbc();
		
		// System.out.println(blackDao.markBlack(2,4));
		// System.out.println(blackDao.markBlack(5,5));
		for (BlackVO bean:blackDao.getList(4)){
			System.out.println(bean+bean.getMember().getMemberAccount());			
		}
		
//		System.out.println(blackDao.removeBlack(4, 2));
//		System.out.println(blackDao.removeAll(4));

	}

}
