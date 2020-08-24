package kr.or.ddit.db;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class CustomSqlSessionFactoryBuilderTest {

	@Test
	public void testGetSqlSessionFactory() {
		SqlSessionFactory sqlSessionFactory =CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
		assertNotNull(sqlSessionFactory);
		PagingVO<MemberVO> pagingVO = new PagingVO<MemberVO>();
		pagingVO.setCurrentPage(1);
		try(
				SqlSession sqlSession =  sqlSessionFactory.openSession();
			){
			
//			int count = (Integer)sqlSession.selectOne("kr.or.ddit.member.dao.IMemberDAO.selelctMemberCount",pagingVO);
		
			//1. 프록시 만들기 --> 하드코딩을 하지않고 맵퍼를 돌려서 카운트 하는거 - dao의 구현체를 만들필요가 없다. **중요**  
			IMemberDAO memberDAOProxy = sqlSession.getMapper(IMemberDAO.class);
			int count =  memberDAOProxy.selelctMemberCount(pagingVO);
			
			assertNotEquals(0, count);
		}
	}

}
