package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.dao.BroadcastOrderDAO;
import model.dao.VideoDAO;
import model.vo.BroadcastOrderVO;
import model.vo.MemberVO;
import model.vo.VideoVO;
import util.ConvertType;
import util.GC;

public class BroadcastOrderDAOjdbc implements BroadcastOrderDAO {
//	private static final String URL = GC.URL;
//	private static final String USERNAME = GC.USERNAME;
//	private static final String PASSWORD = GC.PASSWORD;
	
	private DataSource datasource;

	public BroadcastOrderDAOjdbc() {
		try {
			InitialContext context = new InitialContext();
//			this.datasource = (DataSource) context.lookup("java:comp/env/jdbc/DB");
			this.datasource = (DataSource) context.lookup(GC.DATASOURCE);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_ALL = "SELECT b.*,m.broadcastWebsite FROM BroadcastOrder b Join member m ON b.memberAccount = m.memberAccount ORDER BY broadcastTime DESC";

	@Override
	public List<BroadcastOrderVO> selectAll() {
		List<BroadcastOrderVO> list = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			list = new ArrayList<BroadcastOrderVO>();
			while (rset.next()) {
				MemberVO mbean = new MemberVO();
				BroadcastOrderVO bean = new BroadcastOrderVO();
				bean.setMemberAccount(rset.getString("memberAccount"));
				bean.setBroadcastSite(rset.getString("broadcastSite"));
				bean.setBroadcastTitle(rset.getString("broadcastTitle"));
				bean.setBroadcastTime(rset.getTimestamp("broadcastTime"));
				bean.setBroadcastWatchTimes(rset.getLong("broadcastWatchTimes"));
				mbean.setBroadcastWebsite(rset.getString("broadcastWebsite"));
				System.out.println(rset.getString("broadcastWebsite"));
				bean.setBroadcastWebsite(mbean);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_MEMBERACCOUNT_OR_BROADCASTTITLE = "SELECT * FROM BroadcastOrder WHERE memberAccount like ? OR broadcastTitle like ?";

	@Override
	public List<BroadcastOrderVO> selectByMemberAccountOrBroadcastTitle(String memberAccount, String broadcastTitle) {
		
		List<BroadcastOrderVO> list = null;
		ResultSet rset = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_MEMBERACCOUNT_OR_BROADCASTTITLE);) {
			stmt.setString(1, memberAccount);
			stmt.setString(2, broadcastTitle);
			
			rset = stmt.executeQuery();
			list = new ArrayList<BroadcastOrderVO>();
			while (rset.next()) {
				BroadcastOrderVO bean = new BroadcastOrderVO();
				bean.setMemberAccount(rset.getString("memberAccount"));
				bean.setBroadcastSite(rset.getString("broadcastSite"));
				bean.setBroadcastTitle(rset.getString("broadcastTitle"));
				bean.setBroadcastTime(rset.getTimestamp("broadcastTime"));
				bean.setBroadcastWatchTimes(rset.getLong("broadcastWatchTimes"));
				
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_MEMBERACCOUNT = "SELECT * FROM BroadcastOrder WHERE memberAccount = ?";

	@Override
	public BroadcastOrderVO selectByMemberAccount(String memberAccount) {
		BroadcastOrderVO bean = null;
		ResultSet rset = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_MEMBERACCOUNT);) {
			stmt.setString(1, memberAccount);
			
			rset = stmt.executeQuery();
			bean = new BroadcastOrderVO();
			while (rset.next()) {
				bean.setMemberAccount(rset.getString("memberAccount"));
				bean.setBroadcastSite(rset.getString("broadcastSite"));
				bean.setBroadcastTitle(rset.getString("broadcastTitle"));
				bean.setBroadcastTime(rset.getTimestamp("broadcastTime"));
				bean.setBroadcastWatchTimes(rset.getLong("broadcastWatchTimes"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	private static final String INSERT = "INSERT INTO BroadcastOrder(memberAccount, broadcastSite, broadcastTitle, broadcastTime) VALUES(?, ?, ?, ?)";

	@Override
	public int insert(String memberAccount, String broadcastSite, String broadcastTitle,
			java.util.Date broadcastTime) {
		int result = -1;
		try(
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
					stmt.setString(1, memberAccount);
					stmt.setString(2, broadcastSite);
					stmt.setString(3, broadcastTitle);
					if(broadcastTime!=null) {
						long time = broadcastTime.getTime();
						stmt.setTimestamp(4, new java.sql.Timestamp(time));
					} else {
						stmt.setTimestamp(4, null);				
					}
					int i = stmt.executeUpdate();
					if (i == 1) {
						return 1;
					}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private static final String UPDATE = "UPDATE BroadcastOrder SET broadcastTitle = ? WHERE memberAccount = ?";

	@Override
	public int update(String broadcastTitle, String memberAccount) {
		int result = -1;
		try(
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
					stmt.setString(1, broadcastTitle);
					stmt.setString(2, memberAccount);
					
					int i = stmt.executeUpdate();
					if (i == 1) {
						return 1;
					}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "DELETE FROM BroadcastOrder WHERE memberAccount = ?";

	@Override
	public boolean delete(String memberAccount) {
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setString(1, memberAccount);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		// SelectAll
//		BroadcastOrderDAO temp = new BroadcastOrderDAOjdbc();
//		List<BroadcastOrderVO> list = temp.selectAll();
//		System.out.println(list);

		// Insert
//		 String memberAccount = "FUN";
//		 String broadcastWebsite =
//		 "http://itvvm.cloudapp.net/ITV/LiveShow?memberAccount=FUN";
//		 String broadcastTitle = "TGIF";
//		 BroadcastOrderVO tempinsert = new BroadcastOrderVO();
//		 tempinsert.setMemberAccount(memberAccount);
//		 tempinsert.setBroadcastWebsite(broadcastWebsite);
//		 tempinsert.setBroadcastTitle(broadcastTitle);
//		 tempinsert.setBroadcastTime(new java.sql.Date(System.currentTimeMillis()));
//		
//		 BroadcastOrderDAO dao = new BroadcastOrderDAOjdbc();
//		 int insertlist = dao.insert(tempinsert.getMemberAccount(),tempinsert.getBroadcastWebsite(),tempinsert.getBroadcastTitle(),tempinsert.getBroadcastTime());
//		 System.out.println("Insert : "+ insertlist);

		// Update
//		 String memberAccount = "FUN";
//		 String broadcastTitle = "TGIF IS F";
//		
//		 BroadcastOrderVO tempupdate = new BroadcastOrderVO();
//		 tempupdate.setMemberAccount(memberAccount);
//		 tempupdate.setBroadcastTitle(broadcastTitle);
//		
//		 BroadcastOrderDAO dao = new BroadcastOrderDAOjdbc();
//		 int updatelist = dao.update(tempupdate.getBroadcastTitle(),tempupdate.getMemberAccount());
//		 System.out.println("Update : "+ updatelist);

		// Delete
//		BroadcastOrderDAO dao = new BroadcastOrderDAOjdbc();
//		 boolean d = dao.delete("FUN");
//		 if(d==true){
//		 System.out.println("Delete : Success!!!");
//		 }else{
//		 System.out.println("Delete : Fail!!!");
//		 }
	}
}