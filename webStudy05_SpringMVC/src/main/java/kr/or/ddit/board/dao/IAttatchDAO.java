package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
@Repository
public interface IAttatchDAO { // 첨부파일

	public int insertAttatchs(BoardVO board);
	public AttachVO selectAttatch(int att_no); //첨부파일 번호
	public int incrementDownCount(int att_no);
	public int deleteAttatchs(BoardVO board); // attatch의 정보를 가지고있는 vo의 컬럼을 삭제
	// 파일정보를 여러개 지우고 싶을때
}
