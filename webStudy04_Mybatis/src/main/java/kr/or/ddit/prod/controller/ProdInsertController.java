package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;
import kr.or.ddit.filter.wrapper.PartWrapper;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdInsertController {

	IProdService service = new ProdServiceImpl();

	@URIMapping("/prod/prodInsert.do")
	public String form(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("currentAction", "/prod/prodInsert.do"); // 현재 페이지와 추가 창으로 연결
		return "prod/prodForm";
	}

	@URIMapping(value="/prod/prodInsert.do", method=HttpMethod.POST)
	public String insert(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		req.setAttribute("currentAction", "/prod/prodInsert.do");
		String currentPage = req.getParameter("currentPage");
		ProdVO prod = new ProdVO(); 
		req.setAttribute("prod", prod); // prod에 입력 받은 값을 넣어준다. 
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return null;
		}
		
		//  파일을 업로드를 받고 처리 후 저장명을 prod_img에 전달해줘야 한다.
//		Map<String, String> errors = new LinkedHashMap<>();
//		req.setAttribute("errors", errors);
//		boolean valid = true;
		if(req instanceof FileUploadRequestWrapper) { 
			PartWrapper imageFile =  ((FileUploadRequestWrapper) req).getPartWrapper("prod_image");
			prod.setProd_image(imageFile);
			
		}
		
		CommonValidator<ProdVO> validator = new CommonValidator<>();
		Map<String, List<String>> errors = validator.validate(prod, InsertGroup.class);
		req.setAttribute("errors", errors);
		
//		valid = valid && validate(prod, errors);
		
		String goPage = null;
		String message = null;
		if (errors.size() == 0) {
			 PartWrapper prod_image = prod.getProd_image();
			
			 // 에러의 사이가 0 검증 통과 
			if(prod_image != null) {
				String folderPath = req.getServletContext().getRealPath("/prodImages");
				File saveFolder = new File(folderPath);
				if(!saveFolder.exists()) saveFolder.mkdirs();
//				String filename = prod_image.getOriginalFilename();
//				if(StringUtils.isNotBlank(filename)) { // 파일이 선택됨
					prod_image.saveFile(saveFolder); //저장 끝
					// 저장명을 꺼내서 db에 넣는거 
					prod.setProd_img(prod_image.getSavename());
//				}
			}
			
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAIL:
				message = "쫌따 다시 해보셈.";
				goPage = "prod/prodForm";
				break;
			default: // OK
//					goPage = "redirect:/prod/prodView.do?what=" + prod.getProd_id();
				goPage = "redirect:/prod/prodList.do?page="+currentPage;
				req.getSession().setAttribute("lastUpdateProd", prod);
				break;
			}
		} else {
			goPage = "prod/prodForm";
		}

		req.setAttribute("message", message);

		return goPage;

		}
	}
//
//	private void fileProcess(HttpServletRequest req, ProdVO prod) throws IOException, ServletException {
//		
//		
//	}

//	private boolean validate(ProdVO prod, Map<String, String> errors) {
//		boolean valid = true;
////		if (StringUtils.isBlank(prod.getProd_id())) {
////			valid = false;
////			errors.put("prod_id", "상품코드 누락");
////		}
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
//		if (StringUtils.isBlank(prod.getProd_img())) {
//			valid = false;
//			errors.put("prod_img", "상품이미지 누락");
//		}
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
