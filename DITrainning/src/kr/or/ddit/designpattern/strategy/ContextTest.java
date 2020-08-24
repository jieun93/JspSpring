package kr.or.ddit.designpattern.strategy;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

public class ContextTest {

	@Test
	public void testOperation() {
		ConfigurableApplicationContext container = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/designpattern/adapter/example/conf/strategy-di.xml");
		
//		Sort strategy = new BinarySort();
		Sort strategy = container.getBean("binarySort", BinarySort.class);
		
		
//		Context ctx = new Context();
		Context ctx = container.getBean("context", Context.class);
		
		ctx.setStrategy(strategy);
		ctx.operation();
		
	}

}
