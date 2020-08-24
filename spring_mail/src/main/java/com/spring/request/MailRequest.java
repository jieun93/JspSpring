package com.spring.request;

import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.MailVO;

public class MailRequest {
 
	private String sender; 		 // 보내는 사람
	private String receiver;	 // 받는 사람
	private String title;		 // 제목
	private String content;		 // 내용
	
	private MultipartFile file;   		 // 파일 	// db에 넣을거라서 string 6

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "MailRequest [sender=" + sender + ", receiver=" + receiver + ", title=" + title + ", content=" + content
				+ ", file=" + file + "]";
	}
	
	public MailVO toMailVO() {
		
		MailVO vo = new MailVO();
		vo.setContent(content);
		vo.setReceiver(receiver);
		vo.setSender(sender);
		vo.setTitle(title);
		vo.setFile(file.getOriginalFilename());
		
		return vo;
		
	}
	
	
	
	
}
