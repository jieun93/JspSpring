package kr.or.ddit.servlet03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

public class DDITStudentDAOlmpl implements IDDITStudentDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public String insertStudent(DDITStudentVO vo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
			){
			
			IDDITStudentDAO mapper = sqlSession.getMapper(IDDITStudentDAO.class);
			return mapper.insertStudent(vo);
		}
	}

	@Override
	public int selectStudentCount(PagingVO pagingVO) {
			try(
					SqlSession sqlSession = sqlSessionFactory.openSession();
				){
				 IDDITStudentDAO mapper = sqlSession.getMapper(IDDITStudentDAO.class);
				 return mapper.selectStudentCount(pagingVO);
			}
	}

	@Override
	public List<DDITStudentVO> selectStudentList(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			){
			IDDITStudentDAO mapper = sqlSession.getMapper(IDDITStudentDAO.class);
			return mapper.selectStudentList(pagingVO);
			
		}
		
	}

	@Override
	public DDITStudentVO selectStudent(String code) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
				
			){
			IDDITStudentDAO mapper = sqlSession.getMapper(IDDITStudentDAO.class);
			return mapper.selectStudent(code);
		}
	}

	@Override
	public int updateStudent(DDITStudentVO vo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
				
			){
			IDDITStudentDAO mapper = sqlSession.getMapper(IDDITStudentDAO.class);
			return mapper.updateStudent(vo);
		}
	}

	@Override
	public int deleteStudent(String code) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
			){
			IDDITStudentDAO mapper = sqlSession.getMapper(IDDITStudentDAO.class);
			return mapper.deleteStudent(code);
			
			
		}
	}

}
