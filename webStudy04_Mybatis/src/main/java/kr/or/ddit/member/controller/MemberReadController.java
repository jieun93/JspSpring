package kr.or.ddit.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.ModelData;
import kr.or.ddit.mvc.stereotype.RequestParameter;
import kr.or.ddit.mvc.stereotype.URIMapping;
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
@CommandHandler
public class MemberReadController{
	private static Logger logger = LoggerFactory.getLogger(MemberReadController.class);
  
	IMemberService service = MemberServiceImpl.getInstance();
	@URIMapping("/member/memberList.do")
	public String list(
			@RequestParameter(name="page", required = false, defaultValue = "1") int currentPage,   
			@ModelData("searchVO") SearchVO searchVO,
			HttpServletRequest request){
 		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3);
 		pagingVO.setSearchVO(searchVO);
 		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readeMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<MemberVO> memberList = service.readMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		request.setAttribute("pagingVO", pagingVO);
		// logical view name
		String goPage = "member/memberList";
		return goPage;
	}
	
	@URIMapping("/member/memberView.do")
	public String view(@RequestParameter(name="who", required=true) String mem_id, HttpServletRequest req){
		MemberVO member = service.readMember(mem_id);
		req.setAttribute("member", member);
		String goPage = "member/memberView";
		return goPage;
	}
}




