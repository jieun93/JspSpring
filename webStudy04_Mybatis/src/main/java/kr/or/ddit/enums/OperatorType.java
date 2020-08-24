package kr.or.ddit.enums;
//enum 열거형 
public enum OperatorType{
	PLUS('+', (left, right)->{return left+right;}), 
	MINUS('-', new Operator() {
		@Override
		public double operate(double left, double right) {
			return left-right;
		}
	}), 
	MULTIPLY('*', (left, right)->{return left*right;}), 
	DIVIDE('/', (left, right)->{return left/right;});
	
	
	// 값을 받을 빈 껍데기 만들어주는것 
	@FunctionalInterface
	public static interface Operator{
		public double operate(double left, double right);
	}
	
	private char sign;  // 사칙연산 값 + - * /
	private Operator realOperator; // 사칙연산 결과를 넣을   realOperator
	
	private OperatorType(char sign, Operator realOperator) {
		//sign 은 사칙연산
		this.sign = sign;
		// realOperator 는 계산 값
		this.realOperator = realOperator;
	}
	
	public double operate(double left, double right) {
		return realOperator.operate(left, right);
	}
	public char getSign() {
		return sign;
	}
}







