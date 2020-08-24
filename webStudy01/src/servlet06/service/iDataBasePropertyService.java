package servlet06.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface iDataBasePropertyService {
	
	public List<DataBasePropertyVO> readDataBaseProperties(Map<String, Object> paramMap);
}
