package com.spring.calc;

public class Calculator {
	
	private Sumation sum; // = new SumationImpl();
	
	public void setSum(Sumation sum) {
		this.sum = sum;
	}

	public void sum(int a, int b) {
		int result = sum.sum(a, b);
		System.out.println("두 정수 "+ a+","+b+"의 합은 "+result+"입니다.");
	}
	
	public void sum(int a, int b, int c) {
		int result = sum.sum(a, b, c);
		System.out.println("두 정수 "+ a+","+b+","+c+"의 합은 "+result+"입니다.");
	};
		
}
