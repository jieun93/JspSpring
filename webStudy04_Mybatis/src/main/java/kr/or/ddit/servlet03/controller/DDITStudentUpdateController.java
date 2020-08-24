package kr.or.ddit.servlet03.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.servlet03.dao.GradeLicenseDAOImpl;
import kr.or.ddit.servlet03.dao.IGradeLicenseDAO;
import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.ProdVO;

// 회원 정보 수정하는 서블릿 
// 수정 버튼을 누르면  선택한 학생의 정보가 기본으로 깔려야 한다. 
//@WebServlet("/ddit/studentUpdate.do")
@CommandHandler
public class DDITStudentUpdateController {
	// 의존관계 형성 
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
//	IGradeLicenseDAO dao = GradeLicenseDAOImpl.getInstance();
	IGradeLicenseDAO dao = new GradeLicenseDAOImpl();
	
//	???? 이해 안됨???
	//select 태그를 동적으로 돌릴수 있도록 
	
	private void attributeSetting (HttpServletRequest req){
		req.setAttribute("gradeList", dao.selectGradeList());
		req.setAttribute("licenseList", dao.selectLicenseList());
	}
	
	@URIMapping(value="/ddit/studentUpdate.do", method=HttpMethod.GET)
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터 , 검증
		// 수정양식에서 기존으 정보를 보여줘야 한다.
		attributeSetting(request);
		String code = request.getParameter("code");
		if(StringUtils.isBlank(code)) {
			response.sendError(400, "필수파라미터 누락");
			return null;
		}
		// 등록된 값을 받아 오는거 
		DDITStudentVO vo = service.readStudent(code);
		request.setAttribute("student", vo);
//		request.getRequestDispatcher("/WEB-INF/views/ddit/registForm.jsp").forward(request, response);
		return "ddit/registForm";
	
	}
	
	
	@URIMapping(value="/ddit/studentUpdate.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 자격증 정보 수정  --> 기존의 자격증을 삭제하고 새로운 자격증을 올리는거 
		attributeSetting(req);
//		req.setCharacterEncoding("UTF-8");
		DDITStudentVO vo = new DDITStudentVO();
		req.setAttribute("student", vo);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(vo, parameterMap);
		}catch(IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return null;
		}
		// 검증 : 이름 , 생일 , 나이 , 성별
//		Map<String, String> errors = new LinkedHashMap<>();
//		req.setAttribute("errors", errors);
//		boolean valid = validate(vo, errors);
		
		
		
//		//		3. 검증 : 이름, 생일, 나이, 성별
			CommonValidator<DDITStudentVO> validator = new CommonValidator<DDITStudentVO>();
	 		Map<String, List<String>> errors = validator.validate(vo, InsertGroup.class);
			req.setAttribute("errors", errors);
			String goPage = null;
			String message = null;
			if(errors.size()==0) {
//			4-1. 검증 통과
//				저장
				ServiceResult result = service.modifyStudent(vo);
				if(ServiceResult.OK.equals(result)) {
					// 성공 : /ddit/dditStudents.do(Model2) ,  Redirect -> Get (PRG)
					goPage = "redirect:/ddit/studentView.do?code="+vo.getCode();
				}else {
					message = "쫌따 다시 해보셈.";
					goPage = "ddit/registForm";
				}
			}else {
//			4-2. 불통
				// 실패 : 필수 파라미터 누락, 기존 입력 데이터, 메시지
//				registForm 이동( VO, message  공유)
				// scope 를 통해 데이터를 공유.
				goPage = "ddit/registForm";
			}
			req.setAttribute("message", message);
			return goPage;
		}
		
		private boolean validate(DDITStudentVO vo, Map<String, String> errors) {
			boolean valid = true;
			if(StringUtils.isBlank(vo.getCode())) {
				valid = false;
				errors.put("code", "학번 누락");
			}
			if(StringUtils.isBlank(vo.getName())) {
				valid = false;
				errors.put("name", "이름 누락");
			}
			if(vo.getAge() > 40 || vo.getAge() < 15) {
				valid = false;
				errors.put("age", "연령 제한");
			}
			if(StringUtils.isBlank(vo.getGen()) || !vo.getGen().matches("[FM]")) {
				valid = false;
				errors.put("gen", "성별 확인");
			}
			if(StringUtils.isNotBlank(vo.getBirthday())) {
				// 2020-05-13
				try {
					new SimpleDateFormat("yyyy-MM-dd").parse(vo.getBirthday());
				} catch (ParseException e) {
					valid = false;
					errors.put("birthday", "생일 형식 확인");
				}
			}
			return valid;
		}
	}

