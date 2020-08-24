package kr.or.ddit.servlet03.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author PC-20
 * 학생관리를 위한 Business Logic Layer
 */
public interface IDDITStudentService {
	// 학생관리를 하는거  기본 로직이 필요함
	
	public ServiceResult createStudent(DDITStudentVO vo);
	public int readeStudentCount(PagingVO pagingVO);
	public List<DDITStudentVO> readStudentList(PagingVO pagingVO);
	public DDITStudentVO readStudent(String code);
	public ServiceResult modifyStudent(DDITStudentVO vo);
	public ServiceResult removeStudent(String code);
	
}
