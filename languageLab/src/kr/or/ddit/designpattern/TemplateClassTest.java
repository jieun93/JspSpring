package kr.or.ddit.designpattern;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.designpattern.templetmethod.DerivedClass1;
import kr.or.ddit.designpattern.templetmethod.DerivedClass2;
import kr.or.ddit.designpattern.templetmethod.TemplateClass;

public class TemplateClassTest {

	@Test
	public void testTemplate() {
		
		TemplateClass templateClz = new DerivedClass2();
		templateClz.template();
				
	}
	
	

}
