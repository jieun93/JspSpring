package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IReplyDAO;
import kr.or.ddit.board.enums.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;
@Service
public class ReplyServiceImpl implements IReplyService {
	@Inject
	IReplyDAO dao;
	
	
	@Override // 새로운 댓글을 다는거 
	public ServiceResult createReply(ReplyVO reply) {
		int ireply = dao.insertReply(reply);
		ServiceResult result = ServiceResult.FAIL;
		if(ireply > 0) result = ServiceResult.OK;
		return result;
				
	}

	@Override
	public int readReplyCount(PagingVO paging) {
		return dao.selectReplyCount(paging);
		
	}

	@Override
	public List<ReplyVO> readReplyList(PagingVO paging) {
		return dao.selectReplyList(paging);
	}

	@Override
	public int modifyReply(ReplyVO reply) {
		return dao.updateReply(reply);
		
		
	}

	@Override
	public int removeReply(int rep_no) {
		
		return dao.deleteReply(rep_no);
	}

}
