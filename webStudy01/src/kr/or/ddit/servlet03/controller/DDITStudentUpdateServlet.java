package kr.or.ddit.servlet03.controller;

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

import com.fasterxml.jackson.databind.util.BeanUtil;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.servlet03.dao.GradeLicenseDAOImpl;
import kr.or.ddit.servlet03.dao.IGradeLicenseDAO;
import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;

// 회원 정보 수정하는 서블릿 
// 수정 버튼을 누르면  선택한 학생의 정보가 기본으로 깔려야 한다. 
@WebServlet("/ddit/studentUpdate.do")
public class DDITStudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 의존관계 형성 
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
	IGradeLicenseDAO dao = GradeLicenseDAOImpl.getInstance();
	
//	???? 이해 안됨???
	//select 태그를 동적으로 돌릴수 있도록 
	private void attributeSetting (HttpServletRequest req){
		req.setAttribute("gradeList", dao.selectGradeList());
		req.setAttribute("licenseList", dao.selectLicenseList());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터 , 검증
		// 수정양식에서 기존으 정보를 보여줘야 한다.
		attributeSetting(request);
		String code = request.getParameter("code");
		if(StringUtils.isBlank(code)) {
			response.sendError(400, "필수파라미터 누락");
			return;
		}
		// 등록된 값을 받아 오는거 
		DDITStudentVO vo = service.readStudent(code);
		request.setAttribute("student", vo);
		request.getRequestDispatcher("/WEB-INF/views/ddit/registForm.jsp").forward(request, response);
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 자격증 정보 수정  --> 기존의 자격증을 삭제하고 새로운 자격증을 올리는거 
		attributeSetting(req);
		req.setCharacterEncoding("UTF-8");
		DDITStudentVO vo = new DDITStudentVO();
		req.setAttribute("student", vo);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(vo, parameterMap);
		}catch(IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return;
		}
		// 검증 : 이름 , 생일 , 나이 , 성별
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(vo, errors);
		String goPage = null;
		boolean redirect = false;
		String message = null;
		
		if(valid) {
			// 검증통과
			// 저장 
			ServiceResult result = service.modifyStudent(vo);
			
			if(ServiceResult.OK.equals(result)) {
		// 성공: goPage = "/ddit/dditStudent.do";(model 2구조)  redirect -> get
				goPage = "/ddit/studentView.do?code="+vo.getCode();
				redirect = true;
			}else {
				message = "잠시후 다시 해보세요";
				goPage = "/WEB-INF/views/ddit/registForm.jsp";
			}
			
		}else{
			// 불통
			// 실패 : 필수 파라미터 누락, 기존입력데이터 ,메세지 registForm 이동(vo, message 공유0
			// scope를 통해 데이터를 공유 
			goPage = "/WEB-INF/views/ddit/registForm.jsp";
		}
		req.setAttribute("message", message);
		
		
		// 이동하는거 
		if(redirect) {
			resp.sendRedirect(req.getContextPath()+goPage);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}

	// 검증 
	private boolean validate(DDITStudentVO vo, Map<String ,String> errors) {
		boolean valid = true;// 제일먼저 불린타입의 변수를 만들기 
		// 검증을 통과하면 기본값으로 valid 에  true로 설정 
		
		if(StringUtils.isBlank(vo.getCode())) {
			valid =false;
			errors.put("code", "학번 누락");
		}
		
		if(StringUtils.isBlank(vo.getName())) {
			valid = false;
			errors.put("name","이름누락");
		}
		if(vo.getAge() > 40 || vo.getAge() < 15) {
			valid = false;
			errors.put("age", "연령제한 ");
		}
		// matches 정규 표현식으로 확인
		if(StringUtils.isBlank(vo.getGen()) || !vo.getGen().matches("[FM]") ) {
			valid=false;
			errors.put("gen", "성별확인 ");
		}
		if(StringUtils.isNotBlank(vo.getBirthday())) {
			// 형태를 어떻게 받을건지 체크 //2020-05-13 
			// 예외 처리 
			try {
				new SimpleDateFormat("yyyy-mm-dd").parse(vo.getBirthday());
			} catch (ParseException e) {
				valid = false;
				errors.put("birthday", "생일 형식 확인 ");
			}
		}
		return valid;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
