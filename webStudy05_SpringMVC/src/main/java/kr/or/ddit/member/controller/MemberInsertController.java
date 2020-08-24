package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/insertView.do")
@Controller
@RequestMapping("/member/insertView.do")
public class MemberInsertController{
	@Inject	
	IMemberService service;
			
	@GetMapping
	public String doGet(){
				return "member/memberForm";
			}
	
	
	// 가입 버튼을 누르면 전송되는거 
	@PostMapping
	public String doPost(@Valid @ModelAttribute("member") MemberVO member, 
						Errors erros,
						Model model,
						HttpServletRequest req)  {
		
//		// 파라미터를 담기위한 그릇을 만들어야 한다. 
//		MemberVO vo = new MemberVO();
//		// req에 다음 파라미터를 보내줘야 한다. 
//		req.setAttribute("vo", vo);
//		
//		// BeanUTILS --> 모든 파라미터의 값을  한번에 가져오는거
//		try {
//			BeanUtils.populate(vo, req.getParameterMap());
//		} catch (IllegalAccessException |InvocationTargetException e) {
//			resp.sendError(400,"파라미터누락");
//			
//		}
		
//		CommonValidator<MemberVO> validator = new CommonValidator<>();
//		Map<String, List<String>> errors = validator.validate(member);
		
		// 받은 값을 검증해야 한다. 검증의 경우의 수 확인해야한다. 
//		Map<String, String> errors  = new LinkedHashMap<>();
//		model.addAttribute("errors", erros);
//		boolean valid = validate(vo, errors);
		String goPage = null;
//		boolean redirect = false; // 어디론가 가야 하는 길을 만들어줌 
		String message = null;
		// valid를 체크해야 한다. 
		if(!erros.hasErrors()) {
			ServiceResult result = service.createMember(member);
			
			switch(result) {
			case PKDUPLICATED :
				message ="아이디 중복";
				goPage ="member/memberForm";
				break;
			case FAIL:
				message ="다시 시도해보세요";
				goPage = "member/memberForm"; // 디스패치로 이동해야한다. 
				break;
			default : // ok  --> 웰컴페이지  
				goPage="redirect:/";
//				redirect=true; // command를 남겨 놓을 필요가 없어서 
				break;
			// command가 완료되면 삭제 하자, 완료되지 않으면 데이터를 가져가야 한다. 
				
			}
			
		}else { //valid가 false이면   dispatch방식으로 전달 하면서  오류에 대한 정보도 같이 보내야 한다. 
			goPage ="member/memberForm";
		}
		
		// 메세지를 전달하기 위한 scope를 사용
		model.addAttribute("message", message);
	
		return goPage;
		
	}
	  
	  
	
//	private boolean validate(MemberVO vo, Map<String, String> errors) {
//		boolean valid = true;
//		if(StringUtils.isBlank(vo.getMem_id())) {
//			valid = false;
//			errors.put("mem_id","아이디누락");
//		}
//		if(StringUtils.isBlank(vo.getMem_pass())) {
//			valid = false;
//			errors.put("mem_pass","비밀번호누락");
//		}
//		if(StringUtils.isBlank(vo.getMem_name())) {
//			valid = false;
//			errors.put("mem_name","이름누락");
//		}
//		if(StringUtils.isBlank(vo.getMem_add1())) {
//			valid = false;
//			errors.put("mem_add1","주소누락");
//		}
//		if(StringUtils.isBlank(vo.getMem_hp())) {
//			valid = false;
//			errors.put("mem_hp","연락처 누락");
//		}
//		if(StringUtils.isBlank(vo.getMem_mail())) {
//			valid = false;
//			errors.put("mem_mail","메일누락");
//		}
//		if(StringUtils.isBlank(vo.getMem_regno1())) {
//			valid = false;
//			errors.put("mem_regno1","주민번호 누락");
//		}
//		if(StringUtils.isBlank(vo.getMem_zip())) {
//			valid = false;
//			errors.put("mem_zip","우편번호 누락");
//		}
//		return valid;
//	}
	
}
