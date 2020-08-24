package kr.or.ddit.member.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.MemberVO;

@RestController   //자원만 가지고 하겠다
@RequestMapping("/member")
public class MemberRestController {
	@GetMapping 
	public List<MemberVO> list() { //회원의 목록을 필요한 사람이 요청
		return null;
		
	}
	@GetMapping("a001") //  /member/a001
	public MemberVO oneMember() { // 한 사람의 정보를 필요한 사람이 요청
		return null;
	}
	
	@PostMapping
	public void insert(@RequestBody MemberVO member){
		
	}
	
	@PutMapping
	public void update(@RequestBody MemberVO member) {
		
	}
	
	@DeleteMapping("a001")
	public void delete() { // /member/a001
		
	}
	
}
