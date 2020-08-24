package kr.or.ddit.commons.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService {
	// 싱글턴
	// dao와의 의존관계 형성 
	private AuthenticateServiceImpl() {	}
	private static IAuthenticateService self;
	public static IAuthenticateService getInstance() {
		if(self==null) self = new AuthenticateServiceImpl();
		return self;
	}
	
	// 의존관계 형성  --> 생성자로 바꿔줌 
	IMemberDAO dao = new MemberDAOImpl();
			
	
	@Override
	public ServiceResult authenticated(MemberVO member) {
		
	  MemberVO savedMember = dao.selectMember(member.getMem_id());
	  ServiceResult result = null;
	  if(savedMember!=null && !"Y".equals(savedMember.getMem_delete())) {
		  // 비번을 비교  멤버와 세이브드멤버
		  if(member.getMem_pass().equals(savedMember.getMem_pass())) {
			  try {
				  BeanUtils.copyProperties(member, savedMember);
			  }catch(IllegalAccessException | InvocationTargetException e){
				  throw new RuntimeException(e);
			  }
			  result = ServiceResult.OK;
			  
		  }else {
			  result =ServiceResult.INVALIDPASSWORD;
		  }
	  }else {
		  result = ServiceResult.NOTEXIST; // 아이디가 없음(존재하지 않음)
	  }
		return result;
	}

}
