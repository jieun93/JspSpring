package kr.or.ddit.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.example.vo.CollectionDIVO;

public class CollectionDIContainerTest {
	public static void main(String[] args) {
	  ConfigurableApplicationContext container = 
			  new GenericXmlApplicationContext("classpath:kr/or/ddit/example/conf/CollectionDI-container.xml");
	  container.registerShutdownHook();
	  
	  CollectionDIVO  vo1 = container.getBean("vo1",CollectionDIVO.class);
	  
	  System.out.println(vo1.getList());
	  System.out.println(vo1.getArray());
	  System.out.println(vo1.getMap());
	  System.out.println(vo1.getProps());
	  System.out.println(vo1.getSet());
	  String[] names = container.getBeanDefinitionNames();
	  names = container.getBeanNamesForType(String[].class);  //컨테이너안에 빈의 이름이 등록되어 잇으면 가져오는거
	  
	  for( String beanName : names) {
		  System.out.println(beanName);
	  }
	  
	}
}
