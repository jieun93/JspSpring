package kr.or.ddit.designpattern.adapter.example;

import org.springframework.stereotype.Component;

@Component
public class LGProduct implements PluggableKST {

	@Override
	public void receiveElectricWithTwoLeg() {
		System.out.println("LG 입니다.");
	}

}
