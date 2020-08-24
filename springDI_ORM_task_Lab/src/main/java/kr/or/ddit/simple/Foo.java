package kr.or.ddit.simple;

public class Foo {
//	1. 의존 객체를 직접 생성 : 결합력 최상
//	Bar bar = new Bar();
//	Baz baz = new Baz();
	
	
//	2. Factory pattern
//	Bar bar = ObjectFactory.getBar();
//	Baz baz = ObjectFactory.getBaz();
	
//	3. Strategy Pattern (전략패턴) -- 전략의 주입자가 반드시 있어야 한다. / 결합력을 가장 많이 낮출 수 있다.
	private IBar bar;
 	private IBaz baz;
 	//생성자 만들기
	public Foo(IBar bar, IBaz baz) {
		super();
		this.bar = bar;
		this.baz = baz;
	}
	
 	
 	
	
	
}
