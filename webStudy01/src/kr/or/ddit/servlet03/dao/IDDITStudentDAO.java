package kr.or.ddit.servlet03.dao;

import java.util.List;

import kr.or.ddit.vo.DDITStudentVO;

public interface IDDITStudentDAO {

	public	int insertStudent(DDITStudentVO vo); // 학생 한명을 등록
 
	public List<DDITStudentVO> selectStudentList(); // 학생을 조회
	
	public DDITStudentVO selectStudent(String code); // 학생 한명을 조회
	
	public int updateStudent(DDITStudentVO vo); 

	public int deletStudent(String code); 

}