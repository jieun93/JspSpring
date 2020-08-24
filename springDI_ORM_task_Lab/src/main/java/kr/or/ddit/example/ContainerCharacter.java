package kr.or.ddit.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.example.dao.ISampleDAO;
import kr.or.ddit.example.dao.SampleDAOImpl;

public class ContainerCharacter {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = 
				new ClassPathXmlApplicationContext("kr/or/ddit/example/conf/DI-containerDesc.xml");
	
//		container.registerShutdownHook(); // 어플리케이션이 종료전  사용했던 메모리가 정리되어 소멸 됨  비정상적인 종료가 될때 메모리를 정리할때 사용된다.  
		ISampleDAO dao1 = container.getBean(SampleDAOImpl.class);
		ISampleDAO dao2 = container.getBean(SampleDAOImpl.class);
		System.out.println(dao1 == dao2);
//		container.close(); // 메모리를 정리   정상종료에서 사용
		System.exit(1);
		
	}

}
