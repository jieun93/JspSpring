package kr.or.ddit.Albamon.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.Albamon.enums.ServiceResult;
import kr.or.ddit.Albamon.service.IAlbaService;

import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.AlbaVO;


@Controller
@RequestMapping("/alba/albaUpdate.do")
public class AlbaInsertController {
  
  @Inject
  IAlbaService service;
  @ModelAttribute("currentAction")
	public String currentAction() {
		return "/alba";
	}
	
	@GetMapping("form")
	public String form(@ModelAttribute("alba") AlbaVO alba) {
		return "/alba/albaForm.jsp";
	}
	
	@PostMapping
	public String insert(
		@Validated(InsertGroup.class) @ModelAttribute("alba") AlbaVO alba, BindingResult errors,
		Model model
	) {
		String goPage = null;
		String message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createAlba(alba);
			switch (result) {
			case FAIL:
				message = "쫌따 다시 해보셈.";
				goPage = "/alba/albaForm";
				break;
			default: // OK
				goPage = "redirect:/alba/";
				break;
			}
		} else {
			goPage = "alba/albaForm";
		}

		model.addAttribute("message", message);

		return goPage;
	}
  
  
  
}
