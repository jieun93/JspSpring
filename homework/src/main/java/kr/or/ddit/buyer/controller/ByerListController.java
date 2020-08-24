package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;



/**
 * 거래처 목록 리트스 보여주는곳
 * Servlet implementation class buyerList
 */
//@WebServlet("/buyer/buyerList.do")
@Controller
public class ByerListController{
	private static final long serialVersionUID = 1L;
	
    @Inject
	IBuyerService service;
	
	@GetMapping(value="/buyer/buyerList.do")
	public String BuyerList(
			@RequestParam("page") String pageParam,
			@RequestParam("searchType") String searchType,
			@RequestParam("searchWord") String searchWord,
			@ModelAttribute("serachVO") SearchVO searchVO,
			Model model
			
			) throws ServletException, IOException {
		
//		String pageParam = request.getParameter("page");
//		String searchType =	request.getParameter("searchType");
//		String searchWord =	request.getParameter("searchWord");

//		SearchVO searchVO = new SearchVO();
		searchVO.setSearchType(searchType);
		searchVO.setSearchWord(searchWord);
		
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(5, 3); //페이징 처히마녀서 멤버도 처리하겠다. 
		pagingVO.setSearchVO(searchVO);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readBuyerCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BuyerVO> buyerList = service.readBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		model.addAttribute("pagingVO", pagingVO); // JSP로 보내기 위해서 담아둠 
//		request.getRequestDispatcher("/WEB-INF/views/buyer/BuyerList.jsp").forward(request, response);
		return "buyer/BuyerList";
	}

	

}
