package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.WebAppTestContext;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@RunWith(SpringRunner.class)
@WebAppTestContext
public class MemberDAOImplTest {
	
	@Inject
	IMemberDAO memberDAO;
	
	MemberVO member;
	PagingVO<MemberVO> pagingVO;
	@Before
	public void setup() {
		pagingVO = new PagingVO<MemberVO>();
		pagingVO.setCurrentPage(1);
		member = new MemberVO();
//		member.setMem_id("a009");
		member.setMem_name("신규");
		member.setMem_pass("1111");
		member.setMem_regno1("930329");
		member.setMem_regno2("1234567");
		member.setMem_bir("1993-03-29");
		member.setMem_zip("986-36");
		member.setMem_add1("충북");
		member.setMem_add2("청주시");
		member.setMem_hometel("043-251-1324");
		member.setMem_comtel("02-1234-1234");
		member.setMem_hp("010-1234-1234");
		member.setMem_mail("abc@naver.com");
		member.setMem_job("백수");
		member.setMem_like("등산");
		member.setMem_memorial("입학");
		member.setMem_memorialday("2019-12-25");
	
		
	}
	// 테스트에는 순서가 없다. 순서의 의미가 없다 .
	// 각 케이스가 독립적으로 활동한다. 
	
	@Test
	public void testInsertMember() {
		int mem = memberDAO.insertMember(member);
		assertNotEquals(0, mem);
	}

	@Test
	public void testSelectMember() {
		MemberVO member =  memberDAO.selectMember("c001"); //c001이 널이면 안된다.
		System.out.println(member.getProdList());
		assertNotNull(member);
		
	}

	@Test
	public void testSelelctMemberCount() {
		int count = memberDAO.selelctMemberCount(pagingVO);
		assertNotEquals(0, count);
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> list = memberDAO.selectMemberList(pagingVO);
		assertNotNull(list);
		assertNotEquals(0, list.size());
	}

	@Test
	public void testUpdateMember() {
		int count = memberDAO.updateMember(member);
		assertNotEquals(0, count);
	}

	@Test
	public void testDeleteMember() {
		int count = memberDAO.deleteMember("r001");
		assertNotEquals(1, count);
	}

}
