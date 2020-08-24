package kr.or.ddit.designpattern.example;

import org.springframework.stereotype.Component;

@Component
public class ShotGun implements Gun {

	@Override
	public void shot() {
		System.out.println("샷건");
	}

}
