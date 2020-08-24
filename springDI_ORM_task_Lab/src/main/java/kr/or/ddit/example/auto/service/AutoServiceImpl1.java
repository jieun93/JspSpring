package kr.or.ddit.example.auto.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import kr.or.ddit.example.auto.dao.IAutoDAO;

@Service // 어떤 어노테이션을 쓰나 빈으로 등록 하는건 똑같다. 
public class AutoServiceImpl1 implements IAutoService {
	
	private IAutoDAO dao;
	public AutoServiceImpl1(){}
//	@Resource( name = "autoDAO_oracle")
//	@Autowired // wired 의존관계 형성
//	@Inject
	
//	@Required
	public void setDao(IAutoDAO dao) {
		this.dao = dao;
	}
	AutoServiceImpl1(IAutoDAO dao) {
		 this.dao = dao;
	}
	
	
	@Override
	public String readInfo() {
		return dao.getRawData();
	}

}
