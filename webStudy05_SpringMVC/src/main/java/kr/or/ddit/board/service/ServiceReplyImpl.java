package kr.or.ddit.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;
@Service
public class ServiceReplyImpl implements IServiceReply {

	@Override
	public ServiceResult createReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int readReplyCount(PagingVO<BoardVO> paging) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReplyVO> readReplyList(PagingVO<BoardVO> paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeReply(int rep_no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
