package kr.or.ddit.servlet03.service;

import java.util.List;

import com.sun.xml.internal.bind.v2.util.FatalAdapter;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.servlet03.dao.DDITStudentDAOlmpl;
import kr.or.ddit.servlet03.dao.IDDITStudentDAO;
import kr.or.ddit.servlet03.dao.IStdLicenseDAO;
import kr.or.ddit.vo.DDITStudentVO;

public class DDITStudentServiceImpl implements IDDITStudentService{
	// 싱글톤 만들기
	private DDITStudentServiceImpl () {};
	private static IDDITStudentService self;
	public static IDDITStudentService getInstance() {
		if(self == null) self = new DDITStudentServiceImpl();
		return self;
	}
	// 의존 관계 형성 (domain layer를 제외한 모든 레이어는 싱글턴)
	IDDITStudentDAO dao = DDITStudentDAOlmpl.getInstance();
	IStdLicenseDAO licDAO = 
	
	// 학생 등록하는곳 
	@Override
	public ServiceResult createStudent(DDITStudentVO vo) {
		// 트랜잭션 관리 , 특성 : **원자성 ** (더 이상 쪼갤 수 없는 작업 단위)
		// 트랜잭션 마무리 commit , rollback 
		
		int cnt = dao.insertStudent(vo);
		                                  
		ServiceResult result = null;
		
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}
	
	// 학생 리스트 불러오는거 
	@Override
	public List<DDITStudentVO> readStudentList() {
		
		return dao.selectStudentList();
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
		int cnt = dao.updateStudent(vo); // 이것만 사용하면 테이블 하나만 사용된다. 
		ServiceResult result = null;
		
		if(cnt>0) {
			//기존의 자격증을 지우기
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
	public ServiceResult removeStudent(String code) { // 전체에 대해 트랜잭션을 관리해야 한다.-->중복코드 발생 
		// 자격증을 지워야 함
		int cnt	=licDAO.deleteLicenses(code);
		cnt +=  dao.deletStudent(code);
		ServiceResult result = null;
		
		if(cnt>0) {
		 result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		
		
		return result;
	}

}
