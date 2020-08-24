package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
@RequestMapping("/board")
public class BoardReadController {
	@Inject
	IBoardService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<BoardVO> ajaxForlist(
		@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
		SearchVO searchVO, Model model
		){
		list(currentPage, searchVO, model);
		return (PagingVO<BoardVO>) model.asMap().get("pagingVO");
	}
	
	@GetMapping
	public String list( // 게시판 목록 띄우는거 
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			SearchVO searchVO,
			Model model ){
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BoardVO> prodList = service.readBoardList(pagingVO);
		pagingVO.setDataList(prodList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardList"; //게시판 목록 리스트 
	}
	
	@GetMapping("{bo_no}") //게시글 번호를 눌렀을 때
	public String view(@PathVariable(required = true)  int bo_no, Model model ) {
		BoardVO board = service.readBoard(bo_no);
		model.addAttribute("board", board);
		return "board/boardView";
	}
}











