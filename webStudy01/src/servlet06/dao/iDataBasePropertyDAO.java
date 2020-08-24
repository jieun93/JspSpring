package servlet06.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface iDataBasePropertyDAO {
	// 프롭리스, 헤더정보를 받아와야 한다.
	public List<DataBasePropertyVO> selectDataBaseProperties(Map<String, Object> paramMap);
	
	
}
