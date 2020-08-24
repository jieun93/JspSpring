package kr.or.ddit.validate.stereotype;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import kr.or.ddit.validate.MimeCheckerValidator;

@Target({FIELD, METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MimeCheckerValidator.class})
public @interface MimeChecker {
	public String contentType();
	public String message() default "{kr.or.ddit.validate.stereotype.MimeChecker.message}";
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
}










