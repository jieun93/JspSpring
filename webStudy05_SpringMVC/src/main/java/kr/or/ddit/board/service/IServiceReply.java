package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface IServiceReply {
	public ServiceResult createReply(ReplyVO reply);
	public int readReplyCount(PagingVO<BoardVO> paging);
	public List<ReplyVO> readReplyList(PagingVO<BoardVO> paging);
	public int modifyReply(ReplyVO reply);
	public int removeReply(int rep_no);
}
