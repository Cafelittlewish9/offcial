package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.dao.ShowDAO;
import model.vo.MemberVO;
import model.vo.ShowVO;
import model.vo.VideoVO;
import util.ConvertType;
import util.GC;

public class ShowDAOjdbc implements ShowDAO {
	private DataSource datasource;

	public ShowDAOjdbc() {
		try {
			InitialContext context = new InitialContext();
//			this.datasource = (DataSource) context.lookup("java:comp/env/jdbc/DB");
			this.datasource = (DataSource) context.lookup(GC.DATASOURCE);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_BY_ID_JOIN_MEMBER = "SELECT s.*, broadcastTitle FROM show s Join member m ON website = broadcastWebsite WHERE s.memberId = ? ORDER BY showTime";

	@Override
	public List<ShowVO> selectJoinMember(int memberId) {
		ShowVO result = null;
		List<ShowVO> list = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_JOIN_MEMBER);) {
			stmt.setInt(1, memberId);
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<ShowVO>();
			while (rset.next()) {
				result = new ShowVO();
				result.setMemberId(rset.getInt("memberId"));
				result.setShowTime(ConvertType.convertToLocalTime(rset.getTimestamp("showTime")));
				result.setWebsite(rset.getString("website"));
				MemberVO member = new MemberVO();
				member.setBroadcastTitle(rset.getString("broadcastTitle"));
				result.setMember(member);
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_ID_JOIN_VIDEO = "SELECT s.*, videoTitle FROM show s Join Video v ON website = videoWebsite WHERE s.memberId = ? ORDER BY showTime ASC";

	@Override
	public List<ShowVO> selectJoinVideo(int memberId) {
		ShowVO result = null;
		List<ShowVO> list = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_JOIN_VIDEO);) {
			stmt.setInt(1, memberId);
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<ShowVO>();
			while (rset.next()) {
				result = new ShowVO();
				result.setMemberId(rset.getInt("memberId"));
				result.setShowTime(ConvertType.convertToLocalTime(rset.getTimestamp("showTime")));
				result.setWebsite(rset.getString("website"));
				VideoVO video = new VideoVO();
				video.setVideoTitle(rset.getString("videoTitle"));
				result.setVideo(video);
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_ID_AND_WEBSITE = "SELECT * FROM show WHERE memberId = ? and website = ? ORDER BY showTime ASC";

	@Override
	public ShowVO selectByIdAndWebsite(int memberId,String website) {
		ShowVO bean = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_AND_WEBSITE);) {
			stmt.setInt(1, memberId);
			stmt.setString(2, website);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				bean = new ShowVO();
				bean.setMemberId(rset.getInt("memberId"));
				bean.setShowTime(ConvertType.convertToLocalTime(rset.getTimestamp("showTime")));
				bean.setWebsite(rset.getString("website"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	private static final String SELECT_ALL = "SELECT s.*,m.memberAccount FROM show s Join member m ON s.memberId = m.memberId";

	@Override
	public List<ShowVO> selectAll() {
		List<ShowVO> list = null;
		try (

				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			list = new ArrayList<ShowVO>();
			while (rset.next()) {
				ShowVO bean = new ShowVO();
				bean.setMemberId(rset.getInt("memberId"));
				bean.setShowTime(ConvertType.convertToLocalTime(rset.getTimestamp("showTime")));
				bean.setWebsite(rset.getString("website"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String INSERT = "insert into show(memberId, website) values (?, ?)";

	@Override
	public int insert(ShowVO bean) {
		int result = -1;
		try (

				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if (bean != null) {
				stmt.setInt(1, bean.getMemberId());
				java.util.Date date = new java.util.Date();
				stmt.setString(2, bean.getWebsite());
				result = stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//======沒註解，因為要implement進來才行。======
	private static final String UPDATE = "update show set showTime = ?, website = ? where memberId = ? and showTime = ?";

	@Override
	public int update(java.util.Date showTime, String website, int memberId, java.util.Date showTimed) {
		int result = -1;
		try (

				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

				PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
			if (showTime != null) {
				long time = showTime.getTime();
				stmt.setTimestamp(1, new java.sql.Timestamp(time));
			} else {
				stmt.setTimestamp(1, null);
			}
			stmt.setString(2, website);
			stmt.setInt(3, memberId);
			stmt.setTimestamp(4, new java.sql.Timestamp(showTimed.getTime()));
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//======沒註解，因為要implement進來才行。======

	private static final String DELETE = "delete from show where memberId = ? and website = ?";

	@Override

	public boolean delete(int memberId, String website) {
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, memberId);
			stmt.setString(2, website);
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

//		ShowDAOjdbc dao = new ShowDAOjdbc();
//		List<ShowVO> list = dao.selectJoinMember(2);
//		for (ShowVO bean : list) {
//			System.out.println(bean);
//			System.out.println(bean.getTitle());
//		}

		}
		
		

	
}
