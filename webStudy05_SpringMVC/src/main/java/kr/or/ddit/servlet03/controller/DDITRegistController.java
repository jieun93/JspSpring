package kr.or.ddit.servlet03.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.servlet03.dao.IGradeLicenseDAO;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;


// 학생관리추가 화면

//@WebServlet("/ddit/regist.do")
@Controller
public class DDITRegistController {
	private static final long serialVersionUID = 1L;  
	// 의존 관계필용
	@Inject
	IDDITStudentService service;
//	IGradeLicenseDAO dao = GradeLicenseDAOImpl.getInstance();
	@Inject
	IGradeLicenseDAO dao;
			
	
	private void attributeSetting (Model model){
	
		model.addAttribute("gradeList", dao.selectGradeList());
		model.addAttribute("licenseList", dao.selectLicenseList());

	}
	
// 서블릿을 실행하면 html파일을 보여주도록 주소를 연결한거  1.번으로 실행된다.   
//	 forward로 요청을 받는거   , ui를 연결해주는거 
	@GetMapping(value="/ddit/regist.do")
	public String doGet( HttpServletResponse response) throws ServletException, IOException {
//		attributeSetting(request); // 맵을  스코프에 담는거 
//		request.getRequestDispatcher("/WEB-INF/views/ddit/registForm.jsp").forward(request, response);
		return "ddit/registForm";
	}
	// dao를 사용할 수 있도록 객체 생성 
//	IDDITStudentDAO dao = DDITStudentDAO.getInstance(); //  has a 관계, 의존관계
	// 요청을 받는거  - 내용을 보내는거 
	@PostMapping(value="/ddit/regist.do")
	public String doPost(HttpServletRequest req,
						@Valid @ModelAttribute("vo") DDITStudentVO vo,
						Errors errors,
						Model model,
						HttpSession session,
						HttpServletResponse resp) throws ServletException, IOException {
//		attributeSetting(req); // 중복되지 않는 코드를 만든 상태에서 리퀘스트에 속성데이터를 넣는거 
		//1. 디코딩
//		req.setCharacterEncoding("UTF-8");
		//2. 파라미터 받기 vo 생성 
//		DDITStudentVO vo = new DDITStudentVO();
		model.addAttribute("student", vo); // 속성데이터 넣는거 
		// 정석적으로 vo에 파라미터 값을 받는 방법 		
//		Map<String, String[]> parameterMap  = req.getParameterMap(); //입력받을 값을 map에 넣어주는거 
//		try { //
//			BeanUtils.populate(vo, parameterMap); //BeanUtils의 파라미터에 vo를 채워 넣는다. 
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//			return null;
//		}// 트라이캐치를 통과하면 vo에 변수가 다 담아져 있다. 
		
//		System.out.println(vo);	
//		3. 검증  검증대상(vo) : 이름 ,생일,나이 ,성별
//		CommonValidator<DDITStudentVO> validator = new CommonValidator<DDITStudentVO>();
//		Map<String, String> errors = new LinkedHashMap<>(); // 검증에 문제가 있을시 걸리는곳
		model.addAttribute("errors", errors);
//		boolean valid = validate(vo,errors);
		
		String goPage = null;
//		boolean redirect = false;
		String message = null;
		
		if(!errors.hasErrors()){
			ServiceResult result = service.createStudent(vo);
			
			if(ServiceResult.OK.equals(result)) {// 서비스임플의 결과 갑이 ok 이면 
				// 검증 통과
				goPage = "redirect:ddit/dditStudent.do"; //dditstudentreadservlet // 학생 전체 리스트가 나오는 페이지 이동 
//				redirect=true;
//				HttpSession session = req.getSession();
				session.setAttribute("last",vo); // 마지막학생의 vo를 가져오는거 
			}else{
				message =" 잠시후 다시해보세요";
				goPage ="ddit/registForm"; // 등록하는 화면 
			}
			
		}else {
			// 4-2 검증 불통
			// registForm 이동 , vo, massage(errorMap이 가지고 있다.) ㅡ 검증결과 메세지 
			// *** 스코프를 통해 데이터를 공유 ***중요!! vo
			goPage ="ddit/registForm";
			
		}
			 model.addAttribute("message", message);
			
//			if(redirect) {// redirect 확인하는거 
//				resp.sendRedirect(req.getContextPath()+goPage);
//			}else{
//				req.getRequestDispatcher(goPage).forward(req, resp);
//			}
			return goPage;
	}
	// 검증
//	private boolean validate(DDITStudentVO vo, Map<String, String> errors) {
//		boolean valid = true;// 제일먼저 불린타입의 변수를 만들기 
//		// 검증을 통과하면 기본값으로 valid 에  true로 설정 
//		
//		if(StringUtils.isBlank(vo.getName())) {
//			valid = false;
//			errors.put("name","이름누락");
//		}
//		if(vo.getAge() > 40) {
//			valid = false;
//			errors.put("age", "연령제한 ");
//		}
//		// matches 정규 표현식으로 확인
//		if(StringUtils.isBlank(vo.getGen()) || !vo.getGen().matches("[FM]") ) {
//			valid=false;
//			errors.put("gen", "성별확인 ");
//		}
//		if(StringUtils.isNotBlank(vo.getBirthday())) {
//			// 형태를 어떻게 받을건지 체크 //2020-05-13 
//			// 예외 처리 
//			try {
//				new SimpleDateFormat("yyyy-mm-dd").parse(vo.getBirthday());
//			} catch (ParseException e) {
//				valid = false;
//				errors.put("birthday", "생일 형식 확인 ");
//			}
//		}
//		return valid;	
//	}
}