package kr.or.ddit.designpattern.templetmethod;

public abstract class TemplateClass {
	
	public final void template() {
		stepOne();
		stepTwo();
		stepTree();
	}
	
	
	private void stepOne() {
		
		System.out.println("첫번째 단계");
	}
	
	protected abstract void stepTwo();
	
	private void stepTree() {
		System.out.println("세번째 단계");
	}
	
	
	
}
