package kr.or.ddit.buyer.service;

import java.util.List;


import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerService {
	
	public ServiceResult createBuyer(BuyerVO buyer);
	
	public BuyerVO readBuyer(String buyer_id);
	
	public int readBuyerCount(PagingVO<BuyerVO> pagingVO);
	
	public List<BuyerVO> readBuyerList(PagingVO pagingVO);
	
	public ServiceResult modifyBuyer(BuyerVO buyer);
	
}
