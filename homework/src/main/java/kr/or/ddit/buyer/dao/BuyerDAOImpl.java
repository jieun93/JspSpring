package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public class BuyerDAOImpl implements IBuyerDAO {
	// 데이터 처리를 위해 mybatis에 sql을 실행할  객체의 메소드 호출 
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertBuyer(BuyerVO buyer) {
		try(
			// sqlSessionFactory 는 sqlSession을 생성하여 반환한다. 
			 // sqlsession은 직접생성할 수 없고, sqlsessionfactory
		SqlSession sqlSession = sqlSessionFactory.openSession();
			
		){
							//sqlsession객체에게 sql 실행을 요구
							//sqlsession객체는 sql에 저장된 xml에서 sql을 찾는다.
							//sqlsession은 jdbc드라이버를 통해 데이터베이스의 질의를 실행
		IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
		// sqlsession은 데이터베이스로 부터 가져온 질의 결과를 객체로 생성하여 반환 
		return mapper.insertBuyer(buyer);
		}
	}

	@Override
	public BuyerVO selectBuyer(String buyer_id) {
		try( 
				SqlSession sqlSession = sqlSessionFactory.openSession();
				
		){
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			return mapper.selectBuyer(buyer_id);
		
		}
	}

	@Override
	public int selectBuyerCount(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
			){
			
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerCount(pagingVO);
			
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingVO pagingVO) {
		try( 
			
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			){
			
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerList(pagingVO);
			
			
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
			){
			
			IBuyerDAO mapper = sqlSession.getMapper(IBuyerDAO.class);
			return mapper.updateBuyer(buyer);
		}
	}

}
