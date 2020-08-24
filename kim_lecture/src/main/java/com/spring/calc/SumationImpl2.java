package com.spring.calc;

public class SumationImpl2 implements Sumation{

	@Override
	public int sum(int a, int b) {
		
		return a+b+10000;
	}

	@Override
	public int sum(int a, int b, int c) {
		
		return a+b+c+10000;
	}

}
