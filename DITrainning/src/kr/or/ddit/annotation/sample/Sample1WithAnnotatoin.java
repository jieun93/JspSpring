package kr.or.ddit.annotation.sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ServiceMode;

import kr.or.ddit.annotation.stereotype.CommandHandler;
import kr.or.ddit.annotation.stereotype.SecondSigleValueAnnotation;


@CommandHandler
public class Sample1WithAnnotatoin {

	@SecondSigleValueAnnotation(value="값1")
	@ThirdMutiValurAnnotation(value="text1", number=45)
	public void test1() {
		

	}
	
	
	@SecondSigleValueAnnotation("값2")
	public String test2(HttpServletRequest req, HttpServletResponse resp) {
		실행결과
	}
	
}
