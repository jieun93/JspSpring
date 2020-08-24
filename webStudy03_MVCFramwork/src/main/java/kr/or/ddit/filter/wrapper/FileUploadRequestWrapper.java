package kr.or.ddit.filter.wrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

public class FileUploadRequestWrapper extends HttpServletRequestWrapper {
			// 키는 prart name
	private Map<String, List<PartWrapper>> partWrapperMap; // 똑같은 파일의 이름이 여러개 선택 되었을 떄 
	
	public FileUploadRequestWrapper(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		partWrapperMap = new LinkedHashMap<>();
		parseRequest(request);
		
	}

	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		Iterator<Part> it = parts.iterator();
		while (it.hasNext()) {
			Part part = (Part) it.next();
			
			String header = part.getHeader("Content-Disposition");
			if (header == null || header.indexOf("filename") == -1) { //header == null 파일이 없다. 
				continue;
			}
			
			
//			String partName = part.getName();
					
			// if문을 건너뛰면 파일파트 부분이ㅏㄷ.
			// 파일 사이즈, 마임,원본명을 꺼내야 한다. 
			PartWrapper wrapper = new PartWrapper(part); // 기존의 파트를 웹퍼로 바꿀 수 있다. 뒷단으로 넘겨야 한다. 값이 유ㅜ지될수 있는 구조를 만들어야 한다.
			String partName = wrapper.getPartName();
			List<PartWrapper>list =partWrapperMap.get(partName);
			if(list == null) {
				list = new ArrayList<>();
				partWrapperMap.put(partName,list);
			}
			list.add(wrapper);
		}
	}
	
	public  PartWrapper getPartWrapper(String partName){
		List<PartWrapper> list =  partWrapperMap.get(partName);
		if(list != null && list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	public List<PartWrapper> getPartWrappers(String partName){
		return  partWrapperMap.get(partName);
	}
	
	public Map<String, List<PartWrapper>> getPartWrapperMap() {
		return partWrapperMap;
	}
	
	public Enumeration<String> getPartWrapperNames(){
		final Iterator<String> partNames =  partWrapperMap.keySet().iterator(); // 지역변수 
		return new Enumeration<String>() {
			@Override
			public boolean hasMoreElements() {
				return partNames.hasNext();
			}
			@Override
			public String nextElement() {
				return partNames.next();
			}
		};
	}
}
