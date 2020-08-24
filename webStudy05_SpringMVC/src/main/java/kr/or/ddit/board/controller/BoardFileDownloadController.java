package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.AttachVO;


@Controller
public class BoardFileDownloadController {

	
	@Inject
	IBoardService service;
	
	
	@GetMapping("/board/file/{att_no}")
//	@ResponseBody // 응답데이터가 직접 만들어진다??
	public String download(@PathVariable(required = true) int att_no,Model model){
		AttachVO attatch =  service.downloadAttatch(att_no);		
//		String savename = attatch.getAtt_savename();
//		context.getResource(attatchPath+"/"+savename);
		
		
		model.addAttribute("attatch", attatch);
		
		return "downloadView";
		
		
	
	}
}
