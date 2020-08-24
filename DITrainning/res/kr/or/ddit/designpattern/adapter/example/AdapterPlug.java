package kr.or.ddit.designpattern.adapter.example;

public class AdapterPlug implements PluggableKST {

	private PluggableCN plug;
	public AdapterPlug(PluggableCN plug) {
		super();
		this.plug = plug;
	}


	@Override
	public void receiveElectricWithTwoLeg() {
		 plug.receiveElectricWithThreeLeg();
	}

}
