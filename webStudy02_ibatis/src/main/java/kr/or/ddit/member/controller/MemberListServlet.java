package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * Servlet implementation class MemberListServlet
 * 회원관리를 위한 controller layer
 * 목록조회 : /member/memberList.do
 * 상세조회 : /member/memberView.do
 * 신규등록 : /member/insertView.do
 * 수정 : /member/updateMemvber.do
 * 탈퇴 :/member/deleteMember.do
 */
@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(MemberListServlet.class); //로거를 받아옴
	
       // service랑 의존관계
		IMemberService service = MemberServiceImpl.getInstance();
		
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 페이징
		/// 토탈레ㅁ코드 ,.커런트 페이지
		String pageParam = request.getParameter("page");
		String searchType =	request.getParameter("searchType");
		String searchWord =	request.getParameter("searchWord");
		// 클라이언트가 어떤 파라미터를 넘기는지 로그로 남겨보자
		
		logger.info("전달된 파라미터 : currentPage : {},searchType :{},serarchWord :{}", pageParam, searchType, searchWord);
		
		SearchVO searchVO = new SearchVO();
		searchVO.setSearchType(searchType);
		searchVO.setSearchWord(searchWord);
		
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3); //페이징 처히마녀서 멤버도 처리하겠다. 
		pagingVO.setSearchVO(searchVO);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readeMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<MemberVO> memberList = service.readMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		request.setAttribute("pagingVO", pagingVO); // JSP로 보내기 위해서 담아둠 
		request.getRequestDispatcher("/WEB-INF/views/member/memberList.jsp").forward(request, response);
		
		
		
		
	}

	
}
