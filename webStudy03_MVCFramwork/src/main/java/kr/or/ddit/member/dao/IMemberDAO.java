package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author PC-20
 * 회원 관리(CRUD)를 위한 Persistence Layer
 * 
 */
public interface IMemberDAO {
	/**
	 * 신규등록
	 * @param member
	 * @return 등록된 회원수 
	 */
	public int insertMember(MemberVO member);  // insert 리턴 타입 오브젝트 
	
	/**
	 * 회원정보 상세 조회
	 * @param mem_id
	 * @return 존재하지 않으면 , null 반환
	 */
	public MemberVO selectMember(String mem_id);
	
	/**
	 * 검색 조건에 맞는 회원수 조회
	 * @param pagingVO TODO
	 * @return
	 */
	public int selelctMemberCount(PagingVO pagingVO);
	
	/**
	 * 검색 조건에 맞는 회원목록 조회
	 * @param pagingVO TODO
	 * @return
	 */
	public List<MemberVO> selectMemberList(PagingVO pagingVO);
	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return
	 */
	public int updateMember(MemberVO member);  
	
	/**
	 * 회원 정보 삭제 
	 * @param mem_id
	 * @return
	 */
	public int deleteMember(String mem_id);
	
	
}
