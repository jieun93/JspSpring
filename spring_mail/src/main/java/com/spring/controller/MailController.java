package com.spring.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.dto.MailVO;
import com.spring.request.MailRequest;

import sun.security.mscapi.KeyStore.MY;


@Controller
@RequestMapping("/mail")
public class MailController {

	@RequestMapping(value = "/mailForm", method = RequestMethod.GET)
	public String mailGet() throws Exception{
		String url ="mail/mailForm";
		return  url;
	}
	
	
	@RequestMapping(value = "/send", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String mailPost(
			MailRequest mail,
			HttpServletRequest request,
			HttpServletResponse response,
			RedirectAttributes rttr) throws Exception{
		
		String url="redirect:/mail/mail/success";
		 
		// 1. 파일 저장 
		Map<String, Object> resultMap  = saveFile(mail, response);
		
		try {
		// 2. 메일 메세지 생성 후  메일 보내기
		 notifier.sendMail(mail, (String)resultMap.get("uploadPath"));
		 
		 // 3. 메일 성공 후  service 호출 db에 내역 저장
//		mailService.sendMessage(mailVO)
		 
		 // 4. 해당 내역을 attribute 저장.
		 rttr.addFlashAttribute("result", resultMap.get("mailVO"));
		 
		}catch(Exception e) {
			url="redirect:/mail/mai/fail";
		}
		
		return url;
	}
	
	
	
	
	private Map<String, Object> saveFile(MailRequest mail, 
										HttpServletRequest request,
										HttpServletResponse response) throws Exception{
		
		Map<String , Object> resultMap =  new HashMap<String, Object>();
		
		MultipartFile attach = mail.getFile();
		String  savePath = "";
		String uploadPath = "";
		
		if(attach != null && !attach.isEmpty()) {
			
			if(attach.getSize()<1024*1024*5) {
				uploadPath = request.getSession().getServletContext().getRealPath("resources/mail_attach");
				
				File file = new File(uploadPath, attach.getOriginalFilename());
				
				if(!file.exists()) {
					file.mkdirs();
				}
				
				attach.transferTo(file);
				
				savePath = file.getAbsolutePath();
				
				MailVO mailVO = mail.toMailVO();
				mailVO.setFile(savePath);
				
				resultMap.put("mailVO", mailVO);
				resultMap.put("uploadPath", savePath);
				
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('용량초과입니다.');</script>");
				out.println("<script>history.go(-1);</script>");
				throw new Exception("용량초과");
			}
		}
				
		return resultMap;
	}
	
	
	
	
}
