package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface IBuyerDAO {
	
	public int  insertBuyer(BuyerVO buyer);
	
	public BuyerVO selectBuyer(String buyer_id);
	
	public int selectBuyerCount(PagingVO<BuyerVO> pagingVO);
	
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
	
	public int updateBuyer(BuyerVO buyer);
	

	
	
}
