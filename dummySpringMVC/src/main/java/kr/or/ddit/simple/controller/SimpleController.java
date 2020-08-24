package kr.or.ddit.simple.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.simple.service.SimpleService;
import kr.or.ddit.simple.vo.SimpleVO;

@Controller
@RequestMapping("/simple.do")
public class SimpleController {
	
	
	private SimpleService service;
	
	// 생성자 만듬
	@Inject
	public SimpleController(SimpleService service) {
		super();
		this.service = service;
	}


	// 요청을 받는거
//	@RequestMapping(value = "/simple.do", method = RequestMethod.GET) // get 방식만 처리하는거
	@GetMapping
	public String form() {
		return "simple/simpleForm";
	}
	
	
//	  @RequestMapping(value = "/simple.do", method = RequestMethod.POST) public
	  @PostMapping
//	  public String plus(int leftOp, // 파라미터의 이름을 생략할수 있다.
//			  	  @RequestParam(required = true) int rightOp, Model model ) {
	  
	  
	  public String plus(@Valid @ModelAttribute("simple") SimpleVO simple, Errors errors) {

		  if(!errors.hasErrors()) {
			  service.plus(simple);
		  }
		  
//		  int leftOp = simple.getLeftOp();
//		  int rightOp = simple.getRightOp();
//		  int result = leftOp + rightOp;
//		  
//		  simple.setResult(result);
//		  
//		  service.clac(simple);
//		  model.addAttribute("result", result);
		  return "simple/simpleForm";
				  
		  
	  }
	 
}
