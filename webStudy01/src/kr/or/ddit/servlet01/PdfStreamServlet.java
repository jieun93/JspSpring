package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




// client 쪽에서 pdfRead.do 라는 요청에 대한 처리 
/**
 * @author PC-20
 * resource 종류 : 자원에 대한 접근 방법에 따라 분류
 * 1. FileSystem Resource   ex) d:/contents/sample.pdf
 * 2. Classpath Resource	ex) kr/or/ddit/sample.pdf
 * 3. Web Resource			ex) http://loaclhost/context/image/sample.jpg
 * 
 */

// web.xml 를 처리하는 방법  annotation servlet 3.0으로 하는 방법  등록하거나 mapping은 한번만 해야 한다.
@WebServlet("/pdfRead.do")
public class PdfStreamServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// pdf 확장자에 맞는 mime 
		resp.setContentType("application/pdf");
		
		PdfStreamServlet.class.getResource("/kr/or/ddit/servlet01/sample.pdf"); // 절대경로 
		
	
		// 변경되지 않는 경로에 대해 접근해야 한다.
		//  build ->classes->kr->or->ddit 에 있는  pdf 파일을 가져와야 한다.
		// class에 먼저 접근해야 한다. 
		// 클래스 패스 리소스  Classpath Resource	ex) kr/or/ddit/sample.pdf
		File pdgFile = new File(PdfStreamServlet.class.getResource("sample.pdf").getPath());
		
		byte[] buffer = new byte[1024];
		int cnt = -1;
		try(
				FileInputStream fis = new FileInputStream(pdgFile);
				OutputStream os = resp.getOutputStream();
				
				){
			while((cnt = fis.read(buffer))!=-1) {
				os.write(buffer, 0, cnt);
			}
		}
	}

}
