package kr.or.ddit.designpattern.adapter;

public class Adapter implements Target {
	// 생성자 필요  생성자를 통해서 맵핑할수 있는걸 받아와야 한다. 
	
	private Adaptee adaptee;
	public Adapter(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
	@Override
	public void request() {
		adaptee.specificRequest();
	}


}
