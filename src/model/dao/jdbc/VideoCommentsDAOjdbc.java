package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.dao.VideoCommentsDAO;
import model.vo.MemberVO;
import model.vo.VideoCommentsVO;
import util.ConvertType;
import util.GC;

public class VideoCommentsDAOjdbc implements VideoCommentsDAO {
//	 private static final String URL = GC.URL;
//	 private static final String USERNAME = GC.USERNAME;
//	 private static final String PASSWORD = GC.PASSWORD;
	private DataSource ds;

	public VideoCommentsDAOjdbc() {
		try {
			Context ctx = new InitialContext();
			this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_ALL = "SELECT vc.commentId, vc.memberId, vc.videoId, vc.commentContent, vc.commentTime, m.memberAccount, m.memberPhoto , m.memberName FROM videoComments vc Join member m ON vc.memberId = m.memberId ORDER BY commentTime DESC";

	@Override
	public List<VideoCommentsVO> selectAll() {
		List<VideoCommentsVO> vcvos = null;
		try (Connection conn = ds.getConnection();
//				 Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rs = pstmt.executeQuery();) {
			
			vcvos = new ArrayList<VideoCommentsVO>();
//			System.out.println(rs.next());
			while (rs.next()) {
				VideoCommentsVO vcvo = new VideoCommentsVO();
				vcvo.setCommentId(rs.getInt("commentId"));
				System.out.println(rs.getInt("commentId"));
				vcvo.setMemberId(rs.getInt("memberId"));
				vcvo.setVideoId(rs.getInt("videoId"));
				vcvo.setCommentContent(rs.getString("commentContent"));
				vcvo.setCommentTime(ConvertType.convertToLocalTime(rs.getTimestamp("commentTime")));
				MemberVO bean = new MemberVO();
				bean.setMemberAccount(rs.getString("memberAccount"));
				bean.setMemberName(rs.getString("memberName"));
				vcvo.setMember(bean);
				vcvos.add(vcvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vcvos;
	}
	
	private static final String SELECT_ALL_ASC = "SELECT vc.commentId, vc.memberId, vc.videoId, vc.commentContent, vc.commentTime, m.memberAccount FROM videoComments vc Join member m ON vc.memberId = m.memberId ORDER BY commentTime ASC";

	@Override
	public List<VideoCommentsVO> selectAllASC() {
		List<VideoCommentsVO> vcvos = null;
		try (Connection conn = ds.getConnection();
//				 Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ASC);
				ResultSet rs = pstmt.executeQuery();) {
			
			vcvos = new ArrayList<VideoCommentsVO>();
//			System.out.println(rs.next());
			while (rs.next()) {
				VideoCommentsVO vcvo = new VideoCommentsVO();
				vcvo.setCommentId(rs.getInt("commentId"));
				System.out.println(rs.getInt("commentId"));
				vcvo.setMemberId(rs.getInt("memberId"));
				vcvo.setVideoId(rs.getInt("videoId"));
				vcvo.setCommentContent(rs.getString("commentContent"));
				vcvo.setCommentTime(ConvertType.convertToLocalTime(rs.getTimestamp("commentTime")));
				MemberVO bean = new MemberVO();
				bean.setMemberAccount(rs.getString("memberAccount"));
				vcvo.setMember(bean);
				vcvos.add(vcvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vcvos;
	}

	private static final String SELECT_BY_VIDEOID = "SELECT vc.commentId, vc.memberId, vc.videoId, vc.commentContent, vc.commentTime, m.memberAccount, m.memberPhoto, m.memberName FROM videoComments vc Join member m ON vc.memberId = m.memberId where videoId = ?";

	@Override
	public List<VideoCommentsVO> selectByVideoId(int videoId) {
		VideoCommentsVO vcvo = new VideoCommentsVO();
		List<VideoCommentsVO> vcvos = new ArrayList<VideoCommentsVO>();
		try (Connection conn = ds.getConnection();
//				 Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_VIDEOID);) {
			pstmt.setInt(1, videoId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO bean = new MemberVO();
				vcvo = new VideoCommentsVO();
				vcvo.setCommentId(rs.getInt("commentId"));
				vcvo.setMemberId(rs.getInt("memberId"));
				vcvo.setVideoId(rs.getInt("videoId"));
				vcvo.setCommentContent(rs.getString("commentContent"));
				vcvo.setCommentTime(rs.getTimestamp("commentTime"));
				bean.setMemberAccount(rs.getString("memberAccount"));
				bean.setMemberPhoto(rs.getBytes("memberPhoto"));
				bean.setMemberName(rs.getString("memberName"));
				vcvo.setMember(bean);
				vcvos.add(vcvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vcvos;
	}

	private static final String INSERT = "INSERT INTO videoComments (memberId,videoId,commentContent) VALUES (?,?,?)";

	@Override
	public boolean insert(VideoCommentsVO videoComments) {
		boolean result = false;
		try (Connection conn = ds.getConnection();
				// Connection conn = DriverManager.getConnection(URL, USERNAME,
				// PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(INSERT);) {

			pstmt.setInt(1, videoComments.getMemberId());
			pstmt.setInt(2, videoComments.getVideoId());
			pstmt.setString(3, videoComments.getCommentContent());
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String UPDATE = "UPDATE videoComments SET commentContent=?,commentTime=? WHERE commentId=?";

	@Override
	public boolean update(VideoCommentsVO videoComments) {
		boolean result = false;
		try (Connection conn = ds.getConnection();
				// Connection conn = DriverManager.getConnection(URL, USERNAME,
				// PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(UPDATE);) {
			pstmt.setString(1, videoComments.getCommentContent());
			long comment = videoComments.getCommentTime().getTime();
			pstmt.setTimestamp(2, new java.sql.Timestamp(comment));
			pstmt.setInt(3, videoComments.getCommentId());
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "DELETE FROM videoComments WHERE commentId=?";

	@Override
	public boolean delete(int commentId) {
		boolean removeResult = false;
		try (Connection conn = ds.getConnection();
				// Connection conn = DriverManager.getConnection(URL, USERNAME,
				// PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
			pstmt.setInt(1, commentId);
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
	public static void main(String[] args) {
		VideoCommentsDAO dao = new VideoCommentsDAOjdbc();
		Collection<VideoCommentsVO> beans = dao.selectAll();
		for(VideoCommentsVO bean : beans){
			System.out.println(bean);
		}
		
//		VideoCommentsDAO vcdao = new VideoCommentsDAOjdbc();
//		VideoCommentsVO vcvo = new VideoCommentsVO();
//		vcvo.setCommentId(18);
		// vcvo.setMemberId(4);
		// vcvo.setVideoId(5);
//		vcvo.setCommentContent("我超越了");
//		vcvo.setCommentTime(new java.util.Date());
		// System.out.println(vcdao.insert(vcvo));
//		System.out.println(vcdao.update(vcvo));
		// System.out.println(vcdao.selectAll());
		// System.out.println(vcdao.delete(17));

		// insert時，DB抓的是GMT時間，update時則是傳本地端時間進DB

	}

}
