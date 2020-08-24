package kr.or.ddit.buyer.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class BuyerServiceImpl implements IBuyerService {
	@Inject
	IBuyerDAO dao;
	
//	private static IBuyerService service;
//	public static IBuyerService getInstance() {
//		if(service == null) service = new BuyerServiceImpl();
//		return service;
//	}
	
	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		
//		ServiceResult result = null;
//		try {
//			readBuyer(buyer.getBuyer_id()); //member.getMem_id()에 해당하는 id가 있는지 없는지 
//			result =ServiceResult.PKDUPLICATED;
//		} catch (DataNotFoundException e) {
//		int cnt = dao.insertBuyer(buyer); // 예외가 발생해야지 신규로 등록 할 수 있다.   insert  하면  row count
//		
//		if(cnt>0) {
//			result = ServiceResult.OK;
//		}else {
//			result = ServiceResult.FAIL;
//		}
//		}
//		return result;
		int buyer_id = dao.insertBuyer(buyer);
		ServiceResult result = ServiceResult.FAIL;
		if(buyer_id > 0) result = ServiceResult.OK;
		return result;
		
		
	}
	@Override
	public BuyerVO readBuyer(String buyer_id) {
		return dao.selectBuyer(buyer_id);
	}
	@Override
	public int readBuyerCount(PagingVO pagingVO) {
		return dao.selectBuyerCount(pagingVO);
	}
	@Override
	public List<BuyerVO> readBuyerList(PagingVO pagingVO) {
		return dao.selectBuyerList(pagingVO);
	}
	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		ServiceResult result = null;
		
		BuyerVO savedBuyer = readBuyer(buyer.getBuyer_id());
		if(savedBuyer.getBuyer_id().equals(buyer.getBuyer_id())) {
			int cnt = dao.updateBuyer(buyer);
			if(cnt>0) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAIL;
				}
		}else {
			result = ServiceResult.INVALIDINPUT;
		}
		return result;
	}
	

	

}
