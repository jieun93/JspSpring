package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.MemberVO;

@Controller
public class UserListReadController {
	
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@GetMapping(value = "/getUserList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody  // 마쉘링 할 수 있는 준비 
	public Set<MemberVO> userList() throws IOException, ServletException{

		Set<MemberVO> userList = (Set<MemberVO>) application.getAttribute("userList");
		
		return userList; // 마쉘링할 대상을 다시 보내는거 
		
	}
}










