package kr.or.ddit.servlet07;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class FileUploadServlet extends HttpServlet {
	ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		input name = "paramName"

		req.setCharacterEncoding("UTF-8");
		String uploader = req.getParameter("uploader");

		Collection<Part> parts = req.getParts();
		Iterator<Part> it = parts.iterator();
		Map<String, List<File>> partMap = new HashMap<>();

		while (it.hasNext()) {
			Part part = (Part) it.next();
			// 파일인지 문자인지 구분
			String header = part.getHeader("Content-Disposition");
			if (header == null || header.indexOf("filename") == -1) {
				continue;
			}
			String partName = part.getName();
			List<File> fileList = partMap.get(partName);
			if (fileList == null) {
				fileList = new ArrayList<>();
				partMap.put(partName, fileList);
			}
			try {
				File saveFile = fileUploadProcess(part);
				if (saveFile != null) {
					fileList.add(saveFile);
				}
			} catch (RuntimeException e) {
				resp.sendError(400, e.getMessage());
				return;
			}

		}

		System.out.printf("uploader : %s\n", uploader);
		List<File> fileList = partMap.get("uploadFile");
		for(File saveFile : fileList) {
			System.out.printf("uploadFile : %s, exist : %s \n",  saveFile.getName(), saveFile.exists()); // 원본파일명을 꺼내는거 
			
		}
		req.getSession().setAttribute("fileList", fileList); // jsp에 갈때 리다이렉션
		resp.sendRedirect(req.getContextPath()+"/11/fileUploadForm.jsp");

	}

//		Part uploadFilePart = req.getPart("uploadFile");
	private File fileUploadProcess(Part uploadFilePart) throws IOException {
		
		String fileMime = uploadFilePart.getContentType();
		// 이미지가 아닌 다른걸 업로드 했을 경우
		if (fileMime == null || !fileMime.startsWith("image/")) {
			throw new RuntimeException("이미지가 아님");
		}
		// 파트에는 원본이름을 가져오는 메소드가 없다. 
		long filesize = uploadFilePart.getSize(); // 업로드 가능한 파일의 크기
		String partName = uploadFilePart.getName(); //
//		Content-Disposition: form-data; name="uploadFile"; filename="ìº¡ì².PNG"
		String header = uploadFilePart.getHeader("Content-Disposition");
		int index = header.indexOf("filename"); // 파일네임이 포함되는 위치를 알려줌
		if (index == -1) { // 업로드가 안됨
			System.out.println("파일이 선택되지 않았음");
			return null;
		}
		int secondIndex = header.indexOf("=", index);
		header = header.substring(secondIndex + 1); // 파일명까지 짤라오는거
		String fileName = header.replace("\"", ""); // 이름만 짜르는거
		String savedFolderUrl = "/images"; // 저장할곳
		String savedFolderPath = application.getRealPath(savedFolderUrl);
		File savedFolder = new File(savedFolderPath);
		
		File saveFile = new File(savedFolder, fileName); //복사하는거 

		try (InputStream is = uploadFilePart.getInputStream(); // 입력스트림 받는거, 업로드 처리하는거
				FileOutputStream fos = new FileOutputStream(saveFile);) {
			byte[] buffer = new byte[1024];
			// 복사하는거
			int pointer = -1;
			while ((pointer = is.read(buffer)) != -1) {
				fos.write(buffer, 0, pointer);
			}
		}
		return saveFile;

	}

}
