package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @author PC-20
 * MIME (Multi 
 * : 전송 데이터 (CONTECT)의 종류 형태 타입에 대한 정의 문자열.
 * : main_type/sub_type;charset=encoding ex)text/html, text/plain
 */
public class MimeDescriptionServlet extends HttpServlet{
	String contentPath = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		contentPath = config.getInitParameter("contentPath");
		if(contentPath == null || contentPath.isEmpty())
		throw new ServletException("content path 위치 설정 오류");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// mime 설정하는건 출력 스트림보다 먼저 설정해야 한다.
		resp.setContentType("image/jpeg");
		
		// 데이터의 소스를 java의 객체 형태로 만들어줘야 한다.
		// 파일 시스템 리소스 
		//  FileSystem Resource   ex) d:/contents/sample.pdf
		 File imgFile = new File(contentPath,"d:/contents/B000210202001301017213.jpg");
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
}

