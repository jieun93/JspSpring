package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.vo.BuyerVO;

@RestController
@RequestMapping(value = "/prod", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OthersReadController {
	@Inject
	IOthersDAO othersDAO;
	
	@GetMapping("getBuyerList.do")
	public List<BuyerVO> getBuyerList(
								@RequestParam(required = false)String prod_lgu){
//		String prod_lgu = req.getParameter("prod_lgu");
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(prod_lgu);
//		ObjectMapper mapper = new ObjectMapper();
		// 마임 위에서 한거 
//		resp.setContentType("application/json;charset=UTF-8");
//		try( // 마쉘링
//			PrintWriter out = resp.getWriter();
//		){
//			mapper.writeValue(out, buyerList);
//		}
		return buyerList;		
	}
	
	@GetMapping("getLprodList.do")
	public List<Map<String, Object>> getLprodList(HttpServletRequest req,
								@RequestParam(required = false) ObjectMapper mapper,
								HttpServletResponse resp) throws Exception {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
//		ObjectMapper mapper = new ObjectMapper(); // 마쉘링 해주는거 
//		resp.setContentType("application/json;charset=UTF-8"); //json으로 마쉘링 할거로 지정
//		try( //마쉘링
//			PrintWriter out = resp.getWriter(); // writer 전송기능이 있는거 
//		){
//			mapper.writeValue(out, lprodList); // out을 통해 lprodlist를 마쉘링해서 내보내겠다는 의미 
//		}
		return lprodList;
	}
}




