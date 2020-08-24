package kr.or.ddit.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import kr.or.ddit.validate.stereotype.TelNumber;

public class TelNumberValidator implements ConstraintValidator<TelNumber, String >{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String regex  = "0[\\d]{1,2}-[\\d]{3,4}-\\d{4}";
		if(value==null) return true; // 검증할 필요가 없다.
		else {
			return  value.matches(regex);// 매칭되었는지 확인 
//			return false; // false -> 유효하지 않다. 
		}
	}

}
