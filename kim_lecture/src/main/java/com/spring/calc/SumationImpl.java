package com.spring.calc;

public class SumationImpl implements Sumation {

	@Override
	public int sum(int a, int b) {
		
		return  a+b;
	}

	@Override
	public int sum(int a, int b, int c) {
		return a+b+c;
	}

}
