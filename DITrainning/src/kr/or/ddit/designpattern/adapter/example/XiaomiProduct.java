package kr.or.ddit.designpattern.adapter.example;

import org.springframework.stereotype.Component;

@Component
public class XiaomiProduct implements PluggableCN{

	@Override
	public void receiveElectricWithThreeLeg() {
		System.out.println("샤오미 입니다.");
	}
	

}
