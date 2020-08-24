package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.Constants;



/**
 * @author PC-20
 * MIME (Multi 
 * : 전송 데이터 (CONTECT)의 종류 형태 타입에 대한 정의 문자열.
 * : main_type/sub_type;charset=encoding ex)text/html, text/plain
 */
public class SelectedFileStreamingServlet extends HttpServlet{
	
	 private File folder;
	 private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 하나의 어플리케이션 안에서는 싱글톤 객체이다.  web.xml안에서는 servler객체는  싱글톤이다. 
		application = config.getServletContext();
		System.out.println(this.getClass().getSimpleName()+"에서 확인 :"+application.hashCode());
		String contentPath = application.getInitParameter("contentsPath");
		if(contentPath == null || contentPath.isEmpty())
		throw new ServletException("content path 위치 설정 오류");
		folder = new File(contentPath);
	}
	
	@Override				
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String filename = req.getParameter("filename");// 요청파라미터를 찾아야 한다. 
		if(filename == null || filename.isEmpty()) {
			resp.sendError(400, "필수파라미터 누락"); // 400은 로직에 의 해 개발자가 발생하는 에러
			return;
		}
		String selectedJson = null;
		Cookie[] cookies = req.getCookies(); // 선택한 파일이 한개가 아니므로 배열로 받는다.
		if(cookies !=null){ // 쿠키, 선택한 파일이 있으면
			for(Cookie tmp : cookies) { // 선택한 파일만큼 for문을 돌려준다.
				if(Constants.SELECTEDFILE.equals(tmp.getName())) { // constants의 seletedfile이 선택된 쿠키의 이름과 동일하면
					selectedJson =	URLDecoder.decode(tmp.getValue(), "UTF-8"); // 선택한 파일을  value를 decode해준다.
					// decode 는  인코딩의 반대 
				}
			}
		}
		// unmashalling 해야 함  
		String[] newArray = null;
		ObjectMapper mapper = new ObjectMapper();
		if(selectedJson != null) {
			
			String[] savedArray = mapper.readValue(selectedJson, String[].class);
			 newArray = new String[savedArray.length+1];
			System.arraycopy(savedArray, 0, newArray, 0, savedArray.length);
		}else {
			newArray = new  String[1];
		}
		
		// 쿠키 설정하는거 예시 이틀후에도 유지가 될 수 있도록 
		newArray[newArray.length-1] = filename;
		selectedJson =  mapper.writeValueAsString(newArray);
		//writeValueAsString : String타입으로 변환할 대상 
		
		String encodeFileName =  URLEncoder.encode(filename,"UTF-8"); //특수문자
		Cookie cookie = new Cookie(Constants.SELECTEDFILE,encodeFileName);
		cookie.setPath(req.getContextPath()); 
		cookie.setMaxAge(60*60*48);
		resp.addCookie(cookie);
		
		
		String mime = application.getMimeType(filename);
		
		// mime 설정하는건 출력 스트림보다 먼저 설정해야 한다.
		resp.setContentType(mime);
		
		
		
		
		// 데이터의 소스를 java의 객체 형태로 만들어줘야 한다.
		// 파일 시스템 리소스 
		//  FileSystem Resource   ex) d:/contents/sample.pdf
		 File imgFile = new File(folder,filename);
		 byte[] buffer = new byte[1024];
		 int cnt = -1;
		 // try 를 써주기 
		 try(
				 // 소스를 읽어올 stream를 가져오기 
				 FileInputStream fis = new FileInputStream(imgFile);
				 //출력 스트림
				 OutputStream os = resp.getOutputStream();
		 ){
			 while((cnt = fis.read(buffer))!=-1) {
				os.write(buffer, 0, cnt); 
			 } 
		 }// -1일 될때 까지 
		 //  mime을 사용하려면  jpg의 확장자를 알아야 한다. 
		 // mime 을 응답데이터(resp)에 써야 한다. 
	}

	private String findMime(String filename) {
		int lastIndex = filename.lastIndexOf(".");
		String extension = filename.substring(lastIndex+1);
		String mime = null;
		// 두개의 파일종류만 가져올 수 있다. 
		switch (extension) {
		case "pdf":
			mime = "application/pdf";
			break;
		case "jpg":
			mime = "image/jpeg";
			break;

		default:
			break;
		}
		return mime;
	}
}

