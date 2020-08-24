package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements IMemberDAO{

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true); // 트랜잭션 시작 , 실행하자마자 커핏
			){
				IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
				return  mapper.insertMember(member); 
				
		}
	}

	@Override
	public MemberVO selectMember(String mem_id) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class); //프록시 만들어짐
				return  mapper.selectMember(mem_id);
		}
	}

	@Override
	public int selelctMemberCount(PagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class); //프록시 만들어짐
				return  mapper.selelctMemberCount(pagingVO);
		}
	}

	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class); //프록시 만들어짐
				return  mapper.selectMemberList(pagingVO);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
				
			){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.updateMember(member);
		}
		
	}

	@Override
	public int deleteMember(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
			){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.deleteMember(mem_id);
		}
	}
	

}
