package kr.or.ddit.designpattern.templetmethod;

public class DerivedClass2 extends TemplateClass {

	
	@Override
	protected void stepTwo() {
		System.out.println("파생클래스2에서 결정된 세부 작업");
	}

}
