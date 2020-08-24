package kr.or.ddit.buyer.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.BuyerVO;

/**
 * Servlet implementation class UpdateBuyerController
 */
//@WebServlet("/buyer/updateBuyer.do")
@Controller
@RequestMapping(value = "/buyer/updateBuyer.do", produces ="application/json;charset=UTF-8" )
public class UpdateBuyerController{
	private static final long serialVersionUID = 1L;
    @Inject
	IBuyerService service;
	
	
	
	@GetMapping
	public String doGet(
			@Valid @RequestParam String buyer_id,
			Errors error,
			@ModelAttribute("vo") BuyerVO vo,
		
			Model model) {
			
//		request.setCharacterEncoding("UTF-8");
		
//		String buyer_id = request.getParameter("buyer_id");
//		if(StringUtils.isBlank(buyer_id)) {
//			response.sendError(400, "필수파라미터 누락");
//			return null;
//		}
		
		// 기존의 정보를 뿌려주는거 필요함 
//		BuyerVO vo = service.readBuyer(buyer_id);
		System.out.println(vo);
		model.addAttribute("buyer_id", vo);
			
		return "buyer/BuyerUpdate";
		
	}
	
	
	
  	@PostMapping
	public String doPost(@Valid @ModelAttribute("buyer") BuyerVO buyer,
							Errors errors,
							Model model
			) {
//  		request.setCharacterEncoding("UTF-8");
//		BuyerVO buyer = new BuyerVO();
		model.addAttribute("buyer", buyer); // 수정한 내용을 jsp에 넘겨주는거
		
//		try {
//			BeanUtils.populate(buyer, request.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//		 response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//		 return null;
//		}
		
//		Map<String, String> errors = new LinkedHashMap<>();
		model.addAttribute("errors", errors);
//		boolean valid = validate(buyer, errors);
		String goPage = null;
//		boolean redirect = false;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyBuyer(buyer);
			switch(result) {
			
			case FAIL:
				message = "다시 시도해주세요";
				goPage = "buyer/BuyerUpdate";
				break;
			default :
				goPage = "redirect:/buyer/buyerList.do";
				break;
			}
		}else {
			goPage = "buyer/BuyerUpdate";
			
		}
		model.addAttribute("message", message);
		return goPage;
		
		
	}
	
	

//	private boolean validate(BuyerVO buyer, Map<String, String> errors) {
//		boolean valid = true;
//		if(StringUtils.isBlank(buyer.getBuyer_id())) {
//			valid = false;
//			errors.put("buyer_id", "거래처 아이디 누락");
//		}
//		if(StringUtils.isBlank(buyer.getBuyer_name())) {
//			valid = false;
//			errors.put("buyer_name", "거래처명 누락");
//		}
//		if(StringUtils.isBlank(buyer.getBuyer_lgu())) {
//			valid = false;
//			errors.put("buyer_lgu", "Buyer_lgu 누락");
//		}
//		if(StringUtils.isBlank(buyer.getBuyer_fax())) {
//			valid = false;
//			errors.put("buyer_fax", "Buyer_fax누락");
//		}
//		if(StringUtils.isBlank(buyer.getBuyer_mail())) {
//			valid = false;
//			errors.put("buyer_mail", "mail 누락");
//		}
//		if(StringUtils.isBlank(buyer.getBuyer_add1())) {
//			valid= false;
//			errors.put("buyer_add1", "add1 누락");
//		}
//		if(StringUtils.isBlank(buyer.getBuyer_add2())) {
//			valid= false;
//			errors.put("buyer_add2", "add2 누락");
//		}
//		return valid;
//	}
//	
}
