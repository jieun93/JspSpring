package kr.or.ddit.Albamon.service;

import java.util.List;

import kr.or.ddit.Albamon.enums.ServiceResult;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.PagingVO;

public interface IAlbaService {

	public ServiceResult createAlba(AlbaVO alba); 
	public int readAlbaCount(PagingVO<AlbaVO> pagingVO);
	public List<AlbaVO> readAlbaList(PagingVO<AlbaVO> pagingVO);
	public AlbaVO readAlba(int bo_no);
	public ServiceResult modifyAlba(AlbaVO alba);
	public ServiceResult removeAlba(AlbaVO alba); 
	public AttatchVO downloadAttatch(int att_no);

}
