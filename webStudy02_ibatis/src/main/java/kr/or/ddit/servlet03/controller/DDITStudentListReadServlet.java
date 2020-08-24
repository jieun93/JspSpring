package kr.or.ddit.servlet03.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * Servlet implementation class DDITStudentReadServlet
 * 
 *  목록 조회 
 */
@WebServlet("/ddit/dditStudent.do")
public class DDITStudentListReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// dao를 통해서 데이터를 읽는거 
	// 결합력 낮은거 응집고 높은거 
	
	 IDDITStudentService service = DDITStudentServiceImpl.getInstance();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pageParam = request.getParameter("page");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		SearchVO searchVO = new SearchVO();
		searchVO.setSearchType(searchType);
		searchVO.setSearchWord(searchWord);
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage =Integer.parseInt(pageParam);
		}
		
		
		PagingVO<DDITStudentVO> pagingVO = new PagingVO<>(5,3);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readeStudentCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		
		List<DDITStudentVO> allStudents = service.readStudentList(pagingVO);
		pagingVO.setDataList(allStudents);
		request.setAttribute("pagingVO", pagingVO);
//		dao.selectStudent("s001");
		
		// 서블릿에서 jsp에서 이동하는 과정에서 필요한거 전달  
		request.getRequestDispatcher("/WEB-INF/views/ddit/dditStudent.jsp").forward(request, response);
		
	}

}
