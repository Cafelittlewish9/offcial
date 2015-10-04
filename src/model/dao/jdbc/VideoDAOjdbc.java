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

import model.dao.VideoDAO;
import model.vo.MemberVO;
import model.vo.VideoVO;
import util.ConvertType;
import util.GC;

public class VideoDAOjdbc implements VideoDAO {
//	private static final String URL = GC.URL;
//	private static final String USERNAME = GC.USERNAME;
//	private static final String PASSWORD = GC.PASSWORD;
	private DataSource datasource;

	public VideoDAOjdbc() {
		try {
			InitialContext context = new InitialContext();
			this.datasource = (DataSource) context.lookup("java:comp/env/jdbc/iTV");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_BY_VIDEOTITLE = "SELECT v.*,m.memberAccount, m.memberName FROM video v Join member m ON v.memberId = m.memberId WHERE videoTitle LIKE ?";

	@Override
	public List<VideoVO> selectByVideoTitle(String videoTitle) {
		VideoVO result = null;
		List<VideoVO> list = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_VIDEOTITLE);) {
			stmt.setString(1, "%" + videoTitle + "%");
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<VideoVO>();
			while (rset.next()) {
				MemberVO bean = new MemberVO();
				result = new VideoVO();
				result.setVideoId(rset.getInt("videoId"));
				result.setMemberId(rset.getInt("memberId"));
				result.setVideoWebsite(rset.getString("videoWebsite"));
				result.setVideoClassName(rset.getString("videoClassName"));
				result.setVideoTitle(rset.getString("videoTitle"));
				result.setVideoName(rset.getString("videoName"));
				result.setVideoPath(rset.getString("videoPath"));
				result.setVideoUploadTime(ConvertType.convertToLocalTime(rset.getTimestamp("videoUploadTime")));
				result.setVideoWatchTimes(rset.getLong("videoWatchTimes"));
				result.setVideoDescription(rset.getString("videoDescription"));
				result.setVideoDescriptionModifyTime(
						ConvertType.convertToLocalTime(rset.getTimestamp("videoDescriptionModifyTime")));
				bean.setMemberAccount(rset.getString("memberAccount"));
				bean.setMemberName(rset.getString("memberName"));
				result.setMember(bean);
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_VIDEONAME = "SELECT v.*,m.memberAccount, m.memberName FROM video v Join member m ON v.memberId = m.memberId WHERE videoName = ?";

	@Override
	public List<VideoVO> selectByVideoName(String videoName) {
		VideoVO result = null;
		List<VideoVO> list = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_VIDEONAME);) {
			stmt.setString(1, videoName);
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<VideoVO>();
			while (rset.next()) {
				MemberVO bean = new MemberVO();
				result = new VideoVO();
				result.setVideoId(rset.getInt("videoId"));
				result.setMemberId(rset.getInt("memberId"));
				result.setVideoWebsite(rset.getString("videoWebsite"));
				result.setVideoClassName(rset.getString("videoClassName"));
				result.setVideoTitle(rset.getString("videoTitle"));
				result.setVideoName(rset.getString("videoName"));
				result.setVideoPath(rset.getString("videoPath"));
				result.setVideoUploadTime(ConvertType.convertToLocalTime(rset.getTimestamp("videoUploadTime")));
				result.setVideoWatchTimes(rset.getLong("videoWatchTimes"));
				result.setVideoDescription(rset.getString("videoDescription"));
				result.setVideoDescriptionModifyTime(
						ConvertType.convertToLocalTime(rset.getTimestamp("videoDescriptionModifyTime")));
				bean.setMemberAccount(rset.getString("memberAccount"));
				bean.setMemberName(rset.getString("memberName"));
				result.setMember(bean);
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static final String SELECT_BY_VIDEOCLASSNAME = "SELECT TOP 10 v.*,m.memberAccount, m.memberName FROM video v Join member m ON v.memberId = m.memberId WHERE videoClassName = ?";

	@Override
	public List<VideoVO> selectByVideoClassName(String videoClassName) {
		VideoVO result = null;
		List<VideoVO> list = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_VIDEOCLASSNAME);) {
			stmt.setString(1, videoClassName);
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<VideoVO>();
			while (rset.next()) {
				MemberVO bean = new MemberVO();
				result = new VideoVO();
				result.setVideoId(rset.getInt("videoId"));
				result.setMemberId(rset.getInt("memberId"));
				result.setVideoWebsite(rset.getString("videoWebsite"));
				result.setVideoClassName(rset.getString("videoClassName"));
				result.setVideoTitle(rset.getString("videoTitle"));
				result.setVideoName(rset.getString("videoName"));
				result.setVideoPath(rset.getString("videoPath"));
				result.setVideoUploadTime(ConvertType.convertToLocalTime(rset.getTimestamp("videoUploadTime")));
				result.setVideoWatchTimes(rset.getLong("videoWatchTimes"));
				result.setVideoDescription(rset.getString("videoDescription"));
				result.setVideoDescriptionModifyTime(
						ConvertType.convertToLocalTime(rset.getTimestamp("videoDescriptionModifyTime")));
				bean.setMemberAccount(rset.getString("memberAccount"));
				bean.setMemberName(rset.getString("memberName"));
				result.setMember(bean);
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static final String SELECT_BY_MEMBERID = "SELECT v.*,m.memberAccount, m.memberName FROM video v Join member m ON v.memberId = m.memberId WHERE m.memberId=?";

	@Override
	public List<VideoVO> selectByMemberId(int memberId) {
		VideoVO result = null;
		List<VideoVO> list = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_MEMBERID);) {
			stmt.setInt(1, memberId);
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<VideoVO>();
			while (rset.next()) {
				MemberVO bean = new MemberVO();
				result = new VideoVO();
				result.setVideoId(rset.getInt("videoId"));
				result.setMemberId(rset.getInt("memberId"));
				result.setVideoWebsite(rset.getString("videoWebsite"));
				result.setVideoClassName(rset.getString("videoClassName"));
				result.setVideoTitle(rset.getString("videoTitle"));
				result.setVideoName(rset.getString("videoName"));
				result.setVideoPath(rset.getString("videoPath"));
				result.setVideoUploadTime(ConvertType.convertToLocalTime(rset.getTimestamp("videoUploadTime")));
				result.setVideoWatchTimes(rset.getLong("videoWatchTimes"));
				result.setVideoDescription(rset.getString("videoDescription"));
				result.setVideoDescriptionModifyTime(
						ConvertType.convertToLocalTime(rset.getTimestamp("videoDescriptionModifyTime")));
				bean.setMemberAccount(rset.getString("memberAccount"));
				bean.setMemberName(rset.getString("memberName"));
				result.setMember(bean);
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_ALL = "SELECT v.*,m.memberAccount, m.memberName FROM video v Join member m ON v.memberId = m.memberId ORDER BY videoWatchTimes DESC";

	@Override
	public List<VideoVO> selectAll() {
		List<VideoVO> list = null;
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			list = new ArrayList<VideoVO>();
			while (rset.next()) {
				VideoVO bean = new VideoVO();
				bean.setVideoId(rset.getInt("videoId"));
				bean.setMemberId(rset.getInt("memberId"));
				bean.setVideoWebsite(rset.getString("videoWebsite"));
				bean.setVideoClassName(rset.getString("videoClassName"));
				bean.setVideoTitle(rset.getString("videoTitle"));
				bean.setVideoName(rset.getString("videoName"));
				bean.setVideoPath(rset.getString("videoPath"));
				bean.setVideoUploadTime(ConvertType.convertToLocalTime(rset.getTimestamp("videoUploadTime")));
				bean.setVideoWatchTimes(rset.getLong("videoWatchTimes"));
				bean.setVideoDescription(rset.getString("videoDescription"));
				bean.setVideoDescriptionModifyTime(
						ConvertType.convertToLocalTime(rset.getTimestamp("videoDescriptionModifyTime")));
				MemberVO member = new MemberVO();
				member.setMemberAccount(rset.getString("memberAccount"));
				member.setMemberName(rset.getString("memberName"));
				bean.setMember(member);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String INSERT = "insert into video(memberId, videoWebsite, videoClassName, videoTitle, videoName, videoPath, videoWatchTimes, videoDescription) values (?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public boolean insert(VideoVO bean) {
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if (bean != null) {
				stmt.setInt(1, bean.getMemberId());
				stmt.setString(2, bean.getVideoWebsite());
				stmt.setString(3, bean.getVideoClassName());
				stmt.setString(4, bean.getVideoTitle());
				stmt.setString(5, bean.getVideoName());
				stmt.setString(6, bean.getVideoPath());
				stmt.setLong(7, bean.getVideoWatchTimes());
				stmt.setString(8, bean.getVideoDescription());
				int i = stmt.executeUpdate();
				if (i == 1) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static final String UPDATE_DESCRIPTION = "update video set videoTitle = ?,videoDescription = ?, videoDescriptionModifyTime = GETUTCDATE() where videoId = ?";

	@Override
	public boolean update(int videoId, String videoTitle, String videoDescription) {
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE_DESCRIPTION);) {
			stmt.setString(1, videoTitle);
			stmt.setString(2, videoDescription);
			stmt.setInt(3, videoId);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static final String UPDATE_WATCHTIMES = "update video set videoWatchTimes = ? where videoId = ?";

	@Override
	public void update(long videoWatchTimes, int videoId) {
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE_WATCHTIMES);) {
			stmt.setLong(1, videoWatchTimes);
			stmt.setInt(2, videoId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static final String DELETE = "delete from video where videoId = ?";

	@Override
	public boolean delete(int videoId) {
		try (
				Connection conn = datasource.getConnection();
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, videoId);
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
		// SelectALL
		VideoDAO temp = new VideoDAOjdbc();
		List<VideoVO> list = temp.selectAll();
		System.out.println(list);

		// Insert
		// String url =
		// "http://nextinnovation.cloudapp.net/ITV/PlayVideo.jsp?filename=";
		// String path = "../mp4/";
		// String videoname = "Mamamoo - Um Oh Ah Yeh";
		// VideoVO tempinsert = new VideoVO();
		// tempinsert.setMemberId(2);
		// tempinsert.setVideoWebsite(url+videoname);
		// tempinsert.setVideoClassName("mv");
		// tempinsert.setVideoName(videoname);
		// tempinsert.setVideoPath(path+videoname+".mp4");
		// tempinsert.setVideoWatchTimes(1000);
		//
		// VideoDAO dao = new VideoDAOjdbc();
		// VideoVO insertlist = dao.insert(tempinsert);
		// System.out.println("Insert : "+ insertlist);

		// Update
		// String url =
		// "http://nextinnovation.cloudapp.net/ITV/PlayVideo.jsp?filename=";
		// String path = "../mp4/";
		// String videoname = "Mamamoo";
		//
		// VideoVO tempupdate = new VideoVO();
		// tempupdate.setMemberId(3);
		// tempupdate.setVideoWebsite(url+videoname);
		// tempupdate.setVideoClassName("mv");
		// tempupdate.setVideoName(videoname);
		// tempupdate.setVideoPath(path+videoname+".mp4");
		// tempupdate.setVideoUploadTime(new
		// java.sql.Date(System.currentTimeMillis()));
		// tempupdate.setVideoWatchTimes(2000);
		// tempupdate.setVideoDescription("");
		// tempupdate.setVideoId(14);
		//
		// VideoDAO dao = new VideoDAOjdbc();
		// VideoVO updatelist =
		// dao.update(tempupdate.getMemberId(),tempupdate.getVideoWebsite(),tempupdate.getVideoClassName(),
		// tempupdate.getVideoName(),tempupdate.getVideoPath(),tempupdate.getVideoUploadTime(),tempupdate.getVideoWatchTimes(),tempupdate.getVideoDescription(),tempupdate.getVideoId());
		// System.out.println("Update : "+ updatelist);

		// Delete
		// VideoDAO dao = new VideoDAOjdbc();
		// boolean d = dao.delete(13);
		// if(d==true){
		// System.out.println("Delete : Success!!!");
		// }else{
		// System.out.println("Delete : Fail!!!");
		// }
	}
}
