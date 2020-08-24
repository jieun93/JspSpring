package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/updateMember.do")
@Controller
public class MemberUpdateController{
	//의존관계 형성
	@Inject
	IMemberService service;
	
	@PostMapping("/member/updateMember.do")
	public String update(@Valid @ModelAttribute("member") MemberVO member, Errors errors, Model model) throws ServletException, IOException {
//		MemberVO member = new MemberVO();
//		model.addAttribute("member", member);
		
//		try {
//			BeanUtils.populate(member, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//			return null ;
//		}
		
//		CommonValidator<MemberVO> validator = new CommonValidator<>();
//		Map<String, List<String>> errors = validator.validate(member);
//		
		// 받은 값을 검증해야 한다. 검증의 경우의 수 확인해야한다. 
//		Map<String, String> errors  = new LinkedHashMap<>();
		model.addAttribute("errors", errors);
		String goPage = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyMember(member);
			
			switch (result) {
			case INVALIDPASSWORD:
				message = "비밀 번호 오류";
				goPage = "member/mypage";
				break;
			case FAIL:
				message = "쫌따 다시 해보셈.";
				goPage = "member/mypage";
				break;
			default: // OK
//				goPage = "/mypage.do";
				goPage="redirect:/";
				break;
			}
		}else {
			goPage = "member/mypage";
		}
		model.addAttribute("message", message);
		
		return goPage;
	}
			
		
//		Map<String, String[]> parameterMap = req.getParameterMap();

	
	// 검증 
//	private boolean validate(MemberVO member, Map<String, String> errors) {
//		boolean valid = true;
//		// 검증 룰
//		if (StringUtils.isBlank(member.getMem_id())) {
//			valid = false;
//			errors.put("mem_id", "회원아이디 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_pass())) {
//			valid = false;
//			errors.put("mem_pass", "비밀번호 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_name())) {
//			valid = false;
//			errors.put("mem_name", "회원명 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_regno1())) {
//			valid = false;
//			errors.put("mem_regno1", "주민번호1 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_regno2())) {
//			valid = false;
//			errors.put("mem_regno2", "주민번호2 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_zip())) {
//			valid = false;
//			errors.put("mem_zip", "우편번호 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_add1())) {
//			valid = false;
//			errors.put("mem_add1", "주소1 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_add2())) {
//			valid = false;
//			errors.put("mem_add2", "주소2 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_hometel())) {
//			valid = false;
//			errors.put("mem_hometel", "집전화번호 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_comtel())) {
//			valid = false;
//			errors.put("mem_comtel", "회사전화번호 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_mail())) {
//			valid = false;
//			errors.put("mem_mail", "이메일 누락");
//		}
//		return valid;
//	}
}
