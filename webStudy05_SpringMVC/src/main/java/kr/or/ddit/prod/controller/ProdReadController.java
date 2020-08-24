package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod")
public class ProdReadController {
	@Inject
	IProdService service;
	
	
	@GetMapping(produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public PagingVO<ProdVO> ajaxList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			ProdVO detailSearch,
			Model model
			){
		list(currentPage, detailSearch, model);
		PagingVO<ProdVO>  pagingVO = (PagingVO<ProdVO>) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	
	@GetMapping
	public String list(	@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
//						@RequestParam(required = false) String pageParam,
//						@RequestParam(required = false) String prod_lgu,
//						@RequestParam(required = false) String prod_buyer,
						ProdVO detailSearch,
						Model model
						){
//		String pageParam = request.getParameter("page");
// 		String prod_lgu = request.getParameter("prod_lgu");
// 		String prod_buyer = request.getParameter("prod_buyer");
// 		ProdVO detailSearch = new ProdVO();
// 		detailSearch.setProd_buyer(prod_buyer);
// 		detailSearch.setProd_lgu(prod_lgu);
 		
// 		int currentPage = 1;
// 		if(StringUtils.isNumeric(pageParam)) {
// 			currentPage = Integer.parseInt(pageParam);
// 		}
 		PagingVO<ProdVO> pagingVO = new PagingVO<>(7, 5);
 		pagingVO.setDetailSearch(detailSearch); // 상세 검색 조건
 		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ProdVO> prodList = service.readProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
//		if(request.getHeader("Accept").contains("json")) {
//			response.setContentType("application/json;charset=UTF-8");
//			ObjectMapper mapper = new ObjectMapper();
//			try(
//				PrintWriter out = response.getWriter();	
//			){
//				mapper.writeValue(out, pagingVO);
//			}
//			return null;
//		}else {
			model.addAttribute("pagingVO", pagingVO);
			// logical view name
			return "prod/prodList";
//		}
	}
	
	// 하나 선택 했을 때 목록 띄우주는거 
	@RequestMapping("{what}")
	public String view( // require 속성 기본값은 ture  필수 파라미터가 아니면 false로 해준다. 
						@PathVariable(name="what", required = true) String prod_id,Model model){
//		String prod_id = request.getParameter("what");
//		if(StringUtils.isBlank(prod_id)) {
//			response.sendError(400,"필수파라미터 누락 ");
//			return null;
//		}
		
		ProdVO prod = service.readProd(prod_id);
		model.addAttribute("prod", prod);
		String goPage = "/prod/prodView";
		return goPage;
		
	}
	
	
	
}






