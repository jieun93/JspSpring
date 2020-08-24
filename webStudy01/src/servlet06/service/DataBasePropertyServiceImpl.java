package servlet06.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;
import servlet06.dao.DataBasePropertyDAOImpl;
import servlet06.dao.iDataBasePropertyDAO;
import servlet06.service.iDataBasePropertyService;

public class DataBasePropertyServiceImpl implements iDataBasePropertyService{

	iDataBasePropertyDAO dao = new DataBasePropertyDAOImpl();
	

	@Override
	public List<DataBasePropertyVO> readDataBaseProperties(Map<String, Object> paramMap) {
		List<DataBasePropertyVO> propList = dao.selectDataBaseProperties(paramMap);
		Date today = new Date();
		for(DataBasePropertyVO tmp : propList) {
			tmp.setProperty_value(tmp.getProperty_value()+"_"+today);
		}
		return propList;
	}

}
