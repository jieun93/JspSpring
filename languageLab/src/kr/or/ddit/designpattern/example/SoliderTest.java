package kr.or.ddit.designpattern.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class SoliderTest {

	@Test
	public void testAttack() {
		Gun gun = new BiBiTan();
		Solider solider = new Solider();
		solider.armedWithGun(gun);
		solider.attack();
		
	}

}
