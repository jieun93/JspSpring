package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewProcessor implements IViewProcessor {
	private String prefix;
	private String suffix;
	

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;

	}

	@Override
	public void viewProcess(String viewName, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 템플릿 메소드 패턴 -- 다시보기 
    	// 중복되는 부분을 미리 만들어 줌 
		boolean	redirect = viewName.startsWith("redirect:");
    	
    	if(redirect) {
    		viewName = viewName.substring("redirect:".length());
    		response.sendRedirect(request.getContextPath()+viewName); // 응답데이터가 나갔다가 다시 들어옴 
		}else {
			request.getRequestDispatcher(prefix+viewName+suffix).forward(request, response); // 서버안에서만 이동		
		}
		
    	
	}

}
