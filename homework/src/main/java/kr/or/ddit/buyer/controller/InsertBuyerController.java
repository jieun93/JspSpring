package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.BuyerVO;

//@WebServlet("/buyer/insertBuyer.do")
@Controller
@RequestMapping("/buyer/insertBuyer.do")
public class InsertBuyerController{
	private static final long serialVersionUID = 1L;
    @Inject   
   IBuyerService service;
  
   // 추가버튼은 form으로 보내지는거  get 으로 전달?
    // 추가버튼 을 누르면  거래처 목록 화면으로 이동한다.
   // 추가를 했으니깐 새로운 창을 띄워주기 위해  redirect로???
   @GetMapping
	public String doGet()  {
	   
	   //	 String goPage = "/WEB-INF/views/buyer/BuyerList.jsp";
//	 response.sendRedirect(goPage);
	 return "buyer/BuyerInsert";
	}

	@PostMapping
	public String doPost(@Valid @ModelAttribute("buyer_id") BuyerVO buyer_id,
				Errors errors, Model model
			
			)  {
		
		
		// 추가 양식에 받은 값을 담아주기 위해 그릇을 만든다.
//		BuyerVO buyer_id = new BuyerVO();
		
		//담은걸 service로 보내기위해?? view로 보내기 위해??
		model.addAttribute("buyer_id", buyer_id);
////		try {
//			BeanUtils.populate(buyer_id, request.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			response.sendError(400,"파라미터누락");
//			return null;
//		}
	
		// 받은 값을 검증하기 
		
//		Map<String, String> errors = new LinkedHashMap<>();
		model.addAttribute("errors", errors);
		
//		boolean valid = validate(buyer_id, errors);
		
		String goPage  = null;
//		boolean redirect = false;
		String message = null;
		

//		if(valid) { //입력값의 검증이 true이면 
//			 //service에서의 결과 값을 가ㅈ오고 
//			ServiceResult result = service.createBuyer(buyer_id);
//			// 그 결과 값에 맞는 페이지로 이동 
//			switch(result) {
//			
//			case FAIL:
//				message="다시 시도 해보세요";
//				goPage ="buyer/BuyerInsert";
//				break;		
//			default: // ok --> 추가가 됨   목록리스트 나오는거 
//				goPage="redirect:/buyer/buyerList.do";
////				redirect= true;
//				break;
//			}
//			
//		}else {
//			goPage ="buyer/BuyerInsert";
//		}
//		
		model.addAttribute("message", message);
		return goPage;
		
		// 거래처 목록이 추가가 되면 
//		if(redirect) {
//			response.sendRedirect(request.getContextPath()+goPage);
//		}else {
//			request.getRequestDispatcher(goPage).forward(request, response);
//		}
	}


//	private boolean validate(BuyerVO inputBuy, Map<String, String> errors) {
//		boolean valid = true;
//		/*
//		 * if(StringUtils.isBlank("buyer_id")) { valid = false; errors.put("buyer_id",
//		 * "거래처아이디 누락"); }
//		 */
//		if(StringUtils.isBlank("buyer_name")) {
//			valid = false;
//			errors.put("buyer_name", "거래처명누락");
//		}
//		if(StringUtils.isBlank("buyer_lgu")) {
//			valid = false;
//			errors.put("buyer_lgu", "거래번호 누락");
//		}
//		if(StringUtils.isBlank("buyer_bank")) {
//			valid = false;
//			errors.put("buyer_bank", "거래은행 누락");
//		}
//		if(StringUtils.isBlank("buyer_bankno")) {
//			valid = false;
//			errors.put("buyer_bankno", "계좌번호 누락");
//		}
//		if(StringUtils.isBlank("buyer_bankname")) {
//			valid = false;
//			errors.put("buyer_bankname", "통장명의 누락");
//		}
//		if(StringUtils.isBlank("buyer_zip")) {
//			valid = false;
//			errors.put("buyer_zip", "우편번호 누락");
//		}
//		
//		return valid;
//	}

}
