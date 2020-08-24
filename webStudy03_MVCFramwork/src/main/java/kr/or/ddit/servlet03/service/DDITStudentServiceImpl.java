package kr.or.ddit.servlet03.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.servlet03.dao.DDITStudentDAOlmpl;
import kr.or.ddit.servlet03.dao.IDDITStudentDAO;
import kr.or.ddit.servlet03.dao.IStdLicenseDAO;
import kr.or.ddit.servlet03.dao.StdLicenseDAOImpl;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

public class DDITStudentServiceImpl implements IDDITStudentService{
	// 싱글톤 만들기
	private static IDDITStudentService self;
	public static IDDITStudentService getInstance() {
		if(self == null) self = new DDITStudentServiceImpl();
		return self;
	}
	// 의존 관계 형성 (domain layer를 제외한 모든 레이어는 싱글턴)
	IDDITStudentDAO dao = DDITStudentDAOlmpl.getInstance();
	IStdLicenseDAO licDAO = StdLicenseDAOImpl.getInstance();
	
	// ServiceResult --> enum파일 
	
	// 학생 등록하는곳 
	@Override
	public ServiceResult createStudent(DDITStudentVO vo) {
		// 트랜잭션 관리 , 특성 : **원자성 ** (더 이상 쪼갤 수 없는 작업 단위)
		// 트랜잭션 마무리 commit , rollback 
		
		String code = dao.insertStudent(vo);
		                                  
		ServiceResult result = null;
		
		if(code==null) { // 코드가 없으면  fail로
			result = ServiceResult.FAIL;
		}else {	// 코드가 있으면 ok
			result = ServiceResult.OK;
		}
		return result;
	}
	
	@Override
	public int readeStudentCount(PagingVO pagingVO) {
		return dao.selectStudentCount(pagingVO);
	}
	
	// 학생 리스트 불러오는거 
	@Override
	public List<DDITStudentVO> readStudentList(PagingVO pagingVO) {
		return dao.selectStudentList(pagingVO);
	}
	
	// 불러온 학생이 없을 경우
	@Override
	public DDITStudentVO readStudent(String code) {
		DDITStudentVO vo = dao.selectStudent(code);
		if(vo == null) {
			throw new DataNotFoundException(code+"에 해당하는 학생은 없음.");
		}
		return vo;
	}
	
	//  수정 
	@Override
	public ServiceResult modifyStudent(DDITStudentVO vo) {
		int cnt = dao.updateStudent(vo);
		
		ServiceResult result = null;
		
		if(cnt>0) {
			licDAO.deleteLicenses(vo.getCode());
			if(vo.getLic_codes()!=null) {
				licDAO.insertLicenses(vo);
			}
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}
	
	// 삭제 
	@Override
	public ServiceResult removeStudent(String code) {
		int cnt =  licDAO.deleteLicenses(code);
		cnt += dao.deleteStudent(code);
		ServiceResult result = null;
		
		if(cnt>0) {
		 result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		
		
		return result;
	}



}
