package kr.or.ddit.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.stereotype.MimeChecker;

public class MimeCheckerValidator implements ConstraintValidator<MimeChecker, MultipartFile>{

	private String contentType;
	
	@Override
	public void initialize(MimeChecker constraintAnnotation) {
	contentType = constraintAnnotation.contentType();
	contentType = contentType.replace("*", ".+");
	
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		if(value == null) return true;  // null 이면 리턴 
		// 파일이 실제로 있는 지 확인 해야 한다.
		String filename = value.getOriginalFilename();
		if(filename == null || filename.isEmpty()) return true;
		String fileMime  = value.getContentType();
		Pattern pattern = Pattern.compile(contentType);
		Matcher matcher = pattern.matcher(fileMime);
		return matcher.find();
				
		
//		value.getContentType(); // 현재 클라이언트의 업로드된 파일을  알 수 있다?
//		return false;
	}
	

}
