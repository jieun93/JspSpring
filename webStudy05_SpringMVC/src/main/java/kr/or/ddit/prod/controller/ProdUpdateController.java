package kr.or.ddit.prod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/{what}")
public class ProdUpdateController {
	
	
	@Inject
	IProdService service;
	
	@GetMapping("form") // 수정요청을 받으면 여기로 온다.
	public String updateForm(@PathVariable(name="what", required = true) String prod_id,
								Model model
							) throws Exception{
//		String prod_id = request.getParameter("what");
//		if(StringUtils.isBlank(prod_id)) {
//			response.sendError(400, "필수 파라미터 누락");
//			return null;
//		}
		ProdVO prod = service.readProd(prod_id); 	// 파라미텅로 받은 값을 주소창에 연결해서
		model.addAttribute("prod", prod);			// 기존의 값이 입력창에 뿌져지도록  해놓은거 
		model.addAttribute("currentAction", "/prod/"+prod_id);
		return "prod/prodForm";
	}

	@PutMapping // 새로 업데이트를 받은거 
	public String update(@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod,
						Errors errors, Model model,
						@RequestParam(required = false) String currentPage,
						RedirectAttributes redirectAttributes
			) throws Exception {
		model.addAttribute("currentAction", "/prod/"+prod.getProd_id());
//		String currentPage = req.getParameter("currentPage");
//		ProdVO prod = new ProdVO();
//		req.setAttribute("prod", prod);  // 새로 입력 받은걸 넣어주는거 
//		try {							// 전체 받은걸 검사
//			BeanUtils.populate(prod, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//			return null;
//		}
		
		
//		if(req instanceof FileUploadRequestWrapper) {
//			PartWrapper imageFile = ((FileUploadRequestWrapper) req).getPartWrapper("prod_image");
//			prod.setProd_image(imageFile);
//		}
//		
		
//		CommonValidator<ProdVO> validator = new CommonValidator<>();
//		Map<String, List<String>> errors = validator.validate(prod, UpdateGroup.class);
//		req.setAttribute("errors", errors);
		
		

//		Map<String, String> errors = new LinkedHashMap<>();
//		req.setAttribute("errors", errors);
//		boolean valid = true;
	
		
//		if(req instanceof FileUploadRequestWrapper) { 
//			PartWrapper imageFile =  ((FileUploadRequestWrapper) req).getPartWrapper("prod_image");
//			if(imageFile != null) {
//				String folderPath = req.getServletContext().getRealPath("/prodImages");
//				File saveFolder = new File(folderPath);
//				if(!saveFolder.exists()) saveFolder.mkdirs();
//				String filename = imageFile.getOriginalFilename();
//				if(StringUtils.isNotBlank(filename)) { // 파일이 선택됨
//					imageFile.saveFile(saveFolder); //저장 끝
//					// 저장명을 꺼내서 db에 넣는거 
//					prod.setProd_img(imageFile.getSavename());
//				}
//			}
//		}
		
		
//		 valid = valid && validate(prod, errors);
		
		String goPage = null;
		String message = null;
		if (!errors.hasErrors()) {
		
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case FAIL:
				message = "쫌따 다시 해보셈.";
				goPage = "prod/prodForm";
				break;
			default: // OK
//				goPage = "redirect:/prod/prodView.do?what=" + prod.getProd_id();
				goPage = "redirect:/prod?page="+currentPage;
				redirectAttributes.addFlashAttribute("lastUpdateProd", prod);
				break;
			}
		} else {
			goPage = "prod/prodForm"; // 검증에 걸림 
		}

		model.addAttribute("message", message);

		return goPage;
	}
	
	
//
//	private boolean validate(ProdVO prod, Map<String, String> errors) {
//		boolean valid = true;
//		if (StringUtils.isBlank(prod.getProd_id())) {
//			valid = false;
//			errors.put("prod_id", "상품코드 누락");
//		}
//		if (StringUtils.isBlank(prod.getProd_name())) {
//			valid = false;
//			errors.put("prod_name", "상품명 누락");
//		}
//		if (StringUtils.isBlank(prod.getProd_lgu())) {
//			valid = false;
//			errors.put("prod_lgu", "분류코드 누락");
//		}
//		if (StringUtils.isBlank(prod.getProd_buyer())) {
//			valid = false;
//			errors.put("prod_buyer", "거래처코드 누락");
//		}
//		if (prod.getProd_cost()==null) {
//			valid = false;
//			errors.put("prod_cost", "구매가 누락");
//		}
//		if (prod.getProd_price()==null) {
//			valid = false;
//			errors.put("prod_price", "판매가 누락");
//		}
//		if (prod.getProd_sale()==null) {
//			valid = false;
//			errors.put("prod_sale", "세일가 누락");
//		}
//		if (StringUtils.isBlank(prod.getProd_outline())) {
//			valid = false;
//			errors.put("prod_outline", "정보 누락");
//		}
//		/* 검증을 하면 안됨 
//		 * if (StringUtils.isBlank(prod.getProd_img())) { valid = false;
//		 * errors.put("prod_img", "상품이미지 누락"); }
//		 */
//		if (prod.getProd_totalstock()==null) {
//			valid = false;
//			errors.put("prod_totalstock", "재고 누락");
//		}
//		if (prod.getProd_properstock()==null) {
//			valid = false;
//			errors.put("prod_properstock", "적정재고 누락");
//		}
//		return valid;
//	}
}
