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
import lombok.NonNull;
import lombok.ToString;


@Data  // 게터, 세터
@ToString // 값을 출력하는거랑 비슷 
@NoArgsConstructor // 파라미터가 없는 기본생성자 생성
public class BoardVO implements Serializable{
	
	//  게시물 삭제시  게시물 번호랑 비번을 확인해야 지울 수 있다. 
	public BoardVO(@NonNull Integer bo_no, @NotBlank String bo_pass) {
		super();
		this.bo_no = bo_no;
		this.bo_pass = bo_pass;
	}
	
	
	private Integer rnum; // 순번
	@NotNull(groups = UpdateGroup.class) // 업데이트할떄  게시번호 필수 
	private Integer bo_no; 
	@NotBlank
	private String bo_title;
	@NotBlank
	private String bo_writer;
	@NotBlank
	private String bo_date;
	@NotBlank
	private String bo_content;
	@NotBlank
	private String bo_pass;
	@NotBlank
	private String bo_ip;
	private Integer bo_hit;
	private Integer bo_parent;
	
	private MultipartFile[] bo_files; //MultipartFile 라이브러리  
	
	// 게시글에서 파일을 등록할떄   vo에 파일을 담는거 
	public void setBo_files(MultipartFile[] bo_files) {
		this.bo_files = bo_files;
		
		if(bo_files==null || bo_files.length==0) return;
		// 파일이 없으면 return 파일이 첨부된게 없이 게시물 등록;
		
		attatchList = new ArrayList<>(); 
		//attatch안에 포함된 vo 변수에 값을 넣을  새로운 박스를 만들어준다.
		
		for(MultipartFile boFile : bo_files) { // 새로 파일을 등록하는 만큼 for문을 돌린다.
			if(StringUtils.isBlank(boFile.getOriginalFilename())) continue;
			//  파일이 담긴 boFile의 오리지널파일네임이 비어 있으면  다시 for문으로 
			attatchList.add(new AttachVO(boFile));
			// 새로운 박스에  새로운 파일을 추가해준다. 
		}
		
	}
	
	
	private List<ReplyVO> replyList;
	private List<AttachVO> attatchList;
	private int startAttNo;
	private int[] deleteAttatches;
	
	
	
	
	
}
