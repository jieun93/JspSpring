package kr.or.ddit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author PC-20
 * Throwable
 * 		- Error : 시스템 default 에러에 해당하며, 개발자가 처리하지 않음.
 * 		- Exception : 개발자가 로직에 의해 처리 할 수 있는 예외
 * 			- checked : 반드시처리를 해야하는 예외 , 처리를 하지 않으면 컴파일을 발생시키는 예외
 * 					ex) IOException,  SQLException
 *			- unchecked (RuntimeException) : 발생 가능성이 있더라도 개발자가 처리 하지않아도 되고 호출자에게 제어권이 전달되는 예외
 *					ex) NullPointerException, IllegalArgumentException
 *
 * 처리방법
 * 1) 적극 처리 : try~catch~finally - 예외 발생 메소드 내에서 직접 처리  
 * 2) 수동 처리 : throws  - 예외 발생 메소드의 호출자에게 처리를 위임.
 * 
 *  커스텀예외를 작성하는 방법
 *  커스텀 예외 정의 : 정의 하고 싶은 예외의 처리 제약에 따라 상위 결정
 *  예외 발생시 사용 : throw new 예외 객체 생성 
 *  		
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException{

	public DataNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

}
