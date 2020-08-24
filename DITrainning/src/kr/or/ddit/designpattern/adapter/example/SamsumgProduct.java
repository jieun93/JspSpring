package kr.or.ddit.designpattern.adapter.example;
import org.springframework.stereotype.Component;

@Component
public class SamsumgProduct implements PluggableKST {

	@Override
	public void receiveElectricWithTwoLeg() {
		System.out.println("삼성입니다.");

	}

}
