package kr.or.ddit.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import kr.or.ddit.example.vo.VariousDIVO;

public class VariousDIContinerTest {
	public static void main(String[] args) {
//		 ApplicationContext container =
//				  new GenericApplicationContext(new ClassPathResource("kr/or/ddit/example/conf/VariousDI-container.xml"));
		// 컨테이너를 먼저 만들어주고 해당 xml파일의 경로를 잡아준더.
		ConfigurableApplicationContext  container = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/example/conf/VariousDI-container.xml");
		
		// 검색할 거를 불러온다. 
		VariousDIVO vo2 = container.getBean("vo2",VariousDIVO.class);
		VariousDIVO vo4 = container.getBean("vo2",VariousDIVO.class);
		VariousDIVO vo1 = container.getBean("vo1",VariousDIVO.class);
		VariousDIVO vo3 = container.getBean("vo1",VariousDIVO.class);
		System.out.println(vo1 == vo3);
		System.out.println(vo2 == vo4);
		
				
		 
	
	
	}
}
