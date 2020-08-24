package kr.or.ddit.designpattern.example;

public class Solider {
	private Gun gun;
	
	public void armedWithGun(Gun gun) {
		this.gun = gun;
	}
	
	public void attack() {
		gun.shot();
	}
}
