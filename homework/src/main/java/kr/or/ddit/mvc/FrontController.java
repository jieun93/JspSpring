package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.buyer.controller.BuyerViewController;
import kr.or.ddit.buyer.controller.ByerListController;
import kr.or.ddit.buyer.controller.InsertBuyerController;
import kr.or.ddit.buyer.controller.UpdateBuyerController;


import kr.or.ddit.mvc.annotation.HandlerInvoker;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.mvc.annotation.IHandlerInvoker;
import kr.or.ddit.mvc.annotation.IHandlerMapper;
import kr.or.ddit.mvc.annotation.URIMappingInfo;

/**
 * Servlet implementation class FrontController
 * 모든 요청을 받는 서블릿  메소드에 상관없이 다 요청을 받아야 한다. ==>service
 * 
 * front controller patter을 적용하여 각 컨트롤러에서 반복되는 코드를 제거하기 위함.
 *  front controller로서 모든 요청의 엔트리 포인트가 되며, 각, 커맨드 컨트롤러들의 invoker의 역할을 함 
 */

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(FrontController.class); 
    
    IHandlerMapper handlerMapper;
    IHandlerInvoker handlerInvoker;
    IViewProcessor viewProcessor;
    
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	String basePackage = config.getInitParameter("basePackage");
    	handlerMapper = new HandlerMapper(basePackage);
    	
    	handlerInvoker = new HandlerInvoker();
    	
    	String prefix = config.getInitParameter("prefix");
    	String suffix = config.getInitParameter("suffix");
    	viewProcessor = new ViewProcessor();
    	viewProcessor.setPrefix(Objects.toString(prefix,""));
    	viewProcessor.setSuffix(Objects.toString(suffix,""));
    	
    }
    
    
    // 템플릿 메소드 역할을 한다. 
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    	super.service(req, resp);  
    	req.setCharacterEncoding("UTF-8");
    	
    	
    	
//    	// 반복되는
//    	// go page , 이동방식 결정해야함
//    	String goPage =null;
//    	boolean redirect = false;
    	
    	URIMappingInfo mappingInfo =  handlerMapper.findCommandHandler(req);
    	
     	if(mappingInfo == null) {
     		resp.sendError(404,"해당서비스 지원하지 않습니다.");
     		return;
     	}
     	
     	String viewName =  handlerInvoker.invokeHandeler(mappingInfo, req, resp);
     	     	
    	// isCommitted()  뜻 다시 찾아보기 
    	    	
    	if(viewName == null) { // 처리할 수 있는 서비스가 아닌거
    		if(!resp.isCommitted()) {
    			resp.sendError(500,"논리적인 뷰네임이 결정되어야 함.");
    		}
    		return;
    	}
    	
    	
    	
    	viewProcessor.viewProcess(viewName, req, resp);
    	
    	
    }

}
