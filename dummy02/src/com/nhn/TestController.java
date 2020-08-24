package com.nhn;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;

@CommandHandler
public class TestController {
	@URIMapping("/sample.nhn")
	public String  sample(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("today", new Date());
		return "sampleView";
				
				
		
	}
}
