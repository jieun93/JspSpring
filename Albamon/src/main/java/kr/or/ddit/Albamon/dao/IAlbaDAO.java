package kr.or.ddit.Albamon.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface IAlbaDAO {

	public int insertAlba(AlbaVO alba) ;
	public int selectAlbaCount(PagingVO<AlbaVO>  pagingVO);
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO>  pagingVO);
	public int updateAlba(AlbaVO alba);
	public int deleteAlba(AlbaVO alba);
}
