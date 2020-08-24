package kr.or.ddit.servlet03.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

public class DDITStudentDAOlmpl implements IDDITStudentDAO {
	// dditStudent.xml 에 쿼리랑 연결되는거 
	// 싱글톤 만들기 
	private static IDDITStudentDAO dao;
	public static IDDITStudentDAO getInstance() {
		if(dao== null) dao = new DDITStudentDAOlmpl();
		return dao;
	}
	
	private SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();
	
	// 학생을 추가 하는 메소드 
	@Override
	public String insertStudent(DDITStudentVO vo) {
	
		try {
			// xml의 insertStudent 쿼리로 연결하는거 
		String  code = (String)sqlMapClient.insert("Student.insertStudent",vo); //insert 안에 select키가 있으면 insert를 해야 한다. 
			return code;
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
		}
		
	}
	
	
	@Override
	public int selectStudentCount(PagingVO pagingVO) {
		try {
			return  (Integer)sqlMapClient.queryForObject("Student.selectStudentCount",pagingVO);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 전체 학생을 조회 하는 메소드 
	@Override
	public List<DDITStudentVO> selectStudentList(PagingVO pagingVO) {
		try {
			return  sqlMapClient.queryForList("Student.selectStudentList",pagingVO);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// 학생 한명을 조회 
	@Override
	public DDITStudentVO selectStudent(String code) {
		 try {
			return  (DDITStudentVO) sqlMapClient.queryForObject("Student.selectStudent", code);
		 }catch(SQLException e) {
			 throw new RuntimeException(e);
		 }
	}
	
	// 학생 수정하는거 
	@Override
	public int updateStudent(DDITStudentVO vo) {
		try{
			return sqlMapClient.update("Student.updateStudent",vo);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	// 학생 삭제 하는거 
	@Override
	public int deleteStudent(String code) {
		try{
			return sqlMapClient.delete("Student.deleteStudent",code);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	

}
