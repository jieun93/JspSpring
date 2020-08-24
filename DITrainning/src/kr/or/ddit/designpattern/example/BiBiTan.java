package kr.or.ddit.designpattern.example;

import org.springframework.stereotype.Component;

@Component
public class BiBiTan implements Gun {

	@Override
	public void shot() {
		System.out.println("비비탄");
	}

}
