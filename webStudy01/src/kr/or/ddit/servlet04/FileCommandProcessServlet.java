package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class FileCommandProcessServlet
 */
@WebServlet("/ddit/fileProcess.do")
public class FileCommandProcessServlet extends HttpServlet {
	@Override
		public void init(ServletConfig cofig) throws ServletException {
			super.init(cofig);
			this.application = getServletContext();
		}
	private static final long serialVersionUID = 1L;
	private ServletContext application;
	
	private static enum CommandType{
		 COPY((target, destFolder)->{
			 String name = target.getName();
			 File destFile = new File(destFolder, name);
			 Files.copy(Paths.get(target.getPath()),
					 Paths.get(destFile.getPath()),
					 StandardCopyOption.REPLACE_EXISTING);
		 }),
		
		 DELETE((target, destFolder)->{
			 target.delete();
			 
		 }),
		 MOVE((target, destFolder)->{
			 COPY.fileProcess(target, destFolder);
			 DELETE.fileProcess(target, destFolder);
		 });
		 
		private static interface CommandProcessor{
			public void process(File target, File destFolder) throws IOException; //예외가 DOPOST로간다.
		}
		private CommandProcessor processor;
		private CommandType(CommandProcessor processor) {
			this.processor = processor;
		}
		
		public  void fileProcess( File target, File destFolder) throws IOException {//예외가 DOPOST로간다.
			processor.process(target, destFolder);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// 값 받아오는거 
		String command = request.getParameter("command");
		String file = request.getParameter("file");
		String  dest = request.getParameter("dest");
		
		// 검증
		int status = 200; 
		String message = null;
		CommandType commandType = null;
		try {
			// 정상처리
			 commandType =CommandType.valueOf(command);
		}catch(Exception e){
			//처리 불가능 
			status = 400;
			message ="처리할 수 잇는 명령이 아닙니다.";
		}
		
		if(status == 200 && StringUtils.isBlank(file)) {
			// 처리 불가능 
			status = 400;
			message ="대상파일이 존재하지 않습니다.";
		}
		File targetFile = null;
		if(status == 200) {
			targetFile = new File(application.getRealPath(file));
			if(!targetFile.exists() || targetFile.isDirectory()) {
				status = 400;
				message ="대상파일이 아닙니다.";
			}
		}
		// 앞의 검증 다 통과 dest 유무 확인
		File destFolder = null;
		if(status == 200 && StringUtils.isNoneBlank(dest)) {
			destFolder = new File(application.getRealPath(dest));
			if(!destFolder.exists() || destFolder.isFile()) {
				status = 400;
				message ="이동할 위치가 정상적이지 않습니다.";
				
			}
		}
		//status==200 인경우 
		if(status==200) {
			commandType.fileProcess(targetFile, destFolder);
		}
		// 응답데이터를 JSON으로 나감 
			Map<String, Object> resultMap = new LinkedHashMap<>();
			resultMap.put("status", status);
			ObjectMapper mapper = new ObjectMapper();
			try(
					PrintWriter out =response.getWriter();
				){
				mapper.writeValue(out, resultMap);
			}
		
		}
		
	}


