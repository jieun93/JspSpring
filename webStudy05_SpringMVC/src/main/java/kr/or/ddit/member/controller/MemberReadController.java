package kr.or.ddit.member.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;


/**
 * 회원 관리를 위한 controller layer
 * 목록 조회 : /member/memberList.do(*)
 * 상세 조회 : /member/memberView.do
 * 신규 등록 : /member/insertMember.do
 * 수정 : /member/updateMember.do
 * 탈퇴 : /member/deleteMember.do
 *
 */
@Controller
@RequestMapping("/member")
public class MemberReadController{
	
	private static Logger logger = LoggerFactory.getLogger(MemberReadController.class);
	
	@Inject
	private  IMemberService service;
//	IMemberService service = MemberServiceImpl.getInstance();
//	@URIMapping("/member/memberList.do")
	
	@GetMapping("memberList.do")
	public String list(
			
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,   
			@ModelAttribute("searchVO") SearchVO searchVO, 
			HttpServletRequest request,
			HttpServletResponse response, Model model){
		
 		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3);
 		pagingVO.setSearchVO(searchVO);
 		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readeMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<MemberVO> memberList = service.readMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		model.addAttribute("pagingVO", pagingVO);
		// logical view name
		String goPage = "member/memberList";
		return goPage;
	}
	
//	@URIMapping("/member/memberView.do")
	@GetMapping("memberView.do")
	public String view(@RequestParam(name="who", required=true) String mem_id, Model model){
		MemberVO member = service.readMember(mem_id);
		model.addAttribute("member", member);
		String goPage = "/member/memberView";
		return goPage;
	}
}




