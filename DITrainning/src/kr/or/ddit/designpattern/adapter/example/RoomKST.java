package kr.or.ddit.designpattern.adapter.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class RoomKST {
//	public ConcentKST concentKST= new ConcentKST() ;
	
	private ConcentKST concent;
	
	public void setConcent(ConcentKST concent) {
		this.concent = concent;
	}
	
	
	public static void main(String[] args) {
//		RoomKST  room = new RoomKST();
		
//		PluggableKST samsung = new SamsumgProduct();
//		ConfigurableApplicationContext container =
//				new GenericXmlApplicationContext("classpath:kr/or/ddit/designpattern/RoomKST-container.xml");
//	 	container.registerShutdownHook();
		
//		PluggableKST samsung = container.getBean("samsumgProduct",SamsumgProduct.class);
		
//		PluggableKST lg = new LGProduct();
	
//		PluggableKST lg = container.getBean("LGProduct",LGProduct.class);
		
		
//		PluggableCN xiao = new XiaomiProduct();
//		PluggableKST adapter = container.getBean("adapterPlug", AdapterPlug.class);
		
//		ConcentKST  concent = container.getBean("concentKST",ConcentKST.class);
		
//		concent.plugin(samsung);
//		concent.plugin(lg);
//		concent.plugin(adapter);

		
		
		ConfigurableApplicationContext container = 
				new ClassPathXmlApplicationContext("kr/or/ddit/designpattern/adapter/example/conf/adaptPattern-di.xml");
		RoomKST room = container.getBean(RoomKST.class);
		PluggableKST samsung = container.getBean("samsung",PluggableKST.class);
		PluggableKST lg = container.getBean("lg", PluggableKST.class);
		PluggableKST adapter = container.getBean(AdapterPlug.class);
		
		
		
	}
}
