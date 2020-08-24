package kr.or.ddit.simple;

public class StrategynInjector {
	public static void main(String[] args) {
		IBar bar = new Bar();
		IBaz baz = new Baz();
		Foo foo = new Foo(bar, baz);
	}
}
