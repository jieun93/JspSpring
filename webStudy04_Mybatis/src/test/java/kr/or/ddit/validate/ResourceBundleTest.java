package kr.or.ddit.validate;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class ResourceBundleTest {
	
	@Test
	public void bundleTest() {
		ResourceBundle bundle =  ResourceBundle.getBundle("org.hibernate.validator.ValidationMessages", Locale.ITALIAN);
		String message = bundle.getString("javax.validation.constraints.NotBlank.message");
		System.out.println(message);
	}
}
