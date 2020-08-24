package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.MemberVO;
import oracle.jdbc.proxy.annotation.GetProxy;

@WebServlet("/member/updateMember.do")
public class MemberUpdateServlet extends HttpServlet{
	//의존관계 형성
	IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// session을 가져오는거 
	HttpSession session = req.getSession();
	 String name = (String) session.getAttribute("authUser"); // 파라미터로 받는거를 세선에서 가져오는거 ?
	
	if(StringUtils.isBlank(name)){
		resp.sendError(400, "필수 파라미터 누락 ");
		return;
	}
	
	 MemberVO vo = service.readMember(name);
	 req.setAttribute("member", vo);
	 req.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp");
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		
//		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return;
		}
		
		// 검증을 유징하는거??
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		String goPage = null;
		boolean redirect = false;
		String message = null;
		
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			
			switch (result) {
			case INVALIDPASSWORD:
				message = "비밀 번호 오류";
				goPage = "/WEB-INF/views/member/mypage.jsp";
				break;
			case FAIL:
				message = "쫌따 다시 해보셈.";
				goPage = "/WEB-INF/views/member/mypage.jsp";
				break;
			default: // OK
				goPage = "/mypage.do";
				redirect = true;
				break;
			}
		}else {
			goPage = "/WEB-INF/views/member/mypage.jsp";
		}
		req.setAttribute("message", message);
		
		if(redirect) {
			resp.sendRedirect(req.getContextPath()+goPage);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
		
		
				
	}
	
	// 검증 
		private boolean validate(MemberVO vo, Map<String ,String> errors) {
			boolean valid = true;// 제일먼저 불린타입의 변수를 만들기 
			// 검증을 통과하면 기본값으로 valid 에  true로 설정 
			
			if(StringUtils.isBlank(vo.getMem_name())) {
				valid =false;
				errors.put("mem_name", "회원명 누락");
			}
			
			if(StringUtils.isBlank(vo.getMem_regno1())) {
				valid = false;
				errors.put("mem_regno1","주민번호누락");
			}
			if(StringUtils.isBlank(vo.getMem_hp())) {
				valid = false;
				errors.put("mem_hp","연락처누락");
			}
			if(StringUtils.isBlank(vo.getMem_mail())) {
				valid = false;
				errors.put("mem_mail","메일누락");
			}
			if(StringUtils.isBlank(vo.getMem_add1())) {
				valid = false;
				errors.put("mem_add1","주소누락");
			}
			
			return valid;	
		}
}
