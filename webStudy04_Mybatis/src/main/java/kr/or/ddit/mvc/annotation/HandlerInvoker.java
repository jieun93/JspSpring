package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.mvc.stereotype.ModelData;
import kr.or.ddit.mvc.stereotype.RequestParameter;

public class HandlerInvoker implements IHandlerInvoker{

	@Override
	public String invokeHandeler(URIMappingInfo mappinginfo, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Object controller = mappinginfo.getCommandHandler(); // memberlistcontroller를 잡아온ㅁ
		Method handlerMethod = mappinginfo.getHandlerMethod(); // list를 호출하는거  me 
		Parameter[] parameters = handlerMethod.getParameters();
		
		try {
				if(parameters.length == 0) {
					return  (String) handlerMethod.invoke(controller);
				}else{
					Object[] realParameters = new Object[parameters.length];
					for(int i= 0; i<parameters.length; i++) {
						Parameter param = parameters[i];
						Class<?> paramType =  param.getType();
						ModelData modelData = param.getAnnotation(ModelData.class);
						RequestParameter requsetParameter = param.getAnnotation(RequestParameter.class);	
						if(paramType.equals(HttpServletRequest.class)) {
							realParameters[i] = request;
						}else if(paramType.equals(HttpServletResponse.class)) {
							realParameters[i] = response;
						}else if(modelData != null){
							realParameters[i] = makeModel(request, paramType, modelData);
						}else if(realParameters!=null) {
							realParameters[i] =generateParameter(request, paramType, requsetParameter);
						}else {
							throw new RuntimeException("프레임워크가 예상한 상황이 아녜요.ㅠ.ㅠ");
						}
					}
					return  (String) handlerMethod.invoke(controller, realParameters);
				}
		} catch (Exception e) {
			throw new ServletException(e); // 발생한 예외의 정보를 넘기는거 , 원본예외에 대해 유지를 해야 한다.
		}
	
	}
		
	private Object generateParameter(HttpServletRequest req, Class<?> paramType, RequestParameter requestParameter) {
		String name = requestParameter.name();
		String paramValue = req.getParameter(name);
		if(requestParameter.required() && (paramValue==null || paramValue.isEmpty())) {
			throw new IllegalArgumentException(name+"에 해당하는 요청 파라미터가 누락됨");
		}else {
			Object realParameter = null;
			if(paramValue==null || paramValue.isEmpty()) {
				paramValue = requestParameter.defaultValue();
			}
			if(String.class.equals(paramType)) {
				realParameter = paramValue;
			}else if(byte.class.equals(paramType) || Byte.class.equals(paramType)) {
				realParameter = Byte.parseByte(paramValue);
			}else if(short.class.equals(paramType)|| Short.class.equals(paramType)) {
				realParameter = Short.parseShort(paramValue);
			}else if(int.class.equals(paramType)|| Integer.class.equals(paramType)) {
				realParameter = Integer.parseInt(paramValue);
			}else if(long.class.equals(paramType)|| Long.class.equals(paramType)) {
				realParameter = Long.parseLong(paramValue);
			}else if(float.class.equals(paramType)|| Float.class.equals(paramType)) {
				realParameter = Float.parseFloat(paramValue);
			}else if(double.class.equals(paramType)|| Double.class.equals(paramType)) {
				realParameter = Double.parseDouble(paramValue);
			}else if(char.class.equals(paramType)|| Character.class.equals(paramType)) {
				realParameter = paramValue.charAt(0);
			}else if(boolean.class.equals(paramType)|| Boolean.class.equals(paramType)) {
				realParameter = Boolean.parseBoolean(paramValue);
			}else {
				throw new RuntimeException("@RequestParameter 는 기본형에만 사용할 수 있음.");
			}
			return realParameter;
		}
	}

	private Object makeModel(HttpServletRequest req, Class<?> paramType, ModelData modelData) {
		try {
			Object realParam = paramType.newInstance();
			req.setAttribute(modelData.value(), realParam);
			BeanUtils.populate(realParam, req.getParameterMap());
			return realParam;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

