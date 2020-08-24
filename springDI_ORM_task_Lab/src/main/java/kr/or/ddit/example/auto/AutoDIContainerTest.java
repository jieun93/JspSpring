package kr.or.ddit.example.auto;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.example.auto.dao.AutoDAO_oracle;
import kr.or.ddit.example.auto.service.AutoServiceImpl1;
import kr.or.ddit.example.auto.service.IAutoService;

public class AutoDIContainerTest {
	public static void main(String[] args) {
		ConfigurableApplicationContext container =
				new GenericXmlApplicationContext("kr/or/ddit/example/conf/autoDI-container.xml");
	
//	container.registerShutdownHook();\
	IAutoService service = container.getBean("autoServiceImpl1",IAutoService.class);
//	 AutoDAO_oracle dao = container.getBean("AutoDAO_oracle",AutoDAO_oracle.class);
	 System.out.println(service);
	 
	
	
	}
	
	
	
}
