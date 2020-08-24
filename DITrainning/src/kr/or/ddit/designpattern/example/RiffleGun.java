package kr.or.ddit.designpattern.example;

import org.springframework.stereotype.Component;

@Component
public class RiffleGun implements Gun {

	@Override
	public void shot() {
		System.out.println("riffleGun");

	}

}
