package kr.or.ddit.buyer.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImplTest {
	
	IBuyerDAO buyerDAO = new BuyerDAOImpl();
	BuyerVO buyer;
	PagingVO<BuyerVO> pagingVO;
	
	@Before
	public void setup(){
		pagingVO = new PagingVO<BuyerVO>();
		pagingVO.setCurrentPage(1);
		buyer = new BuyerVO();
		buyer.setBuyer_id("P10104");
		buyer.setBuyer_name("대덕컴퓨터");
		buyer.setBuyer_lgu("P101");
		buyer.setBuyer_bank("신한은행");
		buyer.setBuyer_bankno("123-1234-12345");
		buyer.setBuyer_bankname("정지오");
		buyer.setBuyer_zip("789-123");
		buyer.setBuyer_add1("대전시");
		buyer.setBuyer_add2("중구청역");
		buyer.setBuyer_comtel("042-1234-5464");
		buyer.setBuyer_fax("043-1234-1234");
		buyer.setBuyer_mail("abc@naver.com");
		buyer.setBuyer_charger("김태평");
		buyer.setBuyer_telext("0");
		
		
	}
			
	

	@Test
	public void testInsertBuyer() {
		int buy = buyerDAO.insertBuyer(buyer);
		assertNotEquals(0, buy);
	}

	@Test
	public void testSelectBuyer() {
		BuyerVO buyer = buyerDAO.selectBuyer("P10101");
		System.out.println(buyer.getProdList());
		assertNotNull(buyer);
	}

	@Test
	public void testSelectBuyerCount() {
		int count = buyerDAO.selectBuyerCount(pagingVO);
		assertNotEquals(0, count);
		
	}

	@Test
	public void testSelectBuyerList() {
		List<BuyerVO> list = buyerDAO.selectBuyerList(pagingVO);
		assertNotNull(list);
		assertNotEquals(0, list.size());
		
	}

	@Test
	public void testUpdateBuyer() {
		int count = buyerDAO.updateBuyer(buyer);
		assertNotEquals(0, count);
	}

}
