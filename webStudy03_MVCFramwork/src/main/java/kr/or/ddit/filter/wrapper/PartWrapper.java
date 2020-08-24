package kr.or.ddit.filter.wrapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.Part;

import lombok.Getter;

@Getter
public class PartWrapper {
	private Part part;

	public PartWrapper(Part part) {
		super();
		this.part = part;
		this.contentType = part.getContentType();
		this.filesize = part.getSize();
		this.partName = part.getName();
		this.originalFilename = parseFileName(part);
		this.savename = UUID.randomUUID().toString(); //업로드가 될떄 마다 새로운 이름을 만드는거 
	}
	
	
	private String parseFileName(Part part2) {
		String header = part.getHeader("Content-Disposition"); // header에 있는  Content-Disposition
		int index = header.indexOf("filename"); // 파일네임이 포함되는 위치를 알려줌
		if (index == -1) { // 업로드가 안됨  파일이 선택되지 않거나 문자 파트인 경우 
			return null;
		}
		int secondIndex = header.indexOf("=", index);
		header = header.substring(secondIndex + 1); // 파일명까지 짤라오는거
		return header.replace("\"", ""); // 이름만 짜르는거
	}


	private String contentType;
	private long filesize;
	private String partName;
	private String originalFilename; //원본파일명을 가짐
	private String savename; // 식별할 수 있는 이름을 만드느거??
	
	public void saveFile(File saveFolder) throws IOException {// 저장하는거 
		// 원본 파일명을 그대로 가지고 있다면 ? 해키의 위험성이 있다.
		
		if(originalFilename == null || originalFilename.isEmpty()) return;
			
		File saveFile = new File(saveFolder, savename); // 확장자가 없어짐 ? -- 잠재적인 해킹의 위험성을 막기위해서
		
		try (
				InputStream is = part.getInputStream(); // 입력스트림 받는거, 업로드 처리하는거
				FileOutputStream fos = new FileOutputStream(saveFile);
			){
			byte[] buffer = new byte[1024];
			// 복사하는거
			int pointer = -1;
			while ((pointer = is.read(buffer)) != -1) {
				fos.write(buffer, 0, pointer);
			}
		}
	}
	
}
