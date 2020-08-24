package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;
@Repository
public interface IReplyDAO {
	public int insertReply(ReplyVO reply);
	public int selectReplyCount(PagingVO<BoardVO> pagingVO); 
	public List<ReplyVO> selectReplyList(PagingVO<BoardVO> pagingVO);
	public int updateReply(ReplyVO reply); 
	public int deleteReply(int rep_no);
}
