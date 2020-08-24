package kr.or.ddit.servlet03.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface IGradeLicenseDAO {
	//전체를 다 조회 함 
	// db 를 기반으로 만들어 줘야 한다. 
	public List<Map<String, Object>> selectGradeList();
	public List<Map<String, Object>> selectLicenseList();
}
