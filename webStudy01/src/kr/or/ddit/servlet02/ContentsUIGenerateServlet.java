package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;
import kr.or.ddit.utils.TemplateUtils.VariableVO;

/**
 * @author PC-20
 * d:/contents 폴더의 파일 목록을 select 태그로 완성
 * 파일의 목록에는 ico 파일을 포함 시키지 말것
 *  / contents.do 요청에 의해 UI가 제공됨
 *  단 , 절대로 어떤 경로도 소스상에 하드코딩 하지 말것.
 *  
 */

// CoC(Convention Over Configuration)

// mapping 등록 하는 단계  web.xml에서 하는것을 어노테이션으로 한번에 할 수 있다. 
// @WebServlet(initParams = {@WebInitParam(name="contentsPath",value="d:/contents")},
//	 urlPatterns = "/contents.do", loadOnStartup=1)
public class ContentsUIGenerateServlet extends HttpServlet {
	
	private File folder;
	private ServletContext application; //  싱긑톤
	Date date = new Date();
	//2. 초기화 파라미터를 잡기
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext();
		System.out.println(this.getClass().getSimpleName()+"에서 확인 :"+application.hashCode());
		
		String contentsPath = application.getInitParameter("contentsPath");
		// 문자열은 null로만 판단 할 수 없다. isEmpty() 도 써줘야 한다.
		if(contentsPath == null || contentsPath.isEmpty()) throw new NullPointerException("contentsPath 파라미터 누락");
			folder = new File(contentsPath);
			
			Calendar cal = getInstance();
	}
	
	
	
	// 1. doget method 만들기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//5. mime 설정

		resp.setContentType("text/html;charset=UTF-8");

		
		 
		//3.
	//	String[] childArray = folder.list((dir, name)->{return true;}); // 필터링을 하지 않겠다는거 
		String[] childArray = folder.list((dir, name)->{
			boolean result = !name.endsWith(".ico"); // ico 파일을 제거하는거 
			return result;
		}); 
	
		  //4. html 작성하기 ==> sampleTemplate.tmpl 
		
//		  StringBuffer html = new StringBuffer("<html>\n");
//		  html.append("<body>\n");
//		  html.append("<form action='fileStream.do'>\n"); // 상대경로 방식으로
//		  html.append("<select name='filename'>\n"); // name 을주면 요청 파라미터를 결정
		// <option value='%s'> %s </option> 
		// html.append("$('form').submit(function(){");
//		  html.append("@data"); 
		// html.append("});"); //
//		  String.format("<option value='%s'> %s </option>",args);
//		  html.append("</select>\n"); //
//		  html.append("<input type='submit' value='전송'>\n"); html.append("</form>\n");
//		  html.append("</body>\n"); html.append("</html>");
		 
		
//		Map<String, VariableVO> names = new HashMap<>();
//		String html = TemplateUtils.readTemplate("sampleTemplate.tmpl", names )+"";
		
		StringBuffer data = makeData(childArray); 
				
		Map<String, Object> dataMap = new LinkedHashMap<>();
		dataMap.put("data", data);
		dataMap.put("now",new Date());
		String html = TemplateUtils.makeHTML("/kr/or/ddit/servlet02/sampleTemplate.tmpl",dataMap);
//		for( Entry<String, Object> entry: dataMap.entrySet()) {
//			String name = entry.getKey();
//			Object value = entry.getValue();
//			VariableVO dataVariable = names.get(name);
//			if(dataVariable == null) continue;
//			html = html.replace("@"+name, Objects.toString(value, ""));			
//		}
		
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(html);
			
		}
	
	}



	private StringBuffer makeData(String[] childArray) {
		StringBuffer data = new StringBuffer();
		for(String  child : childArray) {
			data.append(String.format("<option value='%1$s'> %1$s </option>", child));
		}
		
		return data;
	}	
	
	
	
}
