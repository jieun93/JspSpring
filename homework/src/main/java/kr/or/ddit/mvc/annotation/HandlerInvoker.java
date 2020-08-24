package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInvoker implements IHandlerInvoker{

	@Override
	public String invokeHandeler(URIMappingInfo mappinginfo, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Object controller = mappinginfo.getCommandHandler(); // memberlistcontroller를 잡아온ㅁ
		Method handlerMethod = mappinginfo.getHandlerMethod(); // list를 호출하는거  me 
		try {
			return  (String) handlerMethod.invoke(controller, request, response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServletException(e); // 발생한 예외의 정보를 넘기는거 
		}
	
	}
	
}
