package kr.or.ddit.mvc.annotation;

import kr.or.ddit.mvc.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author PC-20
 * @urimapping이 가진 데이터를 캡슐화한 객체
 * 어떤주소, 어떤메소드의 요청인지를 식별할때 사용됨.
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class URIMappingCondition {
	private String uri;
	private HttpMethod method;
	// 두개가 일치해야만 equals 
}
