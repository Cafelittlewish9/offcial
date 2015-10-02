package model.dao;

import java.util.List;

import model.vo.VideoCommentsVO;

public interface VideoCommentsDAO {

	public List<VideoCommentsVO> selectAll();
	
	public List<VideoCommentsVO> selectByVideoId(int videoId);

	public boolean insert(VideoCommentsVO videoComments);

	public boolean update(VideoCommentsVO videoComments);

	public boolean delete(int commentId);

	List<VideoCommentsVO> selectAllASC();

}