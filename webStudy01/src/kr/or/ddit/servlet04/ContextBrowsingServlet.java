package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

//		1.요청접수
//  /ddit/contextBrowse.do
@WebServlet("/ddit/contextBrowse.do")
public class ContextBrowsingServlet extends HttpServlet {
	
	private ServletContext application;
	
	// 초기 설정 
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파일 목록을 뿌려주느거 
		// 파일의 목록을 가져오는거 
//		2.요청분석 !!!!!!중요!!!!!!
		String accept =req.getHeader("Accept"); // 응답데이터를 html또는 json으로 보낼지 구분해줘야 한다.
		
//		3.contents 생성
		String rootUrl ="/"; // 리스팅의 대상
		String param = req.getParameter("base");
		if(StringUtils.isNotBlank(param)) {
			rootUrl = param;
		}
				
		String realPath = application.getRealPath(rootUrl);
		File rootFolder = new File(realPath);
		if(!rootFolder.exists() || rootFolder.isFile()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"존재하지 않거나 , 파일입니다. ");
			return;
		}
		// 파일이 wrapper가 되는거 
		File[] childArray =  rootFolder.listFiles();
		FileWrapper[] wrapperArray = new FileWrapper[childArray.length];
		for(int i=0; i<childArray.length; i++) {
			wrapperArray[i] = new FileWrapper(childArray[i],application);
		}
//		4. contents 전달(공유)
		req.setAttribute("childArray", wrapperArray);
		//!!!!!!!!!!!!!!!!!!!!!!!중요부분!!!!!!!!!!!!!!!!!!!!!!!
//		비동기식으로 처리하는부분
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
//			1)marshalling
//			[{"name":"ddit","id":"/WEB-INF/views/ddit"}]
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(wrapperArray);
//			2) serialization
			try (
				PrintWriter out = resp.getWriter();
			){
				out.print(json);
			}
			
		}else{
//		5.공유되고 있는 데이터를 어떤 데이터로 출력할것인지 결정
			String goPage = "/WEB-INF/views/ddit/browse.jsp";
			// 이동을 할때 버퍼를 지우지 않고 이동하기 위해서 include를 사용한다.
			req.getRequestDispatcher(goPage).include(req, resp);
		}
	
	}
}
