package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BoardVO implements Serializable{
	
	
	public BoardVO(@NotNull(groups = UpdateGroup.class) Integer bo_no, @NotBlank String bo_pass) {
		super();
		this.bo_no = bo_no;
		this.bo_pass = bo_pass;
	}

	private Integer rnum;
	@NotNull(groups = UpdateGroup.class)
	private Integer bo_no; // 게시판 번호 
	@NotBlank
	private String bo_title; // 게시판 제목 
	@NotBlank
	private String bo_writer; // 게시판 작성자
	private String bo_date;  // 게시글 작성일
	private String bo_content; // 게시판 내용
	@NotBlank
	private String bo_pass; // 게시판 비번
	@NotBlank
	private String bo_ip; // 아이피
	private Integer bo_hit; // 조회수 
	private Integer bo_parent;  // 글 -- 댓글
	
	private MultipartFile[] bo_files;  // 파일 첨부 
	
	public void setBo_files(MultipartFile[] bo_files) {
		this.bo_files = bo_files;
		if(bo_files==null || bo_files.length==0) return;
		attatchList = new ArrayList<>();
		for(MultipartFile boFile : bo_files) {
			if(StringUtils.isBlank(boFile.getOriginalFilename())) continue;
			attatchList.add(new AttatchVO(boFile));
		}
	}
	
	private List<ReplyVO> replyList;
	
	private List<AttatchVO> attatchList;
	
	private int startAttNo;
	
	private int[] deleteAttatches;
}




