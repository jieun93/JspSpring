package kr.or.ddit.validate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberVOValidateTest {

	@Test
	public void test() {
		MemberVO member = new MemberVO();
		member.setMem_pass("a");
		member.setMem_mail("b");
		CommonValidator<MemberVO> validator = new CommonValidator<>();
		System.err.println(validator.validate(member));
		
	}

}
