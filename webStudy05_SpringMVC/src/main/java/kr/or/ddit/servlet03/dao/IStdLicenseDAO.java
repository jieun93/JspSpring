package kr.or.ddit.servlet03.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.DDITStudentVO;
@Repository
public interface IStdLicenseDAO{
	public int insertLicenses(DDITStudentVO vo);
	public int deleteLicenses(String code);
	
	
	
}
