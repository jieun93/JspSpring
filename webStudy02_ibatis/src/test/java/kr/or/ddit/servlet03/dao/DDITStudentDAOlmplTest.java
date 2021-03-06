package kr.or.ddit.servlet03.dao;

import static org.junit.Assert.*;  

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.DDITStudentVO;

public class DDITStudentDAOlmplTest {
	
	IDDITStudentDAO dao = DDITStudentDAOlmpl.getInstance(); // 싱글톤 

//	@Test
	public void testInsertStudent() {
		DDITStudentVO vo = new DDITStudentVO();
		vo.setName("테스터");
		vo.setBirthday("2000-01-01");
		vo.setAge(23);
		vo.setGen("F");
		vo.setLic_codes(new String[] {"L001","L002"});
		String cnt = dao.insertStudent(vo);
		assertNotNull(cnt);
				
	}

	// dao 메소드 검증 
	@Test
	public void testSelectStudentList() {
		List<DDITStudentVO> stdList =  dao.selectStudentList(pagingVO);
		assertNotNull(stdList); // assert :예측, 돌아오는게 null이 아닌걸 확인하겠다. 
		assertEquals(false, stdList.isEmpty()); // 예측이 3 , 실제 값 --내가가진게 예측값하고 동일한지 확인
		
	}

//	@Test
	public void testSelectStudent() {
		DDITStudentVO vo =  dao.selectStudent("S001");// 존재함
		// null이 아니다를 테스트
		assertNotNull(vo);
		System.out.println(vo);
		assertEquals(2, vo.getLicense().size());
		System.out.println(vo.getLicense());
		
	}

//	@Test
	public void testUpdateStudent() {
		DDITStudentVO vo = new DDITStudentVO();
		vo.setCode("S004");
		vo.setName("테스터");
		vo.setBirthday("2000-01-01");
		vo.setAge(23);
		vo.setGen("F");
		vo.setLic_codes(new String[] {"L001","L002"});
		int cnt = dao.updateStudent(vo);
		assertNotEquals(0, cnt);
				
	}

//	@Test
	public void testDeletStudent() {
		int cnt = dao.deletStudent("S009");
		assertNotEquals(0, cnt);
	}

}
