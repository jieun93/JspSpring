package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {

	IProdDAO prodDAO = new ProdDAOImpl();
	ProdVO prod;
	PagingVO<ProdVO> pagingVO;
	
	@Before
	public void setUp() {
		pagingVO = new PagingVO<ProdVO>();
		pagingVO.setCurrentPage(1);
		prod = new ProdVO();
		prod.setProd_id("P101000007");
		prod.setProd_name("모니터 삼성전자 20인치 칼라");
		prod.setProd_lgu("P101");
		prod.setProd_buyer("P10102");
		prod.setProd_cost(350000);
		prod.setProd_price(350000);
		prod.setProd_sale(350000);
		prod.setProd_outline("평면모니터의기적");
		prod.setProd_detail("우리 기술의 개가");
		prod.setProd_img("P101000007.gif");
		prod.setProd_totalstock(3);
		prod.setProd_properstock(4);
		prod.setProd_size("20인치");
		prod.setProd_color("블랙");
		prod.setProd_delivery("택배");
		prod.setProd_unit("EA");
		prod.setProd_qtyin(0);
		prod.setProd_qtysale(0);
		
		
	}
	
	
	@Test
	public void testInsertProd() {
		int iprod =prodDAO.insertProd(prod);
		assertNotEquals(0, iprod);
	}

	@Test
	public void testSelectProd() {
		ProdVO prod = prodDAO.selectProd("P101000007");
		assertNotNull(prod);
	}

	@Test
	public void testSelectProdCount() {
		int count = prodDAO.selectProdCount(pagingVO);
		assertNotEquals(0, count);
	}

	@Test
	public void testSelectProdList() {
		List<ProdVO> list = prodDAO.selectProdList(pagingVO);
		assertNotNull(list);
		assertNotEquals(0, list.size());
	}

	@Test
	public void testUpdateProd() {
		int count = prodDAO.updateProd(prod);
		assertNotEquals(0, count);
	}

}
