package kr.or.ddit.example.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.example.dao.ISampleDAO;

@Service
public class SampleServiceImpl implements ISampleService {

//	ISampleDAO dao = new SampleDAOImpl(); // 의존성 결합력이 크다.
	
	
	
//	
	private ISampleDAO dao;

//	
//	모든 니브니스 로직에 기록하자
//	 core conrn  : 비즈니스 로직 ->target(reaInformailn)
//	 cross currting :  시스템 로그
	
	
	
	// 1. 생성자 주입방식 (constructor injection) 주입하는객체가 필수객체이면 생성자 주입방식으로 **반드시 dao를 주입해야한다.
	@Inject
	public SampleServiceImpl(ISampleDAO dao) {
		super();
		this.dao = dao;
		System.out.println(getClass().getSimpleName()+"객체 생성, 생성자 주입");
	}
	
	// 기본생성자 
	public SampleServiceImpl() {
		super();
		System.out.println(getClass().getSimpleName()+"객체 생성");
	}
	
	
	
	//2. setter injection --옵셔녈 전략에 사용 
	public void setDao(ISampleDAO dao) {
		this.dao = dao;
		System.out.println(getClass().getSimpleName()+"에서"+dao.getClass().getSimpleName()+"을 주입받음.setter injection");
	}
	
	
	

	@Override
	public String readInformation() {
		// 로그를 남기기위한 코드를 ㅏ짜야한다. 
//		Logger.info();
		String info = dao.selectRawData()+"를 가공함.";
		System.err.println("로직실행중 ");
		return info;
	}

}
