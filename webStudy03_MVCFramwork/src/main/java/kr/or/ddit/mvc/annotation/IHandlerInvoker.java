package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IHandlerInvoker {
	/**
	 * pojo 형태로 구현된 command handler 역할을 함 
	 * @param mappinginfo
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String invokeHandeler(URIMappingInfo mappinginfo, HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException;
}
