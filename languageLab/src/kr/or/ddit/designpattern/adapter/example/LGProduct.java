package kr.or.ddit.designpattern.adapter.example;

public class LGProduct implements PluggableKST {

	@Override
	public void receiveElectricWithTwoLeg() {
		System.out.println("LG 입니다.");
	}

}
