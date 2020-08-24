package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod" )
public class ProdInsertController {
	@Inject
	IProdService service;
	
	@GetMapping("form")
	public String form(Model model) {
		model.addAttribute("currentAction", "/prod"); // 현재 페이지와 추가 창으로 연결
		return "prod/prodForm";
	}

	@PostMapping
	public String insert(
						
						@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod,
						BindingResult errors,
						Model model,
						RedirectAttributes redirectAttributes,
						@RequestParam(required = false) String currentPage
						
						) throws IllegalStateException, IOException{
		model.addAttribute("currentAction", "/prod");

		String goPage = null;
		String message = null;
		if (!errors.hasErrors()) {
			 MultipartFile prod_image = prod.getProd_image();
			
				/*
				 * // 에러의 사이가 0 검증 통과 if(prod_image != null) { String folderPath =
				 * application.getRealPath("/prodImages"); File saveFolder = new
				 * File(folderPath); if(!saveFolder.exists()) saveFolder.mkdirs();
				 * prod.saveFile(saveFolder);
				 * 
				 * }
				 */
			
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAIL:
				message = "쫌따 다시 해보셈.";
				goPage = "prod/prodForm";
				break;
			default: // OK
//					goPage = "redirect:/prod/prodView.do?what=" + prod.getProd_id();
				goPage = "redirect:/prod?page="+currentPage;
				redirectAttributes.addFlashAttribute("lastUpdateProd", prod);
				break;
			}
		} else {
			goPage = "prod/prodForm";
		}

		model.addAttribute("message", message);

		return goPage;

		}
	}

