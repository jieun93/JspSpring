package kr.or.ddit.example.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.example.service.ISampleService;

public class SampleController {
	// 필수전략의 유무에 따라서   의존을 만들어주는 방법이 달라진다. 
	private ISampleService service;
	
	// 1. 생성자 주입방식 (constructor injection) 
	public SampleController(ISampleService service) {
		super();
		this.service = service;
		System.out.println(getClass().getSimpleName()+"객체 생성, 생성자 주입");
	}
	
	public String commandProcess() {
		return service.readInformation();
	}
	
	
	public static void main(String[] args) {
		ApplicationContext container = new ClassPathXmlApplicationContext("kr/or/ddit/example/conf/Sample-DI.xml");
		SampleController controller = container.getBean(SampleController.class);
		String infomation = controller.commandProcess();
		System.out.println(infomation);
		
	}
}
