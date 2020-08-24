package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface IBoardDAO {

	public int insertBoard(BoardVO board);
	public int selectBoardCount(PagingVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	public BoardVO selectBoard(int bo_no); // 하나만 검색 해서 vo로 반환
	public void incrementHit(int bo_no);
	public int updateBoard(BoardVO board);
	public int deleteBoard(BoardVO board); // 게시글 삭제
	
}
