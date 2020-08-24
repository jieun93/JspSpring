package kr.or.ddit.designpattern.adapter.example;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdapterPlug implements PluggableKST {

	private PluggableCN plug;
	
	@Inject
	public AdapterPlug(PluggableCN plug) {
		super();
		this.plug = plug;
	}


	@Override
	public void receiveElectricWithTwoLeg() {
		 plug.receiveElectricWithThreeLeg();
	}

}
