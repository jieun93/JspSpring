package kr.or.ddit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.or.ddit.servlet02.GugudanServlet;

public class TemplateUtils {
	
	
	
	public static StringBuffer readTemplate(String path) throws IOException {
		StringBuffer template = new StringBuffer();
		try(
				// 입력 하는거 
				InputStream is = TemplateUtils.class.getResourceAsStream(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			){
			String temp = null;
			while((temp = reader.readLine()) != null) {
				template.append(temp+"\n");
			}
		}
		
//		Map<String, VariableVO> names = 
//				findVariables(template, names);
//		System.out.println(names);
		return template;
	}
	
	
	public static class VariableVO{
		// 변수의 이름, 위치 데이터 , start, end
		private String varName;
		private int start;
		private int end;
	
		public VariableVO(String varName, int start, int end) {
			super();
			this.varName = varName;
			this.start = start;
			this.end = end;
		}
		
		// 한번 객체가 생성된 다음에는 변경 할 수 없도록  get만 만들어 줌 
		
		public String getVarName() {
			return varName;
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}

		@Override
		public String toString() {
			return "VariableVO [varName=" + varName + ", start=" + start + ", end=" + end + "]";
		}
		
		
		
	
	}
	
	
	public static Map<String , VariableVO> findVariables(CharSequence text,  Map<String , VariableVO> names){
		// 정규식을 컴파일 하는거
		Pattern regex  = Pattern.compile(ptrn);
		Matcher matcher = regex.matcher(text);
		while(matcher.find()) {
			String variableName = matcher.group(1);
			int start = matcher.start();
			int end = start +(variableName.length())+1;
					
			names.put(variableName, new VariableVO(variableName, start, end));
		}
		return names;
	}
	


	private static final String ptrn = "@([a-zA-Z]+)";
	
	public static String makeHTML(String string, Map<String, Object> dataMap) throws IOException {	
		
//		Map<String, VariableVO> names = new HashMap<>();
		String html = readTemplate(string)+"";
		html = replaceDataVariables(html, dataMap);
//		for( Entry<String, Object> entry: dataMap.entrySet()) {
//			String name = entry.getKey();
//			Object value = entry.getValue();
//			VariableVO dataVariable = names.get(name);
//			if(dataVariable == null) continue;
//			html = html.replace("@"+name, Objects.toString(value, ""));
//	
//			}
		return html;
		}
	
	
	private static String  replaceDataVariables(CharSequence html, Map<String,Object> dataMap ) {
		// 정규식을 컴파일 받는거 
		Pattern pattern = Pattern.compile(ptrn);
		Matcher matcher = pattern.matcher(html);
		StringBuffer result = new StringBuffer();
		
		while(matcher.find()) {
			String name = matcher.group(1);
			Object data = dataMap.get(name);
			matcher.appendReplacement(result, Objects.toString(data,""));
		}
		
		matcher.appendTail(result);
		return result.toString();
		
	}
}
