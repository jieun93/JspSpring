package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberServiceImpl implements IMemberService {
	
	// 의존관계 형성
	IMemberDAO dao = MemberDAOImpl.getInstance();
	
	// 싱글턴 
	private static IMemberService service;
	public static IMemberService getInstance() {
		if(service == null) service = new MemberServiceImpl();
		return service;
	}
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		
		ServiceResult result = null;
		try {
			readMember(member.getMem_id()); //member.getMem_id()에 해당하는 id가 있는지 없는지 
			result =ServiceResult.PKDUPLICATED;
		} catch (DataNotFoundException e) {
		int cnt = dao.insertMember(member); // 예외가 발생해야지 신규로 등록 할 수 있다.   insert  하면  row count
		
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		}
		return result;
		
	}

	@Override
	public MemberVO readMember(String mem_id) {
		MemberVO member = dao.selectMember(mem_id);
		if(member == null) throw new DataNotFoundException(mem_id+"는 없는 회원입니다.");
		return member;
	}

	@Override
	public int readeMemberCount(PagingVO pagingVO) {
		return dao.selelctMemberCount(pagingVO);
	}
	
	// 회원목록 조회 
	@Override
	public List<MemberVO> readMemberList(PagingVO pagingVO) {
		return dao.selectMemberList(pagingVO);
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// 경우의 수 4가지 만들기
		 
		 ServiceResult result = null;
		 MemberVO savedMember = readMember(member.getMem_id());
		 if(savedMember.getMem_pass().equals(member.getMem_pass())){
			 int cnt = dao.updateMember(member);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
			 
		 if(cnt > 0) {
			  result = ServiceResult.OK;
			  
		  }else {
			  result = ServiceResult.FAIL;
		  }
		 }else{
			 result =ServiceResult.INVALIDPASSWORD;
		 }
		 return result;
		 
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = null;
		MemberVO savedMember =readMember(member.getMem_id());
		if(savedMember.getMem_pass().equals(member.getMem_pass())){
			
		int cnt = dao.deleteMember(member.getMem_id());
			if(cnt>0) {
				result = ServiceResult.OK;
				
			}else {
				result = ServiceResult.FAIL;
			}
		}else{
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}
	

}
