package kr.or.ddit.designpattern.templetmethod;

public class DerivedClass1 extends TemplateClass {

	
	@Override
	protected void stepTwo() {
		System.out.println("파생클래스1에서 결정된 세부 작업");
	}

}
