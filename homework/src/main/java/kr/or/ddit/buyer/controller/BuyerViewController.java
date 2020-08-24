package kr.or.ddit.buyer.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.IBuyerService;



// **********************여기 다시 봐야 함  코드가 다름**************************
//@WebServlet("/buyer/buyerView.do")
@Controller
public class BuyerViewController{
	//의존관계형성
	@Inject
	IBuyerService service;
	
	@GetMapping("/buyer/buyerView.do")
	public String BuyerView(@Valid @RequestParam(required = false) String buyer_id,
				@Valid int status
			){
		// parameter받는거 
		// 거래처 목록에서 선택된걸 파라미터로 받는다.
//		String buyer_id = req.getParameter("buyer_id"); // 파라미터로 선택된 id
					
//		int status =  200;
		String gopage = null;
		
//		if(StringUtils.isBlank(buyer_id)) { // 선택되지 않으면 
//			status = 400;
//		}else {
//			try {
//				// 선택되면 id를 읽어와서 요청을 보낸다. 
//				BuyerVO buyer =  service.readBuyer(buyer_id);
//				
//				System.out.println(buyer);
//				req.setAttribute("buyer", buyer);
//				gopage = "buyer/BuyerView"; // 선택된 id의  물품목록 보여주는거 
//			}catch(DataNotFoundException e) {
//				// 정상처리 불가능  클라이언트가 잘못된 요청을 함
//				status = 400;
//			}
//		}
		
//		if(status != 200 ) {
//			// 에러 
//			
//			resp.sendError(status,"ㅇㅇ");
//			return null;
//		}else {
//			
			return gopage;
//		}
	}
}
	

