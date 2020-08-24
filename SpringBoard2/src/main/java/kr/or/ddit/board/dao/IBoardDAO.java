package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface IBoardDAO {
	public int insertBoard(BoardVO board) ;
	public int selectBoardCount(PagingVO<BoardVO>  pagingVO);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO>  pagingVO);
	public BoardVO selectBoard(int bo_no);
	public void incrementHit(int bo_no); // 조횟수 올리는거 
	public int updateBoard(BoardVO board);
	public int deleteBoard(BoardVO board);
	
}
