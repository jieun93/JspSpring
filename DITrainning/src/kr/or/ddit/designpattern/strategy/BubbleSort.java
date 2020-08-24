package kr.or.ddit.designpattern.strategy;

import org.springframework.stereotype.Component;

@Component
public class BubbleSort implements Sort {

	@Override
	public void algorithm() {
		System.out.println("버블에서 구현된 알고리즘");
	}

}
