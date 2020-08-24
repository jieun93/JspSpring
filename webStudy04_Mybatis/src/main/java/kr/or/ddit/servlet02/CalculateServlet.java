package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.OperatorType;


@WebServlet(urlPatterns = "/01/calculate.do")  // uri 또는 dummy url이라고 한다.  
public class CalculateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  사용자에게 입력받는 값을 파라미터로 받는다.
		String leftStr = request.getParameter("left"); // 왼쪽 박스
		String rightStr = request.getParameter("right"); // 오른쪽 박스
		String opParam = request.getParameter("operator"); // 연산자 선택 값
		
		int statusCode = 0;
		// 받은 값을 검사한다.
		// 빈칸 부분, 숫자여부를 확인 한다.
		if(StringUtils.isBlank(leftStr) || StringUtils.isBlank(rightStr)
				|| !StringUtils.isNumeric(leftStr) 
				|| !StringUtils.isNumeric(rightStr)
				|| StringUtils.isBlank(opParam)) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST; 
			// 조건문에 맞지 않으면 오류를 내보낸다. statusCode에 숫자 오류코드 여서 int형이다. 
			//오류를 보낸다. 
		}
		
		OperatorType operator = null; // operatortype을 불러오려고 기본설정 
		
		// 연산자의 선택값을 enum으로 받는거 
		// enum 은 열거형 숫자로 찾을 수 있다. 0, 1, 2, 3
		
		try {
			// 연산자를 선택한 값의 번호를 operator에 담아두는것
			// valueOf : 매개변수로 주어진 String과 열거형에서 일치하는 이름을 갖는 원소를 반환
			operator = OperatorType.valueOf(opParam); 
		}catch (Exception e) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST; //  연산자의 선택값이 없으면 오류코드를 보낸다. 
		}
		
		// statusCode가 0이아니면  오류가나면   응답에 오류를 보낸다. 
		if(statusCode!=0) {
			response.sendError(statusCode);
			return;
		}
		
		
		// 입력 받은 값을 더블형으로 형변환을 해준다. 
		double left = Double.parseDouble(leftStr);
		double right = Double.parseDouble(rightStr);
		
		//OperatorType의  선택 연산자에 값을 넣어서 계산하여  result에 넣는거 
		double result = operator.operate(left, right);
		
		// 사칙연산의 값을 sign에 넣는거 
		char sign = operator.getSign();
		
		// client 입장에서 값을 받아오는 거 를 html에 담아두는거 
		StringBuffer html = new StringBuffer("<html>");
		html.append("<body>");
		html.append(String.format("<h4>%.2f %s %.2f = %.2f</h4>", left, sign, right, result));
		// %는 구멍 뚫는거   .2f는 소수점 자리수를 의미 하는거                   , 왼쪽값  , 연산자 ,  오른쪽값 ,  결과 
		html.append("</body>");
		html.append("</html>");
		
		// 출력하는 부분 
		try(
			PrintWriter out = response.getWriter();	
		){
			out.println(html);
		}
	}
}








