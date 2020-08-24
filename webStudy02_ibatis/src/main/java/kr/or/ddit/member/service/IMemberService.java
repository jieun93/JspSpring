package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원 관리를 위한 Business Logic Layer
 * @author PC-20
 *
 */
public interface IMemberService {
	/**
	 * 신규회원 등록
	 * @param member
	 * @return PKDUPLICATED,OK, FAIL,
	 */
	public ServiceResult createMember(MemberVO member); // 1.클라이언트가 사용하려면 id가 존재하는 경우 , 성공 ,실패
	
	/**
	 * 회원정보 상세 조회
	 * @param mem__id
	 * @return 존재 하지않으면 , DataNotFoundeException 발생 
	 */
	public MemberVO readMember(String mem__id);
	/**
	 * 검색 조건에 맞는 등록 회원수 
	 * @param pagingVO TODO
	 * @return
	 */
	public int readeMemberCount(PagingVO pagingVO);
	/**
	 * 검색 조건에 맞는 회원 목록
	 * @param pagingVO TODO
	 * @param mem_id
	 * @return
	 */
	public List<MemberVO> readMemberList(PagingVO pagingVO);
	
	/**
	 * 회원정보 수정 
	 * @param member
	 * @return 존재 하지 않는다면, DataNotFoundeException 발생 
	 *  인증실패 시 invalidpassword , 인증성공 ok, fail
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	 /**
	  * 회원탈퇴
	 * @param member
	 * @return 존재 하지 않는다면, DataNotFoundeException 발생 ,인증실패 시 invalidpassword , 인증성공 ok, fail
	 */
	public ServiceResult removeMember(MemberVO member);
	
	
	 
}
