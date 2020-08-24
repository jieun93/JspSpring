package kr.or.ddit.example;


import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.example.vo.DbInfoVO;

public class PropertiesInjectionTest {
	public static void main(String[] args) {
		Properties systemProps =  System.getProperties();
		for(Entry<Object, Object> entry : systemProps.entrySet()) {
			System.out.printf("%s : %s \n ", entry.getKey(), entry.getValue() );
		}
		
		ConfigurableApplicationContext container =
				new GenericXmlApplicationContext("kr/or/ddit/example/conf/Porperties-injection.xml");
		// 어플리케이션이 종료하기 전에 메모리를 정리하기 위해서 셧다운한다. 
		container.registerShutdownHook();
		DbInfoVO vo1 = container.getBean("vo1", DbInfoVO.class);
		DbInfoVO vo2 = container.getBean("vo2", DbInfoVO.class);
		
		System.out.println(vo1);
		System.out.println(vo2);
	}
}
