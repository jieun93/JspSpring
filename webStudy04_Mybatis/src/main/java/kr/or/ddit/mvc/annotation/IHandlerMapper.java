package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

public interface IHandlerMapper {
	/**
	 * 특정 요청을 처리 할 수 잇는  command handler를 검색 
	 * @param req
	 * @return
	 */
	public URIMappingInfo findCommandHandler(HttpServletRequest req);
}
