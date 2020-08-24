package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.xml.internal.ws.resources.HttpserverMessages;

import kr.or.ddit.utils.TemplateUtils;
import kr.or.ddit.utils.TemplateUtils.VariableVO;


@WebServlet("/gugudan.do")
public class GugudanServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// table 태그 사용. 한행에 한단씩 구구단 출력.
		// 2~9단 , 1~9승수 
		

		// 4. 검증 결과 -->통과 / 실패
		StringBuffer msg = new StringBuffer();
		boolean valid = validate(request, msg);
		if(!valid) {
			// 검증 실패
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg.toString());
			return;
		}
		
		
		// 3. 최소단,최대단 파라미터 확보
		//input의 name을 가져오는거
		String minStr = request.getParameter("first");
		String maxStr = request.getParameter("second");
		
		// 5. makeData 에서 최종 데이터 생성
		int minDan = Integer.parseInt(minStr);
		int maxDan = Integer.parseInt(maxStr);
		
		// mime 설정 
		response.setContentType("text/html;charset=UTF-8");
		
		
		StringBuffer data = makeDate(minDan, maxDan); // 구구단 돌아가는거 data에 담아두는거 
		Map<String, Object> dataMap = new HashMap<>();
//		Map<String, Object> dataMap = new LinkedHashMap<>();
		dataMap.put("trTags", data);     // @trTags  data에 담아둔 구구단 trTags에 넣는거  
		dataMap.put("now",new Date());   // @now
		
		// html에  templateutils에 있는 makeHTML에  gugudan.tmpl 불러오는거 
		String html = TemplateUtils.makeHTML("/kr/or/ddit/servlet02/gugudan.tmpl",dataMap);
		
		try(
				// 응답할거 
			PrintWriter out = response.getWriter();
			){
			out.println(html);
		}

	}
	
	// 검사  true, false 만드는거 
	private boolean validate(HttpServletRequest request, StringBuffer msg) {
		boolean valid = true;
		// 필수 파라미터 전송 여부
		String min = request.getParameter("minDan");
		String max = request.getParameter("maxDan");
		
		// min, max가 값이 없는 경우 valid에 false, msg에 메세지 붙이기 
		if(min==null || min.isEmpty() || max==null || max.isEmpty()) {
			valid = false;
			msg.append("필수데이터 누락");
		}
		
		// 숫자여부
		// valid가 비어 있거나 , min이 숫자가 2-9가 아니거나 max의 값이 2-9가 아닌경우의 오류 검사  
		if(valid && (!min.matches("[2-9]")|| !max.matches("[2-9]"))) {
			valid = false;
			msg.append("입력 데이터 오류"); 
		}
		
		return valid;
	}

	private StringBuffer makeDate(int minDan, int maxDan) {
		StringBuffer  data = new StringBuffer();
		data.append("<tr>");
		String format = "<td>%d*%d=%d</td>";
		for(int dan = minDan; dan <= maxDan; dan++) {
			data.append("<tr>");
			for(int mul= 1; mul<=9; mul++) {
				data.append(String.format(format, dan, mul, dan*mul));
			} 
			data.append("</tr>");
		}

		return data;
	}



	



	

}
