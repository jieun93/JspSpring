package kr.or.ddit.servlet03.dao;

import java.util.List;

import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

public interface IDDITStudentDAO {

	public	String insertStudent(DDITStudentVO vo); // 학생 한명을 등록
 
	public int selectStudentCount(PagingVO pagingVO);
	
	public List<DDITStudentVO> selectStudentList(PagingVO pagingVO); // 학생을 조회
	
	public DDITStudentVO selectStudent(String code); // 학생 한명을 조회
	
	public int updateStudent(DDITStudentVO vo);  // 수정

	public int deletStudent(String code); 	// 삭제 

}