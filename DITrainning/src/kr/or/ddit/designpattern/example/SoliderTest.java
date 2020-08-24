package kr.or.ddit.designpattern.example;



import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.designpattern.adapter.example.conf.AdapterPatternContainerongf;

public class SoliderTest {

//	@Test
//	public void testAttack() {
//		ConfigurableApplicationContext container
//			= new GenericXmlApplicationContext("classpath:kr/or/ddit/designpattern/adapter/example/conf/soliderTest-di.xml");
//		
////		Gun gun = new BiBiTan();
//		Gun gun = container.getBean("biBiTan", BiBiTan.class);
//		
////		Solider solider = new Solider();
//		Solider solider = container.getBean("solider", Solider.class);
//		
////		solider.armedWithGun(gun);
//		solider.attack();
//		
//	}

	public static void main(String[] args) {
		ConfigurableApplicationContext parent =
				new AnnotationConfigApplicationContext(AdapterPatternContainerongf.class);
	 
		
		ConfigurableApplicationContext child = 
				new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/designpattern/strategy/example/conf/strategy-context.xml"},parent);
s		child.setParent(parent);
		child.refresh();
		child.registerShutdownHook();
		
		// 주입 받기 
//		Gun gun = container.getBean("biBiTan",Gun.class);
		
		// 기본적으로 싱글턴인다.
		Solider solider1 = child.getBean("solider", Solider.class);
		Solider solider2 = child.getBean("solider", Solider.class);
		Solider solider3 = child.getBean("solider", Solider.class);
		Solider solider4 = child.getBean("solider", Solider.class);
		Solider solider5 = child.getBean("solider", Solider.class);
		
//		solider1.armedWithGun(gun);
		solider1.attack();
		System.out.println(solider1.getXiami());
		
	}
	
}
