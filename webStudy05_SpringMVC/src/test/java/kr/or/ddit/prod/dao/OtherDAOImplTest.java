package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.WebAppTestContext;
import kr.or.ddit.vo.BuyerVO;

@RunWith(SpringRunner.class)
@WebAppTestContext
public class OtherDAOImplTest {
	@Inject
	IOthersDAO otherDAO;
	
	
	@Test
	public void testSelectLprodList() {
		List<Map<String, Object>> lprodList = otherDAO.selectLprodList();
		assertNotNull(lprodList);
		assertNotEquals(0, lprodList.size());
	}

	@Test
	public void testSelectBuyerList() {
		String lprd_gu = null;
		List<BuyerVO> buyerList = otherDAO.selectBuyerList(lprd_gu);
		assertNotNull(buyerList);
		assertNotEquals(0, buyerList.size());
	}

}
