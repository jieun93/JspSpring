package kr.or.ddit.designpattern.templetmethod.example2;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOExecuteQueryTest {
	
	ExecuteQueryTemplate<?> template = new GeneralDAOExecuteQuery();

	@Test
	public void testQueryForList() {
		String query = "SELECT * FROM MEMBER  WHERE MEM_ADD1 LIKE '%'||?||'%' ";
		List<?> list =  template.queryForList(query, new String[] {"대전"});
		System.out.println(list);
		
	}

	@Test
	public void testQueryForObject() {
		String query = "SELECT * FROM MEMBER  WHERE MEM_ID =? ";
		Object obj =  template.queryForObject(query, new String[] {"a001"});
		System.out.println(obj);
	}

}
