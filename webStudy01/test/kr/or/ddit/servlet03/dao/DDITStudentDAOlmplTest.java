package kr.or.ddit.servlet03.dao;

import static org.junit.Assert.*;  

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.DDITStudentVO;

public class DDITStudentDAOlmplTest {
	
	IDDITStudentDAO dao = DDITStudentDAOlmpl.getInstance(); // 싱글톤 


	public void testInsertStudent() {
		fail("Not yet implemented");
	}

	// dao 메소드 검증 
	@Test
	public void testSelectStudentList() {
		List<DDITStudentVO> stdList =  dao.selectStudentList();
		assertNotNull(stdList); // assert :예측, 돌아오는게 null이 아닌걸 확인하겠다. 
		assertEquals(false, stdList.isEmpty()); // 예측이 3 , 실제 값 --내가가진게 예측값하고 동일한지 확인
		
	}

	@Test
	public void testSelectStudent() {
		DDITStudentVO vo =  dao.selectStudent("S001");// 존재함
		// null이 아니다를 테스트
		assertNotNull(vo);
		assertEquals(2, vo.getLicense().size());
		System.out.println(vo.getLicense());
		
	}

	
	public void testUpdateStudent() {
		fail("Not yet implemented");
	}

	
	public void testDeletStudent() {
		fail("Not yet implemented");
	}

}
